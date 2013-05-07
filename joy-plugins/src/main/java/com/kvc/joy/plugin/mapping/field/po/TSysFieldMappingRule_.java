/**
 * 
 */
package com.kvc.joy.plugin.mapping.field.po;

import java.util.Set;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 字段映射规则元模型
 * 
 * @author 唐玮琳
 * @time 2012-04-30 下午7:43:38
 */
@StaticMetamodel(TSysFieldMappingRule.class)
public class TSysFieldMappingRule_ {

	public static volatile SingularAttribute<TSysFieldMappingRule, String> ruleId;
	public static volatile SingularAttribute<TSysFieldMappingRule, String> object1;
	public static volatile SingularAttribute<TSysFieldMappingRule, String> object2;
	public static volatile SingularAttribute<TSysFieldMappingRule, String> objectType;
	public static volatile SingularAttribute<TSysFieldMappingRule, String> desc;
	public static volatile SetAttribute<TSysFieldMappingRule, Set<TSysFieldMapping>> fieldMappingSet;
}
