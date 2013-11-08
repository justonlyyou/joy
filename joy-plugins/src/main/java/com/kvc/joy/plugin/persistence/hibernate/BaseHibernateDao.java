package com.kvc.joy.plugin.persistence.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.kvc.joy.commons.exception.ExceptionTool;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;

/**
 * 基于的Hibernate数据访问对象基类
 * 
 * @author <b>唐玮琳</b>
 */
public abstract class BaseHibernateDao<T> {

	private static final Logger log = LoggerFactory.getLogger(BaseHibernateDao.class);
	
	/**
	 * 拼接or查询条件
	 * 
	 * @param orCriterions
	 * @return 拼接后的Criterion
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
	 * @param andCriterions
	 * @return 拼接后的Criterion
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
	 * @param criteria
	 * @return
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

	public static <T> T uniqueResult(List<T> resultList) {
		T result = null;
		if (resultList.size() == 1) {
			result = resultList.get(0);
		} else if (resultList.size() > 1) {
			log.error("期望获取唯一记录，实际查询到" + resultList.size() + "条记录，方法调用轨迹为：");
			ExceptionTool.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> find(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(session());
		return criteria.list();
	}
	
	public Session session() {
		return (Session) JpaTool.getEntityMgr().getDelegate();
//		return JpaUtils.getEntityMgr().unwrap(Session.class);
	}

}
