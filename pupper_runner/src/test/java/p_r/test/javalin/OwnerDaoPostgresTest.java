package p_r.test.javalin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import p_r.Dao.OwnerDaoPostgres;
import p_r.Javalin.util.PRConnectionUtil;
import p_r.pojos.Dog;
import p_r.pojos.Owner;

@RunWith(MockitoJUnitRunner.class)
public class OwnerDaoPostgresTest {

	public OwnerDaoPostgres ownerDao = new OwnerDaoPostgres();
	
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
		ownerDao.setConnUtil(connUtil);
		
	}

	@After
	public void tearDown() throws Exception {
		
		stmt.executeUpdate("delete from owner where userName = 'rayolin'");
		
		realConnection.close();
		
	}

	@Test
	public void createOwnerTest() throws SQLException {

		
		Owner owner = new Owner("rayolin", 20, 3);
		
		ownerDao.createOwner(owner);
		
		String sql = "insert into owner (userName, password, numDogs)"
				+ " values('rayolin', 2234, 2)";
		
		verify(spy).executeUpdate(sql);
		
		ResultSet rs = stmt.executeQuery("select * from owner where userName = 'rayolin'");
		
		assertTrue(rs.next());
		
	}

}

