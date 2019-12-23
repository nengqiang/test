package com.hnq.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author henengqiang
 * @date 2019/11/07
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    /**
     * 操作成功
     */
    SUCCESS(10000, "操作成功"),

    /**
     * 参数错误。
     */
    PARAM_ERROR(10001, "参数错误"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(10002, "系统异常"),

    /**
     * 操作失败
     */
    OPERATE_FAILED(10003, "操作失败");

    private int code;

    private String desc;

}
