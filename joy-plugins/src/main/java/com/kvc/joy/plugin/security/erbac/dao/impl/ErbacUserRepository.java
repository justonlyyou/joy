package com.kvc.joy.plugin.security.erbac.dao.impl;

import com.kvc.joy.core.persistence.orm.jpa.BaseJpaDao;
import com.kvc.joy.plugin.security.erbac.dao.IErbacUserRepository;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup_;
import com.kvc.joy.plugin.security.user.model.po.TUserBasic;
import com.kvc.joy.plugin.security.user.model.po.TUserBasic_;

import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.*;


/**
 * 
 * @author Kevice
 * @time 2012-6-28 下午7:55:31
 */
public class ErbacUserRepository extends BaseJpaDao<TUserBasic> implements IErbacUserRepository {

	public Map<TErbacGroup, Collection<TUserBasic>> getUsersByGroupIds(Collection<String> groudIds) {
		Map<TErbacGroup, Collection<TUserBasic>> resultMap = new HashMap<TErbacGroup, Collection<TUserBasic>>();
		CriteriaQuery<Tuple> q = getCriteriaBuilder().createTupleQuery();
		Root<TUserBasic> c = q.from(TUserBasic.class);
		CollectionJoin<TUserBasic, TErbacGroup> o = c.join(TUserBasic_.groups, JoinType.LEFT);
		q.select(getCriteriaBuilder().tuple(c, o));
		q.where(o.get(TErbacGroup_.id.getName()).in(groudIds));
//		q.orderBy(convertOrder(c, PageStoreFactory.curQueryLogics().getOrderArray()));
//		List<Tuple> resultList = JpaPagingSupport.paging(em, q, c);
		List<Tuple> resultList = null; //TODO
		for (Tuple tuple : resultList) {
			TUserBasic user = tuple.get(c);
			TErbacGroup group = (TErbacGroup) tuple.get(1);
			List<TUserBasic> userList = (List<TUserBasic>) resultMap.get(group);
			if (userList == null) {
				userList = new ArrayList<TUserBasic>();
				resultMap.put(group, userList);
			}
			userList.add(user);
		}
		return resultMap;
	}

}
