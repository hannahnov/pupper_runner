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
import p_r.pojos.Dog;

public class DogDaoPostgres implements DogDao {
	
	private static Logger log = Logger.getRootLogger();

	private PreparedStatement statement;
	
	private PRConnectionUtil connUtil = new PRConnectionUtil();
	
	public void setConnUtil(PRConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public void createDog(Dog dog) {

		String sql = "insert into dogs (dogid, name, ownerusername, skilllevel, isavailable) "
				+ " values (?, ?, ?, ?, ?);";
			
			try (Connection conn = connUtil.createConnection()) {
				statement = conn.prepareStatement(sql);
				System.out.println("Owner name is " + dog.getOwnerName());
				statement.setInt(1, dog.getDogid());
				statement.setString(2, dog.getDogName());
				statement.setString(3, dog.getOwnerName());
				statement.setInt(4, dog.getSkillLevelDog());
				statement.setBoolean(5, dog.isDogAvailable());

				statement.executeUpdate();
				
				log.info("Controller creating dog");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Dog readDog(int dogId) {
		String sql ="select * from dogs where dogid = " + dogId;
		Dog dog = new Dog();
		try (Connection conn = connUtil.createConnection()){
			statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				String name = rs.getString("name");
				int skilllevel = rs.getInt("skilllevel");
				int dogid = rs.getInt("dogid");
				boolean isavailable = rs.getBoolean("isavailable");
				String ownername = rs.getString("ownerusername");
				
			dog.setDogId(dogid);
			dog.setDogName(name);
			dog.setOwnerName(ownername);
			dog.setSkillLevelDog(skilllevel);
			dog.changeDogAvailability(isavailable);
			
			}
			
			log.info("PostgreSQL reading dog by id number" + dog.getDogid());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dog;
	}

	@Override
	public List<Dog> readAllDogs() {
	
		List<Dog> dogList = new ArrayList<>();
		String sql = "select * from dogs";

		try (Connection conn = connUtil.createConnection()) {

			statement = conn.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			
			log.info("Controller reading all dogs" );

			while (rs.next()) {
				
				
				String name = rs.getString("name");
				int skilllevel = rs.getInt("skilllevel");
				int dogid = rs.getInt("dogid");
				boolean isavailable = rs.getBoolean("isavailable");
				String ownername = rs.getString("ownerusername");
				
				Dog dog = new Dog(dogid, name, ownername, skilllevel, isavailable);
				
				dogList.add(dog);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dogList;
	}

	@Override
	public Dog updateDog(int dogId, Dog dog) {
		String sql = "update dogs " + " set name = ?,"
				+ " skilllevel = ?, ownerusername = ?, isavailable = ? " 
				+ " where dogid = ?"; 
				
		try (Connection conn = connUtil.createConnection()){
			statement = conn.prepareStatement(sql);
			
			
			statement.setString(1, dog.getDogName());
			statement.setInt(2, dog.getSkillLevelDog());
			statement.setString(3, dog.getOwnerName());
			statement.setBoolean(4, dog.isDogAvailable());
			statement.setInt(5, dogId);
			
			statement.executeUpdate();
			
			log.info("PostgreSQL updating Dog" );
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dog;
	}

	@Override
	public void deleteDog(int dogid) {
		String sql ="delete from dogs where dogid = ?";
		int rows = 0;
		try (Connection conn = connUtil.createConnection()){
		
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, dogid);	
		
			rows = statement.executeUpdate();
			log.info(" PostgreSQL deleting dog"  );
			if (rows == 0) {
				System.out.println("No dogs to delete");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

