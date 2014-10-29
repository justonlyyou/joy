package org.joy.commons.cn;

import org.joy.commons.enums.Sex;
import org.joy.commons.lang.CharTool;
import org.joy.commons.lang.string.StringTool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份证工具类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-14 下午10:05:19
 */
public class IdCardNoTool {

	/** 中国大陆公民身份证号码最小长度。 */
	private static final int MAINLAND_ID_MIN_LENGTH = 15;

	/** 中国大陆公民身份证号码最大长度。 */
	private static final int MAINLAND_ID_MAX_LENGTH = 18;

	/** 每位加权因子 */
	private static final int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	/** 第18位校检码 */
	private static final String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
	
	/** 最低年限 */
	private static final int MIN = 1930;
	
	/** 台湾身份首字母对应数字 */
	private static final Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
//	/** 香港身份首字母对应数字 */
//	private static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();
	
	static {
		twFirstCode.put("A", 10);
		twFirstCode.put("B", 11);
		twFirstCode.put("C", 12);
		twFirstCode.put("D", 13);
		twFirstCode.put("E", 14);
		twFirstCode.put("F", 15);
		twFirstCode.put("G", 16);
		twFirstCode.put("H", 17);
		twFirstCode.put("J", 18);
		twFirstCode.put("K", 19);
		twFirstCode.put("L", 20);
		twFirstCode.put("M", 21);
		twFirstCode.put("N", 22);
		twFirstCode.put("P", 23);
		twFirstCode.put("Q", 24);
		twFirstCode.put("R", 25);
		twFirstCode.put("S", 26);
		twFirstCode.put("T", 27);
		twFirstCode.put("U", 28);
		twFirstCode.put("V", 29);
		twFirstCode.put("X", 30);
		twFirstCode.put("Y", 31);
		twFirstCode.put("W", 32);
		twFirstCode.put("Z", 33);
		twFirstCode.put("I", 34);
		twFirstCode.put("O", 35);
		
//		hkFirstCode.put("A", 1);
//		hkFirstCode.put("B", 2);
//		hkFirstCode.put("C", 3);
//		hkFirstCode.put("R", 18);
//		hkFirstCode.put("U", 21);
//		hkFirstCode.put("Z", 26);
//		hkFirstCode.put("X", 24);
//		hkFirstCode.put("W", 23);
//		hkFirstCode.put("O", 15);
//		hkFirstCode.put("N", 14);
	}
	
	private IdCardNoTool() {
	}

