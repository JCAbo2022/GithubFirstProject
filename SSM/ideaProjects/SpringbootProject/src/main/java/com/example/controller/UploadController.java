package com.example.controller;

import com.example.pojo.Result;
import com.example.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /*@PostMapping("/upload")
    public Result upload(String username, Integer age, @RequestParam("image") MultipartFile image) throws IOException {
        log.info("文件上传：{}, {}, {}", username, age, image);

        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构建新的唯一文件名
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));//获取文件扩展名
        String newFileName = UUID.randomUUID().toString() + extName;//构建新文件名

        //将文件存储在本地服务器的磁盘目录中：C:\Users\zhoujianbo\Desktop\SSM\文件上传demo
        image.transferTo(new File("C:\\Users\\zhoujianbo\\Desktop\\SSM\\文件上传demo\\" + newFileName));

        return Result.success();
    }*/

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException{
        String url = aliOSSUtils.upload(image);//调用阿里云OSS工具类，将上传上来的文件存入阿里云
        return Result.success(url);//将图片上传完成后的URL返回，用于浏览器展示图片
    }

}
