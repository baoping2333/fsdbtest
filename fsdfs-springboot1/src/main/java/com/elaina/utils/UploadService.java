package com.elaina.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @program: fsdbtest
 * @ClassName UploadService
 * @description:
 * @author: wanbaoping
 * @create: 2021-08-20 16:22
 * @Version 1.0
 **/
@Component
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {
     private Log log =  LogFactory.getLog(UploadService.class);
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private UploadProperties prop;

    public String uploadImage(MultipartFile file) {
        //校验文件类型
        String type = file.getContentType();
        if (!prop.getAllowTypes().contains(type)) {
            throw new RuntimeException("文件类型不支持");
        }
        System.out.println("type"+prop);
        //校验文件内容
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null || image.getWidth() == 0 || image.getHeight() == 0) {
                throw new RuntimeException("上传文件有问题");
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件内容校验失败");
            throw new RuntimeException("校验文件内容失败" + e.getMessage());
        }
        try {
            //上传到fsdfs
            //获取拓展名
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            //上传
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            return prop.getBaseUrl() + storePath.getFullPath();
        } catch (Exception e) {
            log.error("文件上传失败");
            throw new RuntimeException("文件上传失败");

        }
    }
}