	/**
	 * <p>
	 * 将15位身份证号码转换为18位(大陆)
	 * </p>
	 * 
	 * @param idCardNo15 15位身份编码, 非法值将返回null
	 * @return 18位身份编码
	 * @since 1.0.0
	 * @author June
	 * @author Kevice
	 * @time 2013-5-14 下午10:05:19
	 */
	public static String convert15To18(String idCardNo15) {
		if (StringTool.isBlank(idCardNo15) || idCardNo15.length() != MAINLAND_ID_MIN_LENGTH) {
			return null;
		}
		
		String idCard18;
		if (isNumber(idCardNo15)) {
			// 获取出生年月日
			String birthday = idCardNo15.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null) {
				cal.setTime(birthDate);
			}
			// 获取出生年(完全表现形式,如：2010)
			String sYear = String.valueOf(cal.get(Calendar.YEAR));
			idCard18 = idCardNo15.substring(0, 6) + sYear + idCardNo15.substring(8);
			// 转换字符数组
			char[] cArr = idCard18.toCharArray();
            int[] iCard = converCharToInt(cArr);
            int iSum17 = getPowerSum(iCard);
            // 获取校验位
            String sVal = getCheckCode18(iSum17);
            if (sVal.length() > 0) {
                idCard18 += sVal;
            } else {
                return null;
            }
        } else {
			return null;
		}
		return idCard18;
	}

	/**
	 * <p>
	 * 检查指定字符串是否为身份证号(包括大陆、港、澳、台)
	 * </p>
	 * 
	 * @param str 待检查的字符串, 为null返回false
	 * @return true: 为身份证号
	 * @since 1.0.0
	 * @author June
	 * @author Kevice
	 * @time 2013-5-14 下午10:15:33
	 */
	public static boolean isIdCardNo(String str) {
		if(StringTool.isBlank(str)) {
			return false;
		}
        return isIdCardNo18(str) || isIdCardNo15(str) || isHkIdCardNo(str) ||
                isMacauIdCardNo(str) || isTwIdCardNo(str);
    }

	/**
	 * <p>
	 * 检查是否为18位身份号(大陆)
	 * </p>
	 * 
	 * @param str 待检查的字符串, 为null返回false
	 * @return true: 为18位身份证号
	 * @since 1.0.0
	 * @author June
	 * @author Kevice
	 * @time 2013-5-14 下午10:42:48
	 */
	public static boolean isIdCardNo18(String str) {
		if(StringTool.isBlank(str)) {
			return false;
		}
		
		boolean bTrue = false;
		if (str.length() == MAINLAND_ID_MAX_LENGTH) {
			// 前17位
			String code17 = str.substring(0, 17);
			// 第18位
			String code18 = str.substring(17, MAINLAND_ID_MAX_LENGTH);
			if (isNumber(code17)) {
				char[] cArr = code17.toCharArray();
                int[] iCard = converCharToInt(cArr);
                int iSum17 = getPowerSum(iCard);
                // 获取校验位
                String val = getCheckCode18(iSum17);
                if (val.length() > 0) {
                    if (val.equalsIgnoreCase(code18)) {
                        bTrue = true;
                    }
                }
            }
		}
		return bTrue;
	}

	/**
	 * <p>
	 * 检查是否为15位身份号(大陆)
	 * </p>
	 * 
	 * @param str 待检查的字符串, 为null返回false
	 * @return true: 为18位身份证号
	 * @since 1.0.0
	 * @author June
	 * @author Kevice
	 * @time 2013-5-14 下午10:42:48
	 */
	public static boolean isIdCardNo15(String str) {
		if(StringTool.isBlank(str)) {
			return false;
		}
		
		if (str.length() != MAINLAND_ID_MIN_LENGTH) {
			return false;
		}
		if (isNumber(str)) {
			String proCode = str.substring(0, 2);
			if (Province.enumOf(proCode) == null) {
				return false;
			}
			String birthCode = str.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null)
				cal.setTime(birthDate);
			if (!valiDate(cal.get(Calendar.YEAR), Integer.valueOf(birthCode.substring(2, 4)),
					Integer.valueOf(birthCode.substring(4, 6)))) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * 检查是否为台湾身份号
	 * </p>
	 * 
	 * @param str 待检查的字符串, 为null返回false
	 * @return true: 为台湾身份证号
	 * @since 1.0.0
	 * @author June
	 * @author Kevice
	 * @time 2013-5-14 下午10:51:20
	 */
	public static boolean isTwIdCardNo(String str) {
		if(StringTool.isBlank(str)) {
			return false;
		}
		
		if(str.matches("^[a-zA-Z][0-9]{9}$") == false) {
			return false;
		}
		
		String start = str.substring(0, 1);
		String mid = str.substring(1, 9);
		String end = str.substring(9, 10);
		Integer iStart = twFirstCode.get(start);
		if(iStart == null) {
			return false;
		}
		Integer sum = iStart / 10 + (iStart % 10) * 9;
		char[] chars = mid.toCharArray();
		Integer iflag = 8;
		for (char c : chars) {
			sum = sum + Integer.valueOf(c + "") * iflag;
			iflag--;
		}
		return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end);
	}

	/**
	 * <p>
	 * 检查是否为香港身份号(存在Bug，部份特殊身份证无法检查)
	 * </p>
	 * 
	 * <p>
	 * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35
	 * 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
	 * </p>
	 * 
	 * <p>
	 * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
	 * </p>
	 * 
	 * @param str 待检查的字符串, 为null返回false
	 * @return true: 为香港身份证号
	 * @since 1.0.0
	 * @author June
	 * @author Kevice
	 * @time 2013-5-14 下午10:51:20
	 */
	public static boolean isHkIdCardNo(String str) {
		if(StringTool.isBlank(str)) {
			return false;
		}
		
		if(str.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$") == false) {
			return false;
		}
		
		String card = str.replaceAll("[\\(|\\)]", "");
		Integer sum;
		if (card.length() == 9) {
			sum = ((int) card.substring(0, 1).toUpperCase().toCharArray()[0] - 55) * 9
					+ ((int) card.substring(1, 2).toUpperCase().toCharArray()[0] - 55) * 8;
			card = card.substring(1, 9);
		} else {
			sum = 522 + ((int) card.substring(0, 1).toUpperCase().toCharArray()[0] - 55) * 8;
		}
		String mid = card.substring(1, 7);
		String end = card.substring(7, 8);
		char[] chars = mid.toCharArray();
		Integer iflag = 7;
		for (char c : chars) {
			sum = sum + Integer.valueOf(c + "") * iflag;
			iflag--;
		}
		if (end.toUpperCase().equals("A")) {
			sum = sum + 10;
		} else {
			sum = sum + Integer.valueOf(end);
		}
		return (sum % 11 == 0);
	}

	/**
	 * <p>
	 * 检查是否为澳门身份号
	 * </p>
	 * 
	 * @param str 待检查的字符串, 为null返回false
	 * @return true: 为澳门身份证号
	 * @since 1.0.0
	 * @author June
	 * @author Kevice
	 * @time 2013-5-14 下午10:51:20
	 */
	public static boolean isMacauIdCardNo(String str) {
        return !StringTool.isBlank(str) && str.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$");
    }
	
	private static int[] converCharToInt(char[] ca) {
		int len = ca.length;
		int[] iArr = new int[len];
		for (int i = 0; i < len; i++) {
			iArr[i] = CharTool.toIntValue(ca[i]);
		}
		return iArr;
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 * 
	 * @param iArr 身份证每位的数组
	 * @return 身份证编码。
	 * @since 1.0.0
	 * @author June
	 */
	private static int getPowerSum(int[] iArr) {
		int iSum = 0;
		if (power.length == iArr.length) {
			for (int i = 0; i < iArr.length; i++) {
				for (int j = 0; j < power.length; j++) {
					if (i == j) {
						iSum = iSum + iArr[i] * power[j];
					}
				}
			}
		}
		return iSum;
	}

	/**
	 * 将power和值与11取模获得余数进行校验码判断
	 * 
	 * @param iSum power和值
	 * @return 校验位
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-14 下午11:58:56
	 */
	private static String getCheckCode18(int iSum) {
		return verifyCode[iSum % 11];
	}

	/**
	 * <p>
	 * 根据身份编号获取生日(仅限大陆身份证)
	 * <p>
	 * 
	 * @param idCardNo 身份证号, 为null或空或不是大陆身份证将返回null
	 * @return 生日(yyyyMMdd)
	 * @since 1.0.0
	 * @author June
	 * @author Kevice
	 * @time 2013-5-14 下午11:17:13
	 */
	public static String getBirthday(String idCardNo) {
		if(StringTool.isBlank(idCardNo)) {
			return null;
		}
		
		int len = idCardNo.length();
		if (len < MAINLAND_ID_MIN_LENGTH) {
			return null;
		} else if (len == MAINLAND_ID_MIN_LENGTH) {
			idCardNo = convert15To18(idCardNo);
		}
		return idCardNo.substring(6, 14);
	}

	/**
	 * <p>
	 * 根据身份证号获取性别(仅限大陆和台湾)
	 * </p>
	 * 
	 * @param idCardNo 身份证号，为null返回Sex.UNKNOWN
	 * @return 性别枚举
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-14 下午11:25:55
	 */
	public static Sex getSex(String idCardNo) {
		if(StringTool.isBlank(idCardNo)) {
			return Sex.UNKNOWN;
		}
		
		if(isTwIdCardNo(idCardNo)) {
			return idCardNo.charAt(1) == '1' ? Sex.MALE : Sex.FEMALE;
		}
		
		if(idCardNo.length() != MAINLAND_ID_MIN_LENGTH && idCardNo.length() != MAINLAND_ID_MAX_LENGTH) {
			return Sex.UNKNOWN;
		}
		
		Sex sGender;
		if (idCardNo.length() == MAINLAND_ID_MIN_LENGTH) {
			idCardNo = convert15To18(idCardNo);
		}
		String sCardNum = idCardNo.substring(16, 17);
		if (Integer.parseInt(sCardNum) % 2 != 0) {
			sGender = Sex.MALE;
		} else {
			sGender = Sex.FEMALE;
		}
		return sGender;
	}

	/**
	 * <p>
	 * 根据身份证号获取户籍省份(包括大陆、港、澳、台)
	 * </p>
	 * 
	 * @param idCardNo 身份证号 为null或空返回null
	 * @return 省枚举，未匹配返回null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 上午12:36:49
	 */
	public static Province getProvince(String idCardNo) {
		if(StringTool.isBlank(idCardNo)) {
			return null;
		}
		
		String code = null;
		if (isIdCardNo15(idCardNo) || isIdCardNo18(idCardNo)) {
			code = idCardNo.substring(0, 2);
		} else if(isHkIdCardNo(idCardNo)) {
			return Province.XIANG_GANG;
		} else if(isTwIdCardNo(idCardNo)) {
			return Province.TAI_WAN;
		} else if(isMacauIdCardNo(idCardNo)) {
			return Province.AO_MEN;
		}
		
		if(code == null) {
			return null;
		} else {
			return Province.enumOf(code);	
		}
	}

	/**
	 * 判断是否为数字串
	 * @since 1.0.0
	 * @author Kevice
	 */
	private static boolean isNumber(String str) {
		return !StringTool.isBlank(str) && str.matches("^[0-9]*$");
	}

	/**
	 * 验证小于当前日期 是否有效
	 * 
	 * @param iYear 待验证日期(年)
	 * @param iMonth 待验证日期(月 1-12)
	 * @param iDate 待验证日期(日)
	 * @return 是否有效
	 * @since 1.0.0
	 * @author June
	 */
	private static boolean valiDate(int iYear, int iMonth, int iDate) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int datePerMonth;
		if (iYear < MIN || iYear >= year) {
			return false;
		}
		if (iMonth < 1 || iMonth > 12) {
			return false;
		}
		switch (iMonth) {
		case 4:
		case 6:
		case 9:
		case 11:
			datePerMonth = 30;
			break;
		case 2:
			boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0)) && (iYear > MIN && iYear < year);
			datePerMonth = dm ? 29 : 28;
			break;
		default:
			datePerMonth = 31;
		}
		return (iDate >= 1) && (iDate <= datePerMonth);
	}

}
