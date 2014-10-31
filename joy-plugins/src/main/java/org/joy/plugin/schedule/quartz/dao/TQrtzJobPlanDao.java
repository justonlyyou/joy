package org.joy.plugin.schedule.quartz.dao;

import org.joy.commons.lang.DateTool;
import org.joy.core.persistence.orm.jpa.BaseJpaDao;
import org.joy.plugin.schedule.quartz.model.po.TQrtzJobPlan;
import org.joy.plugin.schedule.quartz.model.po.TQrtzJobPlan_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 任务调度计划配置数据访问对象
 *
 * @author Kevice
 * @time 2013-2-17 下午6:58:25
 * @since 1.0.0
 */
public class TQrtzJobPlanDao extends BaseJpaDao<TQrtzJobPlan> {

    /**
     * 查询所有有效的计划配置
     *
     * @return List<TQrtzJobPlan>
     * @author Kevice
     * @time 2013-2-17 下午6:58:25
     * @since 1.0.0
     */
    public List<TQrtzJobPlan> getEffectPlans() {
        return doQuery(new JPACallBack<TQrtzJobPlan>() {

            @Override
            public Expression<Boolean> getRestriction(CriteriaBuilder cb, Root<TQrtzJobPlan> root) {
                Predicate predicate1 = cb.equal(root.get(TQrtzJobPlan_.deleted), "0");
                Predicate predicate2 = cb.equal(root.get(TQrtzJobPlan_.active), "1");
                Predicate predicate3 = cb.greaterThan(root.get(TQrtzJobPlan_.expireTime),
                        DateTool.currentDate(DateTool.UNFMT_yyyyMMddHHmmss));
                return cb.and(predicate1, predicate2, predicate3);
            }

        });
    }

}
