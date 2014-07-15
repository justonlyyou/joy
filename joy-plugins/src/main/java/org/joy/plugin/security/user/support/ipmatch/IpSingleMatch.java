package org.joy.plugin.security.user.support.ipmatch;

import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;

/**
 * 单个IP匹配
 */
class IpSingleMatch implements IpMatch {
	
	protected static final Log log = LogFactory.getLog(IpSingleMatch.class);

	private final String srcIp;

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
