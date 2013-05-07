package com.kvc.joy.core.spring.utils;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import com.kvc.joy.core.ehcache.support.IEhCacheHolder;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbColumnService;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbPrimaryKeyService;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbTableService;
import com.kvc.joy.core.spring.SpringXmlDynamicLoadService;
import com.kvc.joy.core.sysres.code.service.ISysCodeCacheService;
import com.kvc.joy.core.sysres.code.service.ISysCodeService;
import com.kvc.joy.core.sysres.menu.service.ISysMenuService;
import com.kvc.joy.core.sysres.param.po.TSysParam;
import com.kvc.joy.core.sysres.param.service.ISysParamService;

/**
 * 基础Spring Bean的工厂
 * 
 * @author 唐玮琳
 * @time 2012-6-7 下午9:06:16
 */
public class CoreBeanFactory {

	public static JdbcTemplate getJdbcTemplate() {
		return (JdbcTemplate) SpringBeanUtils.getBean("jdbcTemplate");
	}

	public static JpaTemplate getJpaTemplate() {
		return (JpaTemplate) SpringBeanUtils.getBean("jpaTemplate");
	}

	public static TransactionDefinition getTransactionDefinition() {
		return (TransactionDefinition) SpringBeanUtils.getBean("txDefinition");
	}

	public static JpaTransactionManager getJpaTransactionManager() {
		return (JpaTransactionManager) SpringBeanUtils.getBean("transactionManager");
	}

	public static ISysMenuService getSysMenuService() {
		return (ISysMenuService) SpringBeanUtils.getBean("sysMenuService");
	}
	
	public static EhCacheCacheManager getEhCacheCacheManager() {
		return (EhCacheCacheManager) SpringBeanUtils.getBean("ehCacheCacheManager");
	}
	
	public static SpringXmlDynamicLoadService getSpringXmlDynamicLoadService() {
		return SpringBeanUtils.getBean(SpringXmlDynamicLoadService.class);
	}

	/**
	 * 关系数据库表元数据加载服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:00:51
	 */
	public static IMdRdbTableService getMdRdbTableService() {
		return (IMdRdbTableService) SpringBeanUtils.getBean("mdRdbTableService");
	}

	/**
	 * 关系数据库表元数据缓存服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:01:02
	 */
	@SuppressWarnings("unchecked")
	public static IEhCacheHolder<String, MdRdbTable> getMdRdbTableCacheService() {
		return (IEhCacheHolder<String, MdRdbTable>) SpringBeanUtils.getBean("mdRdbTableCacheService");
	}

	/**
	 * 关系数据库表字段元数据加载服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:02:21
	 */
	public static IMdRdbColumnService getMdRdbColumnService() {
		return (IMdRdbColumnService) SpringBeanUtils.getBean("mdRdbColumnService");
	}

	/**
	 * 关系数据库表字段元数据缓存服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:02:35
	 */
	public static IMdRdbColumnService getMdRdbColumnCacheService() {
		return (IMdRdbColumnService) SpringBeanUtils.getBean("mdRdbColumnCacheService");
	}

	/**
	 * 关系数据库表主键元数据加载服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:02:49
	 */
	public static IMdRdbPrimaryKeyService getMdRdbPrimaryKeyService() {
		return (IMdRdbPrimaryKeyService) SpringBeanUtils.getBean("mdRdbPrimaryKeyService");
	}

	/**
	 * 关系数据库表主键元数据缓存服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:03:39
	 */
	public static IMdRdbPrimaryKeyService getMdRdbPrimaryKeyCacheService() {
		return (IMdRdbPrimaryKeyService) SpringBeanUtils.getBean("mdRdbPrimaryKeyCacheService");
	}

	/**
	 * 系统参数服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 上午11:24:11
	 */
	public static ISysParamService getSysParamService() {
		return (ISysParamService) SpringBeanUtils.getBean("sysParamService");
	}
	
	/**
	 * 系统参数服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 上午11:24:11
	 */
	@SuppressWarnings("unchecked")
	public static IEhCacheHolder<String, TSysParam> getSysParamCacheService() {
		return (IEhCacheHolder<String, TSysParam>) SpringBeanUtils.getBean("sysParamCacheService");
	}
	
	/**
	 * 系统代码服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午8:03:02
	 */
	public static ISysCodeService getSysCodeService() {
		return (ISysCodeService) SpringBeanUtils.getBean("sysCodeService");
	}
	
	/**
	 * 系统代码缓存服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午8:03:02
	 */
	public static ISysCodeCacheService getSysCodeCacheService() {
		return (ISysCodeCacheService) SpringBeanUtils.getBean("sysCodeCacheService");
	}
	
}
