package com.kvc.joy.plugin.security.user.model.po;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户登陆IP限制
 *
 * @author 唐玮琳
 * @time 14-2-17 下午11:15
 * @since 1.0.0
 */
@Entity
@Table(name = "t_user_login_ip_limit")
@Comment("用户登陆IP限制")
public class TUserLoginIpLimit extends UuidCrudEntity {

    private String startIp; // 起始IP
    private String endIp; // 终止IP

    @Column(length = 32)
    @Comment("起始IP")
    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    @Column(length = 32)
    @Comment("终止IP")
    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }
}
