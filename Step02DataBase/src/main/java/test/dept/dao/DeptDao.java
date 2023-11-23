package test.dept.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dept.dto.DeptDto;
import test.member.dto.MemberDto;
import test.util.DbcpBean;

public class DeptDao {
	private static DeptDao dao;
	
	private DeptDao() {}
	
	public static DeptDao getInstance() {
		return dao == null ? new DeptDao() : dao;
	}
	
	// 목록 출력
	public List<DeptDto> getList() {
		List<DeptDto> list = new ArrayList();
		DeptDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = new DbcpBean().getConn();
			String sql = "SELECT deptno, dname, loc" +
					" FROM dept" +
					" ORDER BY deptno ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				dto = new DeptDto(deptno, dname, loc);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, rs);
		}
		
		return list;
	}
	
	// 한 부서 출력
	public DeptDto getdept (int deptno) {
		DeptDto dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "SELECT dname, loc"
					+ " FROM dept"
					+ " WHERE deptno=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, deptno);
			
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				dto = new DeptDto(deptno, dname, loc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, rs);
		}
		return dto;
	}
	
	// dept 추가
	public boolean insertDept(DeptDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "INSERT INTO dept"
					+ " (deptno, dname, loc)"
					+ " VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩\
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setNString(3, dto.getLoc());
			
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, null);
		}
		return rowCount > 0;
	}
	
	// dept 수정
	public boolean updateDept(DeptDto dto) {
		int rowCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "UPDATE dept"
					+ " SET dname=?, loc=?"
					+ " WHERE deptno=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getDname());
			pstmt.setString(2, dto.getLoc());
			pstmt.setInt(3, dto.getDeptno());

			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, null);
		}
		return rowCount > 0;
	}
	
	// dept 삭제
	public boolean deleteDept(int deptno) {
		int rowCount = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "DELETE FROM dept"
					+ " WHERE deptno=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(1, deptno);
			
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, null);
		}
		return rowCount > 0;
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
