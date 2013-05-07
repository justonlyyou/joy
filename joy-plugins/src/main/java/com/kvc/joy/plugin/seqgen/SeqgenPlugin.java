package com.kvc.joy.plugin.seqgen;

import org.springframework.stereotype.Component;

import com.kvc.joy.core.init.service.SpringManagedJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-5 上午12:48:26
 */
@Component
public class SeqgenPlugin extends SpringManagedJoyPlugin {

	public String getName() {
		return "序列号生成器";
	}

	public void startup() {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEnabled() {
		return JoyPropeties.PLUGIN_SEQGEN_ENABLED;
	}

	public int getInitPriority() {
		return Integer.MAX_VALUE;
	}

	@Override
	public String getXmlPath() {
		return "/conf/component-applicationContext-seqgen.xml";
	}

}
