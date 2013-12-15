/**
 * 
 */
package com.kvc.joy.core.sysres.code.model.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;

/**
 * 
 * @author 唐玮琳
 * @time 2012-4-30 下午9:52:08
 */
@StaticMetamodel(TSysCodeTable.class)
public class TSysCodeTable_ extends UuidCrudEntity_ {

	public static volatile SingularAttribute<TSysCodeTable, String> tableName;
	public static volatile SingularAttribute<TSysCodeTable, String> cnTableName;
	public static volatile SingularAttribute<TSysCodeTable, String> codeField;
	public static volatile SingularAttribute<TSysCodeTable, String> transField;
	public static volatile SingularAttribute<TSysCodeTable, String> parentField;
	public static volatile SingularAttribute<TSysCodeTable, String> pinyinField;
	public static volatile SingularAttribute<TSysCodeTable, String> segmentRule;
	public static volatile SingularAttribute<TSysCodeTable, String> groupingField;
	public static volatile SingularAttribute<TSysCodeTable, String> groupingComment;
	public static volatile SingularAttribute<TSysCodeTable, String> activeField;
	public static volatile SingularAttribute<TSysCodeTable, String> deletedField;
	public static volatile SingularAttribute<TSysCodeTable, TSysDataSrc> dataSrc;
	

}
