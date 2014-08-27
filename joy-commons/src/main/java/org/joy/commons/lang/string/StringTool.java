package org.joy.commons.lang.string;

import org.apache.commons.lang3.ObjectUtils;
import org.joy.commons.collections.MapTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.security.CryptoTool;
import org.joy.commons.security.DigestTool;

import java.util.*;

/**
 * 字符串操作工具类
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-8 下午9:23:31
 */
public class StringTool {

	protected static final Log logger = LogFactory.getLog(StringTool.class);

	private StringTool() {
	}


	/**
	 * <p>
	 * 查找子串，并用指定字符串替换之（替换所有出现的地方），支持多对替换规则
	 * </p>
	 * 
	 * @see #replaceEach(String text, String[] searchList, String[] replacementList)
	 * @param text 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param map Map<要查找的字符串, 用来替换的字符串>
	 * @return 替换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午9:23:31
	 */
	public static String replaceEach(String text, Map<String, String> map) {
		String[] searchList = null;
		String[] replacementList = null;
		if (MapTool.isNotEmpty(map)) {
            Set<String> set = map.keySet();
            searchList = set.toArray(new String[set.size()]);
            Collection<String> col = map.values();
            replacementList = col.toArray(new String[col.size()]);
		}
		return replaceEach(text, searchList, replacementList);
	}


	/**
	 * <p>
	 * 将字符串转换为十六进制表示的值
	 * </p> 
	 * 
	 * @param str 需要转换的字符串
	 * @return 转换后的十六进制表示的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午10:05:20
	 */
	public static String toHexStr(String str) {
		if (isBlank(str)) {
			return "";
		}
		char[] chars = CryptoTool.encodeHex(str.getBytes());
		return new String(chars);
	}

	/**
	 * <p>
	 * 解码十六进制表示的字符串
	 * </p>
	 * 
	 * @param hexStr 十六进制表示的字符串
	 * @return 解码后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午10:05:20
	 */
	public static String decodeHexStr(String hexStr) {
		if (isBlank(hexStr)) {
			return "";
		}
		byte[] bytes = hexStr.getBytes();
		return new String(CryptoTool.decodeHex(bytes));
	}
	
	/**
	 * 对字符串进行MD5加密后，再进行十六进制编码
	 * 
	 * @param str 待加密的字符串
     * @param saltStr 盐
	 * @return 加密的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月1日 下午10:02:50
	 */
	public static String toMd5HexStr(String str, String saltStr) {
		byte[] data = str.getBytes();
        byte[] salt = isBlank(saltStr) ? null : saltStr.getBytes();
		byte[] digest = DigestTool.digest(data, DigestTool.MD5, salt, 1);
		return new String(CryptoTool.encodeHex(digest));
	}

	/**
	 * <p>
	 * 将字符串按给定的长度均分(最后一组可能不是等分的)
	 * </p>
	 * 
	 * <pre>
	 * divideAverage(null, *) = []
	 * divideAverage("", *) = []
	 * divideAverage(*, 0) = []
	 * divideAverage(*, -3) = []
	 * divideAverage("123456", 3) = ["12", "34", "56"]
	 * divideAverage("1234567", 3) = ["123", "456", "7"]
	 * </pre>
	 * 
	 * @param srcStr 源字符串
	 * @param groupLen 每份长度
	 * @return 等分后每个分组组成的数组
	 * @author Kevice
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午9:52:54
	 */
	public static String[] divideAverage(String srcStr, int groupLen) {
		if (isEmpty(srcStr) || groupLen <= 0) {
			return new String[0];
		}
		int strLen = srcStr.length();
		int eachCount = (int) Math.ceil((double) strLen / groupLen);
		String[] groups = new String[groupLen];
		for (int i = 0; i < groupLen; i++) {
			int beginIndex = i * eachCount;
			int endIndex;
			if (i == groupLen - 1) { // 最后一组
				endIndex = strLen;
			} else {
				endIndex = beginIndex + eachCount;
			}
			groups[i] = srcStr.substring(beginIndex, endIndex);
		}
		return groups;
	}

	/**
	 * <p>
	 * 将“驼峰”式写法的字符串转为用“_”分割的字符串
	 * </p>
	 * 
	 * <pre>
	 * humpToUnderscore(null) = ""
	 * humpToUnderscore("") = ""
	 * humpToUnderscore(" ") = ""
	 * humpToUnderscore("humpToUnderscore") = "HUMP_TO_Underscore"
	 * </pre>
	 * 
	 * @param str “驼峰”式写法的字符串
	 * @return “_”分割的字符串, 并且是大写的
	 * @author Kevice
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午9:54:54
	 */
	public static String humpToUnderscore(String str) {
		if (isNotBlank(str)) {
			str = str.trim();
			char[] chars = str.toCharArray();
			StringBuilder sb = new StringBuilder();
			sb.append(chars[0]);
			for (int i = 1; i < chars.length; i++) {
				if (Character.isUpperCase(chars[i]) && Character.isLowerCase(chars[i - 1])) {
					sb.append("_");
				}
				sb.append(chars[i]);
			}
			return sb.toString().toUpperCase();
		}
		return "";
	}
	
