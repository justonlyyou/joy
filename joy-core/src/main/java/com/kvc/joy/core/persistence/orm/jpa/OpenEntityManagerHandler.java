package com.kvc.joy.core.persistence.orm.jpa;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.kvc.joy.core.spring.utils.SpringBeanUtils;

@Service
public class OpenEntityManagerHandler implements InvocationHandler {

	private static final Logger logger = LoggerFactory.getLogger(OpenEntityManagerHandler.class);
	
	private EntityManager em;
	private Object obj;
	
	public Object invoke(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		Object result = null;
		boolean isOpen = false;
		EntityManagerFactory emFactory = (EntityManagerFactory) SpringBeanUtils.getBean("entityManagerFactory");
		if (TransactionSynchronizationManager.hasResource(emFactory)) {
			isOpen = true;
		} else {
			// 开
			logger.debug("[open entity manager!]");
			em = emFactory.createEntityManager();
			TransactionSynchronizationManager.bindResource(emFactory,
					new EntityManagerHolder(em));
		}
		try {
			result = arg1.invoke(this.obj, arg2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关
				if (!isOpen) {
					EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager
							.unbindResource(emFactory);
					emHolder.getEntityManager().close();
					logger.debug("[close entity manager!]");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public EntityManager getEntityManager() {
		return em;
	}
	
	public Object bind(Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
				.getClass().getInterfaces(), this);
	}
	
//    以上的方案不修改的Service/DAO模型，侵入性小，测试方便，但是有一些局限性。更良好的方案可以将OpenEntityManagerHandler进行一些修改，
//    1.    加入private String FACTORY_NAME=DEFAULT_ENTITYMANGER_FACTORY，修改获取EntityManagerFactory的方法。
//    2.    在applicationContext-*.xml中配置bean，并ref指向EntityManagerFactory的bean。
//    3.    修改你的Service/DAO模型，引入OpenEntityManagerHandler。

}
