package org.joy.plugin.security.user.support.enums;

import org.joy.commons.enums.EnumTool;
import org.joy.commons.enums.ICodeEnum;
import org.joy.core.sysres.SysResTool;

/**
 * ip限制类型枚举
 *
 * @author Kevice
 * @time 14-2-18 下午9:04
 * @since 1.0.0
 */
public enum IpLimitType implements ICodeEnum {

    LOGIN("01");

    public static final String CODE_TABLE_ID = "ip_limit_type";

    private final String code;

    IpLimitType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTrans() {
        return SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
    }

    public static IpLimitType enumOf(String code) {
        return EnumTool.enumOf(IpLimitType.class, code);
    }

}
