package com.hnq.study.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author henengqiang
 * @date 2019/11/07
 */
@Data
public class BaseRequest implements Serializable {

    @NotNull(message = "channel cannot be empty!")
    private String channel;

}
