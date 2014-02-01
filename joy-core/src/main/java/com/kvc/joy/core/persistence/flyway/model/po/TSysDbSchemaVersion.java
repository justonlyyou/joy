package com.kvc.joy.core.persistence.flyway.model.po;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月15日 上午11:12:58
 */
@Entity
@Table(name = "t_sys_db_schema_version")
@Comment("数据库脚本脚本")
public class TSysDbSchemaVersion implements IEntity<String> {

	private String id;
	private int versionRank;
	private int installedRank;
	private String versionDomain; // 版本域 (扩展flyway的字段)
	private String version;
	private String desc;
	private String type;
	private String script;
	private Integer checksum;
	private String installedBy;
	private String installedOn;
	private int executionTime;
	private String success;
	
	@Id
	@Column(length = 32)
	@Comment(value="主键", detailDesc="生成策略: UUID")
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 32)
	@Comment("版本域")
	public String getVersionDomain() {
		return versionDomain;
	}

	public void setVersionDomain(String versionDomain) {
		this.versionDomain = versionDomain;
	}

	@Comment("版本序号")
	public int getVersionRank() {
		return versionRank;
	}

	public void setVersionRank(int versionRank) {
		this.versionRank = versionRank;
	}

	@Column(nullable = false)
	@Comment("安装序号")
	public int getInstalledRank() {
		return installedRank;
	}

	public void setInstalledRank(int installedRank) {
		this.installedRank = installedRank;
	}

	@Column(length = 64, nullable = false)
	@Comment("版本")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "DESCRIPTION", nullable = false)
	@Comment("描述")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(length = 20, nullable = false)
	@Comment("类型")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(length = 128, nullable = false)
	@Comment("脚本")
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	@Column(nullable = true)
	@Comment("校验和")
	public Integer getChecksum() {
		return checksum;
	}

	public void setChecksum(Integer checksum) {
		this.checksum = checksum;
	}

	@Column(length = 100)
	@Comment("安装用户")
	public String getInstalledBy() {
		return installedBy;
	}

	public void setInstalledBy(String installedBy) {
		this.installedBy = installedBy;
	}

	@Column(length = 17, nullable = false)
	@Comment("安装时间")
	public String getInstalledOn() {
		return installedOn;
	}

	public void setInstalledOn(String installedOn) {
		this.installedOn = installedOn;
	}

	@Column(nullable = false)
	@Comment("耗时")
	public int getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	@Column(length = 1, nullable = false)
	@Comment("是否成功")
	public String getSuccess() {
		return success;
	}

	public boolean success() {
		return "1".equals(success);
	}
	
	public void setSuccess(String success) {
		this.success = success;
	}

}
