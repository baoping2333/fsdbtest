package com.elaina.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: fsdbtest
 * @ClassName UploadController
 * @description:
 * @author: wanbaoping
 * @create: 2021-08-20 16:40
 * @Version 1.0
 **/
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/doUpload")
    public Map<String, Object> doUplod(MultipartFile mf) {
        System.out.println(mf.getOriginalFilename());
        Map<String ,Object> map =new HashMap<>();
        String image = uploadService.uploadImage(mf);
        map.put("path",image);
        System.out.println(map);
        return map;
    }
}
