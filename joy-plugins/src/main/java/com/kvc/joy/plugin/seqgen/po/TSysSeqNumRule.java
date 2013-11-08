package com.kvc.joy.plugin.seqgen.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.core.persistence.orm.jpa.annotations.DefaultValue;

/**
 * 序列号生成规则对象模型
 * 
 * @author <b>唐玮琳</b>
 */
@Entity
@Table(name = "t_sys_seq_num_rule")
@Comment("序列号规则")
public class TSysSeqNumRule implements IEntity<String> {

	private String id;
	private String name; // 名称,PK
	private long startValue = 1L; // 序列号起始值，默认为1
	private Long maxValue; // 序列号最大值，大于0时，将忽略周期类型
	private int increment = 1; // 增量,默认为1
	private Integer periodType; // 周期类型,取值见类常量,为空不循环
	private Long periodCount = 1L; // 周期数，默认为1
	private Integer length; // 长度, 有定义时，不足位数会自动补0
	private String prefix; // 前缀
	private String suffix; // 后缀
	private int cacheSize; 

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
	
	@Column(length = 32, nullable = false)
	@Comment("规则名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 20, nullable = false)
	@DefaultValue("1")
	@Comment("起始值")
	public long getStartValue() {
		return startValue;
	}

	public void setStartValue(long startValue) {
		this.startValue = startValue;
	}

	@Column(length = 20)
	@Comment(value="最大值", detailDesc="如果有设置，将忽略周期配置")
	public Long getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}

	@Column(length = 1)
	@Comment(value="周期类型代码", codeId="joy_code_time_unit", detailDesc="为空不循环")
	public Integer getPeriodType() {
		return periodType;
	}

	public void setPeriodType(Integer periodType) {
		this.periodType = periodType;
	}

	@Column(length = 10)
	@Comment("周期数")
	public Long getPeriodCount() {
		return periodCount;
	}

	public void setPeriodCount(Long periodCount) {
		this.periodCount = periodCount;
	}

	@Column(length = 4)
	@Comment(value="序列号长度", detailDesc="不足位数时将左补0")
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	@Column(length = 64)
	@Comment(value="前缀模板", detailDesc="支持java花括号参数占位符，" +
			"如{0}代表第一个参数，还支持java日期格式化，格式化串前后用%括起来")
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(length = 64)
	@Comment(value="后缀模板", detailDesc="支持java花括号参数占位符，" +
			"如{0}代表第一个参数，还支持java日期格式化，格式化串前后用%括起来")
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Column(length = 6, nullable = false)
	@Comment("增量值")
	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	@Column(length = 6)
	@Comment(value="缓存大小", detailDesc="为0或1表示不缓存")
	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

}
