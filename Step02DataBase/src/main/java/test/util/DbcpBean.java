package test.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
 * Data Base Connection Pool의 약자
 * 
 * Bean => java 객체를 콩(bean)이라고 부르기도 한다.
 */
public class DbcpBean {
	private Connection conn;
	
	public DbcpBean() {
		try {
			// Tomcat 서버가 관리하는 Connection 객체를 하나 얻어와 필드에 저장하는 작업하기
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			// Servers/context.xml 문서에 설정된 jdbc/myoracle이라는 이름의 datasource를 얻어온다.
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			// 얻어온 datasource 객체를 이용하여 Connection 객체의 참조값을 얻어와서 필드에 저
			conn = ds.getConnection();
			// 예외가 발생하지 않고 여기까지 실행의 흐름이 넘어온다면 성공
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConn() {
		return conn;
	}
	
	
}
