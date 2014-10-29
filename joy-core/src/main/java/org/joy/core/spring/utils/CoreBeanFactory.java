package org.joy.core.spring.utils;

import org.joy.core.ehcache.support.IEhCacheHolder;
import org.joy.core.persistence.flyway.service.IRdbObjectsInitService;
import org.joy.core.persistence.jdbc.dao.BaseJdbcDao;
import org.joy.core.persistence.jdbc.service.IMdRdbAlterReverseSyncService;
import org.joy.core.persistence.jdbc.service.IMdRdbColumnService;
import org.joy.core.persistence.jdbc.service.IMdRdbPrimaryKeyService;
import org.joy.core.persistence.jdbc.service.IMdRdbTableService;
import org.joy.core.persistence.orm.jpa.JpaTool;
import org.joy.core.spring.SpringXmlDynamicLoadService;
import org.joy.core.sysres.code.service.ISysCodeCacheService;
import org.joy.core.sysres.code.service.ISysCodeService;
import org.joy.core.sysres.datasrc.service.ISysDataSrcService;
import org.joy.core.sysres.menu.service.ISysMenuService;
import org.joy.core.sysres.param.model.po.TSysParam;
import org.joy.core.sysres.param.service.ISysParamService;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 基础Spring Bean的工厂
 * 
 * @author Kevice
 * @time 2012-6-7 下午9:06:16
 * @since 1.0.0
 */
public class CoreBeanFactory {

    /**
     * 返回jpa工具类bean实例
     *
     * @return jpa工具类bean实例
     * @author Kevice
     * @time 2013-2-3 下午10:02:21
     * @since 1.0.0
     */
	public static JpaTool getJpaTool() {
		return (JpaTool) SpringBeanTool.getBean("jpaTool");
	}

    /**
     * 返回基本的Jdbc数据访问bean实例
     *
     * @return 基本的Jdbc数据访问bean实例
     * @author Kevice
     * @time 2013-2-3 下午10:02:21
     * @since 1.0.0
     */
	public static BaseJdbcDao getJdbcTool() {
		return (BaseJdbcDao) SpringBeanTool.getBean("jdbcTool");
	}

    /**
     * 返回事务模板bean实例
     *
     * @return 事务模板bean实例
     * @author Kevice
     * @time 2013-2-3 下午10:02:21
     * @since 1.0.0
     */
    public static TransactionTemplate getTransactionTemplate() {
        return (TransactionTemplate) SpringBeanTool.getBean("transactionTemplate");
    }

    /**
     * 返回系统菜单服务bean实例
     *
     * @return 系统菜单服务bean实例
     * @author Kevice
     * @time 2013-2-3 下午10:02:21
     * @since 1.0.0
     */
	public static ISysMenuService getSysMenuService() {
		return (ISysMenuService) SpringBeanTool.getBean("sysMenuService");
	}

    /**
     * 返回EhCache缓存管理bean实例
     *
     * @return EhCache缓存管理bean实例
     * @author Kevice
     * @time 2013-2-3 下午10:02:21
     * @since 1.0.0
     */
	public static EhCacheCacheManager getEhCacheCacheManager() {
		return (EhCacheCacheManager) SpringBeanTool.getBean("ehCacheCacheManager");
	}

	/**
	 * 返回关系数据库表元数据加载服务bean实例
	 * 
	 * @return 关系数据库表元数据加载服务bean实例
	 * @author Kevice
	 * @time 2013-2-3 下午10:00:51
     * @since 1.0.0
	 */
	public static IMdRdbTableService getMdRdbTableService() {
		return (IMdRdbTableService) SpringBeanTool.getBean("mdRdbTableService");
	}

	/**
	 * 返回关系数据库表元数据缓存服务bean实例
	 * 
	 * @return 关系数据库表元数据缓存服务bean实例
	 * @author Kevice
	 * @time 2013-2-3 下午10:01:02
     * @since 1.0.0
	 */
	public static IMdRdbTableService getMdRdbTableCacheService() {
		return (IMdRdbTableService) SpringBeanTool.getBean("mdRdbTableCacheService");
	}

	/**
	 * 返回关系数据库表字段元数据加载服务bean实例
	 * 
	 * @return 关系数据库表字段元数据加载服务bean实例
	 * @author Kevice
	 * @time 2013-2-3 下午10:02:21
     * @since 1.0.0
	 */
	public static IMdRdbColumnService getMdRdbColumnService() {
		return (IMdRdbColumnService) SpringBeanTool.getBean("mdRdbColumnService");
	}

