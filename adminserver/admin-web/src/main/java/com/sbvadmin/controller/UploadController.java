package com.sbvadmin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
    public String create(@RequestPart MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        //获取当前jar 的执行路径
        ApplicationHome home = new ApplicationHome(getClass());
        File jarFile = null;
        String path = null;
        try{
            jarFile = home.getSource() != null ?  home.getSource() : home.getDir(); // TODO 写个公用函数
            String parent = jarFile.getParent();
            path = jarFile.getParentFile().toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        // 创建uploads 文件夹
        String uploadsDirPath = path+ File.separator + uploadsPath;
        File uploadsDir = new File(uploadsDirPath);
        if (!uploadsDir.exists()){ //如果不存在
            boolean dr = uploadsDir.mkdirs(); //创建目录
        }
        String filePath = uploadsDirPath +  File.separator + fileName;
        File dest = new File(filePath);
        if (!dest.exists()){ //如果不存在
            Files.copy(file.getInputStream(), dest.toPath()); //创建文件
        }
        return uploadsPath + File.separator + fileName; // 只存相对地址
    }
}
