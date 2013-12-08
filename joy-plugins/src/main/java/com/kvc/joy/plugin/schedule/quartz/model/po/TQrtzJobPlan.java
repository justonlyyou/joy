package com.kvc.joy.plugin.schedule.quartz.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

@Entity
@Table(name = "t_qrtz_job_plan")
@Comment("任务调度计划")
public class TQrtzJobPlan extends UuidCrudEntity {

	// Fields
	private String name; // 计划名称
	private String cronExp; // cron表达式
	private String runState; // 运行状态, JobRunState
	private String effectTime; // 生效时间
	private String expireTime; // 到期时间
	private String lastFireTime; // 上次执行时间
	private String nextFireTime; // 下次执行时间
	

	@Column(length = 64, nullable = false)
	@Comment("名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 256)
	@Comment("cron表达式")
	public String getCronExp() {
		return cronExp;
	}

	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}

	@Column(length = 2, nullable = false)
	@Comment(value="运行状态代码", codeId="joy_code_job_run_state")
	public String getRunState() {
		return runState;
	}

	public void setRunState(String runState) {
		this.runState = runState;
	}

	@Column(length = 14)
	@Comment("生效时间")
	public String getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(String effectTime) {
		this.effectTime = effectTime;
	}

	@Column(length = 14)
	@Comment("过期时间")
	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	@Column(length = 14)
	@Comment("最后一次运行时间")
	public String getLastFireTime() {
		return lastFireTime;
	}

	public void setLastFireTime(String lastFireTime) {
		this.lastFireTime = lastFireTime;
	}

	@Column(length = 14)
	@Comment("下一次运行时间")
	public String getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	
}