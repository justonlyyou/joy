package com.kvc.joy.plugin.security.user.model.po;

import com.kvc.joy.core.persistence.support.entity.UuidCrudEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户黑名单
 *
 * @author 唐玮琳
 * @time 14-2-17 下午11:36
 * @since 1.0.0
 */
@Entity
@Table(name = "t_user_blacklist")
@Comment("用户黑名单")
public class TUserBlacklist extends UuidCrudEntity {

    private String userId; // 用户id
    private String reason; // 加入黑名单的原因

    @Column(length = 32)
    @Comment("用户ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column
    @Comment("加入黑名单的原因")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
