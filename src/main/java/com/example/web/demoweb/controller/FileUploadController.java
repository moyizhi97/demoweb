package com.example.web.demoweb.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
    String filePath;
    @PostMapping("/upload")
    public String upload(MultipartFile[] uploadFiles, HttpServletRequest req){
        int count = uploadFiles.length;
        System.out.println(count);
        for (int i=0;i<uploadFiles.length;i++) {
            MultipartFile uploadFile1 =uploadFiles[i] ;
            String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
            String format = sdf.format(new Date());
            File folder = new File(realPath + format);
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            String oldName = uploadFile1.getOriginalFilename();
            System.out.println(oldName);
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
            try {
                uploadFile1.transferTo(new File(folder, newName));
                filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + format + newName+"/n"+filePath;
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败";
            }
        }
        return filePath;
    }
}
