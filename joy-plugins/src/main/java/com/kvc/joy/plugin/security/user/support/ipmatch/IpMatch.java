package com.kvc.joy.plugin.security.user.support.ipmatch;

interface IpMatch {

	/**
	 * ip是否匹配
	 * @param ip
	 * @return
	 */
	boolean isMatch(String ip);
}
