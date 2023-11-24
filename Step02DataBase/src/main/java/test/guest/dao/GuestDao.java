package test.guest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.DATE;
import test.dept.dto.DeptDto;
import test.guest.dto.GuestDto;
import test.util.DbcpBean;

public class GuestDao {
	private static GuestDao dao;
	
	// static 초기화 블럭 (이 클래스가 최초로 사용될 때 오직 한 번만 수행)
	static {
		dao = new GuestDao();
	}
	
	private GuestDao() {}
	
	public static GuestDao getInstace() {
		return dao;
	}
	
	// 글번호는 board_guest_seq.NEXTVAL을 이용하여 부여
	// 글의 등록날짜는 SYSDATE로 등록
	// 글 수정 시 날짜 수정 X
	
	public boolean insert (GuestDto dto) {
		int rowCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "INSERT INTO board_guest"
					+ " (num, writer, content, pwd, regdate)"
					+ " VALUES (board_guest_seq.NEXTVAL, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩\
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getPwd());
			
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, null);
		}
		return rowCount > 0;
	}
	
	public boolean update (GuestDto dto) {
		int rowCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "UPDATE board_guest"
					+ " SET writer=?, content=?"
					+ " WHERE num=? AND pwd=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
			pstmt.setString(4, dto.getPwd());

			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, null);
		}
		return rowCount > 0;
	}
	
	public boolean delete (GuestDto dto) {
		int rowCount = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "DELETE FROM board_guest"
					+ " WHERE num=? AND pwd=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getPwd());
			
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, null);
		}
		return rowCount > 0;
	}
	
	public GuestDto getData (int num) {
		GuestDto dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "SELECT writer, content, pwd, regdate"
					+ " FROM board_guest"
					+ " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, num);
			
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new GuestDto();
				dto.setNum(num);
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setPwd(rs.getString("pwd"));
				// DB에서 DATE 타입이어도 String 타입으로 얻어온다.
				dto.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, rs);
		}
		return dto;
	}
	
	public List<GuestDto> getList () {
		List<GuestDto> list = new ArrayList();
		GuestDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = new DbcpBean().getConn();
			String sql = "SELECT num, writer, content, pwd, regdate" +
					" FROM board_guest" +
					" ORDER BY num DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new GuestDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setPwd(rs.getString("pwd"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, rs);
		}
		
		return list;
	}
	
	// try-with문을 활용한 close 메서드
	private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
