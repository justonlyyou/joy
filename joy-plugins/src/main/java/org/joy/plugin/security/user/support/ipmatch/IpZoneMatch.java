package org.joy.plugin.security.user.support.ipmatch;

import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.net.IpTool;

/**
 * 范围IP匹配
 */
class IpZoneMatch implements IpMatch {

	protected static final Log log = LogFactory.getLog(IpZoneMatch.class);
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
