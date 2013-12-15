package com.kvc.joy.core.sysres.code.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年12月15日 下午9:04:23
 */
@Entity
@Table(name = "t_sys_codes")
@Comment("系统代码表")
public class TSysCodes extends UuidCrudEntity {

	private String groupId; // 组id
	private String groupComment; // 组注释
	private String code; // 代码
	private String trans; // 译文
	private String trnasEnUs; // 英文(美国)译文
	private String ordinal; // 排序
	private String parentCode; // 父代码
	private String pinYin; // 拼音
	private String segmentRule; // 分段规则

	@Column(length=64, nullable = false)
	@Comment("组ID")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(length=64, nullable = false)
	@Comment("组注释")
	public String getGroupComment() {
		return groupComment;
	}

	public void setGroupComment(String groupComment) {
		this.groupComment = groupComment;
	}

	@Column(length=64, nullable = false)
	@Comment("代码")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(length=128, nullable = false)
	@Comment("译文")
	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	@Column(length=16)
	@Comment("排序")
	public String getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}

	@Column(length=64)
	@Comment("父代码")
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Column(length=128)
	@Comment("拼音")
	public String getPinYin() {
		return pinYin;
	}
	
	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}
	
	@Column(length=128)
	@Comment("英文(美国)译文")
	public String getTrnasEnUs() {
		return trnasEnUs;
	}

	public void setTrnasEnUs(String trnasEnUs) {
		this.trnasEnUs = trnasEnUs;
	}

	@Column(length=32)
	@Comment("分段规则")
	public String getSegmentRule() {
		return segmentRule;
	}

	public void setSegmentRule(String segmentRule) {
		this.segmentRule = segmentRule;
	}

}
