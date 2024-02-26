package com.example.boot11.dto;

import org.apache.ibatis.type.Alias;

// 이 클래스로 만든 객체의 별칭을 memberDto로 부여 (mapper xml 문서에서 사용)
@Alias("memberDto")
public class MemberDto {
	private int num;
	private String name;
	private String addr;

	public MemberDto() {
	}

	public MemberDto(int num, String name, String addr) {
		super();
		this.num = num;
		this.name = name;
		this.addr = addr;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
