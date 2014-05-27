package com.kvc.joy.core.persistence.jdbc.model.vo;

import com.kvc.joy.commons.lang.string.StringTool;

import java.io.Serializable;

/**
 * 列注释 格式：briefDesc, [detailDesc,][ {"codeId":"xxx"}]
 * 
 * @author Kevice
 * @time 2013-2-5 下午11:26:43
 */
public class MdRdbColumnComment implements Serializable {

	private String briefDesc; // 简要描述
	private String detailDesc; // 详细描述
	private String codeId; // 代码表id
	private String origComment; // 原始注释

	public MdRdbColumnComment() {
	}

	public MdRdbColumnComment(String origComment) {
		this.origComment = origComment;
	}

	public String getBriefDesc() {
		return briefDesc;
	}

	public void setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getOrigComment() {
		return origComment;
	}

	public void setOrigComment(String origComment) {
		this.origComment = origComment;
	}

	@Override
	public String toString() {
		String briefDesc = StringTool.isBlank(this.briefDesc) ? "" : this.briefDesc;
		String detailDesc = StringTool.isBlank(this.detailDesc) ? "" : ", " + this.detailDesc;
		String codeId = StringTool.isBlank(this.codeId) ? "" : ", {\"codeId\":\"" + this.codeId + "\"}";
		return briefDesc + detailDesc + codeId;
	}
	
}
