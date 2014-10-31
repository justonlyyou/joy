package org.joy.plugin.persistence.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.joy.commons.exception.ExceptionTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.persistence.orm.jpa.JpaTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 基于的Hibernate数据访问对象基类
 *
 * @since 1.0.0
 * @author Kevice
 * @date 2012-5-16 下午02:17:44
 */
public abstract class BaseHibernateDao<T> {

	protected static final Log logger = LogFactory.getLog(BaseHibernateDao.class);
	
	/**
	 * 拼接or查询条件
	 * 
	 * @param orCriterions 或条件可变数组
	 * @return 拼接后的Criterion
     * @since 1.0.0
     * @author Kevice
     * @date 2012-5-16 下午02:17:44
	 */
	protected Criterion appendOrCriterions(Criterion... orCriterions) {
		Criterion orCriterion = null;
		for (Criterion criterion : orCriterions) {
			if (orCriterion == null) {
				orCriterion = criterion;
			} else {
				orCriterion = Restrictions.or(orCriterion, criterion);
			}
		}
		return orCriterion;
	}

	/**
	 * 拼接and查询条件
	 * 
	 * @param andCriterions 与条件可变数组
	 * @return 拼接后的Criterion
     * @since 1.0.0
     * @author Kevice
     * @date 2012-5-16 下午02:17:44
	 */
	protected Criterion appendAndCriterions(Criterion... andCriterions) {
		Criterion andCriterion = null;
		for (Criterion criterion : andCriterions) {
			if (andCriterion == null) {
				andCriterion = criterion;
			} else {
				andCriterion = Restrictions.and(andCriterion, criterion);
			}
		}
		return andCriterion;
	}

	/**
	 * 查询唯一结果
	 * 
	 * @param criteria CriteriaSpecification
	 * @return 实体对象
     * @since 1.0.0
     * @author Kevice
     * @date 2012-5-16 下午02:17:44
	 */
	@SuppressWarnings("unchecked")
	protected T uniqueResult(CriteriaSpecification criteria) {
		List<T> resultList = null;
		if (criteria instanceof DetachedCriteria) {
//			getSession()
			
			resultList = find((DetachedCriteria) criteria);
		} else if (criteria instanceof Criteria) {
			resultList = ((Criteria) criteria).list();
		}
		return uniqueResult(resultList);
	}

    /**
     * 查询唯一结果
     *
     * @param resultList 结果列表
     * @return 实体对象
     * @since 1.0.0
     * @author Kevice
     * @date 2012-5-16 下午02:17:44
     */
	public static <T> T uniqueResult(List<T> resultList) {
		T result = null;
		if (resultList.size() == 1) {
			result = resultList.get(0);
		} else if (resultList.size() > 1) {
			logger.error("期望获取唯一记录，实际查询到" + resultList.size() + "条记录，方法调用轨迹为：");
			ExceptionTool.printStackTrace();
		}
		return result;
	}

    /**
     * 执行查询操作
     *
     * @param detachedCriteria DetachedCriteria
     * @return 实体对象列表
     * @since 1.0.0
     * @author Kevice
     * @date 2012-5-16 下午02:17:44
     */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> find(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(session());
		return criteria.list();
	}

    /**
     * 返回hibernate session
     *
     * @return hibernate session
     * @since 1.0.0
     * @author Kevice
     * @date 2012-5-16 下午02:17:44
     */
	public Session session() {
		return (Session) JpaTool.getEntityMgr().getDelegate();
//		return JpaUtils.getEntityMgr().unwrap(Session.class);
	}

}
