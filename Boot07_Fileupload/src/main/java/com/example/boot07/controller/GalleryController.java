package com.example.boot07.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GalleryController {
    @Value("${file.location}")
    private String fileLocation;

    @PostMapping("/gallery/upload")
    public String upload(MultipartFile image, Model m) {
        String saveFileName = UUID.randomUUID().toString();
        String filePath = fileLocation + File.separator + saveFileName;

        try {
            File f = new File(filePath);
            image.transferTo(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m.addAttribute("saveFileName", saveFileName);

        return "gallery/upload";
    }

    @GetMapping("/gallery/uploadform")
    public String uploadform() {

        return "/gallery/uploadform";
    }

    @GetMapping("/gallery/list")
    public String list() {

        return "gallery/list";
    }

    @ResponseBody
    @GetMapping(value = "/gallery/images/{imageName}",
            // jpg, png, gif 이미지 데이터를 응답할 수 있도록 produces에 배열로 전달한다.
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,
                    MediaType.IMAGE_GIF_VALUE}) // {} : 경로변수. 동적으로 경로를 받아야할 때 사용
    public byte[] image(@PathVariable("imageName") String name) throws IOException {
        // 읽어들일 파일의 절대 경로
        String absolutePath = fileLocation + File.separator + name;
        // 파일에서 읽어들일 InputStream
        InputStream is = new FileInputStream(absolutePath);

        return IOUtils.toByteArray(is);
    }

}
