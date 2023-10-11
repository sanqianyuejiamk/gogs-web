package com.mengka.springboot.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * @author mengka
 * @Date 2022-07-04 20:02
 */
@Data
@Accessors(chain = true)
public class Out implements Serializable {

    @JSONField(name="return_code", ordinal = 1)
    private String returnCode;

    @JSONField(name="return_msg", ordinal = 1)
    private String returnMsg;
}
