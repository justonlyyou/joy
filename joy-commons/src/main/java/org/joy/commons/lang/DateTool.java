package org.joy.commons.lang;

import org.joy.commons.exception.SystemException;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.math.NumberTool;
import org.apache.commons.collections.FastHashMap;
import org.joy.commons.log.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期操作工具类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-28 下午10:47:23
 */
public class DateTool {

	// 常用时间常量
	/** 一秒的毫秒数 */
	public static final int MILLISECONDS_OF_SECOND = 1000;
	/** 一分钟的秒数 */
	public static final int SECONDS_OF_MINUTE = 60;
	/** 一小时的秒数 */
	public static final int SECONDS_OF_HOUR = 60 * SECONDS_OF_MINUTE;
	/** 一天的秒数 */
	public static final int SECONDS_OF_DAY = 24 * SECONDS_OF_HOUR;

	// 常用无分隔符的日期格式常量，一般用于数据库存储
	/** 四位年份＋两位月份 */
	public static final String UNFMT_yyyyMM = "yyyyMM";
	/** 四位年份＋两位月份＋两位天序 */
	public static final String UNFMT_yyyyMMdd = "yyyyMMdd";
	/** 四位年份＋两位月份＋两位天序＋两位24进制的小时数＋两位分钟数＋两位秒数 */
	public static final String UNFMT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	/** 四位年份＋两位月份＋两位天序＋两位24进制的小时数＋两位分钟数＋两位秒数＋三位毫秒数 */
	public static final String UNFMT_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	/** 两位24进制的小时数＋两位分钟数＋两位秒数 */
	public static final String UNFMT_HHmmss = "HHmmss";
	/** 两位24进制的小时数＋两位分钟数 */
	public static final String UNFMT_HHmm = "HHmm";

	// 常用有分隔符的日期格式常量，一般用于展现
	/** 如2013-04 */
	public static final String FMT_HYPHEN_MONTH = "yyyy-MM";
	/** 如2013-04-28 */
	public static final String FMT_HYPHEN_DAY = "yyyy-MM-dd";
	/** 如2013-04-28 22:25 */
	public static final String FMT_HYPHEN_DAY_CLN_MINUTE = "yyyy-MM-dd HH:mm";
	/** 如2013-04-28 22:25:32 */
	public static final String FMT_HYPHEN_DAY_CLN_SECOND = "yyyy-MM-dd HH:mm:ss";
	/** 如2013年04月 */
	public static final String FMT_CN_MONTH = "yyyy年MM月";
	/** 如2013年04月28日 */
	public static final String FMT_CN_DAY = "yyyy年MM月dd";
	/** 如2013年04月28日 22时25分 */
	public static final String FMT_CN_DAY_CN_MINUTE = "yyyy年MM月dd日 HH时mm分";
	/** 如2013年04月28日 22时25分32秒 */
	public static final String FMT_CN_DAY_CN_SECOND = "yyyy年MM月dd日 HH时mm分ss秒";
	/** 如22:25:32秒 */
	public static final String FMT_CLN_SECOND = "HH:mm:ss";
	/** 如22:25 */
	public static final String FMT_CLN_MINUTE = "HH:mm";
	/** 如22时25分32秒 */
	public static final String FMT_CN_SECOND = "HH时mm分ss秒";
	/** 如22时25分 */
	public static final String FMT_CN_MINUTE = "HH时mm分";

	// 用于缓存SimpleDateFormat，以便快速索引
	private static final FastHashMap datePartenMap = new FastHashMap();
	// 用于解决SimpleDateFormat的线程安全问题
	private static final ThreadLocal<FastHashMap> threadLocal = new ThreadLocal<FastHashMap>() {
		protected synchronized FastHashMap initialValue() {
			return (FastHashMap) datePartenMap.clone();
		}
	};
	protected static final Log logger = LogFactory.getLog(DateTool.class);

	static {
		Set<String> fmtSet = new HashSet<String>();
		fmtSet.add(UNFMT_yyyyMM);
		fmtSet.add(UNFMT_yyyyMMdd);
		fmtSet.add(UNFMT_yyyyMMddHHmmss);
		fmtSet.add(UNFMT_yyyyMMddHHmmssSSS);
		fmtSet.add(UNFMT_HHmmss);
		fmtSet.add(UNFMT_HHmm);
		fmtSet.add(FMT_HYPHEN_MONTH);
		fmtSet.add(FMT_HYPHEN_DAY);
		fmtSet.add(FMT_HYPHEN_DAY_CLN_MINUTE);
		fmtSet.add(FMT_HYPHEN_DAY_CLN_SECOND);
		fmtSet.add(FMT_CN_MONTH);
		fmtSet.add(FMT_CN_DAY);
		fmtSet.add(FMT_CN_DAY_CN_MINUTE);
		fmtSet.add(FMT_CN_DAY_CN_SECOND);
		fmtSet.add(FMT_CLN_SECOND);
		fmtSet.add(FMT_CLN_MINUTE);
		fmtSet.add(FMT_CN_SECOND);
		fmtSet.add(FMT_CN_MINUTE);

		datePartenMap.setFast(true);
		for (String fmt : fmtSet) {
			addDateFormat(fmt);
		}
	}

	private DateTool() {
	}

	/**
	 * <p>
	 * 添加非默认的日期格式到缓存 <br>
	 * 提高解析效率并获得线程安全。如果不将非默认的日期格式添加到缓存，在每次调用解析方法时将新建一个SimpleDateFormat， 这样在进行批量解析时效率分比较低，开销也较大，但还是线程安全的。
	 * </p>
	 * 
	 * @param fmt 日期格式
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 下午10:47:23
	 * 
	 */
	public static void addDateFormat(String fmt) {
		datePartenMap.put(fmt, new SimpleDateFormat(fmt));
	}

	/**
	 * 按给定的格式解析指定的日期串
	 * 
	 * @param dateStr 日期串
	 * @param fmt 格式
	 * @return 日期对象
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月15日 下午9:22:14
	 */
	 public static Date parseDate(String dateStr, String fmt) {
		 if (StringTool.isBlank(dateStr)) {
			 return null;
		 }
		 try {
			 return getFormat(fmt).parse(dateStr);	
		} catch (Exception e) {
			throw new SystemException(e);
		}
	 }
	 
	 