	/**
	 * <p>
	 * 将“_”分割的字符串转为“驼峰”式写法的字符串, 如：HUMP_TO_Underscore -> humpToUnderscore
	 * </p>
	 * 
	 * <pre>
	 * underscoreToHump(null) = ""
	 * underscoreToHump("") = ""
	 * underscoreToHump(" ") = ""
	 * underscoreToHump("HUMP_TO_Underscore") = "humpToUnderscore"
	 * </pre>
	 * 
	 * @param str “_”分割的字符串
	 * @return “驼峰”式写法的字符串
	 * @author Kevice
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午9:55:54
	 */
	public static String underscoreToHump(String str) {
		if (isNotBlank(str)) {
			str = str.trim();
			String[] words = split(str, "_");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				if(i == 0) {
					sb.append(word.toLowerCase());
				} else {
					sb.append(capitalize(word.toLowerCase()));
				}
			}
			return sb.toString();
		}
		return "";
	}

	// ----------------------------------------------------------------------------
	// 封装org.apache.commons.lang3.StringUtils
	// ----------------------------------------------------------------------------

	// 判空
	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 判断给定的字符序列是否为空串或为null
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param cs 待判断的字符序列
	 * @return {@code true} 如果字符序列为空串或为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午8:10:47
	 */
	public static boolean isEmpty(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isEmpty(cs);
	}

	/**
	 * <p>
	 * 判断给定的字符序列是否不为空串或为null
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs 待判断的字符序列
	 * @return {@code true} 如果字符序列不为空串或为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午8:16:47
	 */
	public static boolean isNotEmpty(CharSequence cs) {
		return !isEmpty(cs);
	}

	/**
	 * <p>
	 * 判断给定的字符序列是否为空白、空串或为null
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param cs 待判断的字符序列
	 * @return {@code true} 如果字符序列为空白、空串或为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午8:19:17
	 */
	public static boolean isBlank(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isBlank(cs);
	}

	/**
	 * <p>
	 * 判断给定的字符序列是否不为空白、空串或为null
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs 待判断的字符序列，可以为null
	 * @return {@code true} 如果字符序列不为空白、空串或为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午8:22:17
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !isBlank(cs);
	}

	// Trim
	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 去除给定字符串的前后控制符和空白符(如果有的话)
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trim(null)          = null
	 * StringUtils.trim("")            = ""
	 * StringUtils.trim("     ")       = ""
	 * StringUtils.trim("abc")         = "abc"
	 * StringUtils.trim("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str 待处理的字符串，可以为null
	 * @return 去除前后控制符和空白符后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午8:25:23
	 */
	public static String trim(String str) {
		return org.apache.commons.lang3.StringUtils.trim(str);
	}

	/**
	 * <p>
	 * 去除给定字符串的前后控制符和空白符(如果有的话)，结果如果是空串或null的话，将返回null
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trimToNull(null)          = null
	 * StringUtils.trimToNull("")            = null
	 * StringUtils.trimToNull("     ")       = null
	 * StringUtils.trimToNull("abc")         = "abc"
	 * StringUtils.trimToNull("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str 待处理的字符串，可以为null
	 * @return 去除前后控制符和空白符后的字符串，如果是空串或null的话，将返回null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午8:35:54
	 */
	public static String trimToNull(String str) {
		return org.apache.commons.lang3.StringUtils.trimToNull(str);
	}

	/**
	 * <p>
	 * 去除给定字符串的前后控制符和空白符(如果有的话)，结果如果是空串或null的话，将返回空串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trimToEmpty(null)          = ""
	 * StringUtils.trimToEmpty("")            = ""
	 * StringUtils.trimToEmpty("     ")       = ""
	 * StringUtils.trimToEmpty("abc")         = "abc"
	 * StringUtils.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str 待处理的字符串，可以为null
	 * @return 去除前后控制符和空白符后的字符串，如果是空串或null的话，将返回空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午8:45:54
	 */
	public static String trimToEmpty(String str) {
		return org.apache.commons.lang3.StringUtils.trimToEmpty(str);
	}

	// Stripping
	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 去除给定字符串的前后空白符(如果有的话)
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.strip(null)     = null
	 * StringUtils.strip("")       = ""
	 * StringUtils.strip("   ")    = ""
	 * StringUtils.strip("abc")    = "abc"
	 * StringUtils.strip("  abc")  = "abc"
	 * StringUtils.strip("abc  ")  = "abc"
	 * StringUtils.strip(" abc ")  = "abc"
	 * StringUtils.strip(" ab c ") = "ab c"
	 * </pre>
	 * 
	 * @param str 待处理的字符串，可以为null
	 * @return 去除前后空白符后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午8:49:07
	 */
	public static String strip(String str) {
		return org.apache.commons.lang3.StringUtils.strip(str);
	}

	/**
	 * <p>
	 * 去除给定字符串的前后空白符(如果有的话)，结果如果是空串或null的话，将返回null
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripToNull(null)     = null
	 * StringUtils.stripToNull("")       = null
	 * StringUtils.stripToNull("   ")    = null
	 * StringUtils.stripToNull("abc")    = "abc"
	 * StringUtils.stripToNull("  abc")  = "abc"
	 * StringUtils.stripToNull("abc  ")  = "abc"
	 * StringUtils.stripToNull(" abc ")  = "abc"
	 * StringUtils.stripToNull(" ab c ") = "ab c"
	 * </pre>
	 * 
	 * @param str 待处理的字符串，可以为null
	 * @return 去除前后空白符后的字符串，如果是空串或null的话，将返回null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午9:17:07
	 */
	public static String stripToNull(String str) {
		return org.apache.commons.lang3.StringUtils.stripToNull(str);
	}

	/**
	 * <p>
	 * 去除给定字符串的前后空白符(如果有的话)，结果如果是空串或null的话，将返回空串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripToEmpty(null)     = ""
	 * StringUtils.stripToEmpty("")       = ""
	 * StringUtils.stripToEmpty("   ")    = ""
	 * StringUtils.stripToEmpty("abc")    = "abc"
	 * StringUtils.stripToEmpty("  abc")  = "abc"
	 * StringUtils.stripToEmpty("abc  ")  = "abc"
	 * StringUtils.stripToEmpty(" abc ")  = "abc"
	 * StringUtils.stripToEmpty(" ab c ") = "ab c"
	 * </pre>
	 * 
	 * @param str 待处理的字符串，可以为null
	 * @return 去除前后空白符后的字符串，如果是空串或null的话，将返回空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午9:17:07
	 */
	public static String stripToEmpty(String str) {
		return org.apache.commons.lang3.StringUtils.stripToEmpty(str);
	}

	/**
	 * <p>
	 * 删除给定字符串前后匹配任何指定字符集的子集的内容
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.strip(null, *)          = null
	 * StringUtils.strip("", *)            = ""
	 * StringUtils.strip("abc", null)      = "abc"
	 * StringUtils.strip("  abc", null)    = "abc"
	 * StringUtils.strip("abc  ", null)    = "abc"
	 * StringUtils.strip(" abc ", null)    = "abc"
	 * StringUtils.strip("  abcyx", "xyz") = "  abc"
	 * </pre>
	 * 
	 * @param str 待处理的字符串，可以为null
	 * @param stripChars 要删除的字符集，null将被当作空白符
	 * @return 处理后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午9:22:51
	 */
	public static String strip(String str, String stripChars) {
		return org.apache.commons.lang3.StringUtils.strip(str, stripChars);
	}

	/**
	 * <p>
	 * 删除给定字符串前端匹配任何指定字符集的子集的内容
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripStart(null, *)          = null
	 * StringUtils.stripStart("", *)            = ""
	 * StringUtils.stripStart("abc", "")        = "abc"
	 * StringUtils.stripStart("abc", null)      = "abc"
	 * StringUtils.stripStart("  abc", null)    = "abc"
	 * StringUtils.stripStart("abc  ", null)    = "abc  "
	 * StringUtils.stripStart(" abc ", null)    = "abc "
	 * StringUtils.stripStart("yxabc  ", "xyz") = "abc  "
	 * </pre>
	 * 
	 * @param str 待处理的字符串，可以为null
	 * @param stripChars 要删除的字符串，null将被当作空白符
	 * @return 处理后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午9:22:51
	 */
	public static String stripStart(String str, String stripChars) {
		return org.apache.commons.lang3.StringUtils.stripStart(str, stripChars);
	}

	/**
	 * <p>
	 * 删除给定字符串末端匹配任何指定字符集的子集的内容
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripEnd(null, *)          = null
	 * StringUtils.stripEnd("", *)            = ""
	 * StringUtils.stripEnd("abc", "")        = "abc"
	 * StringUtils.stripEnd("abc", null)      = "abc"
	 * StringUtils.stripEnd("  abc", null)    = "  abc"
	 * StringUtils.stripEnd("abc  ", null)    = "abc"
	 * StringUtils.stripEnd(" abc ", null)    = " abc"
	 * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
	 * StringUtils.stripEnd("120.00", ".0")   = "12"
	 * </pre>
	 * 
	 * @param str 待处理的字符串，可以为null
	 * @param stripChars 要删除的字符串，null将被当作空白符
	 * @return 处理后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午9:22:51
	 */
	public static String stripEnd(String str, String stripChars) {
		return org.apache.commons.lang3.StringUtils.stripEnd(str, stripChars);
	}

	/**
	 * <p>
	 * 对字符串数组中的每个字符串进行 strip(String str) ，然后返回
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripAll(null)             = null
	 * StringUtils.stripAll([])               = []
	 * StringUtils.stripAll(["abc", "  abc"]) = ["abc", "abc"]
	 * StringUtils.stripAll(["abc  ", null])  = ["abc", null]
	 * </pre>
	 * 
	 * @param strs 待处理的字符串数组，可以为null
	 * @return 处理后的字符串数组，{@code null} 如果strs参数值为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午10:06:50
	 */
	public static String[] stripAll(String... strs) {
		return org.apache.commons.lang3.StringUtils.stripAll(strs);
	}

	/**
	 * <p>
	 * 对字符串数组中的每个字符串进行 strip(String str, String stripChars) ，然后返回。
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripAll(null, *)                = null
	 * StringUtils.stripAll([], *)                  = []
	 * StringUtils.stripAll(["abc", "  abc"], null) = ["abc", "abc"]
	 * StringUtils.stripAll(["abc  ", null], null)  = ["abc", null]
	 * StringUtils.stripAll(["abc  ", null], "yz")  = ["abc  ", null]
	 * StringUtils.stripAll(["yabcz", null], "yz")  = ["abc", null]
	 * </pre>
	 * 
	 * @param strs 待处理的字符串数组，可以为null
	 * @param stripChars 要删除的字符串，null将被当作空白符
	 * @return 处理后的字符串数组，{@code null} 如果strs参数值为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午10:13:11
	 */
	public static String[] stripAll(String[] strs, String stripChars) {
		return org.apache.commons.lang3.StringUtils.stripAll(strs, stripChars);
	}

	/**
	 * <p>
	 * 移删重音符号，大小写不改变
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripAccents(null)                = null
	 * StringUtils.stripAccents("")                  = ""
	 * StringUtils.stripAccents("control")           = "control"
	 * StringUtils.stripAccents("&eacute;clair")     = "eclair"
	 * </pre>
	 * 
	 * @param input 源字符串，可以为null，为null返回null
	 * @return 移除重音符号后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午10:31:58
	 */
	public static String stripAccents(String input) {
		return org.apache.commons.lang3.StringUtils.stripAccents(input);
	}

	// Equals
	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 比较两个字符串是否相等
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.equals(null, null)   = true
	 * StringUtils.equals(null, "abc")  = false
	 * StringUtils.equals("abc", null)  = false
	 * StringUtils.equals("abc", "abc") = true
	 * StringUtils.equals("abc", "ABC") = false
	 * </pre>
	 * 
	 * @param cs1 字符串1，可以为null
	 * @param cs2 字符串2，可以为null
	 * @return {@code true} 如果两字符串相等，大小写敏感，或两者都为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午10:48:25
	 */
	public static boolean equals(CharSequence cs1, CharSequence cs2) {
		return org.apache.commons.lang3.StringUtils.equals(cs1, cs2);
	}

	/**
	 * <p>
	 * 比较两个字符串是否相等，不区分大小写
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.equalsIgnoreCase(null, null)   = true
	 * StringUtils.equalsIgnoreCase(null, "abc")  = false
	 * StringUtils.equalsIgnoreCase("abc", null)  = false
	 * StringUtils.equalsIgnoreCase("abc", "abc") = true
	 * StringUtils.equalsIgnoreCase("abc", "ABC") = true
	 * </pre>
	 * 
	 * @param str1 字符串1，可以为null
	 * @param str2 字符串2，可以为null
	 * @return {@code true} 如果两字符串相等，大小写不敏感，或两者都为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午10:53:25
	 */
	public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
		return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(str1, str2);
	}

	// IndexOf
	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 返回字符 searchChar 在字符串 str 中第一次出现的位置
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOf(null, *)         = -1
	 * StringUtils.indexOf("", *)           = -1
	 * StringUtils.indexOf("aabaabaa", 'a') = 0
	 * StringUtils.indexOf("aabaabaa", 'b') = 2
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchChar 要查找的字符
	 * @return 第一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午11:01:25
	 */
	public static int indexOf(CharSequence seq, int searchChar) {
		return org.apache.commons.lang3.StringUtils.indexOf(seq, searchChar);
	}

	/**
	 * <p>
	 * 返回字符 searchChar 从 startPos 开始在字符串 str 中第一次出现的位置
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOf(null, *, *)          = -1
	 * StringUtils.indexOf("", *, *)            = -1
	 * StringUtils.indexOf("aabaabaa", 'b', 0)  = 2
	 * StringUtils.indexOf("aabaabaa", 'b', 3)  = 5
	 * StringUtils.indexOf("aabaabaa", 'b', 9)  = -1
	 * StringUtils.indexOf("aabaabaa", 'b', -1) = 2
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchChar 要查找的字符
	 * @param startPos 开始位置，负数将当作0处理
	 * @return 第一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午11:18:14
	 */
	public static int indexOf(CharSequence seq, int searchChar, int startPos) {
		return org.apache.commons.lang3.StringUtils.indexOf(seq, searchChar, startPos);
	}

	/**
	 * <p>
	 * 返回字符串 searchStr 在字符串 str 中第一次出现的位置
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOf(null, *)          = -1
	 * StringUtils.indexOf(*, null)          = -1
	 * StringUtils.indexOf("", "")           = 0
	 * StringUtils.indexOf("", *)            = -1 (except when * = "")
	 * StringUtils.indexOf("aabaabaa", "a")  = 0
	 * StringUtils.indexOf("aabaabaa", "b")  = 2
	 * StringUtils.indexOf("aabaabaa", "ab") = 1
	 * StringUtils.indexOf("aabaabaa", "")   = 0
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchSeq 要查找的字符序列，可以为null
	 * @return 第一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午11:24:27
	 */
	public static int indexOf(CharSequence seq, CharSequence searchSeq) {
		return org.apache.commons.lang3.StringUtils.indexOf(seq, searchSeq);
	}

	/**
	 * <p>
	 * 返回字符串 searchStr 在字符串 str 中第一次出现的位置，从startPos位置开始找
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOf(null, *, *)          = -1
	 * StringUtils.indexOf(*, null, *)          = -1
	 * StringUtils.indexOf("", "", 0)           = 0
	 * StringUtils.indexOf("", *, 0)            = -1 (except when * = "")
	 * StringUtils.indexOf("aabaabaa", "a", 0)  = 0
	 * StringUtils.indexOf("aabaabaa", "b", 0)  = 2
	 * StringUtils.indexOf("aabaabaa", "ab", 0) = 1
	 * StringUtils.indexOf("aabaabaa", "b", 3)  = 5
	 * StringUtils.indexOf("aabaabaa", "b", 9)  = -1
	 * StringUtils.indexOf("aabaabaa", "b", -1) = 2
	 * StringUtils.indexOf("aabaabaa", "", 2)   = 2
	 * StringUtils.indexOf("abc", "", 9)        = 3
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchSeq 要查找的字符序列，可以为null
	 * @param startPos 开始位置，负数将当作0处理
	 * @return 第一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午11:31:06
	 */
	public static int indexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
		return org.apache.commons.lang3.StringUtils.indexOf(seq, searchSeq, startPos);
	}

	/**
	 * <p>
	 * 返回字符串 searchStr 在字符串 str 中第 ordinal 次出现的位置
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.ordinalIndexOf(null, *, *)          = -1
	 * StringUtils.ordinalIndexOf(*, null, *)          = -1
	 * StringUtils.ordinalIndexOf("", "", *)           = 0
	 * StringUtils.ordinalIndexOf("aabaabaa", "a", 1)  = 0
	 * StringUtils.ordinalIndexOf("aabaabaa", "a", 2)  = 1
	 * StringUtils.ordinalIndexOf("aabaabaa", "b", 1)  = 2
	 * StringUtils.ordinalIndexOf("aabaabaa", "b", 2)  = 5
	 * StringUtils.ordinalIndexOf("aabaabaa", "ab", 1) = 1
	 * StringUtils.ordinalIndexOf("aabaabaa", "ab", 2) = 4
	 * StringUtils.ordinalIndexOf("aabaabaa", "", 1)   = 0
	 * StringUtils.ordinalIndexOf("aabaabaa", "", 2)   = 0
	 * </pre>
	 * 
	 * @param str 待查找的字符序列，可以为null
	 * @param searchStr 要查找的字符序列，可以为null
	 * @param ordinal 匹配的序数
	 * @return 第 ordinal 次出现的位置，如果没有找到或输入str为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-4 下午11:36:47
	 */
	public static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
		return org.apache.commons.lang3.StringUtils.ordinalIndexOf(str, searchStr, ordinal);
	}

	/**
	 * <p>
	 * 返回字符 searchChar 在字符串 str 中第一次出现的位置，大小不敏感
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOfIgnoreCase(null, *)          = -1
	 * StringUtils.indexOfIgnoreCase(*, null)          = -1
	 * StringUtils.indexOfIgnoreCase("", "")           = 0
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "a")  = 0
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "b")  = 2
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "ab") = 1
	 * </pre>
	 * 
	 * @param str 待查找的字符序列，可以为null
	 * @param searchStr 要查找的字符序列，可以为null
	 * @return 第一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-5 下午6:58:27
	 */
	public static int indexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
		return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr);
	}

	/**
	 * <p>
	 * 返回字符 searchChar 从 startPos 开始在字符串 str 中第一次出现的位置，大小写敏感
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOfIgnoreCase(null, *, *)          = -1
	 * StringUtils.indexOfIgnoreCase(*, null, *)          = -1
	 * StringUtils.indexOfIgnoreCase("", "", 0)           = 0
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "A", 0)  = 0
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 0)  = 2
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 0) = 1
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 3)  = 5
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 9)  = -1
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "B", -1) = 2
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "", 2)   = 2
	 * StringUtils.indexOfIgnoreCase("abc", "", 9)        = 3
	 * </pre>
	 * 
	 * @param str 待查找的字符序列，可以为null
	 * @param searchStr 要查找的字符序列
	 * @param startPos 开始位置，负数将当作0处理
	 * @return 第一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-5 下午6:59:27
	 */
	public static int indexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
		return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr, startPos);
	}

	/**
	 * <p>
	 * 在字符串 seq 中查找最后一次出现字符 searchChar 的位置(从后往前第一次出现的位置)
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.lastIndexOf(null, *)         = -1
	 * StringUtils.lastIndexOf("", *)           = -1
	 * StringUtils.lastIndexOf("aabaabaa", 'a') = 7
	 * StringUtils.lastIndexOf("aabaabaa", 'b') = 5
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchChar 要查找的字符
	 * @return 最后一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-5 下午7:07:37
	 */
	public static int lastIndexOf(CharSequence seq, int searchChar) {
		return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchChar);
	}

	/**
	 * <p>
	 * 返回字符 searchChar 从 startPos 开始在字符串 seq 中最后一次出现的位置(从后往前第一次出现的位置)
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.lastIndexOf(null, *, *)          = -1
	 * StringUtils.lastIndexOf("", *,  *)           = -1
	 * StringUtils.lastIndexOf("aabaabaa", 'b', 8)  = 5
	 * StringUtils.lastIndexOf("aabaabaa", 'b', 4)  = 2
	 * StringUtils.lastIndexOf("aabaabaa", 'b', 0)  = -1
	 * StringUtils.lastIndexOf("aabaabaa", 'b', 9)  = 5
	 * StringUtils.lastIndexOf("aabaabaa", 'b', -1) = -1
	 * StringUtils.lastIndexOf("aabaabaa", 'a', 0)  = 0
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchChar 要查找的字符
	 * @param startPos 开始位置，负数将当作0处理; 大于待查找的字符串的长度时将查找整个字符串
	 * @return 最后一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-5 下午7:18:14
	 */
	public static int lastIndexOf(CharSequence seq, int searchChar, int startPos) {
		return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchChar, startPos);
	}

	/**
	 * <p>
	 * 在字符串 seq 中查找最后一次出现字符串 searchChar 的位置(从后往前第一次出现的位置)
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.lastIndexOf(null, *)          = -1
	 * StringUtils.lastIndexOf(*, null)          = -1
	 * StringUtils.lastIndexOf("", "")           = 0
	 * StringUtils.lastIndexOf("aabaabaa", "a")  = 7
	 * StringUtils.lastIndexOf("aabaabaa", "b")  = 5
	 * StringUtils.lastIndexOf("aabaabaa", "ab") = 4
	 * StringUtils.lastIndexOf("aabaabaa", "")   = 8
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchSeq 要查找的字符串
	 * @return 最后一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-5 下午7:44:37
	 */
	public static int lastIndexOf(CharSequence seq, CharSequence searchSeq) {
		return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchSeq);
	}

	/**
	 * <p>
	 * 从后往前查找，返回字符串 searchStr 在字符串 str 中第 ordinal 次出现的位置
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.lastOrdinalIndexOf(null, *, *)          = -1
	 * StringUtils.lastOrdinalIndexOf(*, null, *)          = -1
	 * StringUtils.lastOrdinalIndexOf("", "", *)           = 0
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 1)  = 7
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 2)  = 6
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 1)  = 5
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 2)  = 2
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 1) = 4
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 2) = 1
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "", 1)   = 8
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "", 2)   = 8
	 * </pre>
	 * 
	 * @param str 待查找的字符序列，可以为null
	 * @param searchStr 要查找的字符串
	 * @param ordinal 开始位置，负数将当作0处理; 大于待查找的字符串的长度时将查找整个字符串
	 * @return 最后ordina次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-5 下午7:18:14
	 */
	public static int lastOrdinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
		return org.apache.commons.lang3.StringUtils.lastOrdinalIndexOf(str, searchStr, ordinal);
	}

	/**
	 * <p>
	 * 返回字符串 searchChar 从 startPos 开始在字符串 seq 中最后一次出现的位置(从后往前第一次出现的位置)
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.lastIndexOf(null, *, *)          = -1
	 * StringUtils.lastIndexOf(*, null, *)          = -1
	 * StringUtils.lastIndexOf("aabaabaa", "a", 8)  = 7
	 * StringUtils.lastIndexOf("aabaabaa", "b", 8)  = 5
	 * StringUtils.lastIndexOf("aabaabaa", "ab", 8) = 4
	 * StringUtils.lastIndexOf("aabaabaa", "b", 9)  = 5
	 * StringUtils.lastIndexOf("aabaabaa", "b", -1) = -1
	 * StringUtils.lastIndexOf("aabaabaa", "a", 0)  = 0
	 * StringUtils.lastIndexOf("aabaabaa", "b", 0)  = -1
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchSeq 要查找的字符串
	 * @param startPos 开始位置，负数将当作0处理; 大于待查找的字符串的长度时将查找整个字符串
	 * @return 最后一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @author Kevice
	 * @time 2013-4-5 下午8:04:15
	 */
	public static int lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
		return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchSeq, startPos);
	}

	/**
	 * <p>
	 * 返回字符 searchChar 在字符串 str 中最后一次出现的位置，大小不敏感
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.lastIndexOfIgnoreCase(null, *)          = -1
	 * StringUtils.lastIndexOfIgnoreCase(*, null)          = -1
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A")  = 7
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B")  = 5
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB") = 4
	 * </pre>
	 * 
	 * @param str 待查找的字符序列，可以为null
	 * @param searchStr 要查找的字符序列，可以为null
	 * @return 最后一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-5 下午8:29:27
	 */
	public static int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
		return org.apache.commons.lang3.StringUtils.lastIndexOfIgnoreCase(str, searchStr);
	}

	/**
	 * <p>
	 * 返回字符 searchChar 在字符串 str 中从 startPos 开始最后一次出现的位置(从后往前第一次出现的位置)，大小不敏感
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.lastIndexOfIgnoreCase(null, *, *)          = -1
	 * StringUtils.lastIndexOfIgnoreCase(*, null, *)          = -1
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 8)  = 7
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 8)  = 5
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB", 8) = 4
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 9)  = 5
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", -1) = -1
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 0)  = 0
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 0)  = -1
	 * </pre>
	 * 
	 * @param str 待查找的字符序列，可以为null
	 * @param searchStr 要查找的字符序列，可以为null
	 * @param startPos 开始位置，负数将当作0处理; 大于待查找的字符串的长度时将查找整个字符串
	 * @return 最后一次出现的位置，如果没有找到或输入seq为null，将返回-1
	 * @author Kevice
	 * @time 2013-4-5 下午8:40:25
	 */
	public static int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
		return org.apache.commons.lang3.StringUtils.lastIndexOfIgnoreCase(str, searchStr, startPos);
	}

	// Contains
	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 检查字符串 seq 是否包含字符 searchChar
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.contains(null, *)    = false
	 * StringUtils.contains("", *)      = false
	 * StringUtils.contains("abc", 'a') = true
	 * StringUtils.contains("abc", 'z') = false
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchChar 要查找的字符
	 * @return true：如果字符串 seq 包含字符 searchChar, false： 如果不包含或字符串 seq 为null或空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-5 下午8:53:03
	 */
	public static boolean contains(CharSequence seq, int searchChar) {
		return org.apache.commons.lang3.StringUtils.contains(seq, searchChar);
	}

	/**
	 * <p>
	 * 检查字符串 seq 是否包含字符串 searchSeq
	 * </p>
	 * 
	 * <p>
	 * A {@code null} CharSequence will return {@code false}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.contains(null, *)     = false
	 * StringUtils.contains(*, null)     = false
	 * StringUtils.contains("", "")      = true
	 * StringUtils.contains("abc", "")   = true
	 * StringUtils.contains("abc", "a")  = true
	 * StringUtils.contains("abc", "z")  = false
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchSeq 要查找的字符串, 可以为null
	 * @return true：如果字符串 seq 包含字符串 searchSeq, false： 如果不包含或字符串 seq 为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午6:17:06
	 */
	public static boolean contains(CharSequence seq, CharSequence searchSeq) {
		return org.apache.commons.lang3.StringUtils.contains(seq, searchSeq);
	}

	/**
	 * <p>
	 * 检查字符串 str 是否包含字符串 searchStr，忽略大小写
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.contains(null, *) = false
	 * StringUtils.contains(*, null) = false
	 * StringUtils.contains("", "") = true
	 * StringUtils.contains("abc", "") = true
	 * StringUtils.contains("abc", "a") = true
	 * StringUtils.contains("abc", "z") = false
	 * StringUtils.contains("abc", "A") = true
	 * StringUtils.contains("abc", "Z") = false
	 * </pre>
	 * 
	 * @param str 待查找的字符序列，可以为null
	 * @param searchStr 要查找的字符串, 可以为null
	 * @return true：如果字符串 str 包含(忽略大小写)字符串 searchStr, false： 如果不包含或字符串 str 为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午6:19:30
	 */
	public static boolean containsIgnoreCase(CharSequence str, CharSequence searchStr) {
		return org.apache.commons.lang3.StringUtils.containsIgnoreCase(str, searchStr);
	}

	/**
	 * <p>
	 * 检查给定是字符串是否包含任何空白字符
	 * </p>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @return true：如果字符串非空至少包含1个空白字符, false： 如果不包含或字符串为null或空串
	 * @see java.lang.Character#isWhitespace
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午6:23:16
	 */
	public static boolean containsWhitespace(CharSequence seq) {
		return org.apache.commons.lang3.StringUtils.containsWhitespace(seq);
	}

	// IndexOfAny chars
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 在字符序列 cs 中查找给定的一组字符 searchChars，返回第一次出现任何字符的位置
	 * </p>
	 * 
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.indexOfAny(null, *)                = -1
	 * StringUtils.indexOfAny("", *)                  = -1
	 * StringUtils.indexOfAny(*, null)                = -1
	 * StringUtils.indexOfAny(*, [])                  = -1
	 * StringUtils.indexOfAny("zzabyycdxx",['z','a']) = 0
	 * StringUtils.indexOfAny("zzabyycdxx",['b','y']) = 3
	 * StringUtils.indexOfAny("aba", ['z'])           = -1
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null
	 * @param searchChars 要查找的字符组, 可以为null
	 * @return 任何第一次匹配的字符的下标。如果没有找到或cs、searchChars两者之一为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午6:34:21
	 */
	public static int indexOfAny(CharSequence cs, char... searchChars) {
		return org.apache.commons.lang3.StringUtils.indexOfAny(cs, searchChars);
	}

	/**
	 * <p>
	 * 在字符序列 cs 中查找给定的一组字符 searchChars，返回第一次出现给定的任何字符的位置
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOfAny(null, *)            = -1
	 * StringUtils.indexOfAny("", *)              = -1
	 * StringUtils.indexOfAny(*, null)            = -1
	 * StringUtils.indexOfAny(*, "")              = -1
	 * StringUtils.indexOfAny("zzabyycdxx", "za") = 0
	 * StringUtils.indexOfAny("zzabyycdxx", "by") = 3
	 * StringUtils.indexOfAny("aba","z")          = -1
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null
	 * @param searchChars 要查找的字符组, 可以为null
	 * @return 任何第一次匹配的字符的下标。如果没有找到或cs、searchChars两者之一为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午6:37:07
	 */
	public static int indexOfAny(CharSequence cs, String searchChars) {
		return org.apache.commons.lang3.StringUtils.indexOfAny(cs, searchChars);
	}

	// ContainsAny
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查是否字符序列 cs 包含给定的字符组 searchChars 的任何一个字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.containsAny(null, *)                = false
	 * StringUtils.containsAny("", *)                  = false
	 * StringUtils.containsAny(*, null)                = false
	 * StringUtils.containsAny(*, [])                  = false
	 * StringUtils.containsAny("zzabyycdxx",['z','a']) = true
	 * StringUtils.containsAny("zzabyycdxx",['b','y']) = true
	 * StringUtils.containsAny("aba", ['z'])           = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null
	 * @param searchChars 要查找的字符组, 可以为null
	 * @return true：任何给定的字符被找到，false：未找到或cs、searchChars两者之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午6:41:09
	 */
	public static boolean containsAny(CharSequence cs, char... searchChars) {
		return org.apache.commons.lang3.StringUtils.containsAny(cs, searchChars);
	}

	/**
	 * <p>
	 * 检查是否字符序列 cs 包含给定的字符组 searchChars 的任何一个字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.containsAny(null, *)            = false
	 * StringUtils.containsAny("", *)              = false
	 * StringUtils.containsAny(*, null)            = false
	 * StringUtils.containsAny(*, "")              = false
	 * StringUtils.containsAny("zzabyycdxx", "za") = true
	 * StringUtils.containsAny("zzabyycdxx", "by") = true
	 * StringUtils.containsAny("aba","z")          = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null
	 * @param searchChars 要查找的字符组, 可以为null
	 * @return true：任何给定的字符被找到，false：未找到或cs、searchChars两者之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午6:43:44
	 */
	public static boolean containsAny(CharSequence cs, CharSequence searchChars) {
		return org.apache.commons.lang3.StringUtils.containsAny(cs, searchChars);
	}

	// IndexOfAnyBut chars
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 在字符序列 cs 中查找给定的一组字符 searchChars，返回第一次不出现给定的任何字符的位置
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOfAnyBut(null, *)                              = -1
	 * StringUtils.indexOfAnyBut("", *)                                = -1
	 * StringUtils.indexOfAnyBut(*, null)                              = -1
	 * StringUtils.indexOfAnyBut(*, [])                                = -1
	 * StringUtils.indexOfAnyBut("zzabyycdxx", new char[] {'z', 'a'} ) = 3
	 * StringUtils.indexOfAnyBut("aba", new char[] {'z'} )             = 0
	 * StringUtils.indexOfAnyBut("aba", new char[] {'a', 'b'} )        = -1
	 * 
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null
	 * @param searchChars 要查找的字符组, 可以为null
	 * @return 任何第一次不匹配的字符的下标。如果没有找到或cs、searchChars两者之一为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午6:48:25
	 */
	public static int indexOfAnyBut(CharSequence cs, char... searchChars) {
		return org.apache.commons.lang3.StringUtils.indexOfAnyBut(cs, searchChars);
	}

	/**
	 * <p>
	 * 在字符序列 seq 中查找给定的一组字符 searchChars，返回第一次不出现给定的任何字符的位置
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOfAnyBut(null, *)            = -1
	 * StringUtils.indexOfAnyBut("", *)              = -1
	 * StringUtils.indexOfAnyBut(*, null)            = -1
	 * StringUtils.indexOfAnyBut(*, "")              = -1
	 * StringUtils.indexOfAnyBut("zzabyycdxx", "za") = 3
	 * StringUtils.indexOfAnyBut("zzabyycdxx", "")   = -1
	 * StringUtils.indexOfAnyBut("aba","ab")         = -1
	 * </pre>
	 * 
	 * @param seq 待查找的字符序列，可以为null
	 * @param searchChars 要查找的字符组, 可以为null
	 * @return 任何第一次不匹配的字符的下标。如果没有找到或cs、searchChars两者之一为null，将返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午6:49:25
	 */
	public static int indexOfAnyBut(CharSequence seq, CharSequence searchChars) {
		return org.apache.commons.lang3.StringUtils.indexOfAnyBut(seq, searchChars);
	}

	// ContainsOnly
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查字符序列 cs 是否只由 valid 中的字符组成
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.containsOnly(null, *)       = false
	 * StringUtils.containsOnly(*, null)       = false
	 * StringUtils.containsOnly("", *)         = true
	 * StringUtils.containsOnly("ab", '')      = false
	 * StringUtils.containsOnly("abab", 'abc') = true
	 * StringUtils.containsOnly("ab1", 'abc')  = false
	 * StringUtils.containsOnly("abz", 'abc')  = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null
	 * @param valid 有效的字符组, 可以为null
	 * @return true: 如果cs只由valid中的字符组成或cs为空串， false: cs包含其他字符或cs为null或valid为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午7:02:27
	 */
	public static boolean containsOnly(CharSequence cs, char... valid) {
		return org.apache.commons.lang3.StringUtils.containsOnly(cs, valid);
	}

	/**
	 * <p>
	 * 检查字符序列 cs 是否只由 validChars 中的字符组成
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.containsOnly(null, *)       = false
	 * StringUtils.containsOnly(*, null)       = false
	 * StringUtils.containsOnly("", *)         = true
	 * StringUtils.containsOnly("ab", "")      = false
	 * StringUtils.containsOnly("abab", "abc") = true
	 * StringUtils.containsOnly("ab1", "abc")  = false
	 * StringUtils.containsOnly("abz", "abc")  = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null
	 * @param validChars 有效的字符组, 可以为null
	 * @return true: 如果cs只由validChars中的字符组成或cs为空串， false:
	 *         cs包含其他字符或cs为null或validChars为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午7:05:08
	 */
	public static boolean containsOnly(CharSequence cs, String validChars) {
		return org.apache.commons.lang3.StringUtils.containsOnly(cs, validChars);
	}

	// ContainsNone
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查字符序列 cs 是否都不由 searchChars 中的字符组成
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.containsNone(null, *)       = true
	 * StringUtils.containsNone(*, null)       = true
	 * StringUtils.containsNone("", *)         = true
	 * StringUtils.containsNone("ab", '')      = true
	 * StringUtils.containsNone("abab", 'xyz') = true
	 * StringUtils.containsNone("ab1", 'xyz')  = true
	 * StringUtils.containsNone("abz", 'xyz')  = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null
	 * @param searchChars 无效的字符组, 可以为null
	 * @return true: 如果cs只都不由searchChars中的字符组成或cs为null或cs为空串或searchChars为null，
	 *         false: cs包含searchChars中的任何字符
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午7:08:58
	 */
	public static boolean containsNone(CharSequence cs, char... searchChars) {
		return org.apache.commons.lang3.StringUtils.containsNone(cs, searchChars);
	}

	/**
	 * <p>
	 * 检查字符序列 cs 是否都不由字符串 invalidChars 中的字符组成
	 * </p>
	 * 
	 * <p>
	 * A {@code null} CharSequence will return {@code true}. A {@code null}
	 * invalid character array will return {@code true}. An empty String ("")
	 * always returns true.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.containsNone(null, *)       = true
	 * StringUtils.containsNone(*, null)       = true
	 * StringUtils.containsNone("", *)         = true
	 * StringUtils.containsNone("ab", "")      = true
	 * StringUtils.containsNone("abab", "xyz") = true
	 * StringUtils.containsNone("ab1", "xyz")  = true
	 * StringUtils.containsNone("abz", "xyz")  = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null
	 * @param invalidChars 无效的字符组, 可以为null
	 * @return true: 如果cs只都不由searchChars中的字符组成或cs为null或cs为空串或searchChars为null，
	 *         false: cs包含searchChars中的任何字符
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午7:10:58
	 */
	public static boolean containsNone(CharSequence cs, String invalidChars) {
		return org.apache.commons.lang3.StringUtils.containsNone(cs, invalidChars);
	}

	// IndexOfAny strings
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 在字符序列 str 中查找给定的一组字符串 searchStrs，返回第一次出现任何字符串的位置
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOfAny(null, *)                     = -1
	 * StringUtils.indexOfAny(*, null)                     = -1
	 * StringUtils.indexOfAny(*, [])                       = -1
	 * StringUtils.indexOfAny("zzabyycdxx", ["ab","cd"])   = 2
	 * StringUtils.indexOfAny("zzabyycdxx", ["cd","ab"])   = 2
	 * StringUtils.indexOfAny("zzabyycdxx", ["mn","op"])   = -1
	 * StringUtils.indexOfAny("zzabyycdxx", ["zab","aby"]) = 1
	 * StringUtils.indexOfAny("zzabyycdxx", [""])          = 0
	 * StringUtils.indexOfAny("", [""])                    = 0
	 * StringUtils.indexOfAny("", ["a"])                   = -1
	 * </pre>
	 * 
	 * @param str 待查找的字符序列，可以为null
	 * @param searchStrs 要查找的字符串组, 可以为null
	 * @return 任何第一次匹配的字符串的下标。如果没有找到或cs为null、searchChars为null或空数组，将返回-1。
	 *         searchStrs如果有空串的元素，将返回0
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午7:17:42
	 */
	public static int indexOfAny(CharSequence str, CharSequence... searchStrs) {
		return org.apache.commons.lang3.StringUtils.indexOfAny(str, searchStrs);
	}

	/**
	 * <p>
	 * 在字符序列 str 中从后往前查找给定的一组字符串 searchStrs，返回第一次出现任何字符串的位置
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.lastIndexOfAny(null, *)                   = -1
	 * StringUtils.lastIndexOfAny(*, null)                   = -1
	 * StringUtils.lastIndexOfAny(*, [])                     = -1
	 * StringUtils.lastIndexOfAny(*, [null])                 = -1
	 * StringUtils.lastIndexOfAny("zzabyycdxx", ["ab","cd"]) = 6
	 * StringUtils.lastIndexOfAny("zzabyycdxx", ["cd","ab"]) = 6
	 * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
	 * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
	 * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn",""])   = 10
	 * </pre>
	 * 
	 * @param str 待查找的字符序列，可以为null
	 * @param searchStrs 要查找的字符串组, 可以为null
	 * @return 从后往前找任何第一次匹配的字符串的下标。如果没有找到或cs为null、searchChars为null或空数组，将返回-1。
	 *         searchStrs如果有空串的元素，将返回0
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午7:19:46
	 */
	public static int lastIndexOfAny(CharSequence str, CharSequence... searchStrs) {
		return org.apache.commons.lang3.StringUtils.lastIndexOfAny(str, searchStrs);
	}

	// Substring
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 从字符串 str 的指定位置 start 开始获取子串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substring(null, *)   = null
	 * StringUtils.substring("", *)     = ""
	 * StringUtils.substring("abc", 0)  = "abc"
	 * StringUtils.substring("abc", 2)  = "c"
	 * StringUtils.substring("abc", 4)  = ""
	 * StringUtils.substring("abc", -2) = "bc"
	 * StringUtils.substring("abc", -4) = "abc"
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param start 开始位置，负数表示从后往前数
	 * @return 从开始位置开始的子串。str为null将返回null, str为空串将返回空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午7:52:58
	 */
	public static String substring(String str, int start) {
		return org.apache.commons.lang3.StringUtils.substring(str, start);
	}

	/**
	 * <p>
	 * 从字符串 str 获取子串，开始于位置 start 并结束于位置 end
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substring(null, *, *)    = null
	 * StringUtils.substring("", * ,  *)    = "";
	 * StringUtils.substring("abc", 0, 2)   = "ab"
	 * StringUtils.substring("abc", 2, 0)   = ""
	 * StringUtils.substring("abc", 2, 4)   = "c"
	 * StringUtils.substring("abc", 4, 6)   = ""
	 * StringUtils.substring("abc", 2, 2)   = ""
	 * StringUtils.substring("abc", -2, -1) = "b"
	 * StringUtils.substring("abc", -4, 2)  = "ab"
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param start 开始位置，负数表示从后往前数
	 * @param end 结束位置，负数表示从后往前数
	 * @return 从开始位置开始到结束位置的子串。str为null将返回null, str为空串或start<=end将返回空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:03:59
	 */
	public static String substring(String str, int start, int end) {
		return org.apache.commons.lang3.StringUtils.substring(str, start, end);
	}

	// Left/Right/Mid
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回字符串 str 最左边的 len 个字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.left(null, *)    = null
	 * StringUtils.left(*, -ve)     = ""
	 * StringUtils.left("", *)      = ""
	 * StringUtils.left("abc", 0)   = ""
	 * StringUtils.left("abc", 2)   = "ab"
	 * StringUtils.left("abc", 4)   = "abc"
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param len 子串的长度
	 * @return 最左边的字符串, str为null将返回null, str为空串或len为负数将返回空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:05:59
	 */
	public static String left(String str, int len) {
		return org.apache.commons.lang3.StringUtils.leftPad(str, len);
	}

	/**
	 * <p>
	 * 返回字符串 str 最右边的 len 个字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.right(null, *)    = null
	 * StringUtils.right(*, -ve)     = ""
	 * StringUtils.right("", *)      = ""
	 * StringUtils.right("abc", 0)   = ""
	 * StringUtils.right("abc", 2)   = "bc"
	 * StringUtils.right("abc", 4)   = "abc"
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param len 子串的长度
	 * @return 最左边的字符串, str为null将返回null, str为空串或len为负数将返回空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:06:59
	 */
	public static String right(String str, int len) {
		return org.apache.commons.lang3.StringUtils.right(str, len);
	}

	/**
	 * <p>
	 * 返回字符串 str 从 pos 位置开始的 len 个字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.mid(null, *, *)    = null
	 * StringUtils.mid(*, *, -ve)     = ""
	 * StringUtils.mid("", 0, *)      = ""
	 * StringUtils.mid("abc", 0, 2)   = "ab"
	 * StringUtils.mid("abc", 0, 4)   = "abc"
	 * StringUtils.mid("abc", 2, 4)   = "c"
	 * StringUtils.mid("abc", 4, 2)   = ""
	 * StringUtils.mid("abc", -2, 2)  = "ab"
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param pos 开始位置, 负数将被当作0
	 * @param len 子串的长度
	 * @return 从 pos 位置开始的 len 个字符, str为null将返回null, str为空串或len为负数将返回空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:20:02
	 */
	public static String mid(String str, int pos, int len) {
		return org.apache.commons.lang3.StringUtils.mid(str, pos, len);
	}

	// SubStringAfter/SubStringBefore
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 从字符串 str 中获取第一次出现字符串 separator 前的子串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringBefore(null, *)      = null
	 * StringUtils.substringBefore("", *)        = ""
	 * StringUtils.substringBefore("abc", "a")   = ""
	 * StringUtils.substringBefore("abcba", "b") = "a"
	 * StringUtils.substringBefore("abc", "c")   = "ab"
	 * StringUtils.substringBefore("abc", "d")   = "abc"
	 * StringUtils.substringBefore("abc", "")    = ""
	 * StringUtils.substringBefore("abc", null)  = "abc"
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param separator 分隔串, 可以为null
	 * @return 第一次出现字符串 separator 前的子串。str为null将返回null，
	 *         str或separator为空串将返回空串，separator为null或未找到将返回str
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:21:02
	 */
	public static String substringBefore(String str, String separator) {
		return org.apache.commons.lang3.StringUtils.substringBefore(str, separator);
	}

	/**
	 * <p>
	 * 从字符串 str 中获取第一次出现字符串 separator 后的子串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringAfter(null, *)      = null
	 * StringUtils.substringAfter("", *)        = ""
	 * StringUtils.substringAfter(*, null)      = ""
	 * StringUtils.substringAfter("abc", "a")   = "bc"
	 * StringUtils.substringAfter("abcba", "b") = "cba"
	 * StringUtils.substringAfter("abc", "c")   = ""
	 * StringUtils.substringAfter("abc", "d")   = ""
	 * StringUtils.substringAfter("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param separator 分隔串, 可以为null
	 * @return 第一次出现字符串 separator 后的子串。str为null将返回null，
	 *         str或separator为空串或未找到将返回空串，separator为null将返回str
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:21:02
	 */
	public static String substringAfter(String str, String separator) {
		return org.apache.commons.lang3.StringUtils.substringAfter(str, separator);
	}

	/**
	 * <p>
	 * 从字符串 str 中获取最后一次(从后往前找第一次)出现字符串 separator 前的子串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringBeforeLast(null, *)      = null
	 * StringUtils.substringBeforeLast("", *)        = ""
	 * StringUtils.substringBeforeLast("abcba", "b") = "abc"
	 * StringUtils.substringBeforeLast("abc", "c")   = "ab"
	 * StringUtils.substringBeforeLast("a", "a")     = ""
	 * StringUtils.substringBeforeLast("a", "z")     = "a"
	 * StringUtils.substringBeforeLast("a", null)    = "a"
	 * StringUtils.substringBeforeLast("a", "")      = "a"
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param separator 分隔串, 可以为null
	 * @return 最后一次出现字符串 separator 前的子串。str为null将返回null，
	 *         str或separator为空串将返回空串，separator为null或未找到将返回str
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:25:04
	 */
	public static String substringBeforeLast(String str, String separator) {
		return org.apache.commons.lang3.StringUtils.substringBeforeLast(str, separator);
	}

	/**
	 * <p>
	 * 从字符串 str 中获取最后一次(从后往前找第一次)出现字符串 separator 后的子串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringAfterLast(null, *)      = null
	 * StringUtils.substringAfterLast("", *)        = ""
	 * StringUtils.substringAfterLast(*, "")        = ""
	 * StringUtils.substringAfterLast(*, null)      = ""
	 * StringUtils.substringAfterLast("abc", "a")   = "bc"
	 * StringUtils.substringAfterLast("abcba", "b") = "a"
	 * StringUtils.substringAfterLast("abc", "c")   = ""
	 * StringUtils.substringAfterLast("a", "a")     = ""
	 * StringUtils.substringAfterLast("a", "z")     = ""
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param separator 分隔串, 可以为null
	 * @return 最后一次出现字符串 separator 后的子串。str为null将返回null，
	 *         str或separator为空串或未找到将返回空串，separator为null将返回str
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:28:04
	 */
	public static String substringAfterLast(String str, String separator) {
		return org.apache.commons.lang3.StringUtils.substringAfterLast(str, separator);
	}

	// Substring between
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 从字符串 str 中获取嵌在两个相同字符串 tag 中间的子串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringBetween(null, *)            = null
	 * StringUtils.substringBetween("", "")             = ""
	 * StringUtils.substringBetween("", "tag")          = null
	 * StringUtils.substringBetween("tagabctag", null)  = null
	 * StringUtils.substringBetween("tagabctag", "")    = ""
	 * StringUtils.substringBetween("tagabctag", "tag") = "abc"
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param tag 子串前后的字符串, 可以为null
	 * @return 子串, 未找到或str为null或tag为null都将返回null，tag为空串将返回空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:50:04
	 */
	public static String substringBetween(String str, String tag) {
		return org.apache.commons.lang3.StringUtils.substringBetween(str, tag);
	}

	/**
	 * <p>
	 * 从字符串 str 中获取嵌在字符串 open 和 close 中间的子串，返回第一次匹配的结果
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringBetween("wx[b]yz", "[", "]") = "b"
	 * StringUtils.substringBetween(null, *, *)          = null
	 * StringUtils.substringBetween(*, null, *)          = null
	 * StringUtils.substringBetween(*, *, null)          = null
	 * StringUtils.substringBetween("", "", "")          = ""
	 * StringUtils.substringBetween("", "", "]")         = null
	 * StringUtils.substringBetween("", "[", "]")        = null
	 * StringUtils.substringBetween("yabcz", "", "")     = ""
	 * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
	 * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param open 子串前的字符串, 可以为null
	 * @param close 子串后的字符串, 可以为null
	 * @return 子串, 未找到或str为null或open/close为null都将返回null，open/close为空串将返回空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:51:04
	 */
	public static String substringBetween(String str, String open, String close) {
		return org.apache.commons.lang3.StringUtils.substringBetween(str, open, close);
	}

	/**
	 * <p>
	 * 从字符串 str 中获取嵌在字符串 open 和 close 中间的子串，返回全部匹配结果
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringsBetween("[a][b][c]", "[", "]") = ["a","b","c"]
	 * StringUtils.substringsBetween(null, *, *)            = null
	 * StringUtils.substringsBetween(*, null, *)            = null
	 * StringUtils.substringsBetween(*, *, null)            = null
	 * StringUtils.substringsBetween("", "[", "]")          = []
	 * </pre>
	 * 
	 * @param str 主串, null返回null, 空串返回空串
	 * @param open 标识子串开始的字符串, 空串返回null
	 * @param close 标识子串结束的字符串, 空串返回null
	 * @return 子串数组, 未找到返回null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午8:52:04
	 */
	public static String[] substringsBetween(String str, String open, String close) {
		return org.apache.commons.lang3.StringUtils.substringsBetween(str, open, close);
	}

	// Nested extraction
	// -----------------------------------------------------------------------

	// Splitting
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 用空白符分隔给定的字符串，相邻的分隔符将被当作一个分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.split(null)       = null
	 * StringUtils.split("")         = []
	 * StringUtils.split("abc def")  = ["abc", "def"]
	 * StringUtils.split("abc  def") = ["abc", "def"]
	 * StringUtils.split(" abc ")    = ["abc"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，null返回null，空串返回空数组
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午9:02:04
	 */
	public static String[] split(String str) {
		return org.apache.commons.lang3.StringUtils.split(str);
	}

	/**
	 * <p>
	 * 用指定的字符分隔给定的字符串，相邻的分隔符当作一个分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.split(null, *)         = null
	 * StringUtils.split("", *)           = []
	 * StringUtils.split("a.b.c", '.')    = ["a", "b", "c"]
	 * StringUtils.split("a..b.c", '.')   = ["a", "b", "c"]
	 * StringUtils.split("a:b:c", '.')    = ["a:b:c"]
	 * StringUtils.split("a b c", ' ')    = ["a", "b", "c"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，null返回null，空串返回空数组
	 * @param separatorChar 分隔符
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午9:02:04
	 */
	public static String[] split(String str, char separatorChar) {
		return org.apache.commons.lang3.StringUtils.split(str, separatorChar);
	}

	/**
	 * <p>
	 * 用指定的字符串中的字符(任意排列组合)分隔给定的字符串，相邻的分隔符当作一个分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.split(null, *)         = null
	 * StringUtils.split("", *)           = []
	 * StringUtils.split("abc def", null) = ["abc", "def"]
	 * StringUtils.split("abc def", " ")  = ["abc", "def"]
	 * StringUtils.split("abc  def", " ") = ["abc", "def"]
	 * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
	 * StringUtils.split("ab-!-cd-!ef", "!-") = ["ab", "cd", "ef"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，null返回null，空串返回空数组
	 * @param separatorChars 分隔符串，为null将当作空白符
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午9:09:04
	 */
	public static String[] split(String str, String separatorChars) {
		return org.apache.commons.lang3.StringUtils.split(str, separatorChars);
	}

	/**
	 * <p>
	 * 用指定的字符串中的字符(任意排列组合)分隔给定的字符串，限制返回的结果数，相邻的分隔符当作一个分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.split(null, *, *)            = null
	 * StringUtils.split("", *, *)              = []
	 * StringUtils.split("ab de fg", null, 0)   = ["ab", "cd", "ef"]
	 * StringUtils.split("ab   de fg", null, 0) = ["ab", "cd", "ef"]
	 * StringUtils.split("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
	 * StringUtils.split("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，null返回null，空串返回空数组
	 * @param separatorChars 分隔符串，为null将当作空白符
	 * @param max 最大结果数。0或负数表示不受限，实际结果如果大于该参数，结果将返回最后的max个元素
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午9:16:04
	 */
	public static String[] split(String str, String separatorChars, int max) {
		return org.apache.commons.lang3.StringUtils.split(str, separatorChars, max);
	}

	/**
	 * <p>
	 * 用指定的字符串(当作一个不可分的整体)分隔给定的字符串，相邻的分隔串当作一个分隔串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.splitByWholeSeparator(null, *)               = null
	 * StringUtils.splitByWholeSeparator("", *)                 = []
	 * StringUtils.splitByWholeSeparator("ab de fg", null)      = ["ab", "de", "fg"]
	 * StringUtils.splitByWholeSeparator("ab   de fg", null)    = ["ab", "de", "fg"]
	 * StringUtils.splitByWholeSeparator("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
	 * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，null返回null，空串返回空数组
	 * @param separator 分隔串，为null将当作空白符
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午9:19:04
	 */
	public static String[] splitByWholeSeparator(String str, String separator) {
		return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator);
	}

	/**
	 * <p>
	 * 用指定的字符串(当作一个不可分的整体)分隔给定的字符串，限制返回的结果数，相邻的分隔串当作一个分隔串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.splitByWholeSeparator(null, *, *)               = null
	 * StringUtils.splitByWholeSeparator("", *, *)                 = []
	 * StringUtils.splitByWholeSeparator("ab de fg", null, 0)      = ["ab", "de", "fg"]
	 * StringUtils.splitByWholeSeparator("ab   de fg", null, 0)    = ["ab", "de", "fg"]
	 * StringUtils.splitByWholeSeparator("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
	 * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
	 * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，null返回null，空串返回空数组
	 * @param separator 分隔串，为null将当作空白符
	 * @param max 最大结果数。0或负数表示不受限，实际结果如果大于该参数，结果将返回最后的max个元素
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午9:40:04
	 */
	public static String[] splitByWholeSeparator(String str, String separator, int max) {
		return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator, max);
	}

	/**
	 * <p>
	 * 用指定的字符串(当作一个不可分的整体)分隔给定的字符串，相邻的分隔串被当作空串的分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens(null, *)               = null
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("", *)                 = []
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab de fg", null)      = ["ab", "de", "fg"]
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null)    = ["ab", "", "", "de", "fg"]
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null
	 * @param separator 分隔串，为null将当作空白符
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午9:49:04
	 */
	public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator) {
		return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator);
	}

	/**
	 * <p>
	 * 用指定的字符串(当作一个不可分的整体)分隔给定的字符串，限制结果数，相邻的分隔串被当作空串的分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens(null, *, *)               = null
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("", *, *)                 = []
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab de fg", null, 0)      = ["ab", "de", "fg"]
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null, 0)    = ["ab", "", "", "de", "fg"]
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
	 * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，为null将返回null
	 * @param separator 分隔串，为null将当作空白符
	 * @param max 最大结果数。0或负数表示不受限，实际结果如果大于该参数，结果将返回最后的max个元素
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午9:51:04
	 */
	public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max) {
		return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator, max);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 用空白符分隔字符串，保留所有符号，包括由相邻分隔符创建的空串，相邻的分隔串被当作空串的分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.splitPreserveAllTokens(null)       = null
	 * StringUtils.splitPreserveAllTokens("")         = []
	 * StringUtils.splitPreserveAllTokens("abc def")  = ["abc", "def"]
	 * StringUtils.splitPreserveAllTokens("abc  def") = ["abc", "", "def"]
	 * StringUtils.splitPreserveAllTokens(" abc ")    = ["", "abc", ""]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，为null将返回null
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午9:58:04
	 */
	public static String[] splitPreserveAllTokens(String str) {
		return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str);
	}

	/**
	 * <p>
	 * 用指定分隔符分隔字符串，保留所有符号，包括由相邻分隔符创建的空串，相邻的分隔串被当作空串的分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.splitPreserveAllTokens(null, *)         = null
	 * StringUtils.splitPreserveAllTokens("", *)           = []
	 * StringUtils.splitPreserveAllTokens("a.b.c", '.')    = ["a", "b", "c"]
	 * StringUtils.splitPreserveAllTokens("a..b.c", '.')   = ["a", "", "b", "c"]
	 * StringUtils.splitPreserveAllTokens("a:b:c", '.')    = ["a:b:c"]
	 * StringUtils.splitPreserveAllTokens("a\tb\nc", null) = ["a", "b", "c"]
	 * StringUtils.splitPreserveAllTokens("a b c", ' ')    = ["a", "b", "c"]
	 * StringUtils.splitPreserveAllTokens("a b c ", ' ')   = ["a", "b", "c", ""]
	 * StringUtils.splitPreserveAllTokens("a b c  ", ' ')   = ["a", "b", "c", "", ""]
	 * StringUtils.splitPreserveAllTokens(" a b c", ' ')   = ["", a", "b", "c"]
	 * StringUtils.splitPreserveAllTokens("  a b c", ' ')  = ["", "", a", "b", "c"]
	 * StringUtils.splitPreserveAllTokens(" a b c ", ' ')  = ["", a", "b", "c", ""]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，为null将返回null
	 * @param separatorChar 分隔符
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午10:00:04
	 */
	public static String[] splitPreserveAllTokens(String str, char separatorChar) {
		return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChar);
	}

	/**
	 * <p>
	 * 用指定分隔串的任意字符分隔字符串，保留所有符号，包括由相邻分隔符创建的空串，相邻的分隔符被当作空串的分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.splitPreserveAllTokens(null, *)           = null
	 * StringUtils.splitPreserveAllTokens("", *)             = []
	 * StringUtils.splitPreserveAllTokens("abc def", null)   = ["abc", "def"]
	 * StringUtils.splitPreserveAllTokens("abc def", " ")    = ["abc", "def"]
	 * StringUtils.splitPreserveAllTokens("abc  def", " ")   = ["abc", "", def"]
	 * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":")   = ["ab", "cd", "ef"]
	 * StringUtils.splitPreserveAllTokens("ab:cd:ef:", ":")  = ["ab", "cd", "ef", ""]
	 * StringUtils.splitPreserveAllTokens("ab:cd:ef::", ":") = ["ab", "cd", "ef", "", ""]
	 * StringUtils.splitPreserveAllTokens("ab::cd:ef", ":")  = ["ab", "", cd", "ef"]
	 * StringUtils.splitPreserveAllTokens(":cd:ef", ":")     = ["", cd", "ef"]
	 * StringUtils.splitPreserveAllTokens("::cd:ef", ":")    = ["", "", cd", "ef"]
	 * StringUtils.splitPreserveAllTokens(":cd:ef:", ":")    = ["", cd", "ef", ""]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，为null将返回null
	 * @param separatorChars 分隔符串，为空或空串将被当作空白符
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午10:06:04
	 */
	public static String[] splitPreserveAllTokens(String str, String separatorChars) {
		return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChars);
	}

	/**
	 * <p>
	 * 用指定分隔串的任意字符分隔字符串，保留所有符号，包括由相邻分隔符创建的空串，相邻的分隔符被当作空串的分隔符，限制结果数
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.splitPreserveAllTokens(null, *, *)            = null
	 * StringUtils.splitPreserveAllTokens("", *, *)              = []
	 * StringUtils.splitPreserveAllTokens("ab de fg", null, 0)   = ["ab", "cd", "ef"]
	 * StringUtils.splitPreserveAllTokens("ab   de fg", null, 0) = ["ab", "cd", "ef"]
	 * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
	 * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
	 * StringUtils.splitPreserveAllTokens("ab   de fg", null, 2) = ["ab", "  de fg"]
	 * StringUtils.splitPreserveAllTokens("ab   de fg", null, 3) = ["ab", "", " de fg"]
	 * StringUtils.splitPreserveAllTokens("ab   de fg", null, 4) = ["ab", "", "", "de fg"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，为null将返回null
	 * @param separatorChars 分隔符串，为空或空串将被当作空白符
	 * @param max 最大结果数。0或负数表示不受限，实际结果如果大于该参数，结果将返回最后的max个元素
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午10:09:24
	 */
	public static String[] splitPreserveAllTokens(String str, String separatorChars, int max) {
		return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChars, max);
	}

	/**
	 * <p>
	 * 根据字符的类型(由{@code java.lang.Character.getType(char)}
	 * 返回)分隔字符串。多个连续的相同类型的字符将被当作同一组返回。
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.splitByCharacterType(null)         = null
	 * StringUtils.splitByCharacterType("")           = []
	 * StringUtils.splitByCharacterType("ab de fg")   = ["ab", " ", "de", " ", "fg"]
	 * StringUtils.splitByCharacterType("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
	 * StringUtils.splitByCharacterType("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
	 * StringUtils.splitByCharacterType("number5")    = ["number", "5"]
	 * StringUtils.splitByCharacterType("fooBar")     = ["foo", "B", "ar"]
	 * StringUtils.splitByCharacterType("foo200Bar")  = ["foo", "200", "B", "ar"]
	 * StringUtils.splitByCharacterType("ASFRules")   = ["ASFR", "ules"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，为null将返回null
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午10:20:24
	 */
	public static String[] splitByCharacterType(String str) {
		return org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase(str);
	}

	/**
	 * <p>
	 * 根据字符的类型(由{@code java.lang.Character.getType(char)}
	 * 返回)分隔字符串。多个连续的相同类型的字符将被当作同一组返回。 以下情况除外： 大写字母紧跟着小写字母(驼峰)，这样，该大写字母将属于小写字母的组
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.splitByCharacterTypeCamelCase(null)         = null
	 * StringUtils.splitByCharacterTypeCamelCase("")           = []
	 * StringUtils.splitByCharacterTypeCamelCase("ab de fg")   = ["ab", " ", "de", " ", "fg"]
	 * StringUtils.splitByCharacterTypeCamelCase("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
	 * StringUtils.splitByCharacterTypeCamelCase("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
	 * StringUtils.splitByCharacterTypeCamelCase("number5")    = ["number", "5"]
	 * StringUtils.splitByCharacterTypeCamelCase("fooBar")     = ["foo", "Bar"]
	 * StringUtils.splitByCharacterTypeCamelCase("foo200Bar")  = ["foo", "200", "Bar"]
	 * StringUtils.splitByCharacterTypeCamelCase("ASFRules")   = ["ASF", "Rules"]
	 * </pre>
	 * 
	 * @param str 主串, 可以为null，为null将返回null
	 * @return 子串数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午10:44:24
	 */
	public static String[] splitByCharacterTypeCamelCase(String str) {
		return org.apache.commons.lang3.StringUtils.splitByCharacterType(str);
	}

	// Joining
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将多个字符串按先后顺序连接在一起
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.join(null)            = null
	 * StringUtils.join([])              = ""
	 * StringUtils.join([null])          = ""
	 * StringUtils.join(["a", "b", "c"]) = "abc"
	 * StringUtils.join([null, "", "a"]) = "a"
	 * </pre>
	 * 
	 * @param <T> 元素的类型
	 * @param elements 要连接的对象组, 可以为null，为null将返回null, 数组元素为null当空串处理
	 * @return 连接后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午10:51:24
	 */
	public static <T> String join(T... elements) {
		return org.apache.commons.lang3.StringUtils.join(elements);
	}

	/**
	 * <p>
	 * 将多个字符串按先后顺序连接在一起，两两被连接的字符串中间插入分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.join(null, *)               = null
	 * StringUtils.join([], *)                 = ""
	 * StringUtils.join([null], *)             = ""
	 * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
	 * StringUtils.join(["a", "b", "c"], null) = "abc"
	 * StringUtils.join([null, "", "a"], ';')  = ";;a"
	 * </pre>
	 * 
	 * @param array 要连接的对象组, 可以为null，为null将返回null, 数组元素为null当空串处理
	 * @param separator 分隔符
	 * @return 连接后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午10:51:24
	 */
	public static String join(Object[] array, char separator) {
		return org.apache.commons.lang3.StringUtils.join(array, separator);
	}

	/**
	 * <p>
	 * 将多个字符串按先后顺序连接在一起，两两被连接的字符串中间插入分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.join(null, ',', *, *)               = null
	 * StringUtils.join([], ',', 1, 1)                 = ""
	 * StringUtils.join([null], ',', 1, 1)             = ""
	 * StringUtils.join(["a", "b", "c"], ';', 1, 3)  = "b;c"
	 * </pre>
	 * 
	 * @param array 要连接的对象组, 可以为null，为null将返回null, 数组元素为null当空串处理
	 * @param separator 分隔符
	 * @param startIndex 开始连接的第一个元素的数组下标，下标越界将报错。
	 * @param endIndex 结束连接的最后一个(不包括)元素的数组下标，下标越界将报错。
	 * @return 连接后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午10:59:24
	 */
	public static String join(Object[] array, char separator, int startIndex, int endIndex) {
		return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
	}

	/**
	 * <p>
	 * 将多个字符串按先后顺序连接在一起，两两被连接的字符串中间插入分隔串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.join(null, *)                = null
	 * StringUtils.join([], *)                  = ""
	 * StringUtils.join([null], *)              = ""
	 * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
	 * StringUtils.join(["a", "b", "c"], null)  = "abc"
	 * StringUtils.join(["a", "b", "c"], "")    = "abc"
	 * StringUtils.join([null, "", "a"], ',')   = ",,a"
	 * </pre>
	 * 
	 * @param array 要连接的对象组, 可以为null，为null将返回null, 数组元素为null当空串处理
	 * @param separator 分隔串, null当空串处理
	 * @return 连接后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午11:10:24
	 */
	public static String join(Object[] array, String separator) {
		return org.apache.commons.lang3.StringUtils.join(array, separator);
	}

	/**
	 * <p>
	 * 将多个字符串按先后顺序连接在一起，两两被连接的字符串中间插入分隔串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.join(null, *)                = null
	 * StringUtils.join([], *)                  = ""
	 * StringUtils.join([null], *)              = ""
	 * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
	 * StringUtils.join(["a", "b", "c"], null)  = "abc"
	 * StringUtils.join(["a", "b", "c"], "")    = "abc"
	 * StringUtils.join([null, "", "a"], ',')   = ",,a"
	 * </pre>
	 * 
	 * @param array 要连接的对象组, 可以为null，为null将返回null, 数组元素为null当空串处理
	 * @param separator 分隔串, null当空串处理
	 * @param startIndex 开始连接的第一个元素的数组下标，下标越界将报错。
	 * @param endIndex 结束连接的最后一个(不包括)元素的数组下标，下标越界将报错。
	 * @return 连接后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午11:12:24
	 */
	public static String join(Object[] array, String separator, int startIndex, int endIndex) {
		return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
	}

	/**
	 * <p>
	 * 将多个字符串(由{@code Iterator}提供)按先后顺序连接在一起，两两被连接的字符串中间插入分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.join(null, *)               = null
	 * StringUtils.join([], *)                 = ""
	 * StringUtils.join([null], *)             = ""
	 * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
	 * StringUtils.join(["a", "b", "c"], null) = "abc"
	 * StringUtils.join([null, "", "a"], ';')  = ";;a"
	 * </pre>
	 * 
	 * @param iterator 要连接的对象迭代器, 可以为null，为null将返回null, 元素为null当空串处理
	 * @param separator 分隔符
	 * @return 连接后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午11:15:24
	 */
	public static String join(Iterator<?> iterator, char separator) {
		return org.apache.commons.lang3.StringUtils.join(iterator, separator);
	}

	/**
	 * <p>
	 * 将多个字符串(由{@code Iterator}提供)按先后顺序连接在一起，两两被连接的字符串中间插入分隔串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.join(null, *)                = null
	 * StringUtils.join([], *)                  = ""
	 * StringUtils.join([null], *)              = ""
	 * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
	 * StringUtils.join(["a", "b", "c"], null)  = "abc"
	 * StringUtils.join(["a", "b", "c"], "")    = "abc"
	 * StringUtils.join([null, "", "a"], ',')   = ",,a"
	 * </pre>
	 * 
	 * @param iterator 要连接的对象迭代器, 可以为null，为null将返回null, 元素为null当空串处理
	 * @param separator 分隔串, null当空串处理
	 * @return 连接后的字符串
	 * @author Kevice
	 * @time 2013-4-6 下午11:17:24
	 */
	public static String join(Iterator<?> iterator, String separator) {
		return org.apache.commons.lang3.StringUtils.join(iterator, separator);
	}

	/**
	 * <p>
	 * 将多个字符串(由{@code Iterable}提供)按先后顺序连接在一起，两两被连接的字符串中间插入分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.join(null, *)               = null
	 * StringUtils.join([], *)                 = ""
	 * StringUtils.join([null], *)             = ""
	 * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
	 * StringUtils.join(["a", "b", "c"], null) = "abc"
	 * StringUtils.join([null, "", "a"], ';')  = ";;a"
	 * </pre>
	 * 
	 * @param iterable 要连接的可迭代的对象, 可以为null，为null将返回null, 元素为null当空串处理
	 * @param separator 分隔符
	 * @return 连接后的字符串
	 * @since 2.3
	 * @author Kevice
	 * @time 2013-4-6 下午11:20:24
	 */
	public static String join(Iterable<?> iterable, char separator) {
		return org.apache.commons.lang3.StringUtils.join(iterable, separator);
	}

	/**
	 * <p>
	 * 将多个字符串(由{@code Iterable}提供)按先后顺序连接在一起，两两被连接的字符串中间插入分隔串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.join(null, *)                = null
	 * StringUtils.join([], *)                  = ""
	 * StringUtils.join([null], *)              = ""
	 * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
	 * StringUtils.join(["a", "b", "c"], null)  = "abc"
	 * StringUtils.join(["a", "b", "c"], "")    = "abc"
	 * StringUtils.join([null, "", "a"], ',')   = ",,a"
	 * </pre>
	 * 
	 * @param iterable 要连接的可迭代的对象, 可以为null，为null将返回null, 元素为null当空串处理
	 * @param separator 分隔串, null当空串处理
	 * @return 连接后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-6 下午11:21:24
	 */
	public static String join(Iterable<?> iterable, String separator) {
		return org.apache.commons.lang3.StringUtils.join(iterable, separator);
	}

	// Delete
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 删除所有空白字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.deleteWhitespace(null)         = null
	 * StringUtils.deleteWhitespace("")           = ""
	 * StringUtils.deleteWhitespace("abc")        = "abc"
	 * StringUtils.deleteWhitespace("   ab  c  ") = "abc"
	 * </pre>
	 * 
	 * @param str 要删除空白字符的字符串, 可以为null，为null时返回null
	 * @return 没有空白字符的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午7:23:00
	 */
	public static String deleteWhitespace(String str) {
		return org.apache.commons.lang3.StringUtils.deleteWhitespace(str);
	}

	// Remove
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 如果子串在主串的开头部分，则删除该子串，否则返回源主串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.removeStart(null, *)      = null
	 * StringUtils.removeStart("", *)        = ""
	 * StringUtils.removeStart(*, null)      = *
	 * StringUtils.removeStart("www.domain.com", "www.")   = "domain.com"
	 * StringUtils.removeStart("domain.com", "www.")       = "domain.com"
	 * StringUtils.removeStart("www.domain.com", "domain") = "www.domain.com"
	 * StringUtils.removeStart("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param remove 要删除的子串，可以为null，为null或为空串时返回源字符串
	 * @return 去掉开头部分的子串后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午7:27:00
	 */
	public static String removeStart(String str, String remove) {
		return org.apache.commons.lang3.StringUtils.removeStartIgnoreCase(str, remove);
	}

	/**
	 * <p>
	 * 如果子串在主串的开头部分（大小写不敏感），则删除该子串，否则返回源主串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.removeStartIgnoreCase(null, *)      = null
	 * StringUtils.removeStartIgnoreCase("", *)        = ""
	 * StringUtils.removeStartIgnoreCase(*, null)      = *
	 * StringUtils.removeStartIgnoreCase("www.domain.com", "www.")   = "domain.com"
	 * StringUtils.removeStartIgnoreCase("www.domain.com", "WWW.")   = "domain.com"
	 * StringUtils.removeStartIgnoreCase("domain.com", "www.")       = "domain.com"
	 * StringUtils.removeStartIgnoreCase("www.domain.com", "domain") = "www.domain.com"
	 * StringUtils.removeStartIgnoreCase("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param remove 要删除的子串，可以为null，为null或为空串时返回源字符串
	 * @return 去掉开头部分的子串后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午7:27:00
	 */
	public static String removeStartIgnoreCase(String str, String remove) {
		return org.apache.commons.lang3.StringUtils.removeStart(str, remove);
	}

	/**
	 * <p>
	 * 如果子串在主串的末尾，则删除该子串，否则返回源主串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.removeEnd(null, *)      = null
	 * StringUtils.removeEnd("", *)        = ""
	 * StringUtils.removeEnd(*, null)      = *
	 * StringUtils.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
	 * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
	 * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
	 * StringUtils.removeEnd("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param remove 要删除的子串，可以为null，为null或为空串时返回源字符串
	 * @return 去掉末尾的子串后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午7:34:33
	 */
	public static String removeEnd(String str, String remove) {
		return org.apache.commons.lang3.StringUtils.removeEnd(str, remove);
	}

	/**
	 * <p>
	 * 如果子串在主串的末尾（大小写不敏感），则删除该子串，否则返回源主串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.removeEndIgnoreCase(null, *)      = null
	 * StringUtils.removeEndIgnoreCase("", *)        = ""
	 * StringUtils.removeEndIgnoreCase(*, null)      = *
	 * StringUtils.removeEndIgnoreCase("www.domain.com", ".com.")  = "www.domain.com"
	 * StringUtils.removeEndIgnoreCase("www.domain.com", ".com")   = "www.domain"
	 * StringUtils.removeEndIgnoreCase("www.domain.com", "domain") = "www.domain.com"
	 * StringUtils.removeEndIgnoreCase("abc", "")    = "abc"
	 * StringUtils.removeEndIgnoreCase("www.domain.com", ".COM") = "www.domain")
	 * StringUtils.removeEndIgnoreCase("www.domain.COM", ".com") = "www.domain")
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param remove 要删除的子串，可以为null，为null或为空串时返回源字符串
	 * @return 去掉末尾的子串后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午7:35:33
	 */
	public static String removeEndIgnoreCase(String str, String remove) {
		return org.apache.commons.lang3.StringUtils.removeEndIgnoreCase(str, remove);
	}

	/**
	 * <p>
	 * 删除源字符串中所有出现的子串，找不到时返回源字符串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.remove(null, *)        = null
	 * StringUtils.remove("", *)          = ""
	 * StringUtils.remove(*, null)        = *
	 * StringUtils.remove(*, "")          = *
	 * StringUtils.remove("queued", "ue") = "qd"
	 * StringUtils.remove("queued", "zz") = "queued"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param remove 要删除的子串，可以为null，为null或为空串时返回源字符串
	 * @return 去掉所有出现的子串后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午7:38:54
	 */
	public static String remove(String str, String remove) {
		return org.apache.commons.lang3.StringUtils.remove(str, remove);
	}

	/**
	 * <p>
	 * 删除源字符串中所有出现的指定字符，找不到时返回源字符串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.remove(null, *)       = null
	 * StringUtils.remove("", *)         = ""
	 * StringUtils.remove("queued", 'u') = "qeed"
	 * StringUtils.remove("queued", 'z') = "queued"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param remove 要删除的字符
	 * @return 去掉所有出现的指定字符后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午7:40:42
	 */
	public static String remove(String str, char remove) {
		return org.apache.commons.lang3.StringUtils.remove(str, remove);
	}

	// Replacing
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找子串，并用指定字符串替换之, 只替换一次
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.replaceOnce(null, *, *)        = null
	 * StringUtils.replaceOnce("", *, *)          = ""
	 * StringUtils.replaceOnce("any", null, *)    = "any"
	 * StringUtils.replaceOnce("any", *, null)    = "any"
	 * StringUtils.replaceOnce("any", "", *)      = "any"
	 * StringUtils.replaceOnce("aba", "a", null)  = "aba"
	 * StringUtils.replaceOnce("aba", "a", "")    = "ba"
	 * StringUtils.replaceOnce("aba", "a", "z")   = "zba"
	 * </pre>
	 * 
	 * @see #replace(String text, String searchString, String replacement, int
	 *      max)
	 * @param text 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param searchString 要查找的字符串, 可以为null，为null或空串时返回源字符串
	 * @param replacement 用来替换的字符串, 可以为null，为null时返回源字符串
	 * @return 替换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午7:45:51
	 */
	public static String replaceOnce(String text, String searchString, String replacement) {
		return org.apache.commons.lang3.StringUtils.replaceOnce(text, searchString, replacement);
	}

	/**
	 * <p>
	 * 查找子串，并用指定字符串替换所有匹配的地方
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.replace(null, *, *)        = null
	 * StringUtils.replace("", *, *)          = ""
	 * StringUtils.replace("any", null, *)    = "any"
	 * StringUtils.replace("any", *, null)    = "any"
	 * StringUtils.replace("any", "", *)      = "any"
	 * StringUtils.replace("aba", "a", null)  = "aba"
	 * StringUtils.replace("aba", "a", "")    = "b"
	 * StringUtils.replace("aba", "a", "z")   = "zbz"
	 * </pre>
	 * 
	 * @see #replace(String text, String searchString, String replacement, int
	 *      max)
	 * @param text 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param searchString 要查找的字符串, 可以为null，为null或空串时返回源字符串
	 * @param replacement 用来替换的字符串, 可以为null，为null时返回源字符串
	 * @return 替换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午7:48:51
	 */
	public static String replace(String text, String searchString, String replacement) {
		return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement);
	}

	/**
	 * <p>
	 * 查找子串，并用指定字符串替换之, 限定替换次数
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.replace(null, *, *, *)         = null
	 * StringUtils.replace("", *, *, *)           = ""
	 * StringUtils.replace("any", null, *, *)     = "any"
	 * StringUtils.replace("any", *, null, *)     = "any"
	 * StringUtils.replace("any", "", *, *)       = "any"
	 * StringUtils.replace("any", *, *, 0)        = "any"
	 * StringUtils.replace("abaa", "a", null, -1) = "abaa"
	 * StringUtils.replace("abaa", "a", "", -1)   = "b"
	 * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
	 * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
	 * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
	 * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
	 * </pre>
	 * 
	 * @param text 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param searchString 要查找的字符串, 可以为null，为null或空串时返回源字符串
	 * @param replacement 用来替换的字符串, 可以为null，为null时返回源字符串
	 * @param max 最大替换次数
	 * @return 替换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午7:51:19
	 */
	public static String replace(String text, String searchString, String replacement, int max) {
		return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement, max);
	}

	/**
	 * <p>
	 * 查找子串，并用指定字符串替换之（替换所有出现的地方），支持多对替换规则
	 * </p>
	 * 
	 * <pre>
	 *  StringUtils.replaceEach(null, *, *)        = null
	 *  StringUtils.replaceEach("", *, *)          = ""
	 *  StringUtils.replaceEach("aba", null, null) = "aba"
	 *  StringUtils.replaceEach("aba", new String[0], null) = "aba"
	 *  StringUtils.replaceEach("aba", null, new String[0]) = "aba"
	 *  StringUtils.replaceEach("aba", new String[]{"a"}, null)  = "aba"
	 *  StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""})  = "b"
	 *  StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"})  = "aba"
	 *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"})  = "wcte"
	 *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"})  = "dcte"
	 * </pre>
	 * 
	 * @param text 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param searchList 要查找的字符串数组，可以为null，为null时返回源字符串
	 * @param replacementList 用来替换的字符串数组，与查找的数组元素一一对应。可以为null，为null时返回源字符串
	 * @return 替换后的字符串
	 * @throws IllegalArgumentException 如果两个数组的长度不一致时(null或空数组是允许的)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午8:01:31
	 */
	public static String replaceEach(String text, String[] searchList, String[] replacementList) {
		return org.apache.commons.lang3.StringUtils.replaceEach(text, searchList, replacementList);
	}

	/**
	 * <p>
	 * 查找子串，并用指定字符串循环替换之（替换所有出现的地方），支持多对替换规则
	 * </p>
	 * 
	 * <pre>
	 *  StringUtils.replaceEachRepeatedly(null, *, *) = null
	 *  StringUtils.replaceEachRepeatedly("", *, *) = ""
	 *  StringUtils.replaceEachRepeatedly("aba", null, null) = "aba"
	 *  StringUtils.replaceEachRepeatedly("aba", new String[0], null) = "aba"
	 *  StringUtils.replaceEachRepeatedly("aba", null, new String[0]) = "aba"
	 *  StringUtils.replaceEachRepeatedly("aba", new String[]{"a"}, null, *) = "aba"
	 *  StringUtils.replaceEachRepeatedly("aba", new String[]{"a"}, new String[]{""}) = "b"
	 *  StringUtils.replaceEachRepeatedly("aba", new String[]{null}, new String[]{"a"}) = "aba"
	 *  StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}) = "wcte"
	 *  StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}) = "tcte"
	 *  StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"}) = IllegalStateException
	 * </pre>
	 * 
	 * @param text 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param searchList 要查找的字符串数组，可以为null，为null时返回源字符串
	 * @param replacementList 用来替换的字符串数组，与查找的数组元素一一对应。可以为null，为null时返回源字符串
	 * @return 替换后的字符串
	 * @throws IllegalStateException 死循环时
	 * @throws IllegalArgumentException 如果两个数组的长度不一致时(null或空数组是允许的)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午8:16:30
	 */
	public static String replaceEachRepeatedly(String text, String[] searchList, String[] replacementList) {
		return org.apache.commons.lang3.StringUtils.replaceEachRepeatedly(text, searchList, replacementList);
	}

	// Replace, character based
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找所有出现指定字符的地方，并用另一个字符替换所有这些地方
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.replaceChars(null, *, *)        = null
	 * StringUtils.replaceChars("", *, *)          = ""
	 * StringUtils.replaceChars("abcba", 'b', 'y') = "aycya"
	 * StringUtils.replaceChars("abcba", 'z', 'y') = "abcba"
	 * </pre>
	 * 
	 * @param str 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param searchChar 要查找的字符
	 * @param replaceChar 用来替换的字符
	 * @return 替换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午8:19:49
	 */
	public static String replaceChars(String str, char searchChar, char replaceChar) {
		return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChar, replaceChar);
	}

	/**
	 * <p>
	 * 查找所有出现指定字符的地方，并用另一个字符替换所有这些地方，支持多对替换规则
	 * </p>
	 * 
	 * <p>
	 * 正常情况下，要查找的字符数应该与用来替换的字符数相同，这样它们按顺序一一对应。 当其中一者多于另一者时，多的相当于被截短
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.replaceChars(null, *, *)           = null
	 * StringUtils.replaceChars("", *, *)             = ""
	 * StringUtils.replaceChars("abc", null, *)       = "abc"
	 * StringUtils.replaceChars("abc", "", *)         = "abc"
	 * StringUtils.replaceChars("abc", "b", null)     = "ac"
	 * StringUtils.replaceChars("abc", "b", "")       = "ac"
	 * StringUtils.replaceChars("abcba", "bc", "yz")  = "ayzya"
	 * StringUtils.replaceChars("abcba", "bc", "y")   = "ayya"
	 * StringUtils.replaceChars("abcba", "bc", "yzx") = "ayzya"
	 * </pre>
	 * 
	 * @param str 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param searchChars 一组要查找的字符, 可以为null，可以为null，为null时返回源字符串
	 * @param replaceChars 一组用来替换的字符，可以为null，为null时当作空串
	 * @return 替换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午8:29:33
	 */
	public static String replaceChars(String str, String searchChars, String replaceChars) {
		return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChars, replaceChars);
	}

	// Overlay
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 用一个字符串替换源字符串的一部分（通过下标指定）
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.overlay(null, *, *, *)            = null
	 * StringUtils.overlay("", "abc", 0, 0)          = "abc"
	 * StringUtils.overlay("abcdef", null, 2, 4)     = "abef"
	 * StringUtils.overlay("abcdef", "", 2, 4)       = "abef"
	 * StringUtils.overlay("abcdef", "", 4, 2)       = "abef"
	 * StringUtils.overlay("abcdef", "zzzz", 2, 4)   = "abzzzzef"
	 * StringUtils.overlay("abcdef", "zzzz", 4, 2)   = "abzzzzef"
	 * StringUtils.overlay("abcdef", "zzzz", -1, 4)  = "zzzzef"
	 * StringUtils.overlay("abcdef", "zzzz", 2, 8)   = "abzzzz"
	 * StringUtils.overlay("abcdef", "zzzz", -2, -3) = "zzzzabcdef"
	 * StringUtils.overlay("abcdef", "zzzz", 8, 10)  = "abcdefzzzz"
	 * </pre>
	 * 
	 * @param str 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param overlay 用来替换的字符串。可以为null，为null时当作空串
	 * @param start 开始替换的位置，负数当作0，大于源字符串长度当作源字符串长度
	 * @param end 停止替换的位置，负数当作0，大于源字符串长度当作源字符串长度
	 * @return 替换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午8:45:07
	 */
	public static String overlay(String str, String overlay, int start, int end) {
		return org.apache.commons.lang3.StringUtils.overlay(str, overlay, start, end);
	}

	// Chomping
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 删除字符串末尾的换行符("\n"、"\r"、"\r\n")
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.chomp(null)          = null
	 * StringUtils.chomp("")            = ""
	 * StringUtils.chomp("abc \r")      = "abc "
	 * StringUtils.chomp("abc\n")       = "abc"
	 * StringUtils.chomp("abc\r\n")     = "abc"
	 * StringUtils.chomp("abc\r\n\r\n") = "abc\r\n"
	 * StringUtils.chomp("abc\n\r")     = "abc\n"
	 * StringUtils.chomp("abc\n\rabc")  = "abc\n\rabc"
	 * StringUtils.chomp("\r")          = ""
	 * StringUtils.chomp("\n")          = ""
	 * StringUtils.chomp("\r\n")        = ""
	 * </pre>
	 * 
	 * @param str 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @return 处理后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午8:49:09
	 */
	public static String chomp(String str) {
		return org.apache.commons.lang3.StringUtils.chomp(str);
	}

	// Chopping
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 删除指定字符串的最后一个字符(如果该字符串是以"\r\n"结尾，同样也删除这两个字符)
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.chop(null)          = null
	 * StringUtils.chop("")            = ""
	 * StringUtils.chop("abc \r")      = "abc "
	 * StringUtils.chop("abc\n")       = "abc"
	 * StringUtils.chop("abc\r\n")     = "abc"
	 * StringUtils.chop("abc")         = "ab"
	 * StringUtils.chop("abc\nabc")    = "abc\nab"
	 * StringUtils.chop("a")           = ""
	 * StringUtils.chop("\r")          = ""
	 * StringUtils.chop("\n")          = ""
	 * StringUtils.chop("\r\n")        = ""
	 * </pre>
	 * 
	 * @param str 被查找和替换的源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @return 处理后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午8:50:56
	 */
	public static String chop(String str) {
		return org.apache.commons.lang3.StringUtils.chop(str);
	}

	// Conversion
	// -----------------------------------------------------------------------

	// Padding
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 字符串自相连指定次数
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.repeat(null, 2) = null
	 * StringUtils.repeat("", 0)   = ""
	 * StringUtils.repeat("", 2)   = ""
	 * StringUtils.repeat("a", 3)  = "aaa"
	 * StringUtils.repeat("ab", 2) = "abab"
	 * StringUtils.repeat("a", -2) = ""
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null，为空串时返回空串
	 * @param repeat 重复的次数，负数当作0
	 * @return 自相连后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午8:53:17
	 */
	public static String repeat(String str, int repeat) {
		return org.apache.commons.lang3.StringUtils.repeat(str, repeat);
	}

	/**
	 * <p>
	 * 字符串自相连指定次数,两两间用分隔串分隔
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.repeat(null, null, 2) = null
	 * StringUtils.repeat(null, "x", 2)  = null
	 * StringUtils.repeat("", null, 0)   = ""
	 * StringUtils.repeat("", "", 2)     = ""
	 * StringUtils.repeat("", "x", 3)    = "xxx"
	 * StringUtils.repeat("?", ", ", 3)  = "?, ?, ?"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param separator 分隔串, 可以为null，为null时当作空串
	 * @param repeat 重复的次数，负数当作0
	 * @return 自相连后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午8:56:18
	 */
	public static String repeat(String str, String separator, int repeat) {
		return org.apache.commons.lang3.StringUtils.repeat(str, separator, repeat);
	}

	/**
	 * <p>
	 * 字符自相连指定次数
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.repeat('e', 0)  = ""
	 * StringUtils.repeat('e', 3)  = "eee"
	 * StringUtils.repeat('e', -2) = ""
	 * </pre>
	 * 
	 * @param ch 重复的字符
	 * @param repeat 重复的次数，负数当作0
	 * @return 重复相连后的字符串
	 * @see #repeat(String, int)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:33:09
	 */
	public static String repeat(char ch, int repeat) {
		return org.apache.commons.lang3.StringUtils.repeat(ch, repeat);
	}

	/**
	 * <p>
	 * 用空格右补全源字符串到指定的长度
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.rightPad(null, *)   = null
	 * StringUtils.rightPad("", 3)     = "   "
	 * StringUtils.rightPad("bat", 3)  = "bat"
	 * StringUtils.rightPad("bat", 5)  = "bat  "
	 * StringUtils.rightPad("bat", 1)  = "bat"
	 * StringUtils.rightPad("bat", -1) = "bat"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param size 要求的长度，负数当作0
	 * @return 补全长度后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:35:49
	 */
	public static String rightPad(String str, int size) {
		return org.apache.commons.lang3.StringUtils.rightPad(str, size);
	}

	/**
	 * <p>
	 * 用指定字符右补全源字符串到指定的长度
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.rightPad(null, *, *)     = null
	 * StringUtils.rightPad("", 3, 'z')     = "zzz"
	 * StringUtils.rightPad("bat", 3, 'z')  = "bat"
	 * StringUtils.rightPad("bat", 5, 'z')  = "batzz"
	 * StringUtils.rightPad("bat", 1, 'z')  = "bat"
	 * StringUtils.rightPad("bat", -1, 'z') = "bat"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param size 要求的长度，负数当作0
	 * @param padChar 补全的字符
	 * @return 补全长度后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:37:20
	 */
	public static String rightPad(String str, int size, char padChar) {
		return org.apache.commons.lang3.StringUtils.rightPad(str, size, padChar);
	}

	/**
	 * <p>
	 * 用指定字符串右补全源字符串到指定的长度
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.rightPad(null, *, *)      = null
	 * StringUtils.rightPad("", 3, "z")      = "zzz"
	 * StringUtils.rightPad("bat", 3, "yz")  = "bat"
	 * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
	 * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
	 * StringUtils.rightPad("bat", 1, "yz")  = "bat"
	 * StringUtils.rightPad("bat", -1, "yz") = "bat"
	 * StringUtils.rightPad("bat", 5, null)  = "bat  "
	 * StringUtils.rightPad("bat", 5, "")    = "bat  "
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param size 要求的长度，负数当作0
	 * @param padStr 补全的字符串, null或空串当作空格
	 * @return 补全长度后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:39:14
	 */
	public static String rightPad(String str, int size, String padStr) {
		return org.apache.commons.lang3.StringUtils.rightPad(str, size, padStr);
	}

	/**
	 * <p>
	 * 用空格左补全源字符串到指定的长度
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.leftPad(null, *)   = null
	 * StringUtils.leftPad("", 3)     = "   "
	 * StringUtils.leftPad("bat", 3)  = "bat"
	 * StringUtils.leftPad("bat", 5)  = "  bat"
	 * StringUtils.leftPad("bat", 1)  = "bat"
	 * StringUtils.leftPad("bat", -1) = "bat"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param size 要求的长度，负数当作0
	 * @return 补全长度后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:40:22
	 */
	public static String leftPad(String str, int size) {
		return org.apache.commons.lang3.StringUtils.left(str, size);
	}

	/**
	 * <p>
	 * 用指定字符左补全源字符串到指定的长度
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.leftPad(null, *, *)     = null
	 * StringUtils.leftPad("", 3, 'z')     = "zzz"
	 * StringUtils.leftPad("bat", 3, 'z')  = "bat"
	 * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
	 * StringUtils.leftPad("bat", 1, 'z')  = "bat"
	 * StringUtils.leftPad("bat", -1, 'z') = "bat"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param size 要求的长度，负数当作0
	 * @param padChar 补全的字符
	 * @return 补全长度后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:41:16
	 */
	public static String leftPad(String str, int size, char padChar) {
		return org.apache.commons.lang3.StringUtils.leftPad(str, size, padChar);
	}

	/**
	 * <p>
	 * 用指定字符串左补全源字符串到指定的长度
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.leftPad(null, *, *)      = null
	 * StringUtils.leftPad("", 3, "z")      = "zzz"
	 * StringUtils.leftPad("bat", 3, "yz")  = "bat"
	 * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
	 * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
	 * StringUtils.leftPad("bat", 1, "yz")  = "bat"
	 * StringUtils.leftPad("bat", -1, "yz") = "bat"
	 * StringUtils.leftPad("bat", 5, null)  = "  bat"
	 * StringUtils.leftPad("bat", 5, "")    = "  bat"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param size 要求的长度，负数当作0
	 * @param padStr 补全的字符串, null或空串当作空格
	 * @return 补全长度后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:42:05
	 */
	public static String leftPad(String str, int size, String padStr) {
		return org.apache.commons.lang3.StringUtils.leftPad(str, size, padStr);
	}

	/**
	 * 取得字符序列的长度，null返回0
	 * 
	 * @param cs 字符序列，可以为null，null返回0
	 * @return 字符序列的长度
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:43:47
	 */
	public static int length(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.length(cs);
	}

	// Centering
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 用空格左右补全源字符串到指定长度
	 * <p>
	 * 
	 * <p>
	 * 等同于{@code center(str, size, " ")}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.center(null, *)   = null
	 * StringUtils.center("", 4)     = "    "
	 * StringUtils.center("ab", -1)  = "ab"
	 * StringUtils.center("ab", 4)   = " ab "
	 * StringUtils.center("abcd", 2) = "abcd"
	 * StringUtils.center("a", 4)    = " a  "
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param size 要求的长度, 负数当作0，小于源字符串长度将返回源字符串
	 * @return 补全长度后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:49:35
	 */
	public static String center(String str, int size) {
		return org.apache.commons.lang3.StringUtils.center(str, size);
	}

	/**
	 * <p>
	 * 用指定字符左右补全源字符串到指定长度
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.center(null, *, *)     = null
	 * StringUtils.center("", 4, ' ')     = "    "
	 * StringUtils.center("ab", -1, ' ')  = "ab"
	 * StringUtils.center("ab", 4, ' ')   = " ab"
	 * StringUtils.center("abcd", 2, ' ') = "abcd"
	 * StringUtils.center("a", 4, ' ')    = " a  "
	 * StringUtils.center("a", 4, 'y')    = "yayy"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param size 要求的长度, 负数当作0，小于源字符串长度将返回源字符串
	 * @param padChar 用于补全的字符
	 * @return 补全长度后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:51:23
	 */
	public static String center(String str, int size, char padChar) {
		return org.apache.commons.lang3.StringUtils.center(str, size, padChar);
	}

	/**
	 * <p>
	 * 用指定字符串左右补全源字符串到指定长度
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.center(null, *, *)     = null
	 * StringUtils.center("", 4, " ")     = "    "
	 * StringUtils.center("ab", -1, " ")  = "ab"
	 * StringUtils.center("ab", 4, " ")   = " ab"
	 * StringUtils.center("abcd", 2, " ") = "abcd"
	 * StringUtils.center("a", 4, " ")    = " a  "
	 * StringUtils.center("a", 4, "yz")   = "yayz"
	 * StringUtils.center("abc", 7, null) = "  abc  "
	 * StringUtils.center("abc", 7, "")   = "  abc  "
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param size 要求的长度, 负数当作0，小于源字符串长度将返回源字符串
	 * @param padStr 用于补全的字符串
	 * @return 补全长度后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:52:23
	 */
	public static String center(String str, int size, String padStr) {
		return org.apache.commons.lang3.StringUtils.center(str, size, padStr);
	}

	// Case conversion
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将源字符串全部转成大写
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.upperCase(null)  = null
	 * StringUtils.upperCase("")    = ""
	 * StringUtils.upperCase("aBc") = "ABC"
	 * </pre>
	 * 
	 * <p>
	 * <strong>注意:</strong> 根据jdk文档对{@link String#toUpperCase()}的描述,
	 * 转换的结果取决于当前的locale. 为了获得平台独立的转换, 请使用{@link #upperCase(String, Locale)}
	 * 方法(如{@link Locale#ENGLISH}).
	 * </p>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @return 转换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午9:58:59
	 */
	public static String upperCase(String str) {
		return org.apache.commons.lang3.StringUtils.upperCase(str);
	}

	/**
	 * <p>
	 * 根据指定的locale，将源字符串全部转成大写
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.upperCase(null, Locale.ENGLISH)  = null
	 * StringUtils.upperCase("", Locale.ENGLISH)    = ""
	 * StringUtils.upperCase("aBc", Locale.ENGLISH) = "ABC"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param locale 定义大小写转换规则的locale，不能为null
	 * @return 转换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:00:45
	 */
	public static String upperCase(String str, Locale locale) {
		return org.apache.commons.lang3.StringUtils.upperCase(str, locale);
	}

	/**
	 * <p>
	 * 将源字符串全部转成小写
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.lowerCase(null)  = null
	 * StringUtils.lowerCase("")    = ""
	 * StringUtils.lowerCase("aBc") = "abc"
	 * </pre>
	 * 
	 * <p>
	 * <strong>注意:</strong> 根据jdk文档对{@link String#toLowerCase()}的描述,
	 * 转换的结果取决于当前的locale. 为了获得平台独立的转换, 请使用{@link #lowerCase(String, Locale)}
	 * 方法(如{@link Locale#ENGLISH}).
	 * </p>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @return 转换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:10:59
	 */
	public static String lowerCase(String str) {
		return org.apache.commons.lang3.StringUtils.lowerCase(str);
	}

	/**
	 * <p>
	 * 根据指定的locale，将源字符串全部转成小写
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.lowerCase(null, Locale.ENGLISH)  = null
	 * StringUtils.lowerCase("", Locale.ENGLISH)    = ""
	 * StringUtils.lowerCase("aBc", Locale.ENGLISH) = "abc"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param locale 定义大小写转换规则的locale，不能为null
	 * @return 转换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:11:45
	 */
	public static String lowerCase(String str, Locale locale) {
		return org.apache.commons.lang3.StringUtils.lowerCase(str, locale);
	}

	/**
	 * <p>
	 * 将源字符串首字母大写
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.capitalize(null)  = null
	 * StringUtils.capitalize("")    = ""
	 * StringUtils.capitalize("cat") = "Cat"
	 * StringUtils.capitalize("cAt") = "CAt"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @return 首字母大写的字符串
	 * @see org.apache.commons.lang3.text.WordUtils#capitalize(String)
	 * @see #uncapitalize(String)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:14:24
	 */
	public static String capitalize(String str) {
		return org.apache.commons.lang3.StringUtils.capitalize(str);
	}

	/**
	 * <p>
	 * 将源字符串首字母小写
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.uncapitalize(null)  = null
	 * StringUtils.uncapitalize("")    = ""
	 * StringUtils.uncapitalize("Cat") = "cat"
	 * StringUtils.uncapitalize("CAT") = "cAT"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @return 首字母小写的字符串
	 * @see org.apache.commons.lang3.text.WordUtils#uncapitalize(String)
	 * @see #capitalize(String)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:15:16
	 */
	public static String uncapitalize(String str) {
		return org.apache.commons.lang3.StringUtils.uncapitalize(str);
	}

	/**
	 * <p>
	 * 将源字符串中的大写转成小写，小写转成大写
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.swapCase(null)                 = null
	 * StringUtils.swapCase("")                   = ""
	 * StringUtils.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @return 转换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:17:38
	 */
	public static String swapCase(String str) {
		return org.apache.commons.lang3.StringUtils.swapCase(str);
	}

	// Count matches
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 计算子串在源字符串中出现的次数
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.countMatches(null, *)       = 0
	 * StringUtils.countMatches("", *)         = 0
	 * StringUtils.countMatches("abba", null)  = 0
	 * StringUtils.countMatches("abba", "")    = 0
	 * StringUtils.countMatches("abba", "a")   = 2
	 * StringUtils.countMatches("abba", "ab")  = 1
	 * StringUtils.countMatches("abba", "xxx") = 0
	 * </pre>
	 * 
	 * @param str 待查找的字符序列，可以为null，为null或空串返回0
	 * @param sub 子串, 可以为null
	 * @return 子串出现的次数
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:19:39
	 */
	public static int countMatches(CharSequence str, CharSequence sub) {
		return org.apache.commons.lang3.StringUtils.countMatches(str, sub);
	}

	// Character Tests
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 测试字符序列是否只包含Unicode字母
	 * </p>
	 * 
	 * <p>
	 * {@code null} will return {@code false}. An empty CharSequence
	 * (length()=0) will return {@code false}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isAlpha(null)   = false
	 * StringUtils.isAlpha("")     = false
	 * StringUtils.isAlpha("  ")   = false
	 * StringUtils.isAlpha("abc")  = true
	 * StringUtils.isAlpha("ab2c") = false
	 * StringUtils.isAlpha("ab-c") = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null，null或空串返回false
	 * @return true: 只包含Unicode字母
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:23:02
	 */
	public static boolean isAlpha(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isAlpha(cs);
	}

	/**
	 * <p>
	 * 测试字符序列是否只包含Unicode字母或空格
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isAlphaSpace(null)   = false
	 * StringUtils.isAlphaSpace("")     = true
	 * StringUtils.isAlphaSpace("  ")   = true
	 * StringUtils.isAlphaSpace("abc")  = true
	 * StringUtils.isAlphaSpace("ab c") = true
	 * StringUtils.isAlphaSpace("ab2c") = false
	 * StringUtils.isAlphaSpace("ab-c") = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null，null返回false，空串返回true
	 * @return true: 非null并且只包含Unicode字母或空格
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:24:45
	 */
	public static boolean isAlphaSpace(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isAlphaSpace(cs);
	}

	/**
	 * <p>
	 * 测试字符序列是否只包含Unicode字母或数字
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isAlphanumeric(null)   = false
	 * StringUtils.isAlphanumeric("")     = false
	 * StringUtils.isAlphanumeric("  ")   = false
	 * StringUtils.isAlphanumeric("abc")  = true
	 * StringUtils.isAlphanumeric("ab c") = false
	 * StringUtils.isAlphanumeric("ab2c") = true
	 * StringUtils.isAlphanumeric("ab-c") = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null，null或空串返回false
	 * @return true: 只包含Unicode字母或数字
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:26:03
	 */
	public static boolean isAlphanumeric(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isAlpha(cs);
	}

	/**
	 * <p>
	 * 测试字符序列是否只包含Unicode字母、空格或数字
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isAlphanumericSpace(null)   = false
	 * StringUtils.isAlphanumericSpace("")     = true
	 * StringUtils.isAlphanumericSpace("  ")   = true
	 * StringUtils.isAlphanumericSpace("abc")  = true
	 * StringUtils.isAlphanumericSpace("ab c") = true
	 * StringUtils.isAlphanumericSpace("ab2c") = true
	 * StringUtils.isAlphanumericSpace("ab-c") = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null，null返回false，空串返回true
	 * @return true: 非null并且只包含Unicode字母、空格或数字
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:27:44
	 */
	public static boolean isAlphanumericSpace(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isAlphanumeric(cs);
	}

	/**
	 * <p>
	 * 测试字符序列是否只包含ASCII码的可打印的字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isAsciiPrintable(null)     = false
	 * StringUtils.isAsciiPrintable("")       = true
	 * StringUtils.isAsciiPrintable(" ")      = true
	 * StringUtils.isAsciiPrintable("Ceki")   = true
	 * StringUtils.isAsciiPrintable("ab2c")   = true
	 * StringUtils.isAsciiPrintable("!ab-c~") = true
	 * StringUtils.isAsciiPrintable("\u0020") = true
	 * StringUtils.isAsciiPrintable("\u0021") = true
	 * StringUtils.isAsciiPrintable("\u007e") = true
	 * StringUtils.isAsciiPrintable("\u007f") = false
	 * StringUtils.isAsciiPrintable("Ceki G\u00fclc\u00fc") = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null，null返回false，空串返回true
	 * @return true: 非null并且每个字符都在32到126的范围内
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:30:11
	 */
	public static boolean isAsciiPrintable(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isAsciiPrintable(cs);
	}

	/**
	 * <p>
	 * 测试字符序列是否只包含Unicode数字。 十进制的小数不是Unicode数字。
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNumeric(null)   = false
	 * StringUtils.isNumeric("")     = false
	 * StringUtils.isNumeric("  ")   = false
	 * StringUtils.isNumeric("123")  = true
	 * StringUtils.isNumeric("12 3") = false
	 * StringUtils.isNumeric("ab2c") = false
	 * StringUtils.isNumeric("12-3") = false
	 * StringUtils.isNumeric("12.3") = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null，null或空串返回false
	 * @return true: 只包含Unicode数字
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:32:11
	 */
	public static boolean isNumeric(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isNumeric(cs);
	}

	/**
	 * <p>
	 * 测试字符序列是否只包含Unicode数字或空格。 十进制的小数不是Unicode数字。
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNumericSpace(null)   = false
	 * StringUtils.isNumericSpace("")     = true
	 * StringUtils.isNumericSpace("  ")   = true
	 * StringUtils.isNumericSpace("123")  = true
	 * StringUtils.isNumericSpace("12 3") = true
	 * StringUtils.isNumericSpace("ab2c") = false
	 * StringUtils.isNumericSpace("12-3") = false
	 * StringUtils.isNumericSpace("12.3") = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null，null返回false，空串返回true
	 * @return true: 只包含Unicode数字或空格
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:34:30
	 */
	public static boolean isNumericSpace(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isNumeric(cs);
	}

	/**
	 * <p>
	 * 测试字符序列是否只包含空白字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isWhitespace(null)   = false
	 * StringUtils.isWhitespace("")     = true
	 * StringUtils.isWhitespace("  ")   = true
	 * StringUtils.isWhitespace("abc")  = false
	 * StringUtils.isWhitespace("ab2c") = false
	 * StringUtils.isWhitespace("ab-c") = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null，null返回false，空串返回true
	 * @return true：只包含空白字符
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:37:09
	 */
	public static boolean isWhitespace(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isWhitespace(cs);
	}

	/**
	 * <p>
	 * 测试字符序列是否只包含小写字母
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isAllLowerCase(null)   = false
	 * StringUtils.isAllLowerCase("")     = false
	 * StringUtils.isAllLowerCase("  ")   = false
	 * StringUtils.isAllLowerCase("abc")  = true
	 * StringUtils.isAllLowerCase("abC") = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null，null或空串返回false
	 * @return true：只包含小写字母
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:40:25
	 */
	public static boolean isAllLowerCase(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isAllLowerCase(cs);
	}

	/**
	 * <p>
	 * 测试字符序列是否只包含大写字母
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isAllUpperCase(null)   = false
	 * StringUtils.isAllUpperCase("")     = false
	 * StringUtils.isAllUpperCase("  ")   = false
	 * StringUtils.isAllUpperCase("ABC")  = true
	 * StringUtils.isAllUpperCase("aBC") = false
	 * </pre>
	 * 
	 * @param cs 待查找的字符序列，可以为null，null或空串返回false
	 * @return true：只包含大写字母
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:41:25
	 */
	public static boolean isAllUpperCase(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isAllUpperCase(cs);
	}

	// Defaults
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回传入的字符串或null或空串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.defaultString(null)  = ""
	 * StringUtils.defaultString("")    = ""
	 * StringUtils.defaultString("bat") = "bat"
	 * </pre>
	 * 
	 * @see ObjectUtils#toString(Object)
	 * @see String#valueOf(Object)
	 * @param str 待检查的字符串, 可以为null，null返回null，空串返回空串
	 * @return 传入的字符串或null或空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:43:47
	 */
	public static String defaultString(String str) {
		return org.apache.commons.lang3.StringUtils.defaultString(str);
	}

	/**
	 * <p>
	 * 返回传入的字符串，如果它为null时，则返回默认的值
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.defaultString(null, "NULL")  = "NULL"
	 * StringUtils.defaultString("", "NULL")    = ""
	 * StringUtils.defaultString("bat", "NULL") = "bat"
	 * </pre>
	 * 
	 * @see ObjectUtils#toString(Object,String)
	 * @see String#valueOf(Object)
	 * @param str 待检查的字符串, 可以为null，null返回默认值，空串返回空串
	 * @param defaultStr 默认值, 可以为null
	 * @return 传入的字符串，如果它为null时，则返回默认的值
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:47:00
	 */
	public static String defaultString(String str, String defaultStr) {
		return org.apache.commons.lang3.StringUtils.defaultString(str, defaultStr);
	}

	/**
	 * <p>
	 * 返回传入的字符串，如果它为null或空串或空白字符时，则返回默认的值
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.defaultIfBlank(null, "NULL")  = "NULL"
	 * StringUtils.defaultIfBlank("", "NULL")    = "NULL"
	 * StringUtils.defaultIfBlank(" ", "NULL")   = "NULL"
	 * StringUtils.defaultIfBlank("bat", "NULL") = "bat"
	 * StringUtils.defaultIfBlank("", null)      = null
	 * </pre>
	 * 
	 * @param <T> CharSequence的类型
	 * @param str 待查找的字符序列，可以为null
	 * @param defaultStr 默认值, 可以为null
	 * @return 传入的字符串或默认值
	 * @see StringTool#defaultString(String, String)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:49:31
	 */
	public static <T extends CharSequence> T defaultIfBlank(T str, T defaultStr) {
		return org.apache.commons.lang3.StringUtils.defaultIfBlank(str, defaultStr);
	}

	/**
	 * <p>
	 * 返回传入的字符串，如果它为null或空串时，则返回默认的值
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.defaultIfEmpty(null, "NULL")  = "NULL"
	 * StringUtils.defaultIfEmpty("", "NULL")    = "NULL"
	 * StringUtils.defaultIfEmpty(" ", "NULL")   = " "
	 * StringUtils.defaultIfEmpty("bat", "NULL") = "bat"
	 * StringUtils.defaultIfEmpty("", null)      = null
	 * </pre>
	 * 
	 * @param <T> CharSequence的类型
	 * @param str 待查找的字符序列，可以为null
	 * @param defaultStr 默认值, 可以为null
	 * @return 传入的字符串或默认值
	 * @see StringTool#defaultString(String, String)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:50:37
	 */
	public static <T extends CharSequence> T defaultIfEmpty(T str, T defaultStr) {
		return org.apache.commons.lang3.StringUtils.defaultIfEmpty(str, defaultStr);
	}

	// Reversing
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 反转字符串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.reverse(null)  = null
	 * StringUtils.reverse("")    = ""
	 * StringUtils.reverse("bat") = "tab"
	 * StringUtils.reverse("反转字符串") = "串符字转反"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，null返回null
	 * @return 反转后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:53:06
	 */
	public static String reverse(String str) {
		return org.apache.commons.lang3.StringUtils.reverse(str);
	}

	/**
	 * <p>
	 * 根据分隔符反转字符串，分隔符间的字符串当作一个整体(本身不反转)
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.reverseDelimited(null, *)      = null
	 * StringUtils.reverseDelimited("", *)        = ""
	 * StringUtils.reverseDelimited("a.b.c", 'x') = "a.b.c"
	 * StringUtils.reverseDelimited("a.b.c", ".") = "c.b.a"
	 * StringUtils.reverseDelimited("java.lang.String", ".") = "String.lang.java"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，null返回null
	 * @param separatorChar 分隔符
	 * @return 反转后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午10:56:21
	 */
	public static String reverseDelimited(String str, char separatorChar) {
		return org.apache.commons.lang3.StringUtils.reverseDelimited(str, separatorChar);
	}

	// Abbreviating
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 省略字符串
	 * </p>
	 * 
	 * <p>
	 * 规则:
	 * <ul>
	 * <li>如果 {@code str} 的长度 比 {@code maxWidth} 还小, return {@code str}</li>
	 * <li>否则省略为： {@code (substring(str, 0, max-3) + "...")}.</li>
	 * <li>如果 {@code maxWidth} 比 {@code 4} 小, 抛出
	 * {@code IllegalArgumentException} 异常.</li>
	 * <li>永远不会返回长度超过 {@code maxWidth} 的字符串.</li>
	 * </ul>
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.abbreviate(null, *)      = null
	 * StringUtils.abbreviate("", 4)        = ""
	 * StringUtils.abbreviate("abcdefg", 6) = "abc..."
	 * StringUtils.abbreviate("abcdefg", 7) = "abcdefg"
	 * StringUtils.abbreviate("abcdefg", 8) = "abcdefg"
	 * StringUtils.abbreviate("abcdefg", 4) = "a..."
	 * StringUtils.abbreviate("abcdefg", 3) = IllegalArgumentException
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param maxWidth 返回的字符串的最大长度，必须大于等于4
	 * @return 省略的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午11:03:41
	 */
	public static String abbreviate(String str, int maxWidth) {
		return org.apache.commons.lang3.StringUtils.abbreviate(str, maxWidth);
	}

	/**
	 * <p>
	 * 省略字符串，功能类似{@code abbreviate(String, int)}，但可以指定左边界偏移量
	 * </p>
	 * 
	 * <p>
	 * 左边界偏移量不一定要求最左边的字符出现在结果字符串的最左边， 或紧跟省略号之后，但它一定会出现在结果字符串的某个地方
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.abbreviate(null, *, *)                = null
	 * StringUtils.abbreviate("", 0, 4)                  = ""
	 * StringUtils.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
	 * StringUtils.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
	 * StringUtils.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
	 * StringUtils.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
	 * StringUtils.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
	 * StringUtils.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
	 * StringUtils.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
	 * StringUtils.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
	 * StringUtils.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
	 * StringUtils.abbreviate("abcdefghij", 0, 3)        = IllegalArgumentException
	 * StringUtils.abbreviate("abcdefghij", 5, 6)        = IllegalArgumentException
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param offset 源字符串的左边界偏移量
	 * @param maxWidth 返回的字符串的最大长度，必须大于等于4
	 * @return 省略的字符串
	 * @throws IllegalArgumentException 结果长度小于4时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午11:13:50
	 */
	public static String abbreviate(String str, int offset, int maxWidth) {
		return org.apache.commons.lang3.StringUtils.abbreviate(str, offset, maxWidth);
	}

	/**
	 * <p>
	 * 用指定字符串替换源字符串中间的字符，以达到省略源字符串到指定长度的目的
	 * </p>
	 * 
	 * <p>
	 * 省略只有当以下条件满足时才发生：
	 * <ul>
	 * <li>源字符串和用来替换的字符串两者都不为null或空串</li>
	 * <li>指定的目标长度小于源字符串的长度</li>
	 * <li>指定的目标长度大于0</li>
	 * <li>被省略的字符串要有足够的长度提供给替换字符串和第一个、最后一个字符</li>
	 * </ul>
	 * 否则，结果将是传入的源字符串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.abbreviateMiddle(null, null, 0)      = null
	 * StringUtils.abbreviateMiddle("abc", null, 0)      = "abc"
	 * StringUtils.abbreviateMiddle("abc", ".", 0)      = "abc"
	 * StringUtils.abbreviateMiddle("abc", ".", 3)      = "abc"
	 * StringUtils.abbreviateMiddle("abcdef", ".", 4)     = "ab.f"
	 * </pre>
	 * 
	 * @param str 源字符串, 可以为null，为null时返回null
	 * @param middle 用来替换中间字符的字符串, 可以为null，为null表示不替换
	 * @param length 返回的字符串的最大长度
	 * @return 省略的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午11:37:14
	 */
	public static String abbreviateMiddle(String str, String middle, int length) {
		return org.apache.commons.lang3.StringUtils.abbreviateMiddle(str, middle, length);
	}

	// Difference
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 比较两字符串，并返回它们不同的部分 (更精确的讲，返回第二个字符串从与第一个不同部分开始的剩下部分)
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.difference(null, null) = null
	 * StringUtils.difference("", "") = ""
	 * StringUtils.difference("", "abc") = "abc"
	 * StringUtils.difference("abc", "") = ""
	 * StringUtils.difference("abc", "abc") = ""
	 * StringUtils.difference("ab", "abxyz") = "xyz"
	 * StringUtils.difference("abcde", "abxyz") = "xyz"
	 * StringUtils.difference("abcde", "xyz") = "xyz"
	 * </pre>
	 * 
	 * @param str1 第一个字符串, 可以为null
	 * @param str2 第一个字符串, 可以为null
	 * @return 两字符串不同的部分，相同时返回空串，两个都为null时返回null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午11:50:15
	 */
	public static String difference(String str1, String str2) {
		return org.apache.commons.lang3.StringUtils.difference(str1, str2);
	}

	/**
	 * <p>
	 * 比较两字符串，并返回它们开始不同时的下标
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOfDifference(null, null) = -1
	 * StringUtils.indexOfDifference("", "") = -1
	 * StringUtils.indexOfDifference("", "abc") = 0
	 * StringUtils.indexOfDifference("abc", "") = 0
	 * StringUtils.indexOfDifference("abc", "abc") = -1
	 * StringUtils.indexOfDifference("ab", "abxyz") = 2
	 * StringUtils.indexOfDifference("abcde", "abxyz") = 2
	 * StringUtils.indexOfDifference("abcde", "xyz") = 0
	 * </pre>
	 * 
	 * @param cs1 第一个字符串, 可以为null
	 * @param cs2 第一个字符串, 可以为null
	 * @return 开始不同时的下标; 如果两字符串相同返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午11:53:47
	 */
	public static int indexOfDifference(CharSequence cs1, CharSequence cs2) {
		return org.apache.commons.lang3.StringUtils.indexOfDifference(cs1, cs2);
	}

	/**
	 * <p>
	 * 比较数组中的每个字符串，并返回它们开始不同时的下标
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.indexOfDifference(null) = -1
	 * StringUtils.indexOfDifference(new String[] {}) = -1
	 * StringUtils.indexOfDifference(new String[] {"abc"}) = -1
	 * StringUtils.indexOfDifference(new String[] {null, null}) = -1
	 * StringUtils.indexOfDifference(new String[] {"", ""}) = -1
	 * StringUtils.indexOfDifference(new String[] {"", null}) = 0
	 * StringUtils.indexOfDifference(new String[] {"abc", null, null}) = 0
	 * StringUtils.indexOfDifference(new String[] {null, null, "abc"}) = 0
	 * StringUtils.indexOfDifference(new String[] {"", "abc"}) = 0
	 * StringUtils.indexOfDifference(new String[] {"abc", ""}) = 0
	 * StringUtils.indexOfDifference(new String[] {"abc", "abc"}) = -1
	 * StringUtils.indexOfDifference(new String[] {"abc", "a"}) = 1
	 * StringUtils.indexOfDifference(new String[] {"ab", "abxyz"}) = 2
	 * StringUtils.indexOfDifference(new String[] {"abcde", "abxyz"}) = 2
	 * StringUtils.indexOfDifference(new String[] {"abcde", "xyz"}) = 0
	 * StringUtils.indexOfDifference(new String[] {"xyz", "abcde"}) = 0
	 * StringUtils.indexOfDifference(new String[] {"i am a machine", "i am a robot"}) = 7
	 * </pre>
	 * 
	 * @param css 字符串数组，可以为null，为null返回-1
	 * @return 开始不同时的下标; 如果字符串都相同返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-7 下午11:56:31
	 */
	public static int indexOfDifference(CharSequence... css) {
		return org.apache.commons.lang3.StringUtils.indexOfDifference(css);
	}

	/**
	 * <p>
	 * 比较数组中的每个字符串，并返回它们相同的前缀
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.getCommonPrefix(null) = ""
	 * StringUtils.getCommonPrefix(new String[] {}) = ""
	 * StringUtils.getCommonPrefix(new String[] {"abc"}) = "abc"
	 * StringUtils.getCommonPrefix(new String[] {null, null}) = ""
	 * StringUtils.getCommonPrefix(new String[] {"", ""}) = ""
	 * StringUtils.getCommonPrefix(new String[] {"", null}) = ""
	 * StringUtils.getCommonPrefix(new String[] {"abc", null, null}) = ""
	 * StringUtils.getCommonPrefix(new String[] {null, null, "abc"}) = ""
	 * StringUtils.getCommonPrefix(new String[] {"", "abc"}) = ""
	 * StringUtils.getCommonPrefix(new String[] {"abc", ""}) = ""
	 * StringUtils.getCommonPrefix(new String[] {"abc", "abc"}) = "abc"
	 * StringUtils.getCommonPrefix(new String[] {"abc", "a"}) = "a"
	 * StringUtils.getCommonPrefix(new String[] {"ab", "abxyz"}) = "ab"
	 * StringUtils.getCommonPrefix(new String[] {"abcde", "abxyz"}) = "ab"
	 * StringUtils.getCommonPrefix(new String[] {"abcde", "xyz"}) = ""
	 * StringUtils.getCommonPrefix(new String[] {"xyz", "abcde"}) = ""
	 * StringUtils.getCommonPrefix(new String[] {"i am a machine", "i am a robot"}) = "i am a "
	 * </pre>
	 * 
	 * @param strs 字符串数组，可以为null，为null或元素都为null或没有相同前缀时返回空串，
	 * @return 相同的前缀
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 上午12:01:47
	 */
	public static String getCommonPrefix(String... strs) {
		return org.apache.commons.lang3.StringUtils.getCommonPrefix(strs);
	}

	// Misc
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 比较两个字符串的“距离”(相似度)， 这个“距离”其实就是从源字符串变换到目标字符串需要进行的删除、插入和替换的次数。
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
	 * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
	 * StringUtils.getLevenshteinDistance("","")               = 0
	 * StringUtils.getLevenshteinDistance("","a")              = 1
	 * StringUtils.getLevenshteinDistance("aaapppp", "")       = 7
	 * StringUtils.getLevenshteinDistance("frog", "fog")       = 1
	 * StringUtils.getLevenshteinDistance("fly", "ant")        = 3
	 * StringUtils.getLevenshteinDistance("elephant", "hippo") = 7
	 * StringUtils.getLevenshteinDistance("hippo", "elephant") = 7
	 * StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz") = 8
	 * StringUtils.getLevenshteinDistance("hello", "hallo")    = 1
	 * </pre>
	 * 
	 * @param s 第一个字符串, 不能为null
	 * @param t 第一个字符串, 不能为null
	 * @return 距离
	 * @throws IllegalArgumentException 两参数之一为null时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午7:06:49
	 */
	public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
		return org.apache.commons.lang3.StringUtils.getLevenshteinDistance(s, t);
	}

	/**
	 * <p>
	 * 如果两个字符串的“距离”(相似度)小于等于给定的极限值，就返回该“距离”，否则返回-1。
	 * 这个“距离”其实就是从源字符串变换到目标字符串需要进行的删除、插入和替换的次数。
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.getLevenshteinDistance(null, *, *)             = IllegalArgumentException
	 * StringUtils.getLevenshteinDistance(*, null, *)             = IllegalArgumentException
	 * StringUtils.getLevenshteinDistance(*, *, -1)               = IllegalArgumentException
	 * StringUtils.getLevenshteinDistance("","", 0)               = 0
	 * StringUtils.getLevenshteinDistance("aaapppp", "", 8)       = 7
	 * StringUtils.getLevenshteinDistance("aaapppp", "", 7)       = 7
	 * StringUtils.getLevenshteinDistance("aaapppp", "", 6))      = -1
	 * StringUtils.getLevenshteinDistance("elephant", "hippo", 7) = 7
	 * StringUtils.getLevenshteinDistance("elephant", "hippo", 6) = -1
	 * StringUtils.getLevenshteinDistance("hippo", "elephant", 7) = 7
	 * StringUtils.getLevenshteinDistance("hippo", "elephant", 6) = -1
	 * </pre>
	 * 
	 * @param s 第一个字符串, 不能为null
	 * @param t 第一个字符串, 不能为null
	 * @param threshold 目标上限值, 不能为负数
	 * @return 距离或-1
	 * @throws IllegalArgumentException 两字符串之一为null或极限值为负数时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午7:15:47
	 */
	public static int getLevenshteinDistance(CharSequence s, CharSequence t, int threshold) {
		return org.apache.commons.lang3.StringUtils.getLevenshteinDistance(s, t, threshold);
	}

	// startsWith
	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 检查字符串的前缀是否为给定的值
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.startsWith(null, null)      = true
	 * StringUtils.startsWith(null, "abc")     = false
	 * StringUtils.startsWith("abcdef", null)  = false
	 * StringUtils.startsWith("abcdef", "abc") = true
	 * StringUtils.startsWith("ABCDEF", "abc") = false
	 * </pre>
	 * 
	 * @see java.lang.String#startsWith(String)
	 * @param str 待查找的字符序列，可以为null，为null返回false
	 * @param prefix 前缀字符串, 可以为null，为null返回false
	 * @return true: 是字符串的前缀或两个参数都为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午7:23:38
	 */
	public static boolean startsWith(CharSequence str, CharSequence prefix) {
		return org.apache.commons.lang3.StringUtils.startsWith(str, prefix);
	}

	/**
	 * <p>
	 * 大小写不敏感的前缀匹配
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.startsWithIgnoreCase(null, null)      = true
	 * StringUtils.startsWithIgnoreCase(null, "abc")     = false
	 * StringUtils.startsWithIgnoreCase("abcdef", null)  = false
	 * StringUtils.startsWithIgnoreCase("abcdef", "abc") = true
	 * StringUtils.startsWithIgnoreCase("ABCDEF", "abc") = true
	 * </pre>
	 * 
	 * @see java.lang.String#startsWith(String)
	 * @param str 待查找的字符序列，可以为null，为null返回false
	 * @param prefix 前缀字符串, 可以为null，为null返回false
	 * @return true: 是字符串的前缀(大小写不敏感)或两个参数都为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午7:25:58
	 */
	public static boolean startsWithIgnoreCase(CharSequence str, CharSequence prefix) {
		return org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(str, prefix);
	}

	/**
	 * <p>
	 * 检查字符串的前缀是否为给定的任何一个值（大小写不敏感）
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.startsWithAny(null, null)      = false
	 * StringUtils.startsWithAny(null, new String[] {"abc"})  = false
	 * StringUtils.startsWithAny("abcxyz", null)     = false
	 * StringUtils.startsWithAny("abcxyz", new String[] {""}) = false
	 * StringUtils.startsWithAny("abcxyz", new String[] {"abc"}) = true
	 * StringUtils.startsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
	 * </pre>
	 * 
	 * @param string 待查找的字符序列，可以为null
	 * @param searchStrings 待匹配的前缀组, 可以为null
	 * @return true: 任何一个为字符串的前缀(大小写不敏感)或两个参数都为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午7:32:27
	 */
	public static boolean startsWithAny(CharSequence string, CharSequence... searchStrings) {
		return org.apache.commons.lang3.StringUtils.startsWithAny(string, searchStrings);
	}

	// endsWith
	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 检查字符串的后缀是否为给定的值
	 * </p>
	 * 
	 * <p>
	 * 该方法能安全地处理{@code null}而不抛出异常。两个{@code null}被当作相等。
	 * 该比较是大小写相关。
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.endsWith(null, null)      = true
	 * StringUtils.endsWith(null, "def")     = false
	 * StringUtils.endsWith("abcdef", null)  = false
	 * StringUtils.endsWith("abcdef", "def") = true
	 * StringUtils.endsWith("ABCDEF", "def") = false
	 * StringUtils.endsWith("ABCDEF", "cde") = false
	 * </pre>
	 * 
	 * @see java.lang.String#endsWith(String)
	 * @param str 待查找的字符序列，可以为null
	 * @param suffix 待匹配的后缀, 可以为null
	 * @return true: 是字符串的后缀(大小写敏感)或两个参数都为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午7:34:59
	 */
	public static boolean endsWith(CharSequence str, CharSequence suffix) {
		return org.apache.commons.lang3.StringUtils.endsWith(str, suffix);
	}

	/**
	 * <p>
	 * 检查字符串的后缀是否为给定的值(大小写不敏感)
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.endsWithIgnoreCase(null, null)      = true
	 * StringUtils.endsWithIgnoreCase(null, "def")     = false
	 * StringUtils.endsWithIgnoreCase("abcdef", null)  = false
	 * StringUtils.endsWithIgnoreCase("abcdef", "def") = true
	 * StringUtils.endsWithIgnoreCase("ABCDEF", "def") = true
	 * StringUtils.endsWithIgnoreCase("ABCDEF", "cde") = false
	 * </pre>
	 * 
	 * @see java.lang.String#endsWith(String)
	 * @param str 待查找的字符序列，可以为null
	 * @param suffix 待匹配的后缀, 可以为null
	 * @return true: 是字符串的后缀(大小不写敏感)或两个参数都为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午7:36:47
	 */
	public static boolean endsWithIgnoreCase(CharSequence str, CharSequence suffix) {
		return org.apache.commons.lang3.StringUtils.endsWithIgnoreCase(str, suffix);
	}

	/**
	 * <p>
	 * 通过去掉前导和尾随空白并使用单个空格替换一系列空白字符，使空白标准化。 如果省略了该参数，上下文节点的字符串值将标准化并返回。
	 * </p>
	 * 
	 * @see #trim(String)
	 * @see <a href="http://www.w3.org/TR/xpath/#function-normalize-space">
	 *      http://www.w3.org/TR/xpath/#function-normalize-space</a>
	 * @param str 源字符串, 可以为null，为null返回null
	 * @return 替换后的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午7:42:11
	 */
	public static String normalizeSpace(String str) {
		return org.apache.commons.lang3.StringUtils.normalizeSpace(str);
	}

	/**
	 * <p>
	 * 检查字符串的后缀是否为给定的任何一个值（大小写不敏感）
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.endsWithAny(null, null)      = false
	 * StringUtils.endsWithAny(null, new String[] {"abc"})  = false
	 * StringUtils.endsWithAny("abcxyz", null)     = false
	 * StringUtils.endsWithAny("abcxyz", new String[] {""}) = true
	 * StringUtils.endsWithAny("abcxyz", new String[] {"xyz"}) = true
	 * StringUtils.endsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
	 * </pre>
	 * 
	 * @param string 待查找的字符序列，可以为null
	 * @param searchStrings 待匹配的前缀组, 可以为null或空串
	 * @return 任何一个为字符串的后缀(大小写不敏感)或两个参数都为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午7:44:48
	 */
	public static boolean endsWithAny(CharSequence string, CharSequence... searchStrings) {
		return org.apache.commons.lang3.StringUtils.endsWithAny(string, searchStrings);
	}

	/**
	 * <p>
	 * 根据指定的字符集将字节数组转为字符串
	 * </p>
	 * 
	 * @param bytes 字节数组
	 * @param charsetName 字符集, 如果为null将使用平台默认的字符集
	 * @return 字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-8 下午7:46:40
	 */
	public static String toString(byte[] bytes, String charsetName) {
		try {
			return org.apache.commons.lang3.StringUtils.toString(bytes, charsetName);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

}
