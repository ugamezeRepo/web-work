package test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DbcpBean;

public class MemberDao {
	// 자신의 참조값을 담을 static 필드
	private static MemberDao dao;
	
	// 외부에서 객체 생성하지 못하도록 생성자의 접근 지정자를 private로
	private MemberDao() {}
	
	// 자신의 참조값을 리턴해주는 public(공개) 메서드
	public static MemberDao getInstance() {
		// 만일 최초의 호출이라면
		if (dao == null) {
			dao = new MemberDao();
		}
		return dao;
	}
	
	// 회원 목록을 리턴하는 메서드
	public List<MemberDto> getList() {
		List<MemberDto> list = new ArrayList();
		MemberDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// DbcpBean() 객체를 이용해서 Connection 객체 하나 얻어내기(Connection pool에서 하나 꺼내오기)
			conn = new DbcpBean().getConn();
			String sql = "SELECT num, name, addr" +
					" FROM member" +
					" ORDER BY num DESC";
			pstmt = conn.prepareStatement(sql);
			// query문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			
			// 반복문 돌면서
			while (rs.next()) {
				// MemberDto 객체에 각 회원의 정보를 담아
				int num = rs.getInt("num");
				String name = rs.getString("name");
	            String addr = rs.getString("addr");
	            dto = new MemberDto(num, name, addr);
	            // ArrayList 객체에 누적
	            list.add(dto);
			}
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            // Connection 객체의 close() 메서드 호출 시 Pool에 반납
	            if (conn != null) conn.close();
	    	} catch ( Exception e) {
	    		e.printStackTrace();
	    	}
	    }
		
		return list;
	}
	
	public MemberDto getMember(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto dto = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "SELECT name, addr"
					+ " FROM member"
					+ " WHERE num = ?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, num);

			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			//반복문 돌면서 
			if (rs.next()) {
				String name = rs.getString("name");
				String addr = rs. getString("addr");
				dto = new MemberDto(num, name, addr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close(); //Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	// 회원 한 명의 정보를 저장하고 작업의 성공여부를 리턴하는 메서드
	public boolean insert(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		
		try {
			conn = new DbcpBean().getConn();
			String sql = "INSERT INTO member"
					+ " (num, name, addr)"
					+ " VALUES (member_seq.NEXTVAL, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	            if (pstmt != null) pstmt.close();
	            // Connection 객체의 close() 메서드 호출 시 Pool에 반납
	            if (conn != null) conn.close();
	    	} catch ( Exception e) {
	    		e.printStackTrace();
	    	}
	    }
		
		return rowCount>0;
	}
	
	// 회원 한 명의 정보를 수정하고 작업의 성공여부를 리턴하는 메서드
	public boolean update(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "UPDATE member"
					+ " SET name=?, addr=?"
					+ " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.setInt(3, dto.getNum());

			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close(); 
			} catch (Exception e) {
			}
		}
		return rowCount > 0;
	}
	
	// 회원 한 명의 정보를 삭제하고 작업의 성공여부를 리턴하는 메서드
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		
		try {
			conn = new DbcpBean().getConn();
			String sql = "DELETE FROM MEMBER"
					+ " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	            if (pstmt != null) pstmt.close();
	            // Connection 객체의 close() 메서드 호출 시 Pool에 반납
	            if (conn != null) conn.close();
	    	} catch ( Exception e) {
	    		e.printStackTrace();
	    	}
	    }
		
		return rowCount>0;
	}
}
