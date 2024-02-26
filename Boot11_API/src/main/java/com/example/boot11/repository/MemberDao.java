package com.example.boot11.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.boot11.dto.MemberDto;

@Repository
public class MemberDao {
	/*
	 * DB 접속 설정 및 MyBatis 설정이 잘 되어있다면 spring boot에서 자동으로 sqlSession 객체를 만들어
	 * spring bean container에서 관리를 해준다.
	 * SqlSession 객체를 이용해서 DB에 indsert, update, delete, select 작업을 하면 된다.
	 */
	@Autowired
	SqlSession session;

	public int update(MemberDto dto) {

		return session.update("member.update", dto);
	}

	public MemberDto getData(int num) {
		/*
		 * mapper의 namespace => member
		 * sql의 id => getData
		 * parameterType => int
		 * resultType => MemberDto type
		 */

		return session.selectOne("member.getData", num);
	}

	public int delete(int num) {

		return session.delete("member.delete", num);
	}

	// 회원 목록 읽어오기
	public List<MemberDto> getList() {
		/*
		 * member => mapper의 namespace 
		 * getList => sql의 id 
		 * MemberDto => result type
		 * .selectOne() 메서드는 resultType이 리턴된다.
		 * .selectList() 메서드는 List<resultType>이 리턴된다.
		 */

		return session.selectList("member.getList");
	}

	public int insert(MemberDto dto) {
		/*
		 * mapper의 namespace => member
		 * sql의 id => insert
		 * parameterType => MemberDto
		 */

		return session.insert("member.insert", dto);
	}
}
