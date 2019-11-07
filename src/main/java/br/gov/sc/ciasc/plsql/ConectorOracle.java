package br.gov.sc.ciasc.plsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorOracle {
	
	public Connection obterConexao() {		
		Connection conn = null;
		String user = "leiras";
		String password = "password";
		String service = "xe";
		String host = "localhost";
		String port = "1521";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+port+"/"+service, user, password);
		} catch (Exception e) {
			System.out.println(e);
		}			
		return conn;		
	}
	
	public void liberarConexao(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}		
	}

}
