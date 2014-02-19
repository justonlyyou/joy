package com.kvc.joy.plugin.security.user.support.ipmatch;

import java.util.ArrayList;
import java.util.List;

/**
 * IP匹配器工具 支持3种IP模式 1.带*号：192.168.0.* 2.带-号的范围:192.168.0.1-192.168.10.255 3.具体的IP：127.0.0.1
 * 多种IP，用逗号隔开，如：192.168.0.*,192.168.0.1-192.168.10.255,127.0.0.1
 */
public class IpMatchFacility {
	
	public static final String ZONE_SPLIT = "-";

	public static final String FUZZY_FLAG = "*";

	private final List<IpMatch> lstIpMatch = new ArrayList<IpMatch>(0);

	public IpMatchFacility(String ipCluster) {
		String[] ips = ipCluster.split(",");
        for (String ip : ips) {
            if (ip.contains(IpMatchFacility.ZONE_SPLIT)) {
                lstIpMatch.add(new IpZoneMatch(ip));
            } else if (ip.contains(IpMatchFacility.FUZZY_FLAG)) {
                lstIpMatch.add(new IpFuzzyMatch(ip));
            } else {
                lstIpMatch.add(new IpSingleMatch(ip));
            }
        }
	}

	/**
	 * ip是否匹配
	 * @param ip
	 * @return
	 */
	public boolean isMatch(String ip) {
        for (IpMatch aLstIpMatch : lstIpMatch) {
            IpMatch ipMatch = (IpMatch) aLstIpMatch;
            boolean ret = ipMatch.isMatch(ip);
            if (ret) {
                return true;
            }
        }
		return false;
	}

}
