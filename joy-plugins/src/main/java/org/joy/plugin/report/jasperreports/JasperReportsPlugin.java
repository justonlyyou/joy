package org.joy.plugin.report.jasperreports;

import org.joy.core.init.service.IPlugin;
import org.joy.core.init.support.properties.JoyProperties;
import org.springframework.stereotype.Component;

/**
 * JasperReports插件
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-27 下午10:39:51
 */
@Component
public class JasperReportsPlugin implements IPlugin {

	@Override
	public String getName() {
		return "JasperReports";
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
		return JoyProperties.PLUGIN_REPORT_JASPERREPORTS_ENABLED;
	}

	@Override
	public String getSqlMigrationPrefix() {
		return "JASPERREPORTS";
	}

	@Override
	public String getPoPackage() {
		return null;
	}

	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/plugin-appCtx-jasperreports.xml";
	}

}
