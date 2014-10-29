package org.joy.core.persistence.orm.jpa;

import org.joy.core.init.service.IPlugin;
import org.springframework.stereotype.Component;

/**
 * spring-data-jpa插件，提供统一访问接口
 * 
 * @author Kevice
 * @time 2013-2-16 下午8:48:42
 * @since 1.0.0
 */
@Component
public class SpringDataJpaPlugin implements IPlugin {

	@Override
	public String getName() {
		return "spring-data-jpa";
	}

	@Override
	public int getInitPriority() {
		return 0;
	}

	@Override
	public void startup() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public boolean isEnabled() {
		return true; // 作为核心组件，必须启用
	}

	@Override
	public String getSqlMigrationPrefix() {
		return "SPRING_DATA_JPA";
	}

	@Override
	public String getPoPackage() {
		return null;
	}
	
	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/comp-appCtx-spring-data-jpa.xml";
	}

}
