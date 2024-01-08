package com.example.boot09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.boot09.dto.UserDto;
import com.example.boot09.repository.UserDao;

// 서비스클래스는 @Service 어노테이션을 이용해서 bean 으로 만든다 
@Service
public class UserServiceImpl implements UserService{
	//서비스는 dao 를 이용해서 로직 처리를 한다.
	@Autowired
	private UserDao dao;
	//비밀번호 암호화하는 객체도 bean 으로 등록이 되어 있다.
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void addUser(UserDto dto) {
		//암호화된 비밀 번호를 얻어내서 
		String encodedPwd=encoder.encode(dto.getPassword());
		//dto 에 덮어쓰기 한다음
		dto.setPassword(encodedPwd);
		//일반 사용자라는 의미에서 role 에 "USER" 를 넣어준다.
		dto.setRole("USER");
		//DB 에 저장한다.
		dao.insert(dto);
	}

}









