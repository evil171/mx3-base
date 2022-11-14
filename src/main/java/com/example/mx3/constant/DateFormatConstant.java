package com.example.mx3.constant;

import java.time.format.DateTimeFormatter;

public interface DateFormatConstant {

    /**
     * 日期 格式化
     */
    String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 时间 格式化
     */
    String TIME_FORMAT = "HH:mm:ss";

    /**
     * 日期时间 格式化
     */
    String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期 格式化
     */
    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * 时间 格式化
     */
    DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    /**
     * 日期时间 格式化
     */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

}
