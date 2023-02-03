package com.shenfangtao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.file.Files;

/**
 * Notes: 上传文件
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/1/31 13:09
 */
@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    Environment environment;

    private String uploadsDirName = "uploads";

    @PostMapping("/upload")
    public String create(@RequestPart MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        ApplicationHome home = new ApplicationHome(getClass());
//        File jarFile = null;
//        try{
//            jarFile = home.getSource() != null ?  home.getSource() : home.getDir();
//            String parent = jarFile.getParent();
//            path = jarFile.getParentFile().toString();
//            System.out.println(path);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        // 创建uploads 文件夹
        String uploadsDirPath = home+ File.separator + "static"+ File.separator + uploadsDirName;
        File uploadsDir = new File(uploadsDirPath);
        if (!uploadsDir.exists()){ //如果不存在
            boolean dr = uploadsDir.mkdirs(); //创建目录
        }
        String filePath = uploadsDir +  File.separator + fileName;
        File dest = new File(filePath);
        if (!dest.exists()){ //如果不存在
            Files.copy(file.getInputStream(), dest.toPath()); //创建文件
        }
        String port = environment.getProperty("server.port");
        String hostAddress = null;
        try {
            hostAddress = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "http://" + hostAddress + ":" + port + File.separator + uploadsDirName + File.separator + fileName;
    }
}
