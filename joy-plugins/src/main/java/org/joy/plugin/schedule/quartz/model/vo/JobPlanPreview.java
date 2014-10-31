package org.joy.plugin.schedule.quartz.model.vo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 调度任务计划预览VO
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-1-2 上午11:19:07
 */
public class JobPlanPreview {

	private String ordinal; // 顺序号
	private String runDate; // 运行日期
	private String runTime; // 运行时间
	private String dayOfWeek; // 一周中的第几天

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
