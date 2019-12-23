package com.hnq.study.model;

import com.hnq.study.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author henengqiang
 * @date 2019/11/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse implements Serializable {

    private int code;

    private String message;

    public static BaseResponse addError(ErrorCodeEnum errorCodeEnum) {
        return new BaseResponse(errorCodeEnum.getCode(), errorCodeEnum.getDesc());
    }

    public static BaseResponse addError(ErrorCodeEnum errorCodeEnum, String message) {
        return new BaseResponse(errorCodeEnum.getCode(), message);
    }

    public static BaseResponse addResult() {
        return new BaseResponse(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getDesc());
    }
}
