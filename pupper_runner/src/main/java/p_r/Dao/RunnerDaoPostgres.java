package p_r.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import p_r.Javalin.util.PRConnectionUtil;
import p_r.pojos.Owner;
import p_r.pojos.Runner;


public class RunnerDaoPostgres implements RunnerDao {
	
	private static Logger log = Logger.getRootLogger();
	private PreparedStatement statement;;
	
	private PRConnectionUtil connUtil = new PRConnectionUtil();
	
	public void setConnUtil(PRConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public void createRunner(Runner runner) {
		
		
		String sql = "insert into runners (runnerusername, password, skillLevel, isavailable, dogid, age)"
				+ "values(?, ?, ?, ?, ?, ?)";
	
		
		try (Connection conn = connUtil.createConnection()) {
		
			statement = conn.prepareStatement(sql);
			statement.setString(1, runner.getName());
			statement.setInt(2,  runner.getPassword());
			statement.setInt(3, runner.getSkillLevel());
			statement.setBoolean(4, runner.isRunnerAvailable());
			statement.setInt(5, runner.getRunnerDogId());
			statement.setInt(6, runner.getAge());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Runner readRunner(String runnerId) {
		String sql ="select * from runners where runnerusername = ?";
		Runner runner = new Runner();
		try (Connection conn = connUtil.createConnection()){
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, runnerId);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				String username = rs.getString( "runnerusername");
				int age = rs.getInt("age");
				int password = rs.getInt( "password");
				int skillLevel = rs.getInt("skilllevel");
				boolean isAvailable = rs.getBoolean("isavailable");
				int dogId = rs.getInt("dogid");
				runner.setName(username);
				runner.setPassword(password);
				runner.setSkillLevel(skillLevel);
				runner.changeRunnerAvailability(isAvailable);
				runner.setRunnerDogId(dogId);
				runner.setAge(age);
			}
			
			log.info(" PostgreSQL reading runner"  );
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return runner;
	}

	@Override
	public List<Runner> readAllRunners() {
		List<Runner> runnerList = new ArrayList<>();

		String sql = "select * from runners";

		try (Connection conn = connUtil.createConnection()) {

			statement = conn.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			
			log.info("Controller reading all runners" );

			while (rs.next()) {
				
				Runner runner = new Runner();
				String username = rs.getString( "runnerusername");
				int password = rs.getInt( "password");
				int skillLevel = rs.getInt("skilllevel");
				boolean isAvailable = rs.getBoolean("isavailable");
				int dogId = rs.getInt("dogid");
				runner.setName(username);
				runner.setPassword(password);
				runner.setSkillLevel(skillLevel);
				runner.changeRunnerAvailability(isAvailable);
				runner.setRunnerDogId(dogId);
				
				runnerList.add(runner);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return runnerList;
	}

	@Override
	public Runner updateRunner(String runnerId, Runner runner) {
		String sql = "update runners"
				+ " set password = ?, skilllevel = ?, isavailable = ?, dogid = ?" 
				+ " where runnerusername = ?";
				
		try (Connection conn = connUtil.createConnection()){
			statement = conn.prepareStatement(sql);
			statement.setInt(1, runner.getPassword());
			statement.setInt(2, runner.getSkillLevel());
			statement.setBoolean(3, runner.isRunnerAvailable());	
			statement.setInt(4, runner.getRunnerDogId());
			statement.setString(5, runnerId);
			
			statement.executeUpdate();
			 
			log.info(" PostgreSQL updating Runner"  );
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return runner;
	}

	@Override
	public int deleteRunner(String userName) {
		String sql = "delete from runners where runnerusername = ?";
		int rowsToDelete = 0;

		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			rowsToDelete = statement.executeUpdate();

			log.info("Controller deleting runner with runnerusername = " + userName);

			if (rowsToDelete == 0) {
				System.out.println("No rows to delete.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowsToDelete;

	}



}

