package com.sbvadmin.controller;

import com.sbvadmin.common.utils.CommonWebUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

    @PostMapping("/upload")
    public String create(@RequestPart MultipartFile file, @RequestPart(value="dir" ,required=false) String dir) throws IOException {
//    public String create(@RequestPart MultipartFile file, @RequestPart String dir) throws IOException {
        String fileName = file.getOriginalFilename();

        // 创建uploads 文件夹
        if (dir == null) dir = "other";
        String uploadsDirPath = CommonWebUtil.getJarPath(UploadController.class) + File.separator + uploadsPath+ File.separator +dir;
        File uploadsDir = new File(uploadsDirPath);
        if (!uploadsDir.exists()){ //如果不存在
            boolean dr = uploadsDir.mkdirs(); //创建目录
        }
        String filePath = uploadsDirPath +  File.separator + fileName;
        File dest = new File(filePath);
        if (!dest.exists()){ //如果不存在
            Files.copy(file.getInputStream(), dest.toPath()); //创建文件
        }
        return uploadsPath + File.separator + dir + File.separator +fileName; // 只存相对地址
    }
}
