/**
 * 
 */
package com.kvc.joy.plugin.mapping.field.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 字段映射元模型
 * 
 * @author 唐玮琳
 * @time 2012-04-30 下午7:31:16
 */
@StaticMetamodel(TSysFieldMapping.class)
public class TSysFieldMapping_ {

	public static volatile SingularAttribute<TSysFieldMapping, String> id;
	public static volatile SingularAttribute<TSysFieldMapping, String> field1;
	public static volatile SingularAttribute<TSysFieldMapping, String> field2;
	public static volatile SingularAttribute<TSysFieldMapping, String> fieldType;
	public static volatile SingularAttribute<TSysFieldMapping, String> desc;
	public static volatile SingularAttribute<TSysFieldMapping, TSysFieldMappingRule> rule;

}
