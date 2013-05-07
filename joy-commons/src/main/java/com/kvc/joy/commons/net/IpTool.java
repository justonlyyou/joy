package com.kvc.joy.commons.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.collections.ListTool;
import com.kvc.joy.commons.lang.ArrayTool;
import com.kvc.joy.commons.lang.string.StringTool;

/**
 * ip工具类
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-16 下午8:00:00
 */
public class IpTool {

	// 二进制32位为全1的整数值
	private static final long ALL32ONE = 4294967295L;
	private static Logger logger = LoggerFactory.getLogger(IpTool.class);

	/**
	 * <p>
	 * 验证单个IP地址是否合法的ipv4
	 * </p>
	 * 
	 * @param ip 待验证的ip串，为null返回false
	 * @return true: 为合法的ipv4地址
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-16 下午8:00:02
	 */
	public static boolean isValidIpv4(String ip) {
		if(StringTool.isBlank(ip)) {
			return false;
		}
		StringTokenizer st = new StringTokenizer(ip, ".");
		int i = 0;
		for (; st.hasMoreTokens(); i++) {
			int n;
			try {
				n = Integer.valueOf(st.nextToken());	
			} catch (Exception e) {
				return false;
			}
			if (n > 255 || n < 0) {
				return false;
			}
		}
		return i == 4;
	}

	/**
	 * <p>
	 * 单个ipv4地址字符串到数字的转换，非ipv4返回-1
	 * </p>
	 * 
	 * @param ipv4 ipv4地址，为null将返回-1
	 * @return ipv4的数值表示，非ipv4返回-1
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-16 下午9:07:21
	 */
	public static long ipv4StringToLong(String ipv4) {
		if(isValidIpv4(ipv4) == false) {
			return -1;
		}
		
		long temp = 0;
		String cur = "";
		int pos = ipv4.indexOf(".", 0);
		while (pos != -1) {
			cur = ipv4.substring(0, pos);
			ipv4 = ipv4.substring(pos + 1, ipv4.length());
			temp = (temp << 8) | Long.valueOf(cur);
			pos = ipv4.indexOf(".", 0);
		}
		temp = (temp << 8) | Long.valueOf(ipv4);
		return temp;
	}

	/**
	 * <p>
	 * 单个IP地址数字到字符串的转换
	 * </p>
	 * 
	 * @param ipv4Long ipv4长整型值, 小于0或大于4294967295将返回null
	 * @return ipv4地址，参数小于0或大于4294967295将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-16 下午9:15:57
	 */
	public static String ipv4LongToString(long ipv4Long) {
		if(ipv4Long < 0 || ipv4Long > ALL32ONE) {
			return null;
		}
		
		final long MASK = 255;
		long result = ipv4Long & MASK;
		String temp = String.valueOf(result);
		for (int i = 0; i < 3; i++) {
			ipv4Long = ipv4Long >> 8;
			result = ipv4Long & MASK;
			temp = String.valueOf(result) + "." + temp;
		}
		return temp.toString();
	}
	
	/**
	 * <p>
	 * 取得定长的ipv4地址(每个段不足三位在前面用0补足)。 例如: 1.2.13.224 => 001.002.013.224 
	 * </p>
	 * 
	 * @param ipv4 待处理的ipv4，如果ip非法返回原ip
	 * @return 定长的ipv4地址
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-16 下午11:12:44
	 */
	public static String getFixLengthIpv4(String ipv4) {
		if(isValidIpv4(ipv4) == false) {
			return ipv4;
		}
		
		StringBuilder ip = new StringBuilder();
		String[] parts = StringTool.split(ipv4, ".");
		for (int i = 0; i < parts.length; i++) {
			String part = parts[i];
			ip.append(StringTool.leftPad(part, 3, '0'));
			if(i != 3) {
				ip.append(".");
			}
		}
		return ip.toString();
	}
	
	/**
	 * <p>
	 * 将定长的ipv4还原(每个段去掉左边的0). 例如: 001.002.013.224 => 1.2.13.224
	 * </p>
	 * 
	 * @param ipv4 待处理的ipv4，如果ip非法返回原ip
	 * @return 非定长的ipv4
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-17 下午8:57:36
	 */
	public static String getNormalIpv4(String ipv4) {
		if(isValidIpv4(ipv4) == false) {
			return ipv4;
		}
		
		String[] ipArray = StringTool.split(ipv4, ".");
		for (int i = 0; i < ipArray.length; i++) {
			int temp = Integer.parseInt(ipArray[i]);
			ipArray[i] = String.valueOf(temp);
		}
		return StringTool.join(ipArray, ".");
	}
	
