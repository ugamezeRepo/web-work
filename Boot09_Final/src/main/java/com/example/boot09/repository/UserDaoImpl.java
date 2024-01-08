package com.example.boot09.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot09.dto.UserDto;

//dao 는 @Repository 라는 어노테이션을 이용해서 bean 으로 만든다 
@Repository
public class UserDaoImpl implements UserDao{
	//mybatis 기반으로 동작할때 필요한 객체를 주입 받는다
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(UserDto dto) {
		session.insert("user.insert", dto);
	}

	@Override
	public UserDto getData(String userName) {
		
		return session.selectOne("user.getData", userName);
	}

}

















