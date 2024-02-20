package com.example.boot11.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("userDto") // mapper xml 에서 사용할 type alias 설정
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
	//숫자로된 아이디는 PK 
	private int id;
	//사용자명 은 중복된 데이터가 들어가지 않도록 UNIQUE KEY 를 설정해야 한다
	private String userName;
	private String password;
	private String newPassword;
	private String email;
	//Authority 정보를 저장할 칼럼 ROLE_XXX 형식이다. 
	private String role;
	private String profile;	
	private String regdate;
	// 프로필 사진 업로드 처리를 위한 필드
	private MultipartFile image;
}
