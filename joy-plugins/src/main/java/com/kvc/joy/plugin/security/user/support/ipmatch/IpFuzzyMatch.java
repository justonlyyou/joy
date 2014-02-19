package com.kvc.joy.plugin.security.user.support.ipmatch;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.commons.net.IpTool;

/**
 * 模糊IP匹配 实现逻辑：把传入的模糊IP转换为0作为最小IP，转换为255作为做大IP，形成一个区间
 */
class IpFuzzyMatch implements IpMatch {
	
	protected static final Log log = LogFactory.getLog(IpFuzzyMatch.class);
	private final long minValue;
	private final long maxValue;

	public IpFuzzyMatch(String fuzzyIp) {
		if (StringTool.isBlank(fuzzyIp)) {
			minValue = 0;
			maxValue = 0;
			log.warn("传入IP为空！");
			return;
		}

		
		minValue = IpTool.ipv4StringToLong(StringTool.replace(fuzzyIp, IpMatchFacility.FUZZY_FLAG, "0"));
		maxValue = IpTool.ipv4StringToLong(StringTool.replace(fuzzyIp, IpMatchFacility.FUZZY_FLAG, "255"));

	}

	public boolean isMatch(String ip) {
		if (StringTool.isBlank(ip)) {
			return false;
		}
		long ipValue = IpTool.ipv4StringToLong(ip);
		return minValue <= ipValue && maxValue >= ipValue;
	}

}
