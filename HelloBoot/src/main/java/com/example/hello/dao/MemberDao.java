package com.example.hello.dao;

import com.example.hello.dto.MemberDto;

public interface MemberDao {
	public void insert(MemberDto dto);

	public void update(MemberDto dto);

	public MemberDto getData(int num);
}
