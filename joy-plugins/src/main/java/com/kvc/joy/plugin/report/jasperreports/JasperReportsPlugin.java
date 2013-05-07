package com.kvc.joy.plugin.report.jasperreports;

import org.springframework.stereotype.Component;

import com.kvc.joy.core.init.service.SpringManagedJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-27 下午10:39:51
 */
@Component
public class JasperReportsPlugin extends SpringManagedJoyPlugin {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInitPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		return JoyPropeties.PLUGIN_REPORT_JASPERREPORTS_ENABLED;
	}

	@Override
	public String getXmlPath() {
		return "/conf/component-applicationContext-jasperreports.xml";
	}

}
