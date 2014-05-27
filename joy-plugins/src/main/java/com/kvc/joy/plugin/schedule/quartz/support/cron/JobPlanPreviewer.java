package com.kvc.joy.plugin.schedule.quartz.support.cron;

import com.kvc.joy.commons.lang.DateTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.plugin.schedule.quartz.model.vo.JobPlanPreview;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Kevice
 * @time 2012-12-31 下午3:59:18
 */
public class JobPlanPreviewer {

	private CronExpression exp;
	protected static final Log logger = LogFactory.getLog(JobPlanPreviewer.class);

	public JobPlanPreviewer(String cronExp) {
		try {
			exp = new CronExpression(cronExp);
		} catch (ParseException e) {
			logger.error(e, "cron表达式【{0}】解析出错！", cronExp);
		}
	}

	public List<JobPlanPreview> preview(int count) {
		List<JobPlanPreview> previewList = new ArrayList<JobPlanPreview>(count);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i <= count; i++) {
			date = exp.getNextValidTimeAfter(date);
			if (date == null) {
				break;
			}
			JobPlanPreview preview = new JobPlanPreview();
			preview.setOrdinal(i + "");
			calendar.setTime(date);

			String time = DateTool.formatDate(date, DateTool.FMT_HYPHEN_DAY_CLN_SECOND);
			String[] split = time.split(" ");
			preview.setRunDate(split[0]);
			preview.setRunTime(split[1]);
			preview.setDayOfWeek(getWeekDay(calendar));
			previewList.add(preview);
		}
		return previewList;
	}

	public String previewAsString(int count) {
		List<JobPlanPreview> previewList = preview(count);
		String plan;
		if (previewList.isEmpty()) {
			plan = "从现在起无任何执行计划！";
		} else {
			StringBuilder plans = new StringBuilder();
			for (JobPlanPreview preview : previewList) {
				plans.append(preview).append("</br>");
			}
			plan = plans.toString();
		}
		return plan;
	}

	private String getWeekDay(Calendar calendar) {
		int i = calendar.get(Calendar.DAY_OF_WEEK);
		String weekDay = null;
		switch (i) {
		case 1:
			weekDay = "天";
			break;
		case 2:
			weekDay = "一";
			break;
		case 3:
			weekDay = "二";
			break;
		case 4:
			weekDay = "三";
			break;
		case 5:
			weekDay = "四";
			break;
		case 6:
			weekDay = "五";
			break;
		case 7:
			weekDay = "六";
			break;
		}
		return "星期" + weekDay;
	}

	public static void main(String[] args) {
		String cronExp = "0 0 13-15/1 */1 * ?";
		List<JobPlanPreview> previewList = new JobPlanPreviewer(cronExp).preview(10);
		for (JobPlanPreview preview : previewList) {
			System.out.println(preview);
		}
	}

}
