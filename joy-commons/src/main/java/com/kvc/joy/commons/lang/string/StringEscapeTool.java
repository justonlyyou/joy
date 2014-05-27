package com.kvc.joy.commons.lang.string;

/**
 * 字符串转义工具类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-9 下午9:00:18
 */
public class StringEscapeTool {

	private StringEscapeTool() {
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.StringEscapeUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	// Java and JavaScript
	// --------------------------------------------------------------------------
	/**
	 * <p>
	 * 使用java的字符串规则将指定的字符串转义
	 * </p>
	 * 
	 * <p>
	 * 能够正确的处理引号和控制字符(tab, 反斜杠, 回车, 走纸换页等)
	 * </p>
	 * 
	 * <p>
	 * 一个tab字符将被转义为{@code '\\'} 和 {@code 't'}.
	 * </p>
	 * 
	 * <p>
	 * java字符串和javascript字符串的不同仅在于, 在javascript中, 单引号和斜杠将被转义.
	 * </p>
	 * 
	 * <p>
	 * 例如:
	 * 
	 * <pre>
	 * 输入字符串: He didn't say, "Stop!"
	 * 输出字符串: He didn't say, \"Stop!\"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param input 要转义的字符串, 可以为 null
	 * @return 转义后的字符串, {@code null}将返回{@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午9:50:32
	 */
	public static String escapeJava(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.escapeJava(input);
	}

	/**
	 * <p>
	 * 使用EcmaScript的字符串规则将指定的字符串转义
	 * </p>
	 * 
	 * <p>
	 * 能够正确的处理引号和控制字符(tab, 反斜杠, 回车, 走纸换页等)
	 * </p>
	 * 
	 * <p>
	 * 一个tab字符将被转义为{@code '\\'} 和 {@code 't'}.
	 * </p>
	 * 
	 * <p>
	 * java字符串和javascript字符串的不同仅在于, 在javascript中, 单引号和斜杠将被转义.
	 * </p>
	 * 
	 * <p>
	 * EcmaScript最出名的方言为JavaScript 和 ActionScript.
	 * </p>
	 * 
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * 输入字符串: He didn't say, "Stop!"
	 * 输出字符串: He didn't say, \"Stop!\"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param input 要转义的字符串, 可以为 null
	 * @return 转义后的字符串, {@code null}将返回{@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午9:57:15
	 */
	public static String escapeEcmaScript(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.escapeEcmaScript(input);
	}

	/**
	 * <p>
	 * 解码指定的转义后java字符串
	 * </p>
	 * 
	 * <p>
	 * 例如, 将{@code '\'} 和 {@code 'n'}转为换行符, 除非在{@code '\'}前有另一个{@code '\'}
	 * </p>
	 * 
	 * @param input 要解码转义的字符串, 可以为 null
	 * @return 一个新的解码过的字符串 {@code String}, {@code null}将返回{@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午10:05:47
	 */
	public static String unescapeJava(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.unescapeJava(input);
	}

	/**
	 * <p>
	 * 解码指定的转义后EcmaScript字符串
	 * </p>
	 * 
	 * <p>
	 * 例如, 将{@code '\'} 和 {@code 'n'}转为换行符, 除非在{@code '\'}前有另一个{@code '\'}
	 * </p>
	 * 
	 * @see #unescapeJava(String)
	 * @param input 要解码转义的字符串, 可以为 null
	 * @return 一个新的解码过的字符串 {@code String}, {@code null}将返回{@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午10:07:58
	 */
	public static String unescapeEcmaScript(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.unescapeEcmaScript(input);
	}

	// HTML and XML
	// --------------------------------------------------------------------------
	/**
	 * <p>
	 * 使用HTML的实体将指定的字符串转义
	 * </p>
	 * 
	 * <p>
	 * 例如:
	 * </p>
	 * <p>
	 * <code>"bread" & "butter"</code>
	 * </p>
	 * 转义成:
	 * <p>
	 * <code>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</code>.
	 * </p>
	 * 
	 * <p>
	 * 支持所有HTML 4.0实体.请注意平时使用的(&amp;apos;) 不是一个逻辑实体, 所以它是不被支持的.
	 * </p>
	 * 
	 * @param input 要转义的{@code String}, 可以为 null
	 * @return 一个新的转义后的字符串, {@code null}将返回{@code null}
	 * 
	 * @see <a href="http://hotwired.lycos.com/webmonkey/reference/special_characters/">ISO Entities</a>
	 * @see <a href="http://www.w3.org/TR/REC-html32#latin1">HTML 3.2 Character Entities for ISO Latin-1</a>
	 * @see <a href="http://www.w3.org/TR/REC-html40/sgml/entities.html">HTML 4.0 Character entity references</a>
	 * @see <a href="http://www.w3.org/TR/html401/charset.html#h-5.3">HTML 4.01 Character References</a>
	 * @see <a href="http://www.w3.org/TR/html401/charset.html#code-position">HTML 4.01 Code positions</a>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午10:15:52
	 */
	public static String escapeHtml4(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(input);
	}

	/**
	 * <p>
	 * 使用HTML的实体将指定的字符串转义
	 * </p>
	 * <p>
	 * 只支持HTML 3.0的实体
	 * </p>
	 * 
	 * @param input 要转义的{@code String} , 可以为null
	 * @return 一个新的转义后的字符串, {@code null}将返回{@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午10:16:40
	 */
	public static String escapeHtml3(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.escapeHtml3(input);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 解码指定的转义后HTML 4.0实体字符串
	 * </p>
	 * 
	 * <p>
	 * 例如, 字符串 "&amp;lt;Fran&amp;ccedil;ais&amp;gt;" 将转为 "&lt;Fran&ccedil;ais&gt;"
	 * </p>
	 * 
	 * <p>
	 * 如果某个实体不被识别, 将保持原样, 并逐字插入到结果串中.如: "&amp;gt;&amp;zzzz;x" 将转为 "&gt;&amp;zzzz;x".
	 * </p>
	 * 
	 * @param input 要解码转义的字符串, 可以为 null
	 * @return 一个新的解码过的字符串 {@code String}, {@code null}将返回{@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午10:21:09
	 */
	public static String unescapeHtml4(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(input);
	}

	/**
	 * <p>
	 * 解码指定的转义后HTML 3.0实体字符串
	 * </p>
	 * 
	 * @param input 要解码转义的字符串, 可以为 null
	 * @return 一个新的解码过的字符串 {@code String}, {@code null}将返回{@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午10:21:38
	 */
	public static String unescapeHtml3(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3(input);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 使用XML的实体将指定的字符串转义
	 * </p>
	 * 
	 * <p>
	 * 例如: <tt>"bread" & "butter"</tt> => <tt>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</tt>.
	 * </p>
	 * 
	 * <p>
	 * 仅支持5个基本的XML实体 (gt, lt, quot, amp, apos). 不支持 DTDs 或外部实体.
	 * </p>
	 * 
	 * <p>
	 * 请注意大于0x7f的Unicode字符不会被转义. 如果想被转义, 可以这样做:
	 * {@code StringEscapeUtils.ESCAPE_XML.with( NumericEntityEscaper.between(0x7f, Integer.MAX_VALUE) );}
	 * </p>
	 * 
	 * @param input 要转义的 {@code String} , 可以为null
	 * @return 一个新的转义后的字符串, {@code null}将返回{@code null}
	 * @see #unescapeXml(java.lang.String)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午10:26:08
	 */
	public static String escapeXml(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.escapeXml(input);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 解码指定的转义后XML实体字符串
	 * </p>
	 * 
	 * <p>
	 * 仅支持5个基本的XML实体 (gt, lt, quot, amp, apos). 不支持 DTDs 或外部实体.
	 * </p>
	 * 
	 * <p>
	 * 请注意: 数值的 \\u Unicode 编码不会被解码为对应的Unicode字符. 这在未来的版本可能解决.
	 * </p>
	 * 
	 * @param input 要解码转义的字符串, 可以为 null
	 * @return 一个新的解码过的字符串 {@code String}, {@code null}将返回{@code null}
	 * @see #escapeXml(String)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午10:28:22
	 */
	public static String unescapeXml(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.unescapeXml(input);
	}

	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 如果需要, 将csv的列用双引号包起来
	 * </p>
	 * 
	 * <p>
	 * 如果值包含逗号, 换行或双引号, 字符串的值被被双引号包起来
	 * </p>
	 * </p>
	 * 
	 * <p>
	 * 值中出现的双引号将用另一个额外的双引号转义
	 * </p>
	 * 
	 * <p>
	 * 如果值未包含逗号, 换行或双引号, 字符串将原样返回
	 * </p>
	 * </p>
	 * 
	 * 见 <a href="http://en.wikipedia.org/wiki/Comma-separated_values">Wikipedia</a> 和 <a
	 * href="http://tools.ietf.org/html/rfc4180">RFC 4180</a>.
	 * 
	 * @param input CSV列的值, 可以 null
	 * @return 转义后的字符串, {@code null} 将返回 null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午10:35:07
	 */
	public static String escapeCsv(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.escapeCsv(input);
	}

	/**
	 * <p>
	 * 解码转义过的csv列的值
	 * </p>
	 * 
	 * <p>
	 * 如果值以双引号包住, 并且包含逗号, 换行或双引号, 这样双引号将被移除
	 * </p>
	 * 
	 * <p>
	 * 两个连在一起的双引号将被去掉一个
	 * </p>
	 * 
	 * <p>
	 * 如果值没有以双引号包住, 或者有, 但是不包含含逗号, 换行或双引号, 这样字符串将原样返回
	 * </p>
	 * 
	 * 见 <a href="http://en.wikipedia.org/wiki/Comma-separated_values">Wikipedia</a> 和 <a
	 * href="http://tools.ietf.org/html/rfc4180">RFC 4180</a>.
	 * 
	 * @param input CSV列的值, 可以 null
	 * @return 解码过的CSV列的值
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午10:40:45
	 */
	public static String unescapeCsv(String input) {
		return org.apache.commons.lang3.StringEscapeUtils.unescapeCsv(input);
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.StringEscapeUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}
