package com.example.boot07.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data // setter, getter 메서드 만들기 and toString() 메서드 오버라이드(dto를 콘솔창에 출력 가능)
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
