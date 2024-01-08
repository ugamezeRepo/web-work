package com.example.boot09.dto;

import org.apache.ibatis.type.Alias;

@Alias("userDto") // mapper xml 에서 사용할 type alias 설정
public class UserDto {
	//숫자로된 아이디는 PK 
	private int id;
	//사용자명 은 중복된 데이터가 들어가지 않도록 UNIQUE KEY 를 설정해야 한다
	private String userName;
	private String password;
	private String email;
	//Authority 정보를 저장할 칼럼 ROLE_XXX 형식이다. 
	private String role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
