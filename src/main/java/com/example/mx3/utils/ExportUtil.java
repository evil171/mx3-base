package com.example.mx3.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.example.mx3.exception.BizException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExportUtil {

    public static ResponseEntity<Resource> export(InputStream is, String type, String filename) {
        return ExportUtil.export(
                ExportUtil.parse(is),
                type,
                filename
        );
    }

    public static ResponseEntity<Resource> export(ByteArrayOutputStream bos, String contentType, String filename) {

        Resource resource = new ByteArrayResource(bos.toByteArray());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/" + contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        StrUtil.format(
                                "attachment; filename=\"{}\"",
                                URLUtil.encode(filename)
                        )
                )
                .body(resource);
    }

    public static ByteArrayOutputStream parse(InputStream in) {

        final ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        int ch;
        try {
            while ((ch = in.read()) != -1) {
                swapStream.write(ch);
            }
        } catch (IOException e) {
            throw new BizException("文件读取失败", e);
        }
        return swapStream;
    }

}