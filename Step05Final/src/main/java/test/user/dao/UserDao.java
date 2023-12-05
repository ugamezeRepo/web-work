package test.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import test.user.dto.UserDto;
import test.util.DbcpBean;

/**
 * 	가입회원 정보를 insert, update, delete, select할 single ton Dao 만들기
 */

public class UserDao {
	// 자신의 참조값을 저장할 필드
	private static UserDao dao;
	// 외부에서 객체 생성 방지
	private UserDao() {}
	
	// 참조값을 리턴해주는 공개 static 메서드 생성
	public static UserDao getInstance() {
		if (dao == null) {
			dao = new UserDao();
		}
		return dao;
	}
	
	// 회원정보를 DB에 저장하고 성공여부를 리턴하는 메서드
	public boolean insert(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "INSERT INTO user_info"
					+ " (id, pwd, email, regdate)"
					+ " VALUES (?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getEmail());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return rowCount > 0;
	}
	
	// 아이디를 이용해 회원 한 명의 정보를 수정하는 메서드 (이메일, 프로필 이미지 경로)
	public boolean update(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "UPDATE user_info"
					+ " SET email=?, profile=?"
					+ " WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getProfile());
			pstmt.setString(3, dto.getId());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return rowCount > 0;
	}
	
	// 아이디를 이용해 회원 한 명의 비밀번호를 수정하는 메서드
	public boolean updatePwd(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "UPDATE user_info"
					+ " SET pwd=?"
					+ " WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getId());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return rowCount > 0;
	}
	
	// delete
	
	// select
	public UserDto getData(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserDto dto = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "SELECT pwd, email, profile, regdate"
					+ " FROM user_info"
					+ " WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setString(1, id);

			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			//반복문 돌면서 
			if (rs.next()) {
				String pwd = rs.getString("pwd");
				String email = rs.getString("email");
				String profile = rs.getString("profile");
				String regdate = rs.getString("regdate");
				dto = new UserDto(id, pwd, email, profile, regdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		return dto;
	}
	
	// selectAll
//	public List<UserDto> getAllData() {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		UserDto dto = null;
//		try {
//			conn = new DbcpBean().getConn();
//			//실행할 sql 문
//			String sql = "SELECT id, pwd"
//					+ " FROM user_info"
//					+ " WHERE id=?";
//			pstmt = conn.prepareStatement(sql);
//			//? 에 바인딩할 내용이 있으면 여기서 한다.
//			pstmt.setString(1, id);
//			
//			//query 문 수행하고 결과(ResultSet) 얻어내기
//			rs = pstmt.executeQuery();
//			//반복문 돌면서 
//			if (rs.next()) {
//				dto = new UserDto();
//				dto.setId(id);
//				dto.setPwd(rs.getString("pwd"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeResource(conn, pstmt, rs);
//		}
//		return dto;
//	}	
	
	private void closeResource (Connection conn, PreparedStatement pstmt) {
		try {
			if (conn != null) conn.close();
			if (pstmt != null) pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void closeResource (Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (conn != null) conn.close();
			if (pstmt != null) pstmt.close();
			if (rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
