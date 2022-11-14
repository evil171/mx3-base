package com.example.mx3.bean;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @since 2020/1/20 4:16 下午
 */
@Data
public class ResultBean<T> {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    private ResultBean() {

    }

    public static <T> ResultBean<T> success(T data) {
        return success(HttpStatus.OK.value(), data);
    }

    public static <T> ResultBean<T> success(Integer code, T data) {

        ResultBean<T> bean = new ResultBean<>();
        bean.setSuccess(true);
        bean.setCode(code);
        bean.setData(data);
        return bean;
    }

    public static <T> ResultBean<T> fail(Integer code, String message) {

        ResultBean<T> bean = new ResultBean<>();
        bean.setSuccess(false);
        bean.setCode(code);
        bean.setMessage(message);
        return bean;
    }
}