	/**
	 * <p>
	 * 检查指定的ipv4地址是否都在同一网段
	 * </p>
	 * 
	 * @param maskAddress 子网掩码地址，非法将返回false
	 * @param ipv4s ipv4地址可变数组，为null或空数组或其中某个ip非法都将返回false
	 * @return true: 指定的ipv4地址均在同一网段
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-16 下午11:11:02
	 */
	public static boolean isSameIpv4Seg(String maskAddress, String... ipv4s) {
		if(StringTool.isBlank(maskAddress) || ArrayTool.isEmpty(ipv4s)) {
			return false;
		}
		
		long maskIp = ipv4StringToLong(maskAddress);
		if(maskIp == -1) {
			return false;
		}
		
		Long firstValue = null;
		for (String ipv4 : ipv4s) {
			long ipLong = ipv4StringToLong(ipv4);
			long value = maskIp & ipLong;
			if(firstValue == null) {
				firstValue = value;	
			} else {
				if(firstValue != value) {
					return false;
				}	
			}
		}
		
		return true;
	}

	/**
	 * <p>
	 * 返回指定的两个ipv4地址(大小不分先后)间的所有ipv4地址, 
	 * 包括指定的两个ipv4地址，按从小到大的顺序, 两个ip一样将只返回一个
	 * </p>
	 * 
	 * <p>
	 * 只支持最多65536个的ip地址，超过的话将返回空列表
	 * </p>
	 * 
	 * @param beginIp 开始值，包括, 非法ip将返回空列表
	 * @param endIp 结束值，包括, 非法ip将返回空列表
	 * @return 一个包含指定的两个ipv4地址间的所有ipv4地址的列表, 两个参数任一个非法或超过65536个的ip地址将返回空列表
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-16 下午10:41:34
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getIpv4sBetween(String beginIp, String endIp) {
		if(StringTool.isEmpty(beginIp) && StringTool.isEmpty(endIp)) {
			return Collections.EMPTY_LIST;
		}
		
		if(StringTool.isEmpty(beginIp)) {
			beginIp = "0.0.0.0";
		}
		long longBeginip = ipv4StringToLong(beginIp);
		if(longBeginip == -1) {
			return Collections.EMPTY_LIST;
		}
		
		if(StringTool.isEmpty(endIp)) {
			endIp = "255.255.255.255";
		}
		long longEndip = ipv4StringToLong(endIp);
		if(longEndip == -1) {
			return Collections.EMPTY_LIST;
		}
		
		if(longBeginip > longEndip) {
			long temp = longBeginip;
			longBeginip = longEndip;
			longEndip = temp;
		}

		// 求解范围之内的IP地址
		int size = (int) (longEndip - longBeginip) + 1;
		if(size > 65536 || size < 0) {
			return Collections.EMPTY_LIST;
		} else if(size == 1) {
			return ListTool.newArrayList(beginIp);
		}
		
		long[] longIps = new long[size];
		for (int k = 0; k < size; k++) {
			longIps[k] = longBeginip + (long) k;
		}

		// 各个段装换成字符串
		String[] strip = new String[4];
		List<String> ipList = new ArrayList<String>(size);
		for (long longIp : longIps) {
			strip[0] = String.valueOf(longIp & 0x00000000000000ff);
			strip[1] = String.valueOf(longIp >> 8 & 0x00000000000000ff);
			strip[2] = String.valueOf(longIp >> 16 & 0x00000000000000ff);
			strip[3] = String.valueOf(longIp >> 24 & 0x00000000000000ff);
			ipList.add(strip[3] + "." + strip[2] + "." + strip[1] + "." + strip[0]);
		}

		return ipList;
	}
	
	/**
	 * <p>
	 * 判断是否为本地ipv4地址。如：127.0.0.1、192.168.0.123
	 * </p>
	 * 
	 * @param ipv4 待检查的ipv4地址
	 * @return true: 为本地ipv4地址
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-17 下午9:06:07
	 */
    public static boolean isLocalIpv4(String ipv4) {
        if ("127.0.0.1".equals(ipv4)) {
            return true;
        }
        long l = ipv4StringToLong(ipv4); 
        if (l >= 3232235520L) {
            return l <= 3232301055L;
        }
        return (l >= 167772160L) && (l <= 184549375L); 
    }
    
    /**
     * <p>
     * 返回本机的本地ip地址
     * </p>
     * 
     * @return 本机的本地ip地址
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013-5-17 下午10:17:17
     */
    public static String getLocalIp() {
    	try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
    }
    
}
