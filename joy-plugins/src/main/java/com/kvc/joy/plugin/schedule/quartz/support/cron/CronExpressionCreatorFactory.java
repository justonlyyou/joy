package com.kvc.joy.plugin.schedule.quartz.support.cron;

import com.kvc.joy.plugin.schedule.quartz.model.vo.QrtzJobPlanVo;

/**
 * 
 * @author <b>Kevice</b>
 */
public class CronExpressionCreatorFactory {

	private CronExpressionCreatorFactory() {
	}

	public static ICronExpressionCreator getCreator(QrtzJobPlanVo planVo)  {
//		ScheduleConfigMode scheduleMode = ScheduleConfigMode.enumOf(planVo.getScheduleMode());
//		switch (scheduleMode) {
//		case TIMING_ONCE:
//			return new TimingCronExpressionCreator(planVo);
//		case INTERVAL:
//			return new IntervalCronExpressionCreator(planVo);
//		}
		return null;
	}

}
