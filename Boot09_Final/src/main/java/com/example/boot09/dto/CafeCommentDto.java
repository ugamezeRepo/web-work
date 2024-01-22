package com.example.boot09.dto;

import org.apache.ibatis.type.Alias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("cafeCommentDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CafeCommentDto {
    private int num;
    private String writer;
    private String content;
    private String target_id;
    private int ref_group;
    private int comment_group;
    private String deleted;
    private String regdate;
    private String profile;
    private int startNum;
    private int endNum;
}
