package com.kvc.joy.core.sysres.code.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.kvc.joy.core.persistence.orm.jpa.BaseJpaDao;
import com.kvc.joy.core.sysres.code.model.po.TSysCodeTable;
import com.kvc.joy.core.sysres.code.model.po.TSysCodeTable_;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年12月15日 下午8:23:38
 */
public class TSysCodeTableDao extends BaseJpaDao<TSysCodeTable> {
	
	public List<TSysCodeTable>getGroupingCodeTables() {
		return doQuery(new JPACallBack<TSysCodeTable>() {
			
			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder cb, Root<TSysCodeTable> root) {
				Predicate predicate1 = cb.equal(root.get(TSysCodeTable_.deleted), "0");
				Predicate predicate2 = cb.equal(root.get(TSysCodeTable_.active), "1");
				Predicate predicate3 = cb.and(root.get(TSysCodeTable_.groupingComment).isNotNull());
				return cb.and(predicate1, predicate2, predicate3);
			}
			
		});
	}

}
