package com.kvc.joy.core.persistence.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

/**
 * 主键生成策略是UUID的实体基类
 * 
 * @author 唐玮琳
 * @time 2012-6-17 下午9:45:42
 */
@MappedSuperclass
public class UuidEntity implements IEntity<String> {

	protected String id;

	public UuidEntity() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	@Id
	@Column(length = 32)
	@Comment(value="主键", detailDesc="生成策略: UUID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UuidEntity)) {
			return false;
		}
		UuidEntity other = (UuidEntity) obj;
		return getId().equals(other.getId());
	}

}
