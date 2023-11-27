package test.music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.guest.dto.GuestDto;
import test.music.dto.MusicDto;
import test.util.DbcpBean;

public class MusicDao {
	private static MusicDao dao;
	
	static {
		dao = new MusicDao();
	}
	
	private MusicDao() {}
	
	public static MusicDao getInstance() {
		return dao;
	}
	
	public boolean addSong (MusicDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = "INSERT INTO playlist"
					+ " (num, name, artist, rdate)"
					+ " VALUES (playlist_seq.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getArtist());
			pstmt.setString(3, dto.getRdate());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return rowCount > 0 ;
	}
	
	public boolean editSong (MusicDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = "UPDATE playlist"
					+ " SET name=?, artist=?, rdate=?"
					+ " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getArtist());
			pstmt.setString(3, dto.getRdate());
			pstmt.setInt(4, dto.getNum());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return rowCount > 0 ;
	}
	
	public boolean removeSong (int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = "DELETE FROM playlist"
					+ " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return rowCount > 0 ;
	}
	
	public MusicDto findSong (int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MusicDto dto = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = "SELECT name, artist, TO_CHAR(rdate, 'YYYY-MM-DD') rdate"
					+ " FROM playlist"
					+ " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String artist = rs.getString("artist");
				String rdate =  rs.getString("rdate");
				dto = new MusicDto(num, name, artist, rdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		return dto;
	}
	
	public List<MusicDto> findSongList () {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MusicDto> list = new ArrayList<> ();
		MusicDto dto = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = "SELECT num, name, artist, TO_CHAR(rdate, 'YYYY-MM-DD') rdate"
					+ " FROM playlist"
					+ " ORDER BY num DESC";
			pstmt = conn.prepareStatement(sql); 
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String artist = rs.getString("artist");
				String rdate =  rs.getString("rdate");
				dto = new MusicDto(num, name, artist, rdate);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		return list;
	}
	
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
