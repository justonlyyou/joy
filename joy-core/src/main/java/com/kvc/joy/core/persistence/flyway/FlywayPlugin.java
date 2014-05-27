package com.kvc.joy.core.persistence.flyway;

import com.kvc.joy.core.init.service.IJoyPlugin;
import com.kvc.joy.core.persistence.flyway.model.po.TSysDbSchemaVersion;
import org.springframework.stereotype.Component;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月13日 下午8:38:27
 */
@Component
public class FlywayPlugin implements IJoyPlugin {

	@Override
	public String getName() {
		return "数据库脚本版本管理";
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
		return true;
	}

	@Override
	public String getSqlMigrationPrefix() {
		return "FLYWAY";
	}

	@Override
	public String getPoPackage() {
		return TSysDbSchemaVersion.class.getPackage().getName();
	}

	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/comp-appCtx-flyway.xml";
	}

}
