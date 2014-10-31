package org.joy.plugin.security.erbac.dao.impl;

import org.joy.core.persistence.orm.jpa.BaseJpaDao;
import org.joy.plugin.security.erbac.model.po.TErbacAuthority;
import org.joy.plugin.security.erbac.model.po.TErbacAuthority_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 权限数据访问接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-23 下午12:40:12
 */
public class TErbacAuthorityDao extends BaseJpaDao<TErbacAuthority> {

	public List<TErbacAuthority> getActiveAuthorities() {
		return doQuery(new JPACallBack<TErbacAuthority>() {

			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder criteriaBuilder, Root<TErbacAuthority> root) {
				Predicate predicate1 = criteriaBuilder.equal(root.get(TErbacAuthority_.deleted), "0");
				Predicate predicate2 = criteriaBuilder.equal(root.get(TErbacAuthority_.active), "1");
				return criteriaBuilder.and(predicate1, predicate2);
			}

		});
	}
	
	public List<TErbacAuthority> getActiveUrlAuthorities() {
		return doQuery(new JPACallBack<TErbacAuthority>() {

			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder criteriaBuilder, Root<TErbacAuthority> root) {
				Predicate predicate1 = criteriaBuilder.equal(root.get(TErbacAuthority_.deleted), "0");
				Predicate predicate2 = criteriaBuilder.equal(root.get(TErbacAuthority_.active), "1");
				Predicate predicate3 = criteriaBuilder.equal(root.get(TErbacAuthority_.resourceTypeCode), "01"); //TODO AuthResourceType.URL.getCode()
				return criteriaBuilder.and(predicate1, predicate2, predicate3);
			}

		});
	}

}
