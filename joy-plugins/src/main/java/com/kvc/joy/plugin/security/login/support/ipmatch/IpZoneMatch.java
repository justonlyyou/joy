package com.kvc.joy.plugin.security.login.support.ipmatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.net.IpTool;

/**
 * 范围IP匹配
 */
class IpZoneMatch implements IpMatch {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private long minValue;
	private long maxValue;

	public IpZoneMatch(String zoneIp) {
		if (StringTool.isBlank(zoneIp)) {
			minValue = 0;
			maxValue = 0;
			log.warn("传入IP为空！");
		} else {
			String[] ips = zoneIp.split(IpMatchFacility.ZONE_SPLIT);
			if (ips.length != 2) {
				minValue = 0;
				maxValue = 0;
				log.warn("传入IP区间非法，请用" + IpMatchFacility.ZONE_SPLIT + "隔开");
			}
			minValue = IpTool.ipv4StringToLong(ips[0]);
			maxValue = IpTool.ipv4StringToLong(ips[1]);
		}
	}

	public boolean isMatch(String ip) {
		if (StringTool.isBlank(ip)) {
			return false;
		}
		long ipValue = IpTool.ipv4StringToLong(ip);
		return minValue <= ipValue && maxValue >= ipValue;
	}

}
