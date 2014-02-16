package com.kvc.joy.plugin.security.login.model.po;

import com.kvc.joy.core.persistence.entity.UuidEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户账号安全保护
 *
 * @author 唐玮琳
 * @time 14-2-16 上午10:44
 * @since 1.0.0
 */
@Entity
@Table(name = "t_user_protect")
@Comment("用户账号安全保护")
public class TUserProtect extends UuidEntity {

    private String userId; // 用户ID
    private String question1; // 问题1
    private String answer1; // 答案1
    private String question2; // 问题2
    private String answer2; // 答案2
    private String question3; // 问题3
    private String answer3; // 答案3
    private String safeContactWayId; // 安全联系方式ID

    @Column(name = "USER_ID", length = 32, nullable = false, unique = true)
    @Comment("用户ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column
    @Comment("问题1")
    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    @Column
    @Comment("答案1")
    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    @Column
    @Comment("问题2")
    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    @Column
    @Comment("答案2")
    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    @Column
    @Comment("问题3")
    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    @Column
    @Comment("答案3")
    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    @Column(length = 32, nullable = false)
    @Comment(value = "安全联系方式ID", detailDesc = "用于取回密码")
    public String getSafeContactWayId() {
        return safeContactWayId;
    }

    public void setSafeContactWayId(String safeContactWayId) {
        this.safeContactWayId = safeContactWayId;
    }
}
