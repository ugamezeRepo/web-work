package com.example.boot09.dto;

import org.apache.ibatis.type.Alias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("cafeDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CafeDto {
    private int num;
    private String writer;
    private String title;
    private String Content;
    private int viewCount;
    private String regdate;
    private int startRowNum;
    private int endRowNum;
    private int prevNum;
    private int nextNum;
}
