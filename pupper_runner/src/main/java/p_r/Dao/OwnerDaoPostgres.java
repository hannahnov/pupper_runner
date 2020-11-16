package p_r.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import p_r.Javalin.util.PRConnectionUtil;
import p_r.pojos.Owner;
import org.apache.log4j.Logger;

public class OwnerDaoPostgres implements OwnerDao {
	
	private static Logger log = Logger.getRootLogger();

	private PreparedStatement statement;
	
	private PRConnectionUtil connUtil = new PRConnectionUtil();
	
	public void setConnUtil(PRConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public void createOwner(Owner owner) {
		
		String sql = "insert into owners (ownerusername, password, numDogs)"
				+ "values(?, ?, ?)";
			
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
		
			statement.setString(1, owner.getName());
			statement.setInt(2, owner.getPassword());
			statement.setInt(3, owner.getNumDogs());

			statement.executeUpdate();
		
			log.info("Controller creating owner");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Owner readOwner(String userName) {
			String sql ="select * from owners where ownerusername = ?";
			Owner owner = new Owner();
			try (Connection conn = connUtil.createConnection()){
				statement = conn.prepareStatement(sql);
				statement.setString(1, userName);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					
					String username = rs.getString( "ownerusername");
					int password = rs.getInt( "password");
					int numDogs = rs.getInt("numdogs");
					
					owner.setName(username);
					owner.setPassword(password);
					owner.setNumDogs(numDogs);
				}
				
				log.info(" PostgreSQL reading Owner" );
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return owner;
		}

	@Override
	public List<Owner> readAllOwners() {
		
		List<Owner> ownerList = new ArrayList<>();

		String sql = "select * from owners";

		try (Connection conn = connUtil.createConnection()) {

			statement = conn.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			
			log.info("Controller reading all owners" );

			while (rs.next()) {
				
				Owner owner = new Owner();
				
				String userName = rs.getString("ownerusername");
				int password = rs.getInt("password");
				int numDogs = rs.getInt("numdogs");
				
				owner.setName(userName);
				owner.setPassword(password);
				owner.setNumDogs(numDogs);
				
				ownerList.add(owner);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ownerList;
	}

	@Override
	public Owner updateOwner(String userName, Owner owner) {
		String sql = "update owners"
				+ " set password = ?, numdogs = ? "
				+ "where ownerusername = ?";
				
		try (Connection conn = connUtil.createConnection()){
		
			
			statement = conn.prepareStatement(sql);
			statement.setInt(1, owner.getPassword());
			statement.setInt(2,  owner.getNumDogs());
			statement.setString(3, userName);
			statement.executeUpdate();
			
			log.info(" PostgreSQL updating Owner with username " + userName );
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return owner;
	}

	@Override
	public void deleteOwner(String userName) {
		String sql ="delete from owners where ownerusername = ?";
		try (Connection conn = connUtil.createConnection()){
			statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			statement.executeUpdate();
			log.info(" PostgreSQL deleting Owner with username " + userName );
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