	/**
	 * 根据指定的格式对日期进行格式化
	 * 
	 * @param date 日期对象
	 * @param fmt 格式化串(使用本类的以"UNFMT_"或"FMT_"打头的常量)
	 * @return 格式化后的日期字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 下午11:54:12
	 */
	public static String formatDate(Date date, String fmt) {
		if (date == null) {
			return "";
		}
		return getFormat(fmt).format(date);
	}
	
	/**
	 * 根据指定的输入/输出格式对字符串形式的日期进行格式化
	 * 
	 * @param dateStr 字符串形式的日期
	 * @param inFmt 输入格式
	 * @param outFmt 输出格式
	 * @return 格式化后的日期串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月28日 下午11:30:41
	 */
	public static String formatDate(String dateStr, String inFmt, String outFmt) {
		Date date = parseDate(dateStr, inFmt);
		if (date == null) {
			return "";
		}
		return getFormat(outFmt).format(date);
	}
	
	private static DateFormat getFormat(String fmt) {
		DateFormat dateFormat = (DateFormat) threadLocal.get().get(fmt);
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat(fmt);
		}
		return dateFormat;
	}

	/**
	 * <p>
	 * 交换两个日期
	 * </p>
	 * 
	 * @param date1 日期1
	 * @param date2 日期2
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 下午11:15:11
	 */
	public static void swapDates(Date date1, Date date2) {
		if (date1 != null && date2 != null) {
			long dateA = date1.getTime();
			long dateB = date2.getTime();
			date1.setTime(dateB);
			date2.setTime(dateA);
		}
	}

	/**
	 * <p>
	 * 根据出生日期，计算出在某一个日期的年龄(虚岁)
	 * </p>
	 * 
	 * @param birthday 出生日期
	 * @param focus 参照日期, 为null当作当前日期
	 * @return 返回focus那一天出生日期为birthday的年龄(虚岁)，如果出生日期参数为null或birthday大于focus则返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 下午11:44:32
	 */
	public static int getNominalAge(Date birthday, Date focus) {
		if(focus == null) {
			focus = new Date();
		}
		if (null == birthday || birthday.after(focus)) {
			return -1;
		}

		int ibdYear = NumberTool.toInt(formatDate(birthday, "yyyy"), -1);
		int idate2Year = NumberTool.toInt(formatDate(focus, "yyyy"), -1);

		if (ibdYear < 0 || idate2Year < 0 || ibdYear > idate2Year) {
			return -1;
		}

		return idate2Year - ibdYear + 1;
	}

	/**
	 * <p>
	 * 根据出生日期，计算出当前的年龄(虚岁)
	 * </p>
	 * 
	 * @param birthday 出生日期
	 * @return 返回出生日期为birthday的年龄(虚岁)，如果参数为null则返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 下午11:44:32
	 */
	public static int getNominalAge(Date birthday) {
		return getNominalAge(birthday, null);
	}

	/**
	 * <p>
	 * 根据出生日期，计算出在某一个日期的年龄(实岁，小数点后不计)
	 * </p>
	 * 
	 * @param birthday 出生日期
	 * @param focus 参照日期, 为null当作当前日期
	 * @return 返回focus那一天出生日期为birthday的年龄(实岁)，如果出生日期参数为null或birthday大于focus则返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-29 下午3:58:26
	 */
	public static int getActualAge(Date birthday, Date focus) {
		if(focus == null) {
			focus = new Date();
		}
		if (birthday == null || birthday.after(focus)) {
			return -1;
		} else {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(birthday);
			int bornYear = cal1.get(Calendar.YEAR);
			int bornMonth = cal1.get(Calendar.MONTH);
			
			Calendar cal = Calendar.getInstance();
            cal.setTime(focus);
            int currYear = cal.get(Calendar.YEAR);
			int currMonth = cal.get(Calendar.MONTH);
			
			int age = currYear - bornYear;
			age -= (currMonth < bornMonth) ? 1 : 0;
			return age;
		}
	}
	
	/**
	 * <p>
	 * 根据出生日期，计算出当前的年龄(周岁)
	 * </p>
	 * 
	 * @param birthday 出生日期
	 * @return 返回出生日期为birthday的年龄(周岁)，如果参数为null则返回-1
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-29 下午3:59:36
	 */
	public static int getActualAge(Date birthday) {
		return getActualAge(birthday, null);
	}

	/**
	 * <p>
	 * 计算两个日期间的天数(不足一天按一天算)
	 * </p>
	 * 
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return 间隔的天数
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 下午11:28:58
	 */
	public static int daysBetween(Date date1, Date date2) {
		int result = -1;
		if (date1 != null && date2 != null) {
			date1 = truncate(date1, Calendar.DATE);
			date2 = truncate(date2, Calendar.DATE);
			long off = date1.getTime() - date2.getTime(); // 毫秒数差值
			result = (int) ((Math.abs(off)) / (SECONDS_OF_DAY * MILLISECONDS_OF_SECOND)); // 获取天数的差值
		}
		return result;
	}
	
	/**
	 * 返回指定时间与现在相差的毫秒数，指定时间在未来返回正数，在过去返回负数
	 * 
	 * @param date 指定的日期
	 * @return 毫秒数
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月15日 下午9:10:08
	 */
	public static long timesToNow(Date date) {
		return date.getTime() - new Date().getTime(); 
	}

	/**
	 * <p>
	 * 获取系统当前时间,并用指定格式格式化
	 * </p>
	 * 
	 * @param fmt 格式
	 * @return 格式化后的系统当前时间
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-29 上午11:01:04
	 */
	public static String currentDate(String fmt) {
		Date now = new Date();
		return formatDate(now, fmt);
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.DateUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 检查两个Date对象是否为同一天（忽略时间）
	 * </p>
	 * 
	 * <p>
	 * 2002-3-28 13:45 和　2002-3-28 06:01 将返回true。 2002-3-28 13:45 和　2002-3-12 13:45 将返回false。
	 * </p>
	 * 
	 * @param date1 第一个Date对象，不会被修改，不能为null
	 * @param date2 第二个Date对象，不会被修改，不能为null
	 * @return true：如果两个Date对象代表相同的一天
	 * @throws IllegalArgumentException 如果两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-25 下午11:36:23
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
	}

	/**
	 * <p>
	 * 检查两个Calendar对象是否为同一天（忽略时间）
	 * </p>
	 * 
	 * <p>
	 * 2002-3-28 13:45 和　2002-3-28 06:01 将返回true。 2002-3-28 13:45 和　2002-3-12 13:45 将返回false。
	 * </p>
	 * 
	 * @param cal1 第一个Calendar对象，不会被修改，不能为null
	 * @param cal2 第二个Calendar对象，不会被修改，不能为null
	 * @return true：如果两个Calendar对象代表相同的一天
	 * @throws IllegalArgumentException 如果两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-25 下午11:37:23
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		return org.apache.commons.lang3.time.DateUtils.isSameDay(cal1, cal2);
	}

	/**
	 * <p>
	 * 检查两个Date对象是否为同一时间
	 * </p>
	 * 
	 * <p>
	 * 该方法比较两个Date对象的毫秒数
	 * </p>
	 * 
	 * @param date1 第一个Date对象，不会被修改，不能为null
	 * @param date2 第二个Date对象，不会被修改，不能为null
	 * @return true：如果两个Date对象代表同一时间
	 * @throws IllegalArgumentException 如果两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-25 下午11:４1:23
	 */
	public static boolean isSameInstant(Date date1, Date date2) {
		return org.apache.commons.lang3.time.DateUtils.isSameInstant(date1, date2);
	}

	/**
	 * <p>
	 * 检查两个Calendar对象是否为同一时间
	 * </p>
	 * 
	 * <p>
	 * 该方法比较两个Calendar对象的毫秒数
	 * </p>
	 * 
	 * @param cal1 第一个Calendar对象，不会被修改，不能为null
	 * @param cal2 第二个Calendar对象，不会被修改，不能为null
	 * @return true：如果两个Calendar对象代表同一时间
	 * @throws IllegalArgumentException 如果两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-25 下午11:４2:23
	 */
	public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
		return org.apache.commons.lang3.time.DateUtils.isSameInstant(cal1, cal2);
	}

	/**
	 * <p>
	 * 检查两个Calendar对象是否代表相同的本地时间
	 * </p>
	 * 
	 * <p>
	 * 该方法比较两个对象各个时间域的值。
	 * </p>
	 * 
	 * @param cal1 第一个Calendar对象，不会被修改，不能为null
	 * @param cal2 第二个Calendar对象，不会被修改，不能为null
	 * @return true：如果两个Calendar对象代表同一时间
	 * @throws IllegalArgumentException 如果两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-25 下午11:４5:23
	 */
	public static boolean isSameLocalTime(Calendar cal1, Calendar cal2) {
		return org.apache.commons.lang3.time.DateUtils.isSameLocalTime(cal1, cal2);
	}

	/**
	 * <p>
	 * 用各种不同的解析模板解析代表日期的字符串(宽松)
	 * </p>
	 * 
	 * <p>
	 * 解析过程将按顺序尝试每一个解析模板。如果能够解析整个字符串，解析过程将成功终止于当前解析模板。 解析器将宽松地对待日期的解析。
	 * </p>
	 * 
	 * @param str 要解析的日期字符串, 不能为null
	 * @param parsePatterns 要使用的日期模板数组, 见SimpleDateFormat类, 不能为null
	 * @return 解析后的日期对象。如果没有匹配的解析模板，将返回null
	 * @throws IllegalArgumentException 如果两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-25 下午11:57:32
	 */
	public static Date parseDate(String str, String... parsePatterns) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(str, parsePatterns);
		} catch (Exception e) {
			logger.error(e, e.getMessage());
		}
		return null;
	}

	/**
	 * <p>
	 * 用各种不同的解析模板解析代表日期的字符串（严格）
	 * </p>
	 * 
	 * <p>
	 * 解析过程将按顺序尝试每一个解析模板。如果能够解析整个字符串，解析过程将成功终止于当前解析模板。 <br>
	 * 解析器将严格地对待日期的解析，比如它不允许这样的日期："February 942, 1996".
	 * </p>
	 * 
	 * @param 要解析的日期字符串 , 不能为null
	 * @param parsePatterns 要使用的日期模板数组, 见SimpleDateFormat类, 不能为null
	 * @return 解析后的日期对象。如果没有匹配的解析模板，将返回null
	 * @throws IllegalArgumentException 如果两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-25 下午11:59:32
	 */
	public static Date parseDateStrictly(String str, String... parsePatterns) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDateStrictly(str, parsePatterns);
		} catch (Exception e) {
			logger.error(e, e.getMessage());
		}
		return null;
	}

	/**
	 * <p>
	 * 对Date的年份域加上一个数值，返回一个新的Date对象。原来的Date不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要加上数值，可以为负数
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:05:32
	 */
	public static Date addYears(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.addYears(date, amount);
	}

	/**
	 * <p>
	 * 对Date的月份域加上一个数值，返回一个新的Date对象。原来的Date不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要加上数值，可以为负数
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:06:32
	 */
	public static Date addMonths(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.addMonths(date, amount);
	}

	/**
	 * <p>
	 * 对Date的星期域加上一个数值，返回一个新的Date对象。原来的Date不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要加上数值，可以为负数
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:07:32
	 */
	public static Date addWeeks(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.addWeeks(date, amount);
	}

	/**
	 * <p>
	 * 对Date的天域加上一个数值，返回一个新的Date对象。原来的Date不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要加上数值，可以为负数
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:07:59
	 */
	public static Date addDays(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.addDays(date, amount);
	}

	/**
	 * <p>
	 * 对Date的小时域加上一个数值，返回一个新的Date对象。原来的Date不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要加上数值，可以为负数
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:08:59
	 */
	public static Date addHours(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.addHours(date, amount);
	}

	/**
	 * <p>
	 * 对Date的分钟域加上一个数值，返回一个新的Date对象。原来的Date不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要加上数值，可以为负数
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:09:39
	 */
	public static Date addMinutes(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.addMinutes(date, amount);
	}

	/**
	 * <p>
	 * 对Date的秒域加上一个数值，返回一个新的Date对象。原来的Date不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要加上数值，可以为负数
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:09:59
	 */
	public static Date addSeconds(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.addSeconds(date, amount);
	}

	/**
	 * <p>
	 * 对Date的毫秒域加上一个数值，返回一个新的Date对象。原来的Date不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要加上数值，可以为负数
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:10:19
	 */
	public static Date addMilliseconds(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.addMilliseconds(date, amount);
	}

	/**
	 * <p>
	 * 设置日期的年份域，并返回新的日期对象。原来的日期对象不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要设置的数值
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:13:22
	 */
	public static Date setYears(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.setYears(date, amount);
	}

	/**
	 * <p>
	 * 设置日期的月份域，并返回新的日期对象。原来的日期对象不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要设置的数值
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:14:21
	 */
	public static Date setMonths(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.setMonths(date, amount);
	}

	/**
	 * <p>
	 * 设置日期的天域，并返回新的日期对象。原来的日期对象不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要设置的数值
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:14:32
	 */
	public static Date setDays(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.setDays(date, amount);
	}

	/**
	 * <p>
	 * 设置日期的小时域，并返回新的日期对象。原来的日期对象不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要设置的数值
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:14:55
	 */
	public static Date setHours(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.setHours(date, amount);
	}

	/**
	 * <p>
	 * 设置日期的分钟域，并返回新的日期对象。原来的日期对象不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要设置的数值
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:14:59
	 */
	public static Date setMinutes(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.setMinutes(date, amount);
	}

	/**
	 * <p>
	 * 设置日期的秒域，并返回新的日期对象。原来的日期对象不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要设置的数值
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:15:09
	 */
	public static Date setSeconds(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.setSeconds(date, amount);
	}

	/**
	 * <p>
	 * 设置日期的毫秒域，并返回新的日期对象。原来的日期对象不会被改变。
	 * </p>
	 * 
	 * @param date Date对象，不能为null
	 * @param amount 要设置的数值
	 * @return 新的Date对象
	 * @throws IllegalArgumentException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:15:19
	 */
	public static Date setMilliseconds(Date date, int amount) {
		return org.apache.commons.lang3.time.DateUtils.setMilliseconds(date, amount);
	}

	/**
	 * <p>
	 * 将Date对象转化为Calendar对象
	 * </p>
	 * 
	 * @param date 待转化的Date对象
	 * @return Calendar对象
	 * @throws NullPointerException 如果Date参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 上午12:16:19
	 */
	public static Calendar toCalendar(Date date) {
		return org.apache.commons.lang3.time.DateUtils.toCalendar(date);
	}

	/**
	 * <p>
	 * 日期取整（日期精度调节）
	 * </p>
	 * 
	 * <p>
	 * 例如，如果日期为2002-3-28 13:45:01.231，域为HOUR，该方法将返回2002-3-28 14:00:00.000， 如果域为ＭONTH，将返回2002-4-1 ０:00:00.000，
	 * </p>
	 * 
	 * <p>
	 * 对于一个时区的日期处理夏令时的变化，四舍五入到Calendar.HOUR_OF_DAY的表现如下。 假设日光节约时间开始于3月30日02:00。取整一个超过此时间的日期，会产生以下值：
	 * <ul>
	 * <li>2003-3-30 01:10 取整为 2003-3-30 01:00</li>
	 * <li>2003-3-30 01:40 取整为 2003-3-30 03:00</li>
	 * <li>2003-3-30 02:10 取整为 2003-3-30 03:00</li>
	 * <li>2003-3-30 02:40 取整为 2003-3-30 04:00</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 要处理的日期, 不能为null
	 * @param field 通过{@code Calendar}或{@code SEMI_MONTH}指定的域
	 * @return 取整后的日期对象, 不会为null
	 * @throws IllegalArgumentException 如果日期参数为null
	 * @throws ArithmeticException 如果年超过2.8亿
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 下午10:44:27
	 */
	public static Date round(Date date, int field) {
		return org.apache.commons.lang3.time.DateUtils.round(date, field);
	}

	/**
	 * <p>
	 * 日期取整（日期精度调节）
	 * </p>
	 * 
	 * <p>
	 * 例如，如果日期为2002-3-28 13:45:01.231，域为HOUR，该方法将返回2002-3-28 14:00:00.000， 如果域为ＭONTH，将返回2002-4-1 ０:00:00.000，
	 * </p>
	 * 
	 * <p>
	 * 对于一个时区的日期处理夏令时的变化，四舍五入到Calendar.HOUR_OF_DAY的表现如下。 假设日光节约时间开始于3月30日02:00。取整一个超过此时间的日期，会产生以下值：
	 * <ul>
	 * <li>2003-3-30 01:10 取整为 2003-3-30 01:00</li>
	 * <li>2003-3-30 01:40 取整为 2003-3-30 03:00</li>
	 * <li>2003-3-30 02:10 取整为 2003-3-30 03:00</li>
	 * <li>2003-3-30 02:40 取整为 2003-3-30 04:00</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 要处理的日期, 不能为null
	 * @param field 通过{@code Calendar}或{@code SEMI_MONTH}指定的域
	 * @return 取整后的日期对象, 不会为null
	 * @throws IllegalArgumentException 如果日期参数为null
	 * @throws ArithmeticException 如果年超过2.8亿
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 下午10:45:27
	 */
	public static Calendar round(Calendar date, int field) {
		return org.apache.commons.lang3.time.DateUtils.round(date, field);
	}

	/**
	 * <p>
	 * 日期取整（日期精度调节）
	 * </p>
	 * 
	 * <p>
	 * 例如，如果日期为2002-3-28 13:45:01.231，域为HOUR，该方法将返回2002-3-28 14:00:00.000， 如果域为ＭONTH，将返回2002-4-1 ０:00:00.000，
	 * </p>
	 * 
	 * <p>
	 * 对于一个时区的日期处理夏令时的变化，四舍五入到Calendar.HOUR_OF_DAY的表现如下。 假设日光节约时间开始于3月30日02:00。取整一个超过此时间的日期，会产生以下值：
	 * <ul>
	 * <li>2003-3-30 01:10 取整为 2003-3-30 01:00</li>
	 * <li>2003-3-30 01:40 取整为 2003-3-30 03:00</li>
	 * <li>2003-3-30 02:10 取整为 2003-3-30 03:00</li>
	 * <li>2003-3-30 02:40 取整为 2003-3-30 04:00</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 要处理的日期,可以为{@code Date}或{@code Calendar}，不能为null
	 * @param field 通过{@code Calendar}或{@code SEMI_MONTH}指定的域
	 * @return 取整后的日期对象, 不会为null
	 * @throws IllegalArgumentException 如果日期参数为null
	 * @throws ClassCastException 如果对象类型不是{@code Date}或{@code Calendar}
	 * @throws ArithmeticException 如果年超过2.8亿
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 下午10:49:22
	 */
	public static Date round(Object date, int field) {
		return org.apache.commons.lang3.time.DateUtils.round(date, field);
	}

	/**
	 * <p>
	 * 日期向下取整
	 * </p>
	 * 
	 * <p>
	 * 例如，如果日期为2002-3-28 13:45:01.231，域为HOUR，该方法将返回2002-3-28 13:00:00.000， 如果域为ＭONTH，将返回2002-3-1 ０:00:00.000，
	 * </p>
	 * 
	 * @param date 要处理的日期, 不能为null
	 * @param field 通过{@code Calendar}或{@code SEMI_MONTH}指定的域
	 * @return t取整后的日期对象, 不会为null
	 * @throws IllegalArgumentException 如果日期参数为null
	 * @throws ArithmeticException 如果年超过2.8亿
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 下午10:55:02
	 */
	public static Date truncate(Date date, int field) {
		return org.apache.commons.lang3.time.DateUtils.truncate(date, field);
	}

	/**
	 * <p>
	 * 日期向下取整
	 * </p>
	 * 
	 * <p>
	 * 例如，如果日期为2002-3-28 13:45:01.231，域为HOUR，该方法将返回2002-3-28 13:00:00.000， 如果域为ＭONTH，将返回2002-3-1 ０:00:00.000，
	 * </p>
	 * 
	 * @param date 要处理的日期, 不能为null
	 * @param field 通过{@code Calendar}或{@code SEMI_MONTH}指定的域
	 * @return t取整后的日期对象, 不会为null
	 * @throws IllegalArgumentException 如果日期参数为null
	 * @throws ArithmeticException 如果年超过2.8亿
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 下午10:56:02
	 */
	public static Calendar truncate(Calendar date, int field) {
		return org.apache.commons.lang3.time.DateUtils.truncate(date, field);
	}

	/**
	 * <p>
	 * 日期向下取整
	 * </p>
	 * 
	 * <p>
	 * 例如，如果日期为2002-3-28 13:45:01.231，域为HOUR，该方法将返回2002-3-28 13:00:00.000， 如果域为ＭONTH，将返回2002-3-1 ０:00:00.000，
	 * </p>
	 * 
	 * @param date 要处理的日期,可以为{@code Date}或{@code Calendar}，不能为null
	 * @param field 通过{@code Calendar}或{@code SEMI_MONTH}指定的域
	 * @return t取整后的日期对象, 不会为null
	 * @throws IllegalArgumentException 如果日期参数为null
	 * @throws ClassCastException 如果对象类型不是{@code Date}或{@code Calendar}
	 * @throws ArithmeticException 如果年超过2.8亿
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 下午10:57:05
	 */
	public static Date truncate(Object date, int field) {
		return org.apache.commons.lang3.time.DateUtils.truncate(date, field);
	}

	/**
	 * <p>
	 * 日期向上取整
	 * </p>
	 * 
	 * <p>
	 * 例如，如果日期为2002-3-28 13:45:01.231，域为HOUR，该方法将返回2002-3-28 14:00:00.000， 如果域为ＭONTH，将返回2002-4-1 ０:00:00.000，
	 * </p>
	 * 
	 * @param 要处理的日期 , 不能为null
	 * @param field 通过{@code Calendar}或{@code SEMI_MONTH}指定的域
	 * @return t取整后的日期对象, 不会为null
	 * @throws IllegalArgumentException 如果日期参数为null
	 * @throws ArithmeticException 如果年超过2.8亿
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 下午11:01:15
	 */
	public static Date ceiling(Date date, int field) {
		return org.apache.commons.lang3.time.DateUtils.ceiling(date, field);
	}

	/**
	 * <p>
	 * 日期向上取整
	 * </p>
	 * 
	 * <p>
	 * 例如，如果日期为2002-3-28 13:45:01.231，域为HOUR，该方法将返回2002-3-28 14:00:00.000， 如果域为ＭONTH，将返回2002-4-1 ０:00:00.000，
	 * </p>
	 * 
	 * @param 要处理的日期 , 不能为null
	 * @param field 通过{@code Calendar}或{@code SEMI_MONTH}指定的域
	 * @return t取整后的日期对象, 不会为null
	 * @throws IllegalArgumentException 如果日期参数为null
	 * @throws ArithmeticException 如果年超过2.8亿
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 下午11:01:35
	 */
	public static Calendar ceiling(Calendar date, int field) {
		return org.apache.commons.lang3.time.DateUtils.ceiling(date, field);
	}

	/**
	 * <p>
	 * 日期向上取整
	 * </p>
	 * 
	 * <p>
	 * 例如，如果日期为2002-3-28 13:45:01.231，域为HOUR，该方法将返回2002-3-28 14:00:00.000， 如果域为ＭONTH，将返回2002-4-1 ０:00:00.000，
	 * </p>
	 * 
	 * @param 要处理的日期 ,可以为{@code Date}或{@code Calendar}，不能为null
	 * @param field 通过{@code Calendar}或{@code SEMI_MONTH}指定的域
	 * @return t取整后的日期对象, 不会为null
	 * @throws IllegalArgumentException 如果日期参数为null
	 * @throws ClassCastException 如果对象类型不是{@code Date}或{@code Calendar}
	 * @throws ArithmeticException 如果年超过2.8亿
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-26 下午11:05:15
	 */
	public static Date ceiling(Object date, int field) {
		return org.apache.commons.lang3.time.DateUtils.ceiling(date, field);
	}

	/**
	 * <p>
	 * 构造一个由指定日期范围中的每一天构成的<code>Iterator</code>
	 * </p>
	 * 
	 * <p>
	 * 比如，传递的参数为“2002-7-4（星期四）”　和<code>RANGE_MONTH_SUNDAY</code>，将返回从 “2002-6-30 星期天”到“2002-８-3
	 * （星期六）”中间的每一天的Calendar的实例的<code>Iterator</code>
	 * </p>
	 * 
	 * @param focus 日期, 不能为null
	 * @param rangeStyle 范围类型常量。必须为下面中的一个： {@link DateTool#RANGE_MONTH_SUNDAY}, {@link DateTool#RANGE_MONTH_MONDAY},
	 *            {@link DateTool#RANGE_WEEK_SUNDAY}, {@link DateTool#RANGE_WEEK_MONDAY},
	 *            {@link DateTool#RANGE_WEEK_RELATIVE}, {@link DateTool#RANGE_WEEK_CENTER}
	 * @return 日期迭代器
	 * @throws IllegalArgumentException 如果日期参数为null或范围类型参数非法
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-27 下午11:35:40
	 */
	public static Iterator<Calendar> iterator(Date focus, int rangeStyle) {
		return org.apache.commons.lang3.time.DateUtils.iterator(focus, rangeStyle);
	}

	/**
	 * <p>
	 * 构造一个由指定日期范围中的每一天构成的<code>Iterator</code>
	 * </p>
	 * 
	 * <p>
	 * 比如，传递的参数为“2002-7-4（星期四）”　和<code>RANGE_MONTH_SUNDAY</code>，将返回从 “2002-6-30 星期天”到“2002-８-3
	 * （星期六）”中间的每一天的Calendar的实例的<code>Iterator</code>
	 * </p>
	 * 
	 * @param focus 日期, 不能为null
	 * @param rangeStyle 范围类型常量。必须为下面中的一个： {@link DateTool#RANGE_MONTH_SUNDAY}, {@link DateTool#RANGE_MONTH_MONDAY},
	 *            {@link DateTool#RANGE_WEEK_SUNDAY}, {@link DateTool#RANGE_WEEK_MONDAY},
	 *            {@link DateTool#RANGE_WEEK_RELATIVE}, {@link DateTool#RANGE_WEEK_CENTER}
	 * @return 日期迭代器
	 * @throws IllegalArgumentException 如果日期参数为null或范围类型参数非法
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-27 下午11:36:04
	 */
	public static Iterator<Calendar> iterator(Calendar focus, int rangeStyle) {
		return org.apache.commons.lang3.time.DateUtils.iterator(focus, rangeStyle);
	}

	/**
	 * <p>
	 * 构造一个由指定日期范围中的每一天构成的<code>Iterator</code>
	 * </p>
	 * 
	 * <p>
	 * 比如，传递的参数为“2002-7-4（星期四）”　和<code>RANGE_MONTH_SUNDAY</code>，将返回从 “2002-6-30 星期天”到“2002-８-3
	 * （星期六）”中间的每一天的Calendar的实例的<code>Iterator</code>
	 * </p>
	 * 
	 * @param focus 日期,可以为{@code Date}或{@code Calendar}，不能为null
	 * @param rangeStyle 范围类型常量。必须为下面中的一个： {@link DateTool#RANGE_MONTH_SUNDAY}, {@link DateTool#RANGE_MONTH_MONDAY},
	 *            {@link DateTool#RANGE_WEEK_SUNDAY}, {@link DateTool#RANGE_WEEK_MONDAY},
	 *            {@link DateTool#RANGE_WEEK_RELATIVE}, {@link DateTool#RANGE_WEEK_CENTER}
	 * @return 日期迭代器
	 * @throws IllegalArgumentException 如果日期参数为null或范围类型参数非法
	 * @throws ClassCastException 如果对象类型不是{@code Date}或{@code Calendar}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-27 下午11:39:14
	 */
	public static Iterator<?> iterator(Object focus, int rangeStyle) {
		return org.apache.commons.lang3.time.DateUtils.iterator(focus, rangeStyle);
	}

	/**
	 * <p>
	 * 返回片段内的毫秒数。大于该片段的所有日期域将被忽略。
	 * </p>
	 * 
	 * <p>
	 * 
	 * 获取任何日期的毫秒将只返回当前秒的毫秒数(0-999)。该方法将取得任何片断的毫秒数。 例如，如果你想要计算今天过去的毫秒数，你的片断为Calendar.DATE或Calendar.DAY_OF_YEAR，
	 * 其结果将是所有过去的小时、分、秒的毫秒数。
	 * </p>
	 * 
	 * <p>
	 * 合法的片断取值为： Calendar.YEAR, Calendar.MONTH, both Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND 和 Calendar.MILLISECOND。 一个小于等于秒域的片断将返回０
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.SECOND将返回 538</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.SECOND将返回 538</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.MINUTE将返回 10538 (10*1000 + 538)</li>
	 * <li>日期2008-1-16 7:15:10.538 和 片断Calendar.MILLISECOND将返回 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 日期对象，不能为null
	 * @param fragment {@code Calendar}的域常量
	 * @return 片断对应的毫秒数
	 * @throws IllegalArgumentException 两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:10:10
	 */
	public static long getFragmentInMilliseconds(Date date, int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragmentInMilliseconds(date, fragment);
	}

	/**
	 * <p>
	 * 返回片段内的秒数。大于该片段的所有日期域将被忽略。
	 * </p>
	 * 
	 * <p>
	 * 获取任何日期的秒数将只返回当前分钟的秒数(0-59)。该方法将取得任何片断的秒数。 例如，如果你想要计算今天过去的秒数，你的片断为Calendar.DATE或Calendar.DAY_OF_YEAR，
	 * 其结果将是所有过去的小时、分的秒数。
	 * </p>
	 * 
	 * <p>
	 * 合法的片断取值为： Calendar.YEAR, Calendar.MONTH, both Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND 一个小于等于秒域的片断将返回０
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.MINUTE将返回 10</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.MINUTE将返回 10</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.DAY_OF_YEAR将返回 26110 (7*3600 + 15*60 + 10)</li>
	 * <li>日期2008-1-16 7:15:10.538 和 片断Calendar.MILLISECOND将返回 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 日期对象，不能为null
	 * @param fragment {@code Calendar}的域常量
	 * @return 片断对应的秒数
	 * @throws IllegalArgumentException 两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:17:10
	 */
	public static long getFragmentInSeconds(Date date, int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragmentInSeconds(date, fragment);
	}

	/**
	 * <p>
	 * 返回片段内的分钟数。大于该片段的所有日期域将被忽略。
	 * </p>
	 * 
	 * <p>
	 * 获取任何日期的分钟数将只返回当前小时的分钟数(0-59)。该方法将取得任何片断的分钟数。 例如，如果你想要计算今天过去的分钟数，你的片断为Calendar.DATE或Calendar.DAY_OF_YEAR，
	 * 其结果将是所有过去的天、小时的分钟数。
	 * </p>
	 * 
	 * <p>
	 * 合法的片断取值为： Calendar.YEAR, Calendar.MONTH, both Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND。 一个小于等于秒域的片断将返回０
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.HOUR_OF_DAY将返回 15</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.HOUR_OF_DAY将返回 15</li>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.MONTH将返回 15</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.MONTH将返回 435 (7*60 + 15)</li>
	 * <li>日期2008-1-16 7:15:10.538 和 片断Calendar.MILLISECOND将返回 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 日期对象，不能为null
	 * @param fragment {@code Calendar}的域常量
	 * @return 片断对应的分钟数
	 * @throws IllegalArgumentException 两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:17:15
	 */
	public static long getFragmentInMinutes(Date date, int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragmentInMinutes(date, fragment);
	}

	/**
	 * <p>
	 * 返回片段内的小时数。大于该片段的所有日期域将被忽略。
	 * </p>
	 * 
	 * <p>
	 * 获取任何日期的小时数将只返回当天的小时数(0-23)。该方法将取得任何片断的小时数。 例如，如果你想要计算今天过去的小时数，你的片断为Calendar.DATE或Calendar.DAY_OF_YEAR，
	 * 其结果将是所有过去天的小时数。
	 * </p>
	 * 
	 * <p>
	 * 合法的片断取值为： Calendar.YEAR, Calendar.MONTH, both Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND。 一个小于等于小时域的片断将返回０
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.DAY_OF_YEAR将返回 7</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.DAY_OF_YEAR将返回 7</li>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.MONTH将返回 7</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.MONTH将返回 127 (5*24 + 7)</li>
	 * <li>日期2008-1-16 7:15:10.538 和 片断Calendar.MILLISECOND将返回 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 日期对象，不能为null
	 * @param fragment {@code Calendar}的域常量
	 * @return 片断对应的分钟数
	 * @throws IllegalArgumentException 两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:25:15
	 */
	public static long getFragmentInHours(Date date, int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragmentInHours(date, fragment);
	}

	/**
	 * <p>
	 * 返回片段内的天数。大于该片段的所有日期域将被忽略。
	 * </p>
	 * 
	 * <p>
	 * 获取任何日期的天数将只返回当月的天数(1-31)。该方法将取得任何片断的天数。 例如，如果你想要计算今年过去的天数，你的片断为Calendar.YEAR， 其结果将是所有过去月份的天数。
	 * </p>
	 * 
	 * <p>
	 * 合法的片断取值为： Calendar.YEAR, Calendar.MONTH, both Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND。 一个小于等于天域的片断将返回０
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>日期2008-1-28 和 片断Calendar.MONTH将返回 28</li>
	 * <li>日期2008-1-28 和 片断Calendar.MONTH将返回 28</li>
	 * <li>日期2008-1-28 和 片断Calendar.YEAR将返回 28</li>
	 * <li>日期2008-1-28 和 片断Calendar.YEAR将返回 59</li>
	 * <li>日期2008-1-28 和 片断Calendar.MILLISECOND将返回 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 日期对象，不能为null
	 * @param fragment {@code Calendar}的域常量
	 * @return 片断对应的分钟数
	 * @throws IllegalArgumentException 两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:32:15
	 */
	public static long getFragmentInDays(Date date, int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragmentInDays(date, fragment);
	}

	/**
	 * <p>
	 * 返回片段内的毫秒数。大于该片段的所有日期域将被忽略。
	 * </p>
	 * 
	 * <p>
	 * 
	 * 获取任何日期的毫秒将只返回当前秒的毫秒数(0-999)。该方法将取得任何片断的毫秒数。 例如，如果你想要计算今天过去的毫秒数，你的片断为Calendar.DATE或Calendar.DAY_OF_YEAR，
	 * 其结果将是所有过去的小时、分、秒的毫秒数。
	 * </p>
	 * 
	 * <p>
	 * 合法的片断取值为： Calendar.YEAR, Calendar.MONTH, both Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND 和 Calendar.MILLISECOND。 一个小于等于秒域的片断将返回０
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.SECOND将返回 538</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.SECOND将返回 538</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.MINUTE将返回 10538 (10*1000 + 538)</li>
	 * <li>日期2008-1-16 7:15:10.538 和 片断Calendar.MILLISECOND将返回 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 日期对象，不能为null
	 * @param fragment {@code Calendar}的域常量
	 * @return 片断对应的毫秒数
	 * @throws IllegalArgumentException 两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:33:10
	 */
	public static long getFragmentInMilliseconds(Calendar calendar, int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragmentInMilliseconds(calendar, fragment);
	}

	/**
	 * <p>
	 * 返回片段内的秒数。大于该片段的所有日期域将被忽略。
	 * </p>
	 * 
	 * <p>
	 * 获取任何日期的秒数将只返回当前分钟的秒数(0-59)。该方法将取得任何片断的秒数。 例如，如果你想要计算今天过去的秒数，你的片断为Calendar.DATE或Calendar.DAY_OF_YEAR，
	 * 其结果将是所有过去的小时、分的秒数。
	 * </p>
	 * 
	 * <p>
	 * 合法的片断取值为： Calendar.YEAR, Calendar.MONTH, both Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND 一个小于等于秒域的片断将返回０
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.MINUTE将返回 10</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.MINUTE将返回 10</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.DAY_OF_YEAR将返回 26110 (7*3600 + 15*60 + 10)</li>
	 * <li>日期2008-1-16 7:15:10.538 和 片断Calendar.MILLISECOND将返回 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 日期对象，不能为null
	 * @param fragment {@code Calendar}的域常量
	 * @return 片断对应的秒数
	 * @throws IllegalArgumentException 两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:33:20
	 */
	public static long getFragmentInSeconds(Calendar calendar, int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragmentInSeconds(calendar, fragment);
	}

	/**
	 * <p>
	 * 返回片段内的分钟数。大于该片段的所有日期域将被忽略。
	 * </p>
	 * 
	 * <p>
	 * 获取任何日期的分钟数将只返回当前小时的分钟数(0-59)。该方法将取得任何片断的分钟数。 例如，如果你想要计算今天过去的分钟数，你的片断为Calendar.DATE或Calendar.DAY_OF_YEAR，
	 * 其结果将是所有过去的天、小时的分钟数。
	 * </p>
	 * 
	 * <p>
	 * 合法的片断取值为： Calendar.YEAR, Calendar.MONTH, both Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND。 一个小于等于秒域的片断将返回０
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.HOUR_OF_DAY将返回 15</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.HOUR_OF_DAY将返回 15</li>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.MONTH将返回 15</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.MONTH将返回 435 (7*60 + 15)</li>
	 * <li>日期2008-1-16 7:15:10.538 和 片断Calendar.MILLISECOND将返回 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 日期对象，不能为null
	 * @param fragment {@code Calendar}的域常量
	 * @return 片断对应的分钟数
	 * @throws IllegalArgumentException 两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:34:15
	 */
	public static long getFragmentInMinutes(Calendar calendar, int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragmentInMinutes(calendar, fragment);
	}

	/**
	 * <p>
	 * 返回片段内的小时数。大于该片段的所有日期域将被忽略。
	 * </p>
	 * 
	 * <p>
	 * 获取任何日期的小时数将只返回当天的小时数(0-23)。该方法将取得任何片断的小时数。 例如，如果你想要计算今天过去的小时数，你的片断为Calendar.DATE或Calendar.DAY_OF_YEAR，
	 * 其结果将是所有过去天的小时数。
	 * </p>
	 * 
	 * <p>
	 * 合法的片断取值为： Calendar.YEAR, Calendar.MONTH, both Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND。 一个小于等于小时域的片断将返回０
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.DAY_OF_YEAR将返回 7</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.DAY_OF_YEAR将返回 7</li>
	 * <li>日期2008-1-1 7:15:10.538 和 片断Calendar.MONTH将返回 7</li>
	 * <li>日期2008-1-6 7:15:10.538 和 片断Calendar.MONTH将返回 127 (5*24 + 7)</li>
	 * <li>日期2008-1-16 7:15:10.538 和 片断Calendar.MILLISECOND将返回 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 日期对象，不能为null
	 * @param fragment {@code Calendar}的域常量
	 * @return 片断对应的分钟数
	 * @throws IllegalArgumentException 两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:34:35
	 */
	public static long getFragmentInHours(Calendar calendar, int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragmentInHours(calendar, fragment);
	}

	/**
	 * <p>
	 * 返回片段内的天数。大于该片段的所有日期域将被忽略。
	 * </p>
	 * 
	 * <p>
	 * 获取任何日期的天数将只返回当月的天数(1-31)。该方法将取得任何片断的天数。 例如，如果你想要计算今年过去的天数，你的片断为Calendar.YEAR， 其结果将是所有过去月份的天数。
	 * </p>
	 * 
	 * <p>
	 * 合法的片断取值为： Calendar.YEAR, Calendar.MONTH, both Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND。 一个小于等于天域的片断将返回０
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>日期2008-1-28 和 片断Calendar.MONTH将返回 28</li>
	 * <li>日期2008-1-28 和 片断Calendar.MONTH将返回 28</li>
	 * <li>日期2008-1-28 和 片断Calendar.YEAR将返回 28</li>
	 * <li>日期2008-1-28 和 片断Calendar.YEAR将返回 59</li>
	 * <li>日期2008-1-28 和 片断Calendar.MILLISECOND将返回 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date 日期对象，不能为null
	 * @param fragment {@code Calendar}的域常量
	 * @return 片断对应的分钟数
	 * @throws IllegalArgumentException 两个参数之一为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:35:55
	 */
	public static long getFragmentInDays(Calendar calendar, int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragmentInDays(calendar, fragment);
	}

	/**
	 * <p>
	 * 判断给定的两个日期在不超过指定的域的情况下是否相等
	 * </p>
	 * 
	 * @param cal1 日期对象１，不能为 <code>null</code>
	 * @param cal2 日期对象２，不能为 <code>null</code>
	 * @param field {@code Calendar}的域常量
	 * @return <code>true</code> 如果相等; 否则 <code>false</code>
	 * @throws IllegalArgumentException 任意一个参数为null
	 * @see #truncate(Calendar, int)
	 * @see #truncatedEquals(Date, Date, int)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:39:25
	 */
	public static boolean truncatedEquals(Calendar cal1, Calendar cal2, int field) {
		return org.apache.commons.lang3.time.DateUtils.truncatedEquals(cal1, cal2, field);
	}

	/**
	 * <p>
	 * 判断给定的两个日期在不超过指定的域的情况下是否相等
	 * </p>
	 * 
	 * @param date1 日期对象１，不能为 <code>null</code>
	 * @param date2 日期对象２，不能为 <code>null</code>
	 * @param field {@code Calendar}的域常量
	 * @return <code>true</code> 如果相等; 否则 <code>false</code>
	 * @throws IllegalArgumentException 任意一个参数为null
	 * @see #truncate(Calendar, int)
	 * @see #truncatedEquals(Date, Date, int)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:39:45
	 */
	public static boolean truncatedEquals(Date date1, Date date2, int field) {
		return org.apache.commons.lang3.time.DateUtils.truncatedEquals(date1, date2, field);
	}

	/**
	 * <p>
	 * 在不超过指定的域的情况下，比较给定的两个日期
	 * </p>
	 * 
	 * @param cal1 日期对象１，不能为 <code>null</code>
	 * @param cal2 日期对象２，不能为 <code>null</code>
	 * @param field {@code Calendar}的域常量
	 * @return 负数、０、或正数，分别当第一个日期小于、等于或大于第二个时
	 * @throws IllegalArgumentException 任意一个参数为 <code>null</code>
	 * @see #truncate(Calendar, int)
	 * @see #truncatedCompareTo(Date, Date, int)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:44:44
	 */
	public static int truncatedCompareTo(Calendar cal1, Calendar cal2, int field) {
		return org.apache.commons.lang3.time.DateUtils.truncatedCompareTo(cal1, cal2, field);
	}

	/**
	 * <p>
	 * 在不超过指定的域的情况下，比较给定的两个日期
	 * </p>
	 * 
	 * @param date1 日期对象１，不能为 <code>null</code>
	 * @param date2 日期对象２，不能为 <code>null</code>
	 * @param field {@code Calendar}的域常量
	 * @return 负数、０、或正数，分别当第一个日期小于、等于或大于第二个时
	 * @throws IllegalArgumentException 任意一个参数为 <code>null</code>
	 * @see #truncate(Calendar, int)
	 * @see #truncatedCompareTo(Date, Date, int)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-28 上午00:45:12
	 */
	public static int truncatedCompareTo(Date date1, Date date2, int field) {
		return org.apache.commons.lang3.time.DateUtils.truncatedCompareTo(date1, date2, field);
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.DateUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}
