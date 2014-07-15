package org.joy.plugin.seqgen.support;

import org.joy.commons.bean.Pair;
import org.joy.commons.enums.TimeUnit;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.plugin.seqgen.model.po.TSysSeqNum;
import org.joy.plugin.seqgen.model.po.TSysSeqNumRule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 周期处理
 * 
 * @author <b>Kevice</b>
 */
public class CycleProcessor {
	
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TSysSeqNum.TIME_DB_FMT_STR);
	protected static final Log logger = LogFactory.getLog(CycleProcessor.class);

	public static Pair<Boolean, Calendar> processCycle(SequenceCache seqCache) {
		TSysSeqNum tSysSeqNum = seqCache.getSeqNum();
		TSysSeqNumRule rule = tSysSeqNum.getSeqRule();
		Long maxValue = rule.getMaxValue();
		Calendar now = Calendar.getInstance();
		boolean reset = false;
		if (maxValue != null && maxValue.intValue() > 0) { // 序列号最大值大于0时，将忽略周期类型
			long curSeq = seqCache.getRealSeqNum();
			if (curSeq == 0 || curSeq >= maxValue.intValue()) { // 序列号大于最大值，从头开始
				reset = true;
			}
		} else { // 按周期
			String curPeriodStartTimeStr = tSysSeqNum.getCurPeriodStartTime();
			if (curPeriodStartTimeStr != null) {
				Integer periodType = rule.getPeriodType();
				TimeUnit timeUnit = TimeUnit.enumOf(periodType+"");
				if (timeUnit == null) {
					String errMsg = "序列号规则#" + rule.getId() + "配置的周期类型" + periodType + "值非法！";
					logger.error(errMsg);
					throw new RuntimeException(errMsg);
				}
				long periodCount = rule.getPeriodCount();
				Calendar startTime = formatCurPeriodStartTime(curPeriodStartTimeStr);
				int diff = 0;
				switch (timeUnit) {
				case SECOND:
					long offset = now.getTimeInMillis() - startTime.getTimeInMillis();
					diff = (int) (offset / 1000);
					break;
				case MINUTE:
					offset = now.getTimeInMillis() - startTime.getTimeInMillis();
					diff = (int) (offset / 1000 / 60);
					now.set(Calendar.SECOND, 0);
					break;
				case HOUR:
					offset = now.getTimeInMillis() - startTime.getTimeInMillis();
					diff = (int) (offset / 1000 / 60 / 60);
					now.set(Calendar.MINUTE, 0);
					now.set(Calendar.SECOND, 0);
					break;
				case DAY:
					offset = now.getTimeInMillis() - startTime.getTimeInMillis();
					diff = (int) (offset / 1000 / 60 / 60 / 24);
					now.set(Calendar.HOUR_OF_DAY, 0);
					now.set(Calendar.MINUTE, 0);
					now.set(Calendar.SECOND, 0);
					break;
				case MONTH:
					diff = monthsBetween(startTime, now);
					now.set(Calendar.DAY_OF_MONTH, 1);
					now.set(Calendar.HOUR_OF_DAY, 0);
					now.set(Calendar.MINUTE, 0);
					now.set(Calendar.SECOND, 0);
					break;
				case YEAR:
					diff = now.get(Calendar.YEAR) - startTime.get(Calendar.YEAR);
					now.set(Calendar.MONTH, 0);
					now.set(Calendar.DAY_OF_MONTH, 1);
					now.set(Calendar.HOUR_OF_DAY, 0);
					now.set(Calendar.MINUTE, 0);
					now.set(Calendar.SECOND, 0);
					break;
				default:
					break;
				}
				reset = (diff >= periodCount);
			}
		}
		Pair<Boolean, Calendar> result = new Pair<Boolean, Calendar>();
		result.setLeft(reset);
		if (reset) {
			result.setRight(now);
		}
		return result;
	}
	
	/**
	 * 计算两个日期之间相差的月数
	 * 
	 * @param fromCal 开始时间
	 * @param toCal 结束时间
	 * @return 月数
	 */
	public static int monthsBetween(Calendar fromCal, Calendar toCal) {
		if (toCal.equals(fromCal)) {
			return 0;
		}

		if (fromCal.after(toCal)) {
			Calendar temp = fromCal;
			fromCal = toCal;
			toCal = temp;
		}

		int flag = 0;
		if (toCal.get(Calendar.DAY_OF_MONTH) < fromCal.get(Calendar.DAY_OF_MONTH)) {
			flag = 1;
		}

		int months;
		int fromYear = fromCal.get(Calendar.YEAR);
		int toYear = toCal.get(Calendar.YEAR);
		int fromMonth = fromCal.get(Calendar.MONTH);
		int toMonth = toCal.get(Calendar.MONTH);
		if (toYear > fromYear) {
			months = ((toYear - fromYear) * 12 + toMonth - flag) - fromMonth;
		} else {
			months = toMonth - fromMonth - flag;
		}

		return months;
	}
	
	public static Calendar formatCurPeriodStartTime(String curPeriodStartTimeStr) {
		Date curPeriodStartTime = null;
		try {
			curPeriodStartTime = simpleDateFormat.parse(curPeriodStartTimeStr);
		} catch (ParseException e) {
			logger.error(e, "当前周期开始时间：{0}日期转换出错！", curPeriodStartTimeStr);
		}
		Calendar startTime = Calendar.getInstance();
		startTime.setTime(curPeriodStartTime);
		return startTime;
	}
	
}
