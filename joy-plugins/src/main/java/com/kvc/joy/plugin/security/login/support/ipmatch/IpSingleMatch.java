package com.kvc.joy.plugin.security.login.support.ipmatch;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;

/**
 * 单个IP匹配
 */
class IpSingleMatch implements IpMatch {
	
	protected static final Log log = LogFactory.getLog(IpSingleMatch.class);

	private String srcIp;

	public IpSingleMatch(String ip) {
		if (ip == null) {
			ip = "";
			log.warn("传入IP为空！");
		}
		this.srcIp = ip;
	}

	public boolean isMatch(String ip) {
		return srcIp.equals(ip);
	}

}
