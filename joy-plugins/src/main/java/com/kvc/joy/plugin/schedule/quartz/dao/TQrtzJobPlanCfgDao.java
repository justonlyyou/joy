package com.kvc.joy.plugin.schedule.quartz.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.kvc.joy.commons.lang.DateTool;
import com.kvc.joy.core.persistence.orm.jpa.BaseJpaDao;
import com.kvc.joy.plugin.schedule.quartz.model.po.TQrtzJobPlan;
import com.kvc.joy.plugin.schedule.quartz.model.po.TQrtzJobPlan_;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-17 下午6:58:25
 */
@Repository
public class TQrtzJobPlanCfgDao extends BaseJpaDao<TQrtzJobPlan> {
	
	public List<TQrtzJobPlan>getEffectPlans() {
		return doQuery(new JPACallBack<TQrtzJobPlan>() {
			
			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder cb, Root<TQrtzJobPlan> root) {
				Predicate predicate1 = cb.equal(root.get(TQrtzJobPlan_.deleted), false);
				Predicate predicate2 = cb.equal(root.get(TQrtzJobPlan_.active), true);
				Predicate predicate3 = cb.greaterThan(root.get(TQrtzJobPlan_.expireTime), 
						DateTool.currentDate(DateTool.UNFMT_yyyyMMddHHmmss));
				return cb.and(predicate1, predicate2, predicate3);
			}
			
		});
	}

}
