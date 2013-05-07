package com.kvc.joy.plugin.schedule.quartz.support.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.plugin.schedule.quartz.model.vo.QrtzJobPlanCfgVO;

/**
 * 
 * @author <b>唐玮琳</b>
 */
public abstract class AbstractCronExpressionCreator implements ICronExpressionCreator {

	protected QrtzJobPlanCfgVO plan;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractCronExpressionCreator(QrtzJobPlanCfgVO plan) {
		this.plan = plan;
	}

	public String create() {
		String cron = genSecondZone() + " " + genMinuteZone() + " " + genHourZone() + " " + genDayZone() + " " + genMonthZone() + " " + genWeekZone();
		String yearZone = genYearZone();
		if (StringTool.isNotBlank(yearZone)) {
			cron += " " + yearZone;
		}
		return cron;
	}

	protected abstract String genSecondZone();

	protected abstract String genMinuteZone();

	protected abstract String genHourZone();

	protected abstract String genDayZone();

	protected abstract String genMonthZone();

	protected abstract String genWeekZone();

	protected String genYearZone() {
		return null;
	}

}
