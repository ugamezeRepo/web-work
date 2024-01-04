package com.example.boot07.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileDto {
	private int num;
	private String wirter;
	private String title;
	private String orgFileName;
	private String saveFileName;
	private long fileSize;
	private MultipartFile myFile;
}
