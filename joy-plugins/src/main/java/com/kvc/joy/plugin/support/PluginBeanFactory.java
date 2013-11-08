package com.kvc.joy.plugin.support;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.shiro.mgt.SecurityManager;
import org.quartz.Scheduler;
import org.springframework.core.task.TaskExecutor;

import com.google.code.kaptcha.Producer;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import com.kvc.joy.core.spring.utils.SpringBeanTool;
import com.kvc.joy.plugin.image.captcha.service.ICaptchaService;
import com.kvc.joy.plugin.monitor.jdbc.service.ISysSqlLogService;
import com.kvc.joy.plugin.schedule.quartz.service.IQuartzJobRegistry;
import com.kvc.joy.plugin.schedule.quartz.service.IQuartzTriggersHolder;
import com.kvc.joy.plugin.security.login.service.ILoginLogService;
import com.kvc.joy.plugin.security.login.service.ILoginService;
import com.kvc.joy.plugin.security.login.service.ILogoutLogService;
import com.kvc.joy.plugin.security.login.service.ILogoutService;
import com.kvc.joy.plugin.seqgen.service.ISequenceGenerator;

/**
 * 各种组件Bean的工厂
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2012-6-7 下午10:33:48
 */
public class PluginBeanFactory extends CoreBeanFactory {

	public static ISequenceGenerator getSequenceGenerator() {
		return (ISequenceGenerator) SpringBeanTool.getBean("sequenceGenerator");
	}
	
//	public static EhCacheManager getShiroCacheManager() {
//		return (EhCacheManager) SpringBeanUtils.getBean("shiroCacheManager");
//	}

	public static Scheduler getScheduler() {
		return (Scheduler) SpringBeanTool.getBean("schedulerFactory");
	}

	public static IQuartzJobRegistry getQuartzJobRegistry() {
		return (IQuartzJobRegistry) SpringBeanTool.getBean("quartzJobRegistry");
	}

	public static IQuartzTriggersHolder getQuartzTriggersHolder() {
		return (IQuartzTriggersHolder) SpringBeanTool.getBean("quartzTriggersHolder");
	}
	
	public static SecurityManager getSecurityManager() {
		return (SecurityManager) SpringBeanTool.getBean("securityManager");
	}
	
	public static RepositoryService getWfRepositoryService() {
		return (RepositoryService) SpringBeanTool.getBean("wfRepositoryService");
	}
	
	public static  RuntimeService getWfRuntimeService() {
		return (RuntimeService) SpringBeanTool.getBean("wfRuntimeService");
	}
	
	public static  TaskService getWfTaskService() {
		return (TaskService) SpringBeanTool.getBean("wfTaskService");
	}
	
	public static  HistoryService getWfHistoryService() {
		return (HistoryService) SpringBeanTool.getBean("wfHistoryService");
	}
	
	public static  ManagementService getWfManagementService() {
		return (ManagementService) SpringBeanTool.getBean("wfManagementService");
	}
	
	public static  FormService getWfFormService() {
		return (FormService) SpringBeanTool.getBean("wfFormService");
	}
	
	public static IdentityService getWfIdentityService() {
		return (IdentityService) SpringBeanTool.getBean("wfIdentityService");
	}
	
	public static ICaptchaService getCaptchaService() {
		return (ICaptchaService) SpringBeanTool.getBean("captchaService");
	}
	
	public static ILoginService getLoginService() {
		return (ILoginService) SpringBeanTool.getBean("loginService");
	}
	
	public static ILoginLogService getLoginLogService() {
		return (ILoginLogService) SpringBeanTool.getBean("loginLogService");
	}
	
	public static ILogoutService getLogoutService() {
		return (ILogoutService) SpringBeanTool.getBean("logoutService");
	}
	
	public static ILogoutLogService getLogoutLogService() {
		return (ILogoutLogService) SpringBeanTool.getBean("logoutLogService");
	}
	
	public static ISysSqlLogService getSysSqlLogService() {
		return (ISysSqlLogService) SpringBeanTool.getBean("sysSqlLogService");
	}
	
	public static TaskExecutor getSysSqlLogThreadPool() {
		return (TaskExecutor) SpringBeanTool.getBean("sysSqlLogThreadPool");
	}
	
	/**
	 * 验证码生产者
	 * 
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年9月21日 下午6:39:43
	 */
	public static Producer getCaptchaProducer() {
		return (Producer) SpringBeanTool.getBean("captchaProducer");
	}
	
}
