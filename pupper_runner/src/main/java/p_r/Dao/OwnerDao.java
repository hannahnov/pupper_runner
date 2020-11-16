package p_r.Dao;

import java.util.List;

import p_r.pojos.Owner;

public interface OwnerDao {
	
	public void createOwner(Owner owner);
	
	public Owner readOwner(String ownerUserName);
	
	public List<Owner> readAllOwners();
	
	public Owner updateOwner(String ownerId, Owner owner);
	
	public void deleteOwner(String username);

}
