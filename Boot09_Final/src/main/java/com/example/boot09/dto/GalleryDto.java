package com.example.boot09.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("galleryDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GalleryDto {
    private int num;
    private String writer;
    private String caption;
    private String saveFileName;
    private String regdate;
    private int startRowNum;
    private int endRowNum;
    private MultipartFile image; // 이미지 파일 업로드 처리를 위한 필드
}
