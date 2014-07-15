package org.joy.commons.lang;


/**
 * 字符处理工具类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-9 下午8:32:48
 */
public class CharTool {

	private CharTool() {
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.CharUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	
	/**
     * <p>
     * 将字符串的第一个字符转为Character, 如果字符串为null或空串将返回null
     * 
     * <p>
     * 对于7位ASCII码的字符, 该方法将使用缓存, 因此同一字符将返回同一个Character对象
     * </p>
     * 
     * <pre>
     *   CharUtils.toCharacterObject(null) = null
     *   CharUtils.toCharacterObject("")   = null
     *   CharUtils.toCharacterObject("A")  = 'A'
     *   CharUtils.toCharacterObject("BA") = 'B'
     * </pre>
     *
     * @param str 要处理的字符串
     * @return 第一个字符对应的Character对象
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午7:17:44
	 */
    public static Character toCharacterObject(String str) {
    	return org.apache.commons.lang3.CharUtils.toCharacterObject(str);
    }
    
    //-----------------------------------------------------------------------
    /**
     * <p>
     * 将Character转为char, 如果参数为{@code null}将抛出异常
     * </p>
     * 
     * <pre>
     *   CharUtils.toChar(' ')  = ' '
     *   CharUtils.toChar('A')  = 'A'
     *   CharUtils.toChar(null) throws IllegalArgumentException
     * </pre>
     *
     * @param ch 要转化的Character
     * @return 转化后的char
     * @throws IllegalArgumentException 如果参数为{@code null}
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:20:16
     */
    public static char toChar(Character ch) {
        return org.apache.commons.lang3.CharUtils.toChar(ch);
    }
    
    /**
     * <p>
     * 将Character转为char, 如果参数为{@code null}将返回指定的默认值
     * </p>
     * 
     * <pre>
     *   CharUtils.toChar(null, 'X') = 'X'
     *   CharUtils.toChar(' ', 'X')  = ' '
     *   CharUtils.toChar('A', 'X')  = 'A'
     * </pre>
     *
     * @param ch 要转化的Character
     * @param defaultValue 默认值
     * @return Character参数的char值 或 返回默认值参数如果Character参数为null的话
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:22:40
     */
    public static char toChar(Character ch, char defaultValue) {
    	return org.apache.commons.lang3.CharUtils.toChar(ch, defaultValue);
    }
    
    //-----------------------------------------------------------------------
    /**
     * <p>
     * 将字符串的第一个字符转为Character, 如果字符串为null或空串将返抛出一个异常
     * </p>
     * 
     * <pre>
     *   CharUtils.toChar("A")  = 'A'
     *   CharUtils.toChar("BA") = 'B'
     *   CharUtils.toChar(null) throws IllegalArgumentException
     *   CharUtils.toChar("")   throws IllegalArgumentException
     * </pre>
     *
     * @param str 要转化的字符串
     * @return 第一个字符对应的Character对象
     * @throws IllegalArgumentException 如果字符串为null或空串将返抛出一个异常
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:28:06
     */
    public static char toChar(String str) {
    	return org.apache.commons.lang3.CharUtils.toChar(str);
    }
    
    /**
     * <p>
     * 将字符串的第一个字符转为Character, 如果字符串为null或空串将返回指定的默认值
     * </p>
     * 
     * <pre>
     *   CharUtils.toChar(null, 'X') = 'X'
     *   CharUtils.toChar("", 'X')   = 'X'
     *   CharUtils.toChar("A", 'X')  = 'A'
     *   CharUtils.toChar("BA", 'X') = 'B'
     * </pre>
     *
     * @param str 要转化的字符串
     * @param defaultValue 默认值
     * @return 第一个字符对应的char值 或 返回默认值参数如果Character参数为null或空串的话
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:28:16
     */
    public static char toChar(String str, char defaultValue) {
    	return org.apache.commons.lang3.CharUtils.toChar(str, defaultValue);
    }
    
    //-----------------------------------------------------------------------
    /**
     * <p>
     * 将char转化它代表的int值, 如果字符不是一个数值将抛出一个异常
     * </p>
     * 
     * <pre>
     *   CharUtils.toIntValue('3')  = 3
     *   CharUtils.toIntValue('A')  throws IllegalArgumentException
     * </pre>
     *
     * @param ch 要转化的char
     * @return the 字符的int值
     * @throws IllegalArgumentException 如果字符不是一个ASCII码数值
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:31:34
     */
    public static int toIntValue(char ch) {
    	return org.apache.commons.lang3.CharUtils.toIntValue(ch);
    }
    
    /**
     * <p>
     * 将char转化它代表的int值, 如果字符不是一个数值将返回指定的默认值
     * </p>
     * 
     * <pre>
     *   CharUtils.toIntValue('3', -1)  = 3
     *   CharUtils.toIntValue('A', -1)  = -1
     * </pre>
     *
     * @param ch 要转化的char
     * @param defaultValue 默认值
     * @return 字符的int值
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:33:23
     */
    public static int toIntValue(char ch, int defaultValue) {
    	return org.apache.commons.lang3.CharUtils.toIntValue(ch, defaultValue);
    }
    
    /**
     * <p>
     * 将Character转化它代表的int值, 如果字符不是一个数值将抛出一个异常
     * </p>
     * 
     * <pre>
     *   CharUtils.toIntValue('3')  = 3
     *   CharUtils.toIntValue(null) throws IllegalArgumentException
     *   CharUtils.toIntValue('A')  throws IllegalArgumentException
     * </pre>
     *
     * @param ch 要转化的char
     * @return 字符的int值
     * @throws IllegalArgumentException 果字符不是一个ASCII码数值或者为null
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:36:07
     */
    public static int toIntValue(Character ch) {
    	return org.apache.commons.lang3.CharUtils.toIntValue(ch);
    }
    
    /**
     * <p>
     * 将char转化它代表的int值, 如果字符不是一个数值将返回指定的默认值
     * </p>
     * 
     * <pre>
     *   CharUtils.toIntValue(null, -1) = -1
     *   CharUtils.toIntValue('3', -1)  = 3
     *   CharUtils.toIntValue('A', -1)  = -1
     * </pre>
     *
     * @param ch 要转化的char
     * @param defaultValue 默认值
     * @return 字符的int值
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:37:06
     */
    public static int toIntValue(Character ch, int defaultValue) {
    	return org.apache.commons.lang3.CharUtils.toIntValue(ch, defaultValue);
    }
    
    //-----------------------------------------------------------------------
    /**
     * <p>
     * 将char转化为字符串
     * </p>
     * 
     * <p>
     * 对于7位ASCII码的字符, 该方法将使用缓存, 因此同一字符将返回同一个字符串对象
     * </p>
     *
     * <pre>
     *   CharUtils.toString(' ')  = " "
     *   CharUtils.toString('A')  = "A"
     * </pre>
     *
     * @param ch 要转化的char
     * @return 包含一个给定字符的字符串
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:39:00
     */
    public static String toString(char ch) {
    	return org.apache.commons.lang3.CharUtils.toString(ch);
    }
    
    /**
     * <p>
     * 将character转化为字符串
     * </p>
     * 
     * <p>
     * 对于7位ASCII码的字符, 该方法将使用缓存, 因此同一字符将返回同一个字符串对象
     * </p>
     * 
     * <p>传入 {@code null} 将返回 {@code null}</p>
     *
     * <pre>
     *   CharUtils.toString(null) = null
     *   CharUtils.toString(' ')  = " "
     *   CharUtils.toString('A')  = "A"
     * </pre>
     *
     * @param ch 要转化的char
     * @return 包含一个给定字符的字符串
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:40:29
     */
    public static String toString(Character ch) {
    	return org.apache.commons.lang3.CharUtils.toString(ch);
    }
    
    //--------------------------------------------------------------------------
    /**
     * <p>
     * 将char转化为其Unicode编码的字符串
     * </p>
     * 
     * <pre>
     *   CharUtils.unicodeEscaped(' ') = "\u0020"
     *   CharUtils.unicodeEscaped('A') = "\u0041"
     * </pre>
     * 
     * @param ch 要转化的char
     * @return 字符对应Unicode编码的字符串
      * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:42:26
     */
    public static String unicodeEscaped(char ch) {
    	return org.apache.commons.lang3.CharUtils.unicodeEscaped(ch);
    }
    
    /**
     * <p>
     * 将Character转化为其Unicode编码的字符串
     * </p>
     * 
     * <p>传入 {@code null} 将返回 {@code null}</p>
     *
     * <pre>
     *   CharUtils.unicodeEscaped(null) = null
     *   CharUtils.unicodeEscaped(' ')  = "\u0020"
     *   CharUtils.unicodeEscaped('A')  = "\u0041"
     * </pre>
     * 
     * @param ch 要转化的Character, 可以为null
     * @return 字符对应Unicode编码的字符串, 传入的参数为null将返回null
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:44:29
     */
    public static String unicodeEscaped(Character ch) {
    	return org.apache.commons.lang3.CharUtils.unicodeEscaped(ch);
    }
    
    //--------------------------------------------------------------------------
    /**
     * <p>
     * 检测给定的char是否为7位的ASCII码
     * </p>
     *
     * <pre>
     *   CharUtils.isAscii('a')  = true
     *   CharUtils.isAscii('A')  = true
     *   CharUtils.isAscii('3')  = true
     *   CharUtils.isAscii('-')  = true
     *   CharUtils.isAscii('\n') = true
     *   CharUtils.isAscii('&copy;') = false
     * </pre>
     * 
     * @param ch 要检测的char
     * @return true: 如果ASCII码值小于128
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:52:09
     */
    public static boolean isAscii(char ch) {
    	return org.apache.commons.lang3.CharUtils.isAscii(ch);
    }
    
    /**
     * <p>
     * 检测给定的char是否为7位可打印的ASCII码
     * </p>
     *
     * <pre>
     *   CharUtils.isAsciiPrintable('a')  = true
     *   CharUtils.isAsciiPrintable('A')  = true
     *   CharUtils.isAsciiPrintable('3')  = true
     *   CharUtils.isAsciiPrintable('-')  = true
     *   CharUtils.isAsciiPrintable('\n') = false
     *   CharUtils.isAsciiPrintable('&copy;') = false
     * </pre>
     * 
     * @param ch 要检测的char
     * @return true: 如果ASCII码值介于32和126之间
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:53:41
     */
    public static boolean isAsciiPrintable(char ch) {
    	return org.apache.commons.lang3.CharUtils.isAsciiPrintable(ch);
    }
    
    /**
     * <p>
     * 检测给定的char是否为7位ASCII码的控制字符
     * </p>
     *
     * <pre>
     *   CharUtils.isAsciiControl('a')  = false
     *   CharUtils.isAsciiControl('A')  = false
     *   CharUtils.isAsciiControl('3')  = false
     *   CharUtils.isAsciiControl('-')  = false
     *   CharUtils.isAsciiControl('\n') = true
     *   CharUtils.isAsciiControl('&copy;') = false
     * </pre>
     * 
     * @param ch 要检测的char
     * @return true: 如果ASCII码值介于32和127之间 
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:56:14
     */
    public static boolean isAsciiControl(char ch) {
    	return org.apache.commons.lang3.CharUtils.isAsciiControl(ch);
    }
    
    /**
     * <p>
     * 检测给定的char是否为7位ASCII码的字母
     * </p>
     *
     * <pre>
     *   CharUtils.isAsciiAlpha('a')  = true
     *   CharUtils.isAsciiAlpha('A')  = true
     *   CharUtils.isAsciiAlpha('3')  = false
     *   CharUtils.isAsciiAlpha('-')  = false
     *   CharUtils.isAsciiAlpha('\n') = false
     *   CharUtils.isAsciiAlpha('&copy;') = false
     * </pre>
     * 
     * @param ch 要检测的char
     * @return true: 如果ASCII码值介于65和90之间(大写字母)或97和122之间(小写字母)
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午7:59:51
     */
    public static boolean isAsciiAlpha(char ch) {
    	return org.apache.commons.lang3.CharUtils.isAsciiAlpha(ch);
    }
    
    /**
     * <p>
     * 检测给定的char是否为7位ASCII码的大写字母
     * </p>
     *
     * <pre>
     *   CharUtils.isAsciiAlphaUpper('a')  = false
     *   CharUtils.isAsciiAlphaUpper('A')  = true
     *   CharUtils.isAsciiAlphaUpper('3')  = false
     *   CharUtils.isAsciiAlphaUpper('-')  = false
     *   CharUtils.isAsciiAlphaUpper('\n') = false
     *   CharUtils.isAsciiAlphaUpper('&copy;') = false
     * </pre>
     * 
     * @param ch 要检测的char
     * @return true: 如果ASCII码值介于65和90
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午8:00:45
     */
    public static boolean isAsciiAlphaUpper(char ch) {
    	return org.apache.commons.lang3.CharUtils.isAsciiAlphaUpper(ch);
    }
    
    /**
     * <p>
     * 检测给定的char是否为7位ASCII码的大写字母
     * </p>
     *
     * <pre>
     *   CharUtils.isAsciiAlphaLower('a')  = true
     *   CharUtils.isAsciiAlphaLower('A')  = false
     *   CharUtils.isAsciiAlphaLower('3')  = false
     *   CharUtils.isAsciiAlphaLower('-')  = false
     *   CharUtils.isAsciiAlphaLower('\n') = false
     *   CharUtils.isAsciiAlphaLower('&copy;') = false
     * </pre>
     * 
     * @param ch 要检测的char
     * @return true: 如果ASCII码值介于97和122之间
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午8:01:40
     */
    public static boolean isAsciiAlphaLower(char ch) {
    	return org.apache.commons.lang3.CharUtils.isAsciiAlphaLower(ch);
    }
    
    /**
     * <p>
     * 检测给定的char是否为7位ASCII码的数字
     * </p>
     *
     * <pre>
     *   CharUtils.isAsciiNumeric('a')  = false
     *   CharUtils.isAsciiNumeric('A')  = false
     *   CharUtils.isAsciiNumeric('3')  = true
     *   CharUtils.isAsciiNumeric('-')  = false
     *   CharUtils.isAsciiNumeric('\n') = false
     *   CharUtils.isAsciiNumeric('&copy;') = false
     * </pre>
     * 
     * @param ch 要检测的char
     * @return true: 如果ASCII码值介于48和57之间
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午8:03:01
     */
    public static boolean isAsciiNumeric(char ch) {
    	return org.apache.commons.lang3.CharUtils.isAsciiNumeric(ch);
    }
    
    /**
     * <p>
     * 检测给定的char是否为7位ASCII码的字母或数字
     * </p>
     *
     * <pre>
     *   CharUtils.isAsciiAlphanumeric('a')  = true
     *   CharUtils.isAsciiAlphanumeric('A')  = true
     *   CharUtils.isAsciiAlphanumeric('3')  = true
     *   CharUtils.isAsciiAlphanumeric('-')  = false
     *   CharUtils.isAsciiAlphanumeric('\n') = false
     *   CharUtils.isAsciiAlphanumeric('&copy;') = false
     * </pre>
     * 
     * @param ch 要检测的char
     * @return true: 如果ASCII码值介于48和57之间(数字)或65和90之间(大写字母)或97和122之间(小写字母)
     * @since 1.0.0
     * @author Kevice
     * @time 2013-4-30 下午8:07:04
     */
    public static boolean isAsciiAlphanumeric(char ch) {
    	return org.apache.commons.lang3.CharUtils.isAsciiAlphanumeric(ch);
    }
    
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 	// 封装org.apache.commons.lang3.CharUtils
 	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}
