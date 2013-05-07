/**
 * 
 */
package com.kvc.joy.plugin.seqgen.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 序列号规则元模型
 * 
 * @author 唐玮琳
 * @time 2012-04-30 下午6:11:37
 */
@StaticMetamodel(TSysSeqNumRule.class)
public class TSysSeqNumRule_ {

	public static volatile SingularAttribute<TSysSeqNumRule, String> id;
	public static volatile SingularAttribute<TSysSeqNumRule, String> name;
	public static volatile SingularAttribute<TSysSeqNumRule, Long> startValue;
	public static volatile SingularAttribute<TSysSeqNumRule, Long> maxValue;
	public static volatile SingularAttribute<TSysSeqNumRule, Integer> increment;
	public static volatile SingularAttribute<TSysSeqNumRule, Integer> periodType;
	public static volatile SingularAttribute<TSysSeqNumRule, Long> periodCount;
	public static volatile SingularAttribute<TSysSeqNumRule, Integer> length;
	public static volatile SingularAttribute<TSysSeqNumRule, String> prefix;
	public static volatile SingularAttribute<TSysSeqNumRule, String> suffix;
	public static volatile SingularAttribute<TSysSeqNumRule, Integer> cacheSize;

}
