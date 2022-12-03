package com.example.mx3.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @since 2020/3/7 10:35 下午
 */
@Data
@Accessors(chain = true)
public class CaptchaBean {

    /**
     * 图片
     */
    private String base64;

    /**
     * token
     */
    private String token;

}
