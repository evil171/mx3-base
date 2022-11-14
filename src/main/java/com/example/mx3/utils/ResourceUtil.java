package com.example.mx3.utils;

import cn.hutool.core.io.IoUtil;
import com.example.mx3.exception.BizException;
import lombok.var;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ResourceUtil {

    public static InputStream readFile(String path) {

        InputStream is;
        try {
            is = new ClassPathResource(path).getInputStream();
        } catch (IOException e) {
            throw new BizException("{}读取失败", path);
        }

        return is;
    }

    public static String readFileContent(String path) {

        var is = ResourceUtil.readFile(path);
        return IoUtil.getReader(is, StandardCharsets.UTF_8)
                .lines()
                .collect(Collectors.joining());
    }

}
