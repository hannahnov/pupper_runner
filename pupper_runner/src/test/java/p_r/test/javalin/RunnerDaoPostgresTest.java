package p_r.test.javalin;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import p_r.Dao.RunnerDaoPostgres;
import p_r.Javalin.util.PRConnectionUtil;
import p_r.pojos.Runner;

@RunWith(MockitoJUnitRunner.class)
public class RunnerDaoPostgresTest {

	public RunnerDaoPostgres runnerDao = new RunnerDaoPostgres();
	
	@Mock
	private PRConnectionUtil connUtil;
	
	@Mock
	private Connection connection;
	
	private Statement stmt;
	
	private Statement spy;
	
	private Connection realConnection;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		realConnection = new PRConnectionUtil().createConnection();
		
		//creating a real stmt from a connection
		stmt = realConnection.createStatement(); 
		
		//spying on that real stmt
		spy = Mockito.spy(stmt);
		
		//mock our connection and util, so we will only use the stmt we are spying on
		when(connection.createStatement()).thenReturn(spy);
		when(connUtil.createConnection()).thenReturn(connection);
		
		//set up Dao to use the mocked object
		runnerDao.setConnUtil(connUtil);
		
	}

	@After
	public void tearDown() throws Exception {
		
		stmt.executeUpdate("delete from runner where  userName = 'Saran'");
		
		realConnection.close();
		
	}

	@Test
	public void createGuestTest() throws SQLException {
		
		Runner runner = new Runner("saran", 10, 3344, 2, true, 1);
		
		runnerDao.createRunner(runner);
		
		String sql = "insert into runner (userName, age, password, skillLevel, isAvailable)"
				+ " values('saran', 10, 3344, 2, true)";
		
		verify(spy).executeUpdate(sql);
		
		ResultSet rs = stmt.executeQuery("select * from runner where userName = 'Saran'");
		
		assertTrue(rs.next());
		
	}

}

