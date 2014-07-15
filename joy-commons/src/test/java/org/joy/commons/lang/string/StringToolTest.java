package org.joy.commons.lang.string;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * StringUtils单元测试类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-4 下午8:00:54
 */
public class StringToolTest {

	@Test
	public void testReplaceEach() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "壹");
		map.put("2", "贰");
		map.put("3", "叁");
		map.put(null, "*");
		map.put("", "*");
		Assert.assertEquals("壹贰叁.壹", StringTool.replaceEach("123.1", map));
	}

	@Test
	public void testToHexStr() {
		String str = "Kevice";
		Assert.assertEquals("4b6576696365", StringTool.toHexStr(str));
	}
	
	@Test
	public void testDecodeHexStr() {
		String hexStr = "4b6576696365";
		Assert.assertEquals("Kevice", StringTool.decodeHexStr(hexStr));
	}
	
	@Test
	public void testDivideAverage() {
		Assert.assertEquals(0, StringTool.divideAverage(null, 3).length);
		Assert.assertEquals(0, StringTool.divideAverage("", 3).length);
		Assert.assertEquals(0, StringTool.divideAverage("ererr", 0).length);
		Assert.assertEquals(0, StringTool.divideAverage("ererr", -3).length);
		Assert.assertEquals(0, StringTool.divideAverage(null, 3).length);
		Assert.assertEquals(0, StringTool.divideAverage(null, 3).length);
		
		String[] arr = StringTool.divideAverage("123456", 3);
		Assert.assertEquals(3, arr.length);
		Assert.assertEquals("12", arr[0]);
		Assert.assertEquals("34", arr[1]);
		Assert.assertEquals("56", arr[2]);
		
		arr = StringTool.divideAverage("1234567", 3);
		Assert.assertEquals(3, arr.length);
		Assert.assertEquals("123", arr[0]);
		Assert.assertEquals("456", arr[1]);
		Assert.assertEquals("7", arr[2]);
	}
	
	@Test
	public void testHumpToUnderscore() {
		Assert.assertEquals("", StringTool.humpToUnderscore(null));
		Assert.assertEquals("", StringTool.humpToUnderscore(""));
		Assert.assertEquals("HUMP_TO_UNDERLINE", StringTool.humpToUnderscore("humpToUnderline"));
	}
	
	@Test
	public void testUnderscoreToHump() {
		Assert.assertEquals("", StringTool.underscoreToHump(null));
		Assert.assertEquals("", StringTool.underscoreToHump(""));
		Assert.assertEquals("humpToUnderline", StringTool.underscoreToHump("HUMP_TO_UNDERLINE"));
		Assert.assertFalse("HumpToUnderline".equals(StringTool.underscoreToHump("HUMP_TO_UNDERLINE")));
	}
	

	// ----------------------------------------------------------------------------
	// 封装org.apache.commons.lang3.StringUtils
	// ----------------------------------------------------------------------------

	// 判空
	// -----------------------------------------------------------------------

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(StringTool.isEmpty(null));
		Assert.assertTrue(StringTool.isEmpty(""));
		Assert.assertFalse(StringTool.isEmpty("null"));
		Assert.assertFalse(StringTool.isEmpty(" "));
		Assert.assertFalse(StringTool.isEmpty("kevice"));
		Assert.assertFalse(StringTool.isEmpty(" kevice "));
	}

	@Test
	public void testIsNotEmpty() {
		Assert.assertFalse(StringTool.isNotEmpty(null));
		Assert.assertFalse(StringTool.isNotEmpty(""));
		Assert.assertTrue(StringTool.isNotEmpty("null"));
		Assert.assertTrue(StringTool.isNotEmpty(" "));
		Assert.assertTrue(StringTool.isNotEmpty("kevice"));
		Assert.assertTrue(StringTool.isNotEmpty(" kevice "));
	}

	@Test
	public void testIsBlank() {
		Assert.assertTrue(StringTool.isBlank(null));
		Assert.assertTrue(StringTool.isBlank(""));
		Assert.assertTrue(StringTool.isBlank(" "));
		Assert.assertTrue(StringTool.isBlank("\t\n\r\f"));
		Assert.assertFalse(StringTool.isBlank("null"));
		Assert.assertFalse(StringTool.isBlank("kevice"));
		Assert.assertFalse(StringTool.isBlank(" kevice "));
	}

	@Test
	public void testIsNotBlank() {
		Assert.assertFalse(StringTool.isNotBlank(null));
		Assert.assertFalse(StringTool.isNotBlank(""));
		Assert.assertFalse(StringTool.isNotBlank(" "));
		Assert.assertFalse(StringTool.isNotBlank("\t\n\r\f"));
		Assert.assertTrue(StringTool.isNotBlank("null"));
		Assert.assertTrue(StringTool.isNotBlank("kevice"));
		Assert.assertTrue(StringTool.isNotBlank(" kevice "));
	}

	// Trim
	// -----------------------------------------------------------------------

	@Test
	public void testTrim() {
		Assert.assertEquals(null, StringTool.trim(null));
		Assert.assertEquals("", StringTool.trim(""));
		Assert.assertEquals("", StringTool.trim(" "));
		Assert.assertEquals("", StringTool.trim("\b\t\n\r\f"));
		Assert.assertEquals("null", StringTool.trim("null"));
		Assert.assertEquals("kevice", StringTool.trim("kevice"));
		Assert.assertEquals("k e v i c e", StringTool.trim(" k e v i c e "));
	}

	@Test
	public void testTrimToNull() {
		Assert.assertEquals(null, StringTool.trimToNull(null));
		Assert.assertEquals(null, StringTool.trimToNull(""));
		Assert.assertEquals(null, StringTool.trimToNull(" "));
		Assert.assertEquals(null, StringTool.trimToNull("\b\t\n\r\f"));
		Assert.assertEquals("null", StringTool.trimToNull("null"));
		Assert.assertEquals("kevice", StringTool.trimToNull("kevice"));
		Assert.assertEquals("k e v i c e", StringTool.trimToNull(" k e v i c e "));
	}

	@Test
	public void testTrimToEmpty() {
		Assert.assertEquals("", StringTool.trimToEmpty(null));
		Assert.assertEquals("", StringTool.trimToEmpty(""));
		Assert.assertEquals("", StringTool.trimToEmpty(" "));
		Assert.assertEquals("", StringTool.trimToEmpty("\b\t\n\r\f"));
		Assert.assertEquals("null", StringTool.trimToEmpty("null"));
		Assert.assertEquals("kevice", StringTool.trimToEmpty("kevice"));
		Assert.assertEquals("k e v i c e", StringTool.trimToEmpty(" k e v i c e "));
	}

	// Stripping
	// -----------------------------------------------------------------------

	@Test
	public void testStrip() {
		Assert.assertEquals(null, StringTool.strip(null));
		Assert.assertEquals("", StringTool.strip(""));
		Assert.assertEquals("", StringTool.strip(" "));
		Assert.assertEquals("\b", StringTool.strip("\b\t\n\r\f")); // 注意和trim的差别
		Assert.assertEquals("null", StringTool.strip("null"));
		Assert.assertEquals("kevice", StringTool.strip("kevice"));
		Assert.assertEquals("k e v i c e", StringTool.strip(" k e v i c e "));
	}

	@Test
	public void testStripToNull() {
		Assert.assertEquals(null, StringTool.stripToNull(null));
		Assert.assertEquals(null, StringTool.stripToNull(""));
		Assert.assertEquals(null, StringTool.stripToNull(" "));
		Assert.assertEquals("\b", StringTool.stripToNull("\b\t\n\r\f"));
		Assert.assertEquals("null", StringTool.stripToNull("null"));
		Assert.assertEquals("kevice", StringTool.stripToNull("kevice"));
		Assert.assertEquals("k e v i c e", StringTool.stripToNull(" k e v i c e "));
	}

	@Test
	public void testStripToEmpty() {
		Assert.assertEquals("", StringTool.stripToEmpty(null));
		Assert.assertEquals("", StringTool.stripToEmpty(""));
		Assert.assertEquals("", StringTool.stripToEmpty(" "));
		Assert.assertEquals("\b", StringTool.stripToEmpty("\b\t\n\r\f"));
		Assert.assertEquals("null", StringTool.stripToEmpty("null"));
		Assert.assertEquals("kevice", StringTool.stripToEmpty("kevice"));
		Assert.assertEquals("k e v i c e", StringTool.stripToEmpty(" k e v i c e "));
	}

	@Test
	public void testStripByStr() {
		Assert.assertEquals(null, StringTool.strip(null, "*"));
		Assert.assertEquals("k", StringTool.strip(" k ", null));
		Assert.assertEquals("\b", StringTool.strip("\b\t\n\r\f", null));
		Assert.assertEquals("null", StringTool.strip("null", "*"));
		Assert.assertEquals("  abc", StringTool.strip("yxzxy  abcyx", "xyz"));
	}

	@Test
	public void testStripStartByStr() {
		Assert.assertEquals(null, StringTool.stripStart(null, "*"));
		Assert.assertEquals("k ", StringTool.stripStart(" k ", null));
		Assert.assertEquals("\b", StringTool.stripStart("\t\n\r\f\b", null));
		Assert.assertEquals("null", StringTool.stripStart("null", "*"));
		Assert.assertEquals("  abcyx", StringTool.stripStart("yxzxy  abcyx", "xyz"));
	}

	@Test
	public void testStripEndByStr() {
		Assert.assertEquals(null, StringTool.stripEnd(null, "*"));
		Assert.assertEquals(" k", StringTool.stripEnd(" k ", null));
		Assert.assertEquals("\b", StringTool.stripEnd("\b\t\n\r\f", null));
		Assert.assertEquals("null", StringTool.stripEnd("null", "*"));
		Assert.assertEquals("yxzxy  abc", StringTool.stripEnd("yxzxy  abcyx", "xyz"));
		Assert.assertEquals("12", StringTool.stripEnd("120.00", ".0"));
	}

	@Test
	public void testStripAll() {
		String[] strs = { null, "", " ", "\b\t\n\r\f", "null", "kevice", " k e v i c e " };

		String[] results = StringTool.stripAll(strs);

		Assert.assertEquals(null, results[0]);
		Assert.assertEquals("", results[1]);
		Assert.assertEquals("", results[2]);
		Assert.assertEquals("\b", results[3]);
		Assert.assertEquals("null", results[4]);
		Assert.assertEquals("kevice", results[5]);
		Assert.assertEquals("k e v i c e", results[6]);
	}

	// @Test
	// public void testStripAccents() {
	// Assert.assertEquals(null, StringUtils.stripAccents(null));
	// Assert.assertEquals("", StringUtils.stripAccents(""));
	// Assert.assertEquals("control", StringUtils.stripAccents("control"));
	// Assert.assertEquals("eclair", StringUtils.stripAccents("&eacute;clair"));
	// Assert.assertEquals("a", StringUtils.stripAccents("&agrave;"));
	// }

	// Equals
	// -----------------------------------------------------------------------

	@Test
	public void testEquals() {
		Assert.assertTrue(StringTool.equals(null, null));
		Assert.assertTrue(StringTool.equals("abc", "abc"));
		Assert.assertFalse(StringTool.equals(null, "abc"));
		Assert.assertFalse(StringTool.equals("abc", null));
		Assert.assertFalse(StringTool.equals("abc", "ABC"));
	}

	@Test
	public void testEqualsIgnoreCase() {
		Assert.assertTrue(StringTool.equalsIgnoreCase(null, null));
		Assert.assertTrue(StringTool.equalsIgnoreCase("abc", "abc"));
		Assert.assertTrue(StringTool.equalsIgnoreCase("abc", "ABC"));
		Assert.assertFalse(StringTool.equalsIgnoreCase(null, "abc"));
		Assert.assertFalse(StringTool.equalsIgnoreCase("abc", null));
	}

	// IndexOf
	// -----------------------------------------------------------------------

	@Test
	public void testIndexOf() {
		Assert.assertEquals(-1, StringTool.indexOf(null, '*'));
		Assert.assertEquals(-1, StringTool.indexOf("", '*'));
		Assert.assertEquals(0, StringTool.indexOf("aabaabaa", 'a'));
		Assert.assertEquals(2, StringTool.indexOf("aabaabaa", 'b'));
	}

	@Test
	public void testIndexOfFrom() {
		Assert.assertEquals(-1, StringTool.indexOf(null, '*', 1));
		Assert.assertEquals(-1, StringTool.indexOf("", '*', 1));
		Assert.assertEquals(2, StringTool.indexOf("aabaabaa", 'b', 0));
		Assert.assertEquals(5, StringTool.indexOf("aabaabaa", 'b', 3));
		Assert.assertEquals(-1, StringTool.indexOf("aabaabaa", 'b', 9));
		Assert.assertEquals(2, StringTool.indexOf("aabaabaa", 'b', -1));
	}

	@Test
	public void testIndexOfString() {
		Assert.assertEquals(-1, StringTool.indexOf(null, "*"));
		Assert.assertEquals(-1, StringTool.indexOf("*", null));
		Assert.assertEquals(-1, StringTool.indexOf("", "*"));
		Assert.assertEquals(0, StringTool.indexOf("", ""));
		Assert.assertEquals(0, StringTool.indexOf("aabaabaa", "a"));
		Assert.assertEquals(2, StringTool.indexOf("aabaabaa", "b"));
		Assert.assertEquals(1, StringTool.indexOf("aabaabaa", "ab"));
		Assert.assertEquals(0, StringTool.indexOf("aabaabaa", ""));
	}

	@Test
	public void testIndexOfStringFrom() {
		Assert.assertEquals(-1, StringTool.indexOf(null, "*", 1));
		Assert.assertEquals(-1, StringTool.indexOf("*", null, 1));
		Assert.assertEquals(-1, StringTool.indexOf("", "*", 2));
		Assert.assertEquals(0, StringTool.indexOf("", "", 0));
		Assert.assertEquals(1, StringTool.indexOf("aabaabaa", "a", 1));
		Assert.assertEquals(5, StringTool.indexOf("aabaabaa", "b", 4));
		Assert.assertEquals(1, StringTool.indexOf("aabaabaa", "ab", 0));
		Assert.assertEquals(0, StringTool.indexOf("aabaabaa", "", -1));
	}

	@Test
	public void testOrdinalIndexOf() {
		Assert.assertEquals(-1, StringTool.ordinalIndexOf(null, "*", 1));
		Assert.assertEquals(-1, StringTool.ordinalIndexOf("*", null, 1));
		Assert.assertEquals(-1, StringTool.ordinalIndexOf("", "*", 2));
		Assert.assertEquals(0, StringTool.ordinalIndexOf("", "", 2));
		Assert.assertEquals(0, StringTool.ordinalIndexOf("aabaabaa", "a", 1));
		Assert.assertEquals(5, StringTool.ordinalIndexOf("aabaabaa", "b", 2));
		Assert.assertEquals(4, StringTool.ordinalIndexOf("aabaabaa", "ab", 2));
		Assert.assertEquals(0, StringTool.ordinalIndexOf("aabaabaa", "", 2));
	}

	@Test
	public void testIndexOfIgnoreCase() {
		Assert.assertEquals(-1, StringTool.indexOfIgnoreCase(null, "*"));
		Assert.assertEquals(-1, StringTool.indexOfIgnoreCase("", "*"));
		Assert.assertEquals(0, StringTool.indexOfIgnoreCase("", ""));
		Assert.assertEquals(0, StringTool.indexOfIgnoreCase("aabaabaa", "a"));
		Assert.assertEquals(2, StringTool.indexOfIgnoreCase("aabaaBaa", "B"));
		Assert.assertEquals(1, StringTool.indexOfIgnoreCase("aabaAbaa", "aB"));
	}

	@Test
	public void testIndexOfIgnoreCaseFrom() {
		Assert.assertEquals(-1, StringTool.indexOfIgnoreCase(null, "*", 1));
		Assert.assertEquals(-1, StringTool.indexOfIgnoreCase("*", null, 1));
		Assert.assertEquals(-1, StringTool.indexOfIgnoreCase("", "*", 2));
		Assert.assertEquals(0, StringTool.indexOfIgnoreCase("", "", 0));
		Assert.assertEquals(1, StringTool.indexOfIgnoreCase("aabaabaa", "A", 1));
		Assert.assertEquals(5, StringTool.indexOfIgnoreCase("aaBaaBaa", "B", 4));
		Assert.assertEquals(1, StringTool.indexOfIgnoreCase("aabaabaa", "Ab", 0));
		Assert.assertEquals(0, StringTool.indexOfIgnoreCase("aabaabaa", "", -1));
	}

	@Test
	public void testLastIndexOf() {
		Assert.assertEquals(-1, StringTool.lastIndexOf(null, '*'));
		Assert.assertEquals(-1, StringTool.lastIndexOf("", '*'));
		Assert.assertEquals(7, StringTool.lastIndexOf("aabaabaa", 'a'));
		Assert.assertEquals(5, StringTool.lastIndexOf("aabaabaa", 'b'));
	}

	@Test
	public void testLastIndexOfFrom() {
		Assert.assertEquals(-1, StringTool.lastIndexOf(null, '*', 0));
		Assert.assertEquals(-1, StringTool.lastIndexOf("", '*', 0));
		Assert.assertEquals(5, StringTool.lastIndexOf("aabaabaa", 'b', 8));
		Assert.assertEquals(2, StringTool.lastIndexOf("aabaabaa", 'b', 4));
		Assert.assertEquals(-1, StringTool.lastIndexOf("aabaabaa", 'b', 0));
		Assert.assertEquals(5, StringTool.lastIndexOf("aabaabaa", 'b', 9));
		Assert.assertEquals(-1, StringTool.lastIndexOf("aabaabaa", 'b', -1));
		Assert.assertEquals(0, StringTool.lastIndexOf("aabaabaa", 'a', 0));
	}

	@Test
	public void testLastIndexOfString() {
		Assert.assertEquals(-1, StringTool.lastIndexOf(null, "*"));
		Assert.assertEquals(-1, StringTool.lastIndexOf("*", null));
		Assert.assertEquals(0, StringTool.lastIndexOf("", ""));
		Assert.assertEquals(7, StringTool.lastIndexOf("aabaabaa", "a"));
		Assert.assertEquals(5, StringTool.lastIndexOf("aabaabaa", "b"));
		Assert.assertEquals(4, StringTool.lastIndexOf("aabaabaa", "ab"));
		Assert.assertEquals(8, StringTool.lastIndexOf("aabaabaa", ""));
	}

	@Test
	public void testLastOrdinalIndexOf() {
		Assert.assertEquals(-1, StringTool.lastOrdinalIndexOf(null, "*", 8));
		Assert.assertEquals(-1, StringTool.lastOrdinalIndexOf("*", null, 8));
		Assert.assertEquals(0, StringTool.lastOrdinalIndexOf("", "", 8));
		Assert.assertEquals(7, StringTool.lastOrdinalIndexOf("aabaabaa", "a", 1));
		Assert.assertEquals(6, StringTool.lastOrdinalIndexOf("aabaabaa", "a", 2));
		Assert.assertEquals(5, StringTool.lastOrdinalIndexOf("aabaabaa", "b", 1));
		Assert.assertEquals(2, StringTool.lastOrdinalIndexOf("aabaabaa", "b", 2));
		Assert.assertEquals(4, StringTool.lastOrdinalIndexOf("aabaabaa", "ab", 1));
		Assert.assertEquals(1, StringTool.lastOrdinalIndexOf("aabaabaa", "ab", 2));
		Assert.assertEquals(8, StringTool.lastOrdinalIndexOf("aabaabaa", "", 1));
		Assert.assertEquals(8, StringTool.lastOrdinalIndexOf("aabaabaa", "", 2));
	}

	@Test
	public void testLastIndexOfStringFrom() {
		Assert.assertEquals(-1, StringTool.lastIndexOf(null, "*", 8));
		Assert.assertEquals(-1, StringTool.lastIndexOf("*", null, 8));
		Assert.assertEquals(7, StringTool.lastIndexOf("aabaabaa", "a", 8));
		Assert.assertEquals(5, StringTool.lastIndexOf("aabaabaa", "b", 8));
		Assert.assertEquals(4, StringTool.lastIndexOf("aabaabaa", "ab", 8));
		Assert.assertEquals(5, StringTool.lastIndexOf("aabaabaa", "b", 9));
		Assert.assertEquals(-1, StringTool.lastIndexOf("aabaabaa", "b", -1));
		Assert.assertEquals(0, StringTool.lastIndexOf("aabaabaa", "a", 0));
		Assert.assertEquals(-1, StringTool.lastIndexOf("aabaabaa", "b", 0));
	}

	@Test
	public void testlastIndexOfIgnoreCase() {
		Assert.assertEquals(-1, StringTool.lastIndexOfIgnoreCase(null, "*"));
		Assert.assertEquals(-1, StringTool.lastIndexOfIgnoreCase("*", null));
		Assert.assertEquals(7, StringTool.lastIndexOfIgnoreCase("aabaabaa", "A"));
		Assert.assertEquals(5, StringTool.lastIndexOfIgnoreCase("aabaabaa", "B"));
		Assert.assertEquals(4, StringTool.lastIndexOfIgnoreCase("aabaabaa", "AB"));
	}

	@Test
	public void testLastIndexOfIgnoreCase() {
		Assert.assertEquals(-1, StringTool.lastIndexOfIgnoreCase(null, "*", 8));
		Assert.assertEquals(-1, StringTool.lastIndexOfIgnoreCase("*", null, 8));
		Assert.assertEquals(7, StringTool.lastIndexOfIgnoreCase("aabaabaa", "A", 8));
		Assert.assertEquals(5, StringTool.lastIndexOfIgnoreCase("aabaabaa", "B", 8));
		Assert.assertEquals(4, StringTool.lastIndexOfIgnoreCase("aabaabaa", "AB", 8));
		Assert.assertEquals(5, StringTool.lastIndexOfIgnoreCase("aabaabaa", "B", 9));
		Assert.assertEquals(-1, StringTool.lastIndexOfIgnoreCase("aabaabaa", "B", -1));
		Assert.assertEquals(0, StringTool.lastIndexOfIgnoreCase("aabaabaa", "A", 0));
		Assert.assertEquals(-1, StringTool.lastIndexOfIgnoreCase("aabaabaa", "B", 0));
	}

	// Contains
	// -----------------------------------------------------------------------

	@Test
	public void testContains() {
		Assert.assertFalse(StringTool.contains(null, '*'));
		Assert.assertFalse(StringTool.contains("", '*'));
		Assert.assertTrue(StringTool.contains("abc", 'a'));
		Assert.assertFalse(StringTool.contains("abc", 'z'));
	}
	
}
