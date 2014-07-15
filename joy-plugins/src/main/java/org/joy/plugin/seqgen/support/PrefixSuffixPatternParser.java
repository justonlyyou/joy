package org.joy.plugin.seqgen.support;

import org.joy.commons.bean.Pair;
import org.joy.commons.lang.string.StringTool;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * <pre>
 * 序列号前缀、后缀模板解析器
 * </pre>
 * <pre>
 * &lt;b&gt;--样例--&lt;/b&gt;
 *      Pair prefixPair = new Pair("{0}(%yyyy%)第", "预警指令");
		Pair suffixPair = new Pair("号", null);
		PrefixSuffixPatternParser parser = new PrefixSuffixPatternParser(prefixPair, suffixPair);
		parser.parse();
		String seqNum = parser.getPrefix() + "001"+parser.getSuffix();
		System.out.println(seqNum);
 * </pre>
 * 
 * @author <b>Kevice</b>
 */
public class PrefixSuffixPatternParser {

	/**
	 * 转意字符
	 */
	private static final String ESCAPE_SYMBOL = "\\";
	/**
	 * Pair(前缀模板，前缀参数值串)
	 */
	private final Pair<String, String> prefixPair;
	/**
	 * Pair(后缀模板，后缀参数值串)
	 */
	private final Pair<String, String> suffixPair;
	/**
	 * 当前系统时间
	 */
	private Calendar now;
	/**
	 * 前缀
	 */
	private String prefix;
	/**
	 * 后缀
	 */
	private String suffix;

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public PrefixSuffixPatternParser(Pair<String, String> prefixPair, Pair<String, String> suffixPair) {
		this.prefixPair = prefixPair;
		this.suffixPair = suffixPair;
	}

	public Pair<String, String> parse() {
		prefix = parse(prefixPair);
		suffix = parse(suffixPair);
		return new Pair<String, String>(prefix, suffix);
	}
	
	/**
	 * 对前缀或后缀模板进行解析
	 * @param fixPair
	 * @return 
	 */
	private String parse(Pair<String, String> fixPair) {
		String resultStr = "";
		String pattern = (String) fixPair.getLeft();
		if (StringTool.isBlank(pattern) == false) {
			String params = (String) fixPair.getRight();
			pattern = replaceTimeForPtn(pattern);
			resultStr = fillParameters(pattern, params);	
		}
		return resultStr;
	}
	
	/**
	 * 替换前缀或后缀模板里的时间参数 <br>
	 * 替换的规则如下：<br>
	 * 取出模板中两个%号之间的格式化串(必须按java Calendar里定义的时间格式化定义),
	 * 对当前系统时间按格式化串格式化，替换两个%号(包括)括起来的内容
	 * 
	 * @return 替换时间参数后的模板
	 */
	private String replaceTimeForPtn(String pattern) {
		final String TIME_QUALIFIER = "%";
		if (now == null) {
			now = Calendar.getInstance();
		}
		Map<Integer, Integer> groupMap = groupQualierStr(pattern, TIME_QUALIFIER);
		Set<Entry<Integer, Integer>> entrySet = groupMap.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			int beginIndex = entry.getKey();
			int endIndex = entry.getValue();
			String fmtStr = pattern.substring(beginIndex + 1, endIndex - 1);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmtStr);
			String date = simpleDateFormat.format(now.getTime());
			pattern = pattern.substring(0, beginIndex) + date + pattern.substring(endIndex);
		}
		escape(pattern, TIME_QUALIFIER);
		return pattern;
	}
	
	/**
	 * 根据限定符分组提取模板里要替换的串
	 * @param pattern 模板
	 * @param qualier 限定符
	 * @return Map
	 */
	private static Map<Integer, Integer> groupQualierStr(String pattern, String qualier) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		char[] chars = pattern.toCharArray();
		int startPos = -1;
		for (int i = 0; i < chars.length; i++) {
			String ch = Character.toString(chars[i]);
			if (qualier.equals(ch)) {
				if (startPos == -1) {
					startPos = i;
				} else {
					String preChar = Character.toString(chars[i - 1]);
					if (ESCAPE_SYMBOL.equals(preChar) == false) {
						map.put(startPos, i + 1);
						startPos = -1;
					}
				}
			}
		}
		return map;
	}

	/**
	 * 转意 <br>
	 * 把例如"\%"、"\{", "\}", "\\"转意为"%","{","}", "\"
	 * 
	 * @param pattern
	 *            模板
	 * @param qualifier
	 *            限定符
	 * @return 转意后的字符串
	 */
	private String escape(String pattern, String qualifier) {
		String resultStr = pattern.replaceAll(ESCAPE_SYMBOL + qualifier, qualifier);
		resultStr = resultStr.replaceAll(ESCAPE_SYMBOL + ESCAPE_SYMBOL, ESCAPE_SYMBOL);
		return resultStr;
	}
	
	/**
	 * 将参数值填充到模板中 <br>
	 * 一一替换"{}"里的数字
	 * @param pattern 模板
	 * @param parameters 以逗号分隔的参数值串
	 * @return 替换后的串
	 */
	private String fillParameters(String pattern, String parameters) {
		String result = pattern;
		if (StringTool.isBlank(parameters) == false) {
			String[] params = parameters.split(",");
			result = MessageFormat.format(pattern, (Object[])params);	
		}
		return result;
	}
	
	public static void main(String[] args) {
		Pair<String, String> prefixPair = new Pair<String, String>("{0}(%yyyy%)第", null);
		Pair<String, String> suffixPair = new Pair<String, String>("号", null);
		PrefixSuffixPatternParser parser = new PrefixSuffixPatternParser(prefixPair, suffixPair);
		parser.parse();
		String seqNum = parser.getPrefix() + "001"+parser.getSuffix();
		System.out.println(seqNum);
	}
	

}
