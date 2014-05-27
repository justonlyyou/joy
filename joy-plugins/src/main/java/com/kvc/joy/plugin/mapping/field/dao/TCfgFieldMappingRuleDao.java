package com.kvc.joy.plugin.mapping.field.dao;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.plugin.mapping.field.model.po.TSysFieldMappingRule;

import java.util.List;

/**
 * 字段映射规则数据访问对象
 * 
 * @author <b>Kevice</b>
 */
public class TCfgFieldMappingRuleDao {

	public List<TSysFieldMappingRule> loadAllFieldMappingRules() {
		List<TSysFieldMappingRule> ruleList;
		try {
			
			ruleList = JpaTool.searchAll(TSysFieldMappingRule.class);
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
			throw new SystemException(e, "加载字段映射规则出错！");
		}
		return ruleList;
	}

}
