package com.kvc.joy.plugin.security.erbac.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.kvc.joy.core.persistence.orm.jpa.BaseJpaDao;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacAuthority;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacAuthority_;
import com.kvc.joy.plugin.security.erbac.support.enums.AuthResourceType;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-23 下午12:40:12
 */
public class TErbacAuthorityDao extends BaseJpaDao<TErbacAuthority> {

	public List<TErbacAuthority> getActiveAuthorities() {
		return doQuery(new JPACallBack<TErbacAuthority>() {

			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder criteriaBuilder, Root<TErbacAuthority> root) {
				Predicate predicate1 = criteriaBuilder.equal(root.get(TErbacAuthority_.deleted), false);
				Predicate predicate2 = criteriaBuilder.equal(root.get(TErbacAuthority_.active), true);
				return criteriaBuilder.and(predicate1, predicate2);
			}

		});
	}
	
	public List<TErbacAuthority> getActiveUrlAuthorities() {
		return doQuery(new JPACallBack<TErbacAuthority>() {

			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder criteriaBuilder, Root<TErbacAuthority> root) {
				Predicate predicate1 = criteriaBuilder.equal(root.get(TErbacAuthority_.deleted), false);
				Predicate predicate2 = criteriaBuilder.equal(root.get(TErbacAuthority_.active), true);
				Predicate predicate3 = criteriaBuilder.equal(root.get(TErbacAuthority_.resourceTypeCode), AuthResourceType.URL.getCode());
				return criteriaBuilder.and(predicate1, predicate2, predicate3);
			}

		});
	}

}
