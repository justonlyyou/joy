package com.kvc.joy.plugin.security.user.model.po;

import com.kvc.joy.core.persistence.support.entity.UuidEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户联系方式
 *
 * @author 唐玮琳
 * @time 14-2-17 下午9:19
 * @since 1.0.0
 */
@Entity
@Table(name = "t_user_contact_way")
@Comment("用户联系方式")
public class TUserContactWay extends UuidEntity {

    private String userId; // 用户ID
    private String contactWayCode; // 联系方式代码
    private String contactWay; // 联系方式
    private String remark; // 备注

    @Column(length = 32)
    @Comment("用户ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(length = 6)
    @Comment(value = "联系方式代码", codeId = "contact_way")
    public String getContactWayCode() {
        return contactWayCode;
    }

    public void setContactWayCode(String contactWayCode) {
        this.contactWayCode = contactWayCode;
    }

    @Column
    @Comment("联系方式")
    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    @Column
    @Comment("备注")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
