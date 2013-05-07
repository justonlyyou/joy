package com.kvc.joy.core.persistence.orm.jpa;

import org.springframework.stereotype.Component;

import com.kvc.joy.core.init.service.SpringManagedJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-16 下午8:48:42
 */
@Component
public class SpringDataJpaPlugin extends SpringManagedJoyPlugin {

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
		return JoyPropeties.PLUGIN_SPRING_DATA_JPA_ENABLED;
	}

	@Override
	public String getXmlPath() {
		return "/conf/spring-data-jpa.xml";
	}
	
	

}
