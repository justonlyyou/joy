package org.joy.core.sysres.code.model.vo;

import java.io.Serializable;

/**
 * 代码记录VO
 *
 * @author Kevice
 * @time 2013年12月7日 下午9:22:01
 * @since 1.0.0
 */
public class CodeRecord implements Serializable {

    private String code;  // 代码
    private String trans; // 译文
    private String parentCode; // 父代码
    private String order; // 顺序
    private String codeCategory; // 代码种类

    public CodeRecord(String code, String trans, String parentCode, String order, String codeCategory) {
        this.code = code;
        this.trans = trans;
        this.parentCode = parentCode;
        this.order = order;
        this.codeCategory = codeCategory;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCodeCategory() {
        return codeCategory;
    }

    public void setCodeCategory(String codeCategory) {
        this.codeCategory = codeCategory;
    }

    @Override
    public String toString() {
        return "(" + code + " : " + trans + ")";
    }

}
