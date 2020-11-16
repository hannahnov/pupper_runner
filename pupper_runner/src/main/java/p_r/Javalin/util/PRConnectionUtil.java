package p_r.Javalin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PRConnectionUtil {
	
	private Connection conn;
	
	public Connection createConnection() throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?", "postgres", "Ripeanut1!");
		
		return conn;
		
	}
	

}
