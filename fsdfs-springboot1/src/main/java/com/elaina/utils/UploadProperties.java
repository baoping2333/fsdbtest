package com.elaina.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @program: fsdbtest
 * @ClassName UploadProperties
 * @description:
 * @author: wanbaoping
 * @create: 2021-08-20 16:14
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadProperties {
    private String baseUrl;
    private List<String> allowTypes;
}
