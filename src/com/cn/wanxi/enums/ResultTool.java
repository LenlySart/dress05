package com.cn.wanxi.enums;

/**
 * 枚举：将所有的常量全部写入枚举
 */
public enum ResultTool {
    SUCCESS(0, "success"),
    ERROR(1, "error"),
    NO_LOGIN(2, "error-code"),
    EXIPERS(3, "session已过期，重新登录");

    private String msg;
    private Integer code;

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    ResultTool(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
