package com.mawen.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/3
 */
@RestController
public class FileUploadController {

    /**
     * 上传文件
     * <p>
     * 测试方法：
     * <p>
     * 有界面的测试：http://localhost:8050/index.html
     * 使用命令：curl -F "file=@文件命名" localhost:8050/upload
     *
     * @param file 待上传文件
     * @return 文件在服务器上的绝对路径
     * @throws IOException IO 异常
     */
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        File fileToSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, fileToSave);
        return fileToSave.getAbsolutePath();
    }

}
