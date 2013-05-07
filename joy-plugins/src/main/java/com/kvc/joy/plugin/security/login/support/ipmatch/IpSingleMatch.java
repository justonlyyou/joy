package com.kvc.joy.plugin.security.login.support.ipmatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单个IP匹配
 */
class IpSingleMatch implements IpMatch {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

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
