/**
 * 
 */
package org.joy.plugin.seqgen.service;

import org.joy.plugin.seqgen.model.po.TSysSeqNum;

/**
 * 序列号生成器接口
 * 
 * @author Kevice
 * @time 2012-04-30 下午5:08:04
 */
public interface ISequenceGenerator {

	/**
	 * 取得下一个序列号
	 * 
	 * @param seqNumId 序列号码表的主键
	 * @return 序列号(包含前缀、后缀)
	 */
	String nextSequence(String seqNumId);

	/**
	 * 取得当前序列号
	 * 
	 * @param seqNumId 序列号码表的主键
	 * @return 序列号(包含前缀、后缀)
	 */
	String curSequence(String seqNumId);

	/**
	 * 取得当前序列号
	 * 
	 * @param seqNumId 序列号码表的主键
	 * @return 序列号(包含前缀、后缀)
	 */
	long curSeqNum(String seqNumId);

	/**
	 * 填充模板，仅在子类和序列号统计时调用
	 * 
	 * @author Kevice
	 * @time 2012-04-30 下午5:13:31
	 * @param curSeq 当前序列号值(不包含前缀、后缀)
	 * @param seqCache 序列号缓存
	 * @param seqNum 序列号对象
	 * @return 包含前缀、后缀的序列号
	 */
	String fillPattern(long curSeq, TSysSeqNum seqNum);

	/**
	 * 集群时使用，启动序列号生成服务
	 * 
	 * @author Kevice
	 * @time 2012-04-30 下午5:10:28
	 */
	void start();

	/**
	 * 集群时使用，停止序列号生成服务 将调用onExit()
	 * 
	 * @author Kevice
	 * @time 2012-04-30 下午5:10:54
	 */
	void stop();

	/**
	 * 系统退出时，将缓存中的序列号同步到数据库
	 */
	void onExit();

}
