package com.kvc.joy.plugin.seqgen.support;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.plugin.seqgen.model.po.TSysSeqNum;
import com.kvc.joy.plugin.seqgen.model.po.TSysSeqNumRule;

/**
 * 序列号缓存
 * @author  <b>唐玮琳</b>
 */
public class SequenceCache {
	
	private long realSeqNum; // 实时的序列号
	private long cacheSeqNum; // 缓存中的序列号
	private final int cacheSize; // 缓存大小
	private final int increment; // 增量
	private final long maxNum; // 号码最大值
	private final long startNum; // 开始号码
	private boolean newCycle;
	
	private final TSysSeqNum seqNum;
	protected static final Log logger = LogFactory.getLog(SequenceCache.class);
	
	public SequenceCache(TSysSeqNum seqNum) {
		this.seqNum = seqNum;
		realSeqNum = seqNum.getCurSeq();
		TSysSeqNumRule seqRule = seqNum.getSeqRule();
		cacheSize = seqRule.getCacheSize();
		increment = seqRule.getIncrement();
		startNum = seqRule.getStartValue();
		Long maxValue = seqRule.getMaxValue();
		if (maxValue == null) {
			maxNum = Long.MAX_VALUE;
		} else {
			maxNum = maxValue;
		}
	}
	
//	public void onCycleRestart() {
////		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SequenceNum.TIME_DB_FMT_STR);
////		Calendar now = Calendar.getInstance();
////		String curPeriodStartTime = simpleDateFormat.format(now.getTime());
////		seqNum.setCurPeriodStartTime(curPeriodStartTime);
//		SequenceRule seqRule = seqNum.getSeqRule();
//		if (hasNext() == false) {
//			realSeqNum = seqRule.getStartValue();
//			seqNum.setCurSeq(realSeqNum);
//			seqNum.setCurPeriodStartTime(DateUtils.getCurrentTime());
//		} else {
//			System.out.println("*********************************************************************************************************");
//			seqNum.setCurSeq(seqNum.getCurSeq() - realSeqNum);
//			realSeqNum = seqRule.getStartValue() - seqRule.getIncrement();	
//		}
//	}
	
	public void refreshCache(boolean newCycle) {
		this.newCycle = newCycle;
		if (newCycle) {
			realSeqNum = startNum;
			cacheSeqNum = startNum; // + cacheSize;
		} else {
			if (hasNext() == false) {
				if (realSeqNum + increment * cacheSize > maxNum) {
					cacheSeqNum = maxNum;
				} else {
					cacheSeqNum = realSeqNum + increment * cacheSize;
				}
			} else {
				logger.warn("缓存中还有号码，不能刷新缓存！");
			}
		}
	}

	public boolean hasNext() {
		return realSeqNum < cacheSeqNum;
	}

	public long next() {
		if (newCycle == false) {
			if (hasNext() == false) {
				throw new RuntimeException("调用next()方法前，请先调用hasNext()确定缓存中还有值可以用!");
			}
			long seqNum = realSeqNum + increment;
			if (seqNum > maxNum) {
				realSeqNum = maxNum;
			} else {
				realSeqNum += increment;
			}
		} else {
			newCycle = false;
			cacheSeqNum = realSeqNum + increment * cacheSize - 1;
		} 
		return realSeqNum;
	}
	
	public long getRealSeqNum() {
		return realSeqNum;
	}
	
	public long getCacheSeqNum() {
		return cacheSeqNum;
	}
	
	public TSysSeqNum getSeqNum() {
		return seqNum;
	}

}