	/**
	 * 返回关系数据库表字段元数据缓存服务bean实例
	 * 
	 * @return 关系数据库表字段元数据缓存服务bean实例
	 * @author Kevice
	 * @time 2013-2-3 下午10:02:35
     * @since 1.0.0
	 */
	public static IMdRdbColumnService getMdRdbColumnCacheService() {
		return (IMdRdbColumnService) SpringBeanTool.getBean("mdRdbColumnCacheService");
	}

	/**
	 * 返回关系数据库表主键元数据加载服务bean实例
	 * 
	 * @return 关系数据库表主键元数据加载服务bean实例
	 * @author Kevice
	 * @time 2013-2-3 下午10:02:49
     * @since 1.0.0
	 */
	public static IMdRdbPrimaryKeyService getMdRdbPrimaryKeyService() {
		return (IMdRdbPrimaryKeyService) SpringBeanTool.getBean("mdRdbPrimaryKeyService");
	}

	/**
	 * 返回关系数据库表主键元数据缓存服务bean实例
	 * 
	 * @return 关系数据库表主键元数据缓存服务bean实例
	 * @author Kevice
	 * @time 2013-2-3 下午10:03:39
     * @since 1.0.0
	 */
	public static IMdRdbPrimaryKeyService getMdRdbPrimaryKeyCacheService() {
		return (IMdRdbPrimaryKeyService) SpringBeanTool.getBean("mdRdbPrimaryKeyCacheService");
	}

	/**
	 * 返回系统参数服务bean实例
	 * 
	 * @return 系统参数服务bean实例
	 * @author Kevice
	 * @time 2013-2-8 上午11:24:11
     * @since 1.0.0
	 */
	public static ISysParamService getSysParamService() {
		return (ISysParamService) SpringBeanTool.getBean("sysParamService");
	}
	
	/**
	 * 返回EhCache持有者bean实例
	 * 
	 * @return EhCache持有者bean实例
	 * @author Kevice
	 * @time 2013-2-8 上午11:24:11
     * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public static IEhCacheHolder<String, TSysParam> getSysParamCacheService() {
		return (IEhCacheHolder<String, TSysParam>) SpringBeanTool.getBean("sysParamCacheService");
	}
	
	/**
	 * 返回系统代码服务bean实例
	 * 
	 * @return 系统代码服务bean实例
	 * @author Kevice
	 * @time 2013-2-8 下午8:03:02
     * @since 1.0.0
	 */
	public static ISysCodeService getSysCodeService() {
		return (ISysCodeService) SpringBeanTool.getBean("sysCodeService");
	}

    /**
     * 返回系统数据源服务bean实例
     *
     * @return 系统数据源服务bean实例
     * @author Kevice
     * @time 2013年11月28日 下午4:53:28
     * @since 1.0.0
     */
	public static ISysDataSrcService getSysDataSrcService() {
		return (ISysDataSrcService) SpringBeanTool.getBean("sysDataSrcService");
	}
	
	/**
	 * 返回系统代码缓存服务bean实例
	 * 
	 * @return 系统代码缓存服务bean实例
	 * @author Kevice
	 * @time 2013-2-8 下午8:03:02
     * @since 1.0.0
	 */
	public static ISysCodeCacheService getSysCodeCacheService() {
		return (ISysCodeCacheService) SpringBeanTool.getBean("sysCodeCacheService");
	}

    /**
     * 返回关系型数据库对象初始化服务bean实例
     *
     * @return 关系型数据库对象初始化服务bean实例
     * @author Kevice
     * @time 2013年11月13日 下午8:32:22
     * @since 1.0.0
     */
	public static IRdbObjectsInitService getRdbObjectsInitService() {
		return (IRdbObjectsInitService) SpringBeanTool.getBean("rdbObjectsInitService");
	}

    /**
     * 返回关系型数据库表修改反向同步服务bean实例
     *
     * @return 关系型数据库表修改反向同步服务bean实例
     * @author Kevice
     * @time 2013-11-10 下午8:32:17
     * @since 1.0.0
     */
	public static IMdRdbAlterReverseSyncService getMdRdbAlterReverseSyncService() {
		return (IMdRdbAlterReverseSyncService) SpringBeanTool.getBean("mdRdbAlterReverseSyncService");
	}
	
}
