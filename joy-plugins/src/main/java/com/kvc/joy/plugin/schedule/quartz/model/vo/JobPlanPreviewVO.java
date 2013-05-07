package com.kvc.joy.plugin.schedule.quartz.model.vo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-2 上午11:19:07
 */
public class JobPlanPreviewVO {

	private String ordinal;
	private String runDate;
	private String runTime;
	private String dayOfWeek;

	public String getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}

	public String getRunDate() {
		return runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getFullTime() {
		return runDate + " " + runTime;
	}

	@Override
	public String toString() {
		NumberFormat format = new DecimalFormat("00");
		ordinal = format.format(Integer.valueOf(ordinal));
		return ordinal + ". " + runDate + "(" + dayOfWeek + ") " + runTime;
	}

}
