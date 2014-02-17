package com.kvc.joy.plugin.schedule.quartz.support.cron;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.plugin.schedule.quartz.model.vo.QrtzJobPlanVo;

/**
 * 
 * @author <b>唐玮琳</b>
 */
public abstract class AbstractCronExpressionCreator implements ICronExpressionCreator {

	protected final QrtzJobPlanVo plan;
	protected static final Log logger = LogFactory.getLog(AbstractCronExpressionCreator.class);

	public AbstractCronExpressionCreator(QrtzJobPlanVo plan) {
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
