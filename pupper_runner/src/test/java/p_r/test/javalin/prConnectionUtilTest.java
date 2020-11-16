package p_r.test.javalin;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import p_r.Javalin.util.PRConnectionUtil;


public class prConnectionUtilTest {
	
	private PRConnectionUtil connectionUtil = new PRConnectionUtil();

	@Test
	public void test() throws SQLException {
		Connection conn = connectionUtil.createConnection();
		conn.close();
	}

}
