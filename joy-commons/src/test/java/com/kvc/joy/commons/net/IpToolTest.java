package com.kvc.joy.commons.net;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-16 下午7:59:08
 */
public class IpToolTest {
	
	@Test
	public void isValidIpv4() {
		Assert.assertTrue(IpTool.isValidIpv4("192.168.0.123"));
		Assert.assertTrue(IpTool.isValidIpv4("127.0.0.1"));
		Assert.assertTrue(IpTool.isValidIpv4("127.0.0.01"));
		Assert.assertTrue(IpTool.isValidIpv4("0.0.0.0"));
		Assert.assertTrue(IpTool.isValidIpv4("255.255.255.255"));
		Assert.assertFalse(IpTool.isValidIpv4("0.0.0.0.0"));
		Assert.assertFalse(IpTool.isValidIpv4("192.168.0.256"));
		Assert.assertFalse(IpTool.isValidIpv4(null));
		Assert.assertFalse(IpTool.isValidIpv4(""));
		Assert.assertFalse(IpTool.isValidIpv4("invalid ip"));
		Assert.assertFalse(IpTool.isValidIpv4("..."));
	}
	
	@Test
	public void ipv4StringToLong() {
		Assert.assertTrue(2130706433L == IpTool.ipv4StringToLong("127.0.0.1"));
		Assert.assertTrue(2130706433L == IpTool.ipv4StringToLong("127.0.0.01"));
		Assert.assertTrue(-1 == IpTool.ipv4StringToLong("192.168.0.256"));
		Assert.assertTrue(-1 == IpTool.ipv4StringToLong(null));
		Assert.assertTrue(-1 == IpTool.ipv4StringToLong(""));
		Assert.assertTrue(-1 == IpTool.ipv4StringToLong("invalid ip"));
		Assert.assertTrue(-1 == IpTool.ipv4StringToLong("..."));
	}
	
	@Test
	public void ipv4LongToString() {
		Assert.assertEquals("127.0.0.1", IpTool.ipv4LongToString(2130706433L));
		Assert.assertNull(IpTool.ipv4LongToString(-1));
		Assert.assertNull(IpTool.ipv4LongToString(99999999999L));
		Assert.assertEquals("0.0.0.0", IpTool.ipv4LongToString(0));
	}
	
	@Test
	public void getIpv4sBetween() {
		List<String> ipv4s = IpTool.getIpv4sBetween("127.0.0.1", "127.0.0.3");
		Assert.assertEquals(3, ipv4s.size());
		Assert.assertEquals("127.0.0.1", ipv4s.get(0));
		Assert.assertEquals("127.0.0.2", ipv4s.get(1));
		Assert.assertEquals("127.0.0.3", ipv4s.get(2));
		
		ipv4s = IpTool.getIpv4sBetween("127.0.0.3", "127.0.0.1");
		Assert.assertEquals(3, ipv4s.size());
		Assert.assertEquals("127.0.0.1", ipv4s.get(0));
		Assert.assertEquals("127.0.0.2", ipv4s.get(1));
		Assert.assertEquals("127.0.0.3", ipv4s.get(2));
		
		ipv4s = IpTool.getIpv4sBetween("127.0.0.0", null);
		Assert.assertEquals(0, ipv4s.size());
		
		ipv4s = IpTool.getIpv4sBetween("127.0.0.0", "");
		Assert.assertEquals(0, ipv4s.size());
		
		ipv4s = IpTool.getIpv4sBetween(null, "127.0.0.3");
		Assert.assertEquals(0, ipv4s.size());
		
		ipv4s = IpTool.getIpv4sBetween("", "127.0.0.3");
		Assert.assertEquals(0, ipv4s.size());
		
		ipv4s = IpTool.getIpv4sBetween("invalid ip", "127.0.0.3");
		Assert.assertEquals(0, ipv4s.size());
		
		ipv4s = IpTool.getIpv4sBetween("127.0.0.0", "127.0.0.0");
		Assert.assertEquals(1, ipv4s.size());
	}
	
	@Test
	public void isSameIpv4Seg() {
		String[] ips = {"1.2.3.4", "1.2.3.5"};
		Assert.assertTrue(IpTool.isSameIpv4Seg("255.255.255.0", ips));
		
		String[] ips2 = {"1.2.4.5", "1.2.6.7"};
		Assert.assertTrue(IpTool.isSameIpv4Seg("255.255.0.0", ips2));
		
		String[] ips3 = {"1.3.4.5", "1.6.7.8"};
		Assert.assertTrue(IpTool.isSameIpv4Seg("255.0.0.0", ips3));
		
		String[] ips4 = {"1.2.3.4", "5.6.7.8"};
		Assert.assertTrue(IpTool.isSameIpv4Seg("0.0.0.0", ips4));
		
		String[] ips5 = {"1.2.3.4", "1.2.5.6"};
		Assert.assertFalse(IpTool.isSameIpv4Seg("255.255.255.0", ips5));
		
		String[] ips6 = {"1.3.4.5", "1.6.7.8"};
		Assert.assertFalse(IpTool.isSameIpv4Seg("255.255.0.0", ips6));
		
		String[] ips7 = {"1.2.3.4", "5.6.7.8"};
		Assert.assertFalse(IpTool.isSameIpv4Seg("255.0.0.0", ips7));
		
		String[] ips8 = {"1.2.3.4", "5.6.7.888"};
		Assert.assertFalse(IpTool.isSameIpv4Seg("255.255.255.0", ips8));
		
		String[] ips9 = {"1.2.3.4", null};
		Assert.assertFalse(IpTool.isSameIpv4Seg("255.255.255.0", ips9));
		
		Assert.assertFalse(IpTool.isSameIpv4Seg("255.255.255.0"));
		Assert.assertFalse(IpTool.isSameIpv4Seg("255.255.255.0", new String[0]));
	}
	
	@Test
	public void getFixLengthIpv4() {
		String ip = IpTool.getFixLengthIpv4("1.2.13.224");
		Assert.assertEquals("001.002.013.224", ip);
	
		ip = IpTool.getFixLengthIpv4("1.2.13.256");
		Assert.assertEquals("1.2.13.256", ip);
		
		ip = IpTool.getFixLengthIpv4(null);
		Assert.assertEquals(null, ip);
	}
	
	@Test
	public void getNormalIpv4() {
		String ip = IpTool.getNormalIpv4("001.002.013.224");
		Assert.assertEquals("1.2.13.224", ip);
	
		ip = IpTool.getNormalIpv4("1.2.13.256");
		Assert.assertEquals("1.2.13.256", ip);
		
		ip = IpTool.getNormalIpv4(null);
		Assert.assertEquals(null, ip);
	}
	
	@Test
	public void isLocalIpv4() {
		Assert.assertTrue(IpTool.isLocalIpv4("127.0.0.1"));
		Assert.assertTrue(IpTool.isLocalIpv4("192.168.0.123"));
		Assert.assertFalse(IpTool.isLocalIpv4("220.160.156.242"));
	}
	

}

