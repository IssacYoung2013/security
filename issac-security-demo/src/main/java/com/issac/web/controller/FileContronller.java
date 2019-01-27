package com.issac.web.controller;

import com.issac.dto.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * author:  ywy
 * date:    2019-01-24
 * desc:
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileContronller {

    private String folder = "/Users/Issac/workspaces/github/security/issac-security-demo/";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        log.info("{},{},{}", file.getName(), file.getOriginalFilename(), file.getSize());

        File localFile = new File(folder, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(HttpServletRequest request,
                         HttpServletResponse response,
                         @PathVariable String id) {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream()) {

            response.setContentType("application/x-donwload");
            response.addHeader("Content-Dispositon", "attachment;filename-test.txt");

            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
