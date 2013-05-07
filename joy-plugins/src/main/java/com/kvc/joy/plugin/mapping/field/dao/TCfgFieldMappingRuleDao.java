package com.kvc.joy.plugin.mapping.field.dao;

import java.util.List;

import com.kvc.joy.commons.exception.ServiceException;
import com.kvc.joy.core.persistence.orm.jpa.JpaUtils;
import com.kvc.joy.plugin.mapping.field.po.TSysFieldMappingRule;

/**
 * 字段映射规则数据访问对象
 * 
 * @author <b>唐玮琳</b>
 */
public class TCfgFieldMappingRuleDao {

	public List<TSysFieldMappingRule> loadAllFieldMappingRules() {
		List<TSysFieldMappingRule> ruleList = null;
		try {
			
			ruleList = JpaUtils.searchAll(TSysFieldMappingRule.class);
			for (TSysFieldMappingRule rule : ruleList) {
				rule.getFieldMappingSet();
			}
			
//			ruleList = HibernateUtil.hibernateTemplate().executeFind(new HibernateCallback<List<TSysFieldMapRule>>() {
//
//				public List<TSysFieldMapRule> doInHibernate(Session session) throws HibernateException, SQLException {
//					Criteria criteria = session.createCriteria(TSysFieldMapRule.class);
//					List<TSysFieldMapRule> list = criteria.list();
//					for (TSysFieldMapRule rule : list) {
//						Hibernate.initialize(rule.getFieldMappingSet());
//					}
//					return list;
//				}
//			});
		} catch (Exception e) {
			throw new ServiceException("加载字段映射规则出错！", e);
		}
		return ruleList;
	}

}
