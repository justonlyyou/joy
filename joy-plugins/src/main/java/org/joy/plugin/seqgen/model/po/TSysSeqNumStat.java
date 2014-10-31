package org.joy.plugin.seqgen.model.po;

import org.joy.core.persistence.orm.jpa.annotations.Comment;
import org.joy.core.persistence.support.entity.UuidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 序列号统计信息实体
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2012-5-16 下午02:16:16
 */
@Entity
@Table(name = "t_sys_seq_num_stat")
@Comment("序列号统计")
public class TSysSeqNumStat extends UuidEntity {

	private String numId; // 外键，序列号码表主键
	private int periodType; // 周期类型
	private Long periodCount; // 周期数
	private Long maxNum; // 最大序列号
	private String maxFullNum; // 最大完整序列号(包括前缀、后缀)
	private Long numCount; // 序列号个数
	private String periodStartTime; // 周期开始时间，周期如果为不循环，无值
	private String statTime; // 统计时间(入库时间)

	@Column(length = 32, nullable = false)
	@Comment("序列号ID")
	public String getNumId() {
		return numId;
	}

	public void setNumId(String numId) {
		this.numId = numId;
	}

	@Column
	@Comment(value="周期类型代码", codeId="time_unit")
	public int getPeriodType() {
		return periodType;
	}

	public void setPeriodType(int periodType) {
		this.periodType = periodType;
	}

	@Column
	@Comment("周期数")
	public Long getPeriodCount() {
		return periodCount;
	}

	public void setPeriodCount(Long periodCount) {
		this.periodCount = periodCount;
	}

	@Column
	@Comment("最大序列号")
	public Long getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Long maxNum) {
		this.maxNum = maxNum;
	}

	@Column(length = 128)
	@Comment("最大完整序列号")
	public String getMaxFullNum() {
		return maxFullNum;
	}

	public void setMaxFullNum(String maxFullNum) {
		this.maxFullNum = maxFullNum;
	}

	@Column
	@Comment("序列号数")
	public Long getNumCount() {
		return numCount;
	}

	public void setNumCount(Long numCount) {
		this.numCount = numCount;
	}

	@Column(length = 14)
	@Comment("周期开始时间")
	public String getPeriodStartTime() {
		return periodStartTime;
	}

	public void setPeriodStartTime(String periodStartTime) {
		this.periodStartTime = periodStartTime;
	}

	@Column(length = 14)
	@Comment("开始时间")
	public String getStatTime() {
		return statTime;
	}

	public void setStatTime(String statTime) {
		this.statTime = statTime;
	}

}
