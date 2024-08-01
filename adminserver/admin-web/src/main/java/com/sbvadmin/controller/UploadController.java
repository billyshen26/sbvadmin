package com.sbvadmin.controller;

import com.sbvadmin.common.utils.CommonUtil;
import com.sbvadmin.service.utils.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Notes: 上传文件
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/1/31 13:09
 */
@RestController
@RequestMapping("/api")
public class UploadController {

    @Value("${application.uploadsPath}")
    private String uploadsPath;

    @Autowired
    CommonService commonService;

    @PostMapping("/upload")
    public Object create(@RequestPart MultipartFile file
            , @RequestPart(value="dir" ,required=false) String dir
            , @RequestPart(value="fileName" ,required=false) String fileName) throws IOException {
        // 获取文件名
        fileName = fileName!=null?fileName:file.getOriginalFilename();
        // 创建uploads 文件夹
        if (dir == null) dir = "other";
        String uploadsDirPath = CommonUtil.getJarPath(UploadController.class) + File.separator + uploadsPath+ File.separator +dir;
        File uploadsDir = new File(uploadsDirPath);
        if (!uploadsDir.exists()){ //如果不存在
            boolean dr = uploadsDir.mkdirs(); //创建目录
        }
        String filePath = uploadsDirPath +  File.separator + fileName;
        File dest = new File(filePath);
        if (!dest.exists()){ //如果不存在
            Files.copy(file.getInputStream(), dest.toPath()); //创建文件
        }
        Map<String,Object> result = new HashMap<>();
        String relativeUrl = uploadsPath + File.separator + dir + File.separator +fileName;
        result.put("url",relativeUrl); // 相对地址
        result.put("absoluteUrl",commonService.getAvatarUrl(relativeUrl)); // 绝对地址
        return result;
    }
}
