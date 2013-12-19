package com.kvc.joy.plugin.schedule.quartz.support.cron;

import com.kvc.joy.plugin.schedule.quartz.model.vo.QrtzJobPlanVo;

/**
 * 
 * @author <b>唐玮琳</b>
 */
public class IntervalCronExpressionCreator extends AbstractCronExpressionCreator {

	public IntervalCronExpressionCreator(QrtzJobPlanVo plan) {
		super(plan);
	}

	@Override
	protected String genSecondZone() {
		return "0"; // 不精确到秒
	}

	@Override
	protected String genMinuteZone() {
		String minuteZone = "0";

//		String isDayExeOnce = plan.getIsDayExeOnce();
//		
//		if ("1".equals(isDayExeOnce)) {
//			String dayFreqExecuteTime = plan.getDayFreqExecuteTime(); // 每日一次执行
//			String[] times = dayFreqExecuteTime.split(":");
//			minuteZone = times[1];
//		} else {
//			PeriodInDay dayFreqPeriodType = PeriodInDay.enumOf(plan.getDayFreqPeriodType());
//			if (dayFreqPeriodType == PeriodInDay.MINUTE) {
//				Long dayFreqPeriodCount = plan.getDayFreqPeriodCount();
//				//TODO cron表达式不支持如”每天的9点30分到10点40分每6分钟发生一次“的时间策略，整点的是可以的
////				String dayFreqEffectTime = plan.getDayFreqEffectTime();
////				String dayFreqExpireTime = plan.getDayFreqExpireTime();
//				minuteZone = "*/" + dayFreqPeriodCount;
//			}
//		}
		
		return minuteZone;
	}

	@Override
	protected String genHourZone() {
		String hourZone = "0";
		
//		String isDayExeOnce = plan.getIsDayExeOnce();
//		if ("1".equals(isDayExeOnce)) {
//			String dayFreqExecuteTime = plan.getDayFreqExecuteTime(); // 每日一次执行
//			String[] times = dayFreqExecuteTime.split(":");
//			hourZone = times[0];
//		} else { // 每日周期执行
//			String dayFreqEffectTime = plan.getDayFreqEffectTime();
//			String dayFreqExpireTime = plan.getDayFreqExpireTime();
//			int fromHour = Integer.valueOf(dayFreqEffectTime.split(":")[0]);
//			int endHour = Integer.valueOf(dayFreqExpireTime.split(":")[0]);
//			if (endHour > fromHour) {
//				endHour -= 1;
//			}
//			hourZone = fromHour + "-" + endHour;
//			
//			PeriodInDay dayFreqPeriodType = PeriodInDay.enumOf(plan.getDayFreqPeriodType());
//			if (dayFreqPeriodType == PeriodInDay.HOUR) {
//				Long dayFreqPeriodCount = plan.getDayFreqPeriodCount();
//				hourZone += "/" + dayFreqPeriodCount;
//			}
//		}
		
		return hourZone;
	}

	@Override
	protected String genDayZone() {
		String dayZone = "?";
		
//		ScheduleFrequency intervalType = ScheduleFrequency.enumOf(plan.getScheduleIntervalType());
//		switch (intervalType) {
//		case DAY:
//			Long dayPeriodCount = plan.getDayFreqDayPeriodCount();
//			if (dayPeriodCount != null) {
//				dayZone = "*/" + dayPeriodCount;	
//			}
//			break;
//		case MONTH: 
//			String isWeekOfMonth = plan.getIsWeekOfMonth();
//			if ("0".equals(isWeekOfMonth)) {
//				String monthDays = plan.getMonthFreqMonthDays();
//				if (StringUtils.isBlank(monthDays)) {
//					throw new ApplicationException("至少选择一个月中的某一天！");
//				} else {
//					if (monthDays.length() > 1 && monthDays.contains("L")) {
//						throw new ApplicationException("选择'最后一天'时，不能再选择其它天！");
//					}	
//				}
//				dayZone = monthDays.replaceFirst(",$", "");
//			}
//			break;
//		}
		
		return dayZone;
	}

	@Override
	protected String genMonthZone() {
		String monthZone = "*";
		
//		ScheduleFrequency intervalType = ScheduleFrequency.enumOf(plan.getScheduleIntervalType());
//		if (intervalType == ScheduleFrequency.MONTH) {
//			Long monthPeriodCount = plan.getMonthFreqMonthPeriodCount();
//			monthZone = "*/" + monthPeriodCount;
//		}
		
		return monthZone;
	}

	@Override
	protected String genWeekZone() {
		String weekZone = "?";
		
//		ScheduleFrequency intervalType = ScheduleFrequency.enumOf(plan.getScheduleIntervalType());
//		switch (intervalType) {
//		case WEEK: 
//			//TODO cron表达式不支持如”每两周的周一发生一次“的时间策略，周只支持每周和第几周
////			Long weekPeriodCount = plan.getWeekPeriodCount();
////			if (weekPeriodCount != null) {
////				weekZone = "#" + weekPeriodCount;
////			}
//			
//			String weedDays = plan.getWeekFreqWeekDays();
//			if (StringUtils.isNotBlank(weedDays)) {
//				weekZone = weedDays.replaceFirst(",$", "");
//			} else {
//				throw new ApplicationException("至少选择一星期中的某一天！");
//			}
//			break;
//		case MONTH: 
//			String isWeekOfMonth = plan.getIsWeekOfMonth();
//			if ("1".equals(isWeekOfMonth)) {
//				String ordinal = plan.getMonthFreqWeekDayOrdinal();
//				String weedDay = plan.getMonthFreqWeekDays();
//				if (WeekCount.LAST_WEEK.getCode().equals(ordinal)) {
//					weekZone = weedDay + "L";
//				} else {
//					weekZone = weedDay + "#" + ordinal;
//				}
//			}
//			break;
//		}
		
		return weekZone;
	}

	@Override
	protected String genYearZone() {
		return "";
	}

}

