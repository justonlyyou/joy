package org.joy.core.persistence.orm.jpa;

import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.spring.utils.SpringBeanTool;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 实体管理器延迟加载处理, 使用动态代理AOP结构，来管理EntityManager，这种方案不依赖于容器 <br>
 * 用于解决以下问题：<br>
 * 关于级联操作中包含了lazy加载，可以优化加载数据。如果采用EAGER方式加载会耗损很多资源。<br>
 * 不过在设置了lazy以后，操作的时候可能会出现Session close的问题。<br>
 *
 * 参考http://blog.csdn.net/xfworld/article/details/5583449
 * @author xfworld
 * @author Kevice
 * @time 2012-6-26 下午10:36:28
 * @since 1.0.0
 */
@Service
public class OpenEntityManagerHandler implements InvocationHandler {

	protected static final Log logger = LogFactory.getLog(OpenEntityManagerHandler.class);
	
	private EntityManager em;
	private Object obj;
	
	public Object invoke(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		Object result = null;
		boolean isOpen = false;
		EntityManagerFactory emFactory = (EntityManagerFactory) SpringBeanTool.getBean("entityManagerFactory");
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
