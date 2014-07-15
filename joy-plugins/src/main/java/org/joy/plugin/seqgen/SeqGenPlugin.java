package org.joy.plugin.seqgen;

import org.joy.core.init.service.IJoyPlugin;
import org.joy.core.init.support.properties.JoyProperties;
import org.joy.plugin.seqgen.model.po.TSysSeqNumRule;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Kevice
 * @time 2013-2-5 上午12:48:26
 */
@Component
public class SeqGenPlugin implements IJoyPlugin {

	public String getName() {
		return "序列号生成器";
	}

	public void startup() {
		
	}

	public void destroy() {
		
	}

	public boolean isEnabled() {
		return JoyProperties.PLUGIN_SEQGEN_ENABLED;
	}

	public int getInitPriority() {
		return Integer.MAX_VALUE;
	}

	@Override
	public String getSqlMigrationPrefix() {
		return "SEQGEN";
	}

	@Override
	public String getPoPackage() {
		return TSysSeqNumRule.class.getPackage().getName();
	}
	
	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/plugin-appCtx-seqgen.xml";
	}

}
