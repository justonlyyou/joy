package com.kvc.joy.core.spring.utils;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import com.kvc.joy.core.ehcache.support.IEhCacheHolder;
import com.kvc.joy.core.persistence.flyway.service.IRdbObjectsInitService;
import com.kvc.joy.core.persistence.jdbc.dao.BaseJdbcDao;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbAlterReverseSyncService;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbColumnService;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbPrimaryKeyService;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbTableService;
import com.kvc.joy.core.persistence.orm.jpa.BaseJpaDao;
import com.kvc.joy.core.spring.SpringXmlDynamicLoadService;
import com.kvc.joy.core.sysres.code.service.ISysCodeCacheService;
import com.kvc.joy.core.sysres.code.service.ISysCodeService;
import com.kvc.joy.core.sysres.datasrc.service.ISysDataSrcService;
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
	
	@SuppressWarnings("rawtypes")
	public static BaseJpaDao getJpaTool() {
		return (BaseJpaDao) SpringBeanTool.getBean("jpaTool");
	}
	
	public static BaseJdbcDao getJdbcTool() {
		return (BaseJdbcDao) SpringBeanTool.getBean("jdbcTool");
	}

	public static JdbcTemplate getJdbcTemplate() {
		return (JdbcTemplate) SpringBeanTool.getBean("jdbcTemplate");
	}

	public static JpaTemplate getJpaTemplate() {
		return (JpaTemplate) SpringBeanTool.getBean("jpaTemplate");
	}

	public static TransactionDefinition getTransactionDefinition() {
		return (TransactionDefinition) SpringBeanTool.getBean("txDefinition");
	}

	public static JpaTransactionManager getJpaTransactionManager() {
		return (JpaTransactionManager) SpringBeanTool.getBean("transactionManager");
	}

	public static ISysMenuService getSysMenuService() {
		return (ISysMenuService) SpringBeanTool.getBean("sysMenuService");
	}
	
	public static EhCacheCacheManager getEhCacheCacheManager() {
		return (EhCacheCacheManager) SpringBeanTool.getBean("ehCacheCacheManager");
	}
	
	public static SpringXmlDynamicLoadService getSpringXmlDynamicLoadService() {
		return SpringBeanTool.getBean(SpringXmlDynamicLoadService.class);
	}

	/**
	 * 关系数据库表元数据加载服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:00:51
	 */
	public static IMdRdbTableService getMdRdbTableService() {
		return (IMdRdbTableService) SpringBeanTool.getBean("mdRdbTableService");
	}

	/**
	 * 关系数据库表元数据缓存服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:01:02
	 */
	public static IMdRdbTableService getMdRdbTableCacheService() {
		return (IMdRdbTableService) SpringBeanTool.getBean("mdRdbTableCacheService");
	}

	/**
	 * 关系数据库表字段元数据加载服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:02:21
	 */
	public static IMdRdbColumnService getMdRdbColumnService() {
		return (IMdRdbColumnService) SpringBeanTool.getBean("mdRdbColumnService");
	}

	/**
	 * 关系数据库表字段元数据缓存服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:02:35
	 */
	public static IMdRdbColumnService getMdRdbColumnCacheService() {
		return (IMdRdbColumnService) SpringBeanTool.getBean("mdRdbColumnCacheService");
	}

	/**
	 * 关系数据库表主键元数据加载服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:02:49
	 */
	public static IMdRdbPrimaryKeyService getMdRdbPrimaryKeyService() {
		return (IMdRdbPrimaryKeyService) SpringBeanTool.getBean("mdRdbPrimaryKeyService");
	}

	/**
	 * 关系数据库表主键元数据缓存服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午10:03:39
	 */
	public static IMdRdbPrimaryKeyService getMdRdbPrimaryKeyCacheService() {
		return (IMdRdbPrimaryKeyService) SpringBeanTool.getBean("mdRdbPrimaryKeyCacheService");
	}

	/**
	 * 系统参数服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 上午11:24:11
	 */
	public static ISysParamService getSysParamService() {
		return (ISysParamService) SpringBeanTool.getBean("sysParamService");
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
		return (IEhCacheHolder<String, TSysParam>) SpringBeanTool.getBean("sysParamCacheService");
	}
	
	/**
	 * 系统代码服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午8:03:02
	 */
	public static ISysCodeService getSysCodeService() {
		return (ISysCodeService) SpringBeanTool.getBean("sysCodeService");
	}
	
	public static ISysDataSrcService getSysDataSrcService() {
		return (ISysDataSrcService) SpringBeanTool.getBean("sysDataSrcService");
	}
	
	/**
	 * 系统代码缓存服务
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午8:03:02
	 */
	public static ISysCodeCacheService getSysCodeCacheService() {
		return (ISysCodeCacheService) SpringBeanTool.getBean("sysCodeCacheService");
	}
	
	public static IRdbObjectsInitService getRdbObjectsInitService() {
		return (IRdbObjectsInitService) SpringBeanTool.getBean("rdbObjectsInitService");
	}
	
	public static IMdRdbAlterReverseSyncService getMdRdbAlterReverseSyncService() {
		return (IMdRdbAlterReverseSyncService) SpringBeanTool.getBean("mdRdbAlterReverseSyncService");
	}
	
}
