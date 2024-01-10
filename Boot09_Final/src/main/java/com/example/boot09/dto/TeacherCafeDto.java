package com.example.boot09.dto;

import org.apache.ibatis.type.Alias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("tcafeDto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeacherCafeDto {
    private int num;
    private String writer;
    private String title;
    private String content;
    private int viewCount;
    private String regdate;
    private int startRowNum;
    private int endRowNum;
}
