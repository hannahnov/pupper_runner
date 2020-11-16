package p_r.Service;

import java.util.List;

import org.apache.log4j.Logger;

import p_r.Dao.OwnerDao;
import p_r.Dao.OwnerDaoPostgres;
import p_r.pojos.Owner;

public class OwnerServiceFullStack implements OwnerService {
	
	OwnerDao ownerDao = new OwnerDaoPostgres();
	
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public Owner createOwner(Owner owner) {
		log.info("Owner Service: create owner");
		ownerDao.createOwner(owner);
		return owner;
	}

	@Override
	public Owner readOwner(String userName) {
		log.info("Owner Service: read owner");
		return ownerDao.readOwner(userName);
	}

	@Override
	public List<Owner> readAllOwners() {
		log.info("Owner Service: read all owners");
		return ownerDao.readAllOwners();
	}
	
	@Override
	public Owner updateOwner(String userName, Owner owner) {
		log.info("Owner Service: update owner");
		ownerDao.updateOwner(userName, owner);
		return owner;
	}

	@Override
	public void deleteOwner(String username) {
		log.info("Owner Service: delete owner");
		ownerDao.deleteOwner(username);
	}

	@Override
	public List<Owner> getAllOwners() {
		return ownerDao.readAllOwners();
	}

	@Override
	public List<Owner> getAllOwnersByNumdogs(int numDogs) {
		return null;
	}


}

