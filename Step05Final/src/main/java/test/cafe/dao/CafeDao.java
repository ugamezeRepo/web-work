package test.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.cafe.dto.CafeDto;
import test.util.DbcpBean;

public class CafeDao {
	private static CafeDao dao;

	private CafeDao() {
	}

	public static CafeDao getInstance() {
		if (dao == null) {
			dao = new CafeDao();
		}
		return dao;
	}

	// 전체 글의 개수를 리턴하는 메서드
	public int getCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "SELECT MAX(ROWNUM) AS count" + "	FROM board_cafe";
			pstmt = conn.prepareStatement(sql);

			// query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			// 반복문 돌면서
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		return count;
	}

	public boolean insert(CafeDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "INSERT INTO board_cafe" + " ( num, writer, title, content, viewCount, regdate)"
					+ " VALUES (  board_cafe_seq.NEXTVAL, ?, ?, ?, 0, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());

			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return rowCount > 0;
	}

	public boolean updateView(CafeDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "UPDATE board_cafe" + "	SET viewCount=?" + "	WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(1, dto.getViewCount());
			pstmt.setInt(2, dto.getNum());

			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return rowCount > 0;
	}

	public boolean update(CafeDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "UPDATE board_cafe" + " SET title=?, content=?" + " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());

			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return rowCount > 0;
	}

	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "DELETE FROM board_cafe" + " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(1, num);

			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return rowCount > 0;
	}

	public CafeDto getData(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		CafeDto dto = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "SELECT writer, title, content, viewCount, regdate" + " FROM board_cafe" + " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, num);

			// query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			// 반복문 돌면서
			if (rs.next()) {
				dto = new CafeDto();
				dto.setNum(num);
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViewCount(rs.getInt("viewCount"));
				dto.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		return dto;
	}

	public List<CafeDto> getList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<CafeDto> list = new ArrayList<>();
		CafeDto dto = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "SELECT num, writer, title, viewCount, regdate" + " FROM board_cafe";
			pstmt = conn.prepareStatement(sql);

			// query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			// 반복문 돌면서
			while (rs.next()) {
				dto = new CafeDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setViewCount(rs.getInt("viewCount"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		return list;
	}

	public List<CafeDto> getList(CafeDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<CafeDto> list = new ArrayList<>();
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "SELECT *" + " FROM" + "	 (SELECT result1.*, ROWNUM AS rnum" + "	 FROM"
					+ "	  (SELECT num, writer, title, viewCount, regdate" + "	  FROM board_cafe"
					+ "	  ORDER BY num DESC) result1)" + " WHERE rnum BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getStartRowNum());
			pstmt.setInt(2, dto.getEndRowNum());

			// query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			// 반복문 돌면서
			while (rs.next()) {
				dto = new CafeDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setViewCount(rs.getInt("viewCount"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		return list;
	}

	private void closeResource(Connection conn, PreparedStatement pstmt) {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeResource(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
