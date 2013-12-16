/**
 * 
 */
package com.kvc.joy.plugin.seqgen.model.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 序列号元模型
 * 
 * @author 唐玮琳
 * @time 2012-04-30 下午6:06:29
 */
@StaticMetamodel(TSysSeqNum.class)
public class TSysSeqNum_ {

	public static volatile SingularAttribute<TSysSeqNum, String> id;
	public static volatile SingularAttribute<TSysSeqNum, String> seqName;
	public static volatile SingularAttribute<TSysSeqNum, Long> curSeq;
	public static volatile SingularAttribute<TSysSeqNum, String> curPeriodStartTime;
	public static volatile SingularAttribute<TSysSeqNum, String> active;
	public static volatile SingularAttribute<TSysSeqNum, String> prefixParams;
	public static volatile SingularAttribute<TSysSeqNum, String> suffixParams;
	public static volatile SingularAttribute<TSysSeqNum, TSysSeqNumRule> seqRule;
}
