/**
 * 
 */
package com.kvc.joy.plugin.seqgen.model.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 序列号统计对象元模型
 * 
 * @author 唐玮琳
 * @time 2012-04-30 下午7:28:09
 */
@StaticMetamodel(TSysSeqNumStat.class)
public class TSysSeqNumStat_ {

	public static volatile SingularAttribute<TSysSeqNumStat, String> id;
	public static volatile SingularAttribute<TSysSeqNumStat, String> numId;
	public static volatile SingularAttribute<TSysSeqNumStat, Integer> periodType;
	public static volatile SingularAttribute<TSysSeqNumStat, Long> periodCount;
	public static volatile SingularAttribute<TSysSeqNumStat, Long> maxNum;
	public static volatile SingularAttribute<TSysSeqNumStat, String> maxFullNum;
	public static volatile SingularAttribute<TSysSeqNumStat, Long> numCount;
	public static volatile SingularAttribute<TSysSeqNumStat, String> periodStartTime;
	public static volatile SingularAttribute<TSysSeqNumStat, String> statTime;

}
