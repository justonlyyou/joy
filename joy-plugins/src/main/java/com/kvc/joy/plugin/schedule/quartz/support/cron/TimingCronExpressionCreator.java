package com.kvc.joy.plugin.schedule.quartz.support.cron;

import java.util.Calendar;
import java.util.Date;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.plugin.schedule.quartz.model.vo.QrtzJobPlanVo;

/**
 * 
 * @author <b>唐玮琳</b>
 */
public class TimingCronExpressionCreator extends AbstractCronExpressionCreator {

	private Calendar time;

	public TimingCronExpressionCreator(QrtzJobPlanVo plan) {
		super(plan);
		parseTime();
	}

	private void parseTime() {
		Date onceExecuteTime = null; //TODO
		//plan.getOnceExecuteTime();
		if (onceExecuteTime == null) {
			throw new SystemException("【一次发生时间】不允许为空！");
		}
		time = Calendar.getInstance();
		time.setTime(onceExecuteTime);
	}

	@Override
	protected String genSecondZone() {
		int second = time.get(Calendar.SECOND);
		return numToString(second);
	}

	@Override
	protected String genMinuteZone() {
		int minute = time.get(Calendar.MINUTE);
		return numToString(minute);
	}

	@Override
	protected String genHourZone() {
		int hour = time.get(Calendar.HOUR_OF_DAY);
		return numToString(hour);
	}

	@Override
	protected String genDayZone() {
		int day = time.get(Calendar.DAY_OF_MONTH);
		return numToString(day);
	}

	@Override
	protected String genMonthZone() {
		int month = time.get(Calendar.MONTH);
		month++;
		return numToString(month);
	}

	@Override
	protected String genWeekZone() {
		return "?";
	}

	@Override
	protected String genYearZone() {
		int year = time.get(Calendar.YEAR);
		return numToString(year);
	}

	private final String numToString(Number num) {
		return String.valueOf(num == null ? 0L : num);
	}

}
