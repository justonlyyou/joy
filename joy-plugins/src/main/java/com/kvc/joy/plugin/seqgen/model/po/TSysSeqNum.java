package com.kvc.joy.plugin.seqgen.model.po;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.core.persistence.orm.jpa.annotations.DefaultValue;

import javax.persistence.*;

/**
 * 序列号信息对象模型
 * 
 * @author <b>Kevice</b>
 */
@Entity
@Table(name = "t_sys_seq_num")
@Comment("序列号")
public class TSysSeqNum implements IEntity<String> {

	/** 当前周期开始时间格式 */
	public static final String TIME_DB_FMT_STR = "yyyyMMddHHmmss";

	private String id;
	private String seqName; // 序列号名称
	private long curSeq; // 当前序列号(不包含前缀、后缀)
	private String curPeriodStartTime; // 当前周期开始时间
	private TSysSeqNumRule seqRule; // 序列号生成规则
	private String active; // 有效性
	private String prefixParams; // 前缀模板的参数值串，以逗号分隔
	private String suffixParams; // 后缀模板的参数值串，以逗号分隔

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(length=32, nullable=false)
	@Comment("主键")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Comment("前缀模板参数")
	public String getPrefixParams() {
		return prefixParams;
	}

	public void setPrefixParams(String prefixParams) {
		this.prefixParams = prefixParams;
	}

	@Comment("后缀模板参数")
	public String getSuffixParams() {
		return suffixParams;
	}

	public void setSuffixParams(String suffixParams) {
		this.suffixParams = suffixParams;
	}

	@Column(length=64, nullable=false)
	@Comment("序列号名称")
	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	@Column(length=20)
	@Comment("序列号当前值")
	public long getCurSeq() {
		return curSeq;
	}

	public void setCurSeq(long curSeq) {
		this.curSeq = curSeq;
	}

	@Column(length = 14)
	@Comment("当前周期开始值")
	public String getCurPeriodStartTime() {
		return curPeriodStartTime;
	}

	public void setCurPeriodStartTime(String curPeriodStartTime) {
		this.curPeriodStartTime = curPeriodStartTime;
	}

	@Column(nullable = false)
	@DefaultValue("1")
	@Comment("是否启用")
	public String getActive() {
		return active;
	}
	
	public boolean active() {
		return "1".equals(active);
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "RULE_ID") 
	@Comment("规则ID")
	public TSysSeqNumRule getSeqRule() {
		return seqRule;
	}

	public void setSeqRule(TSysSeqNumRule seqRule) {
		this.seqRule = seqRule;
	}

}
