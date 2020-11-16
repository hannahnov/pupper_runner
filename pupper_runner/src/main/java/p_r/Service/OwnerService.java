package p_r.Service;

import java.util.List;

import p_r.pojos.Owner;

public interface OwnerService {

	public Owner createOwner(Owner owner);

	public Owner readOwner(String userName);

	public List<Owner> readAllOwners();

	public Owner updateOwner(String userName, Owner owner);

	public List<Owner> getAllOwners();

	public List<Owner> getAllOwnersByNumdogs(int numDogs);

	void deleteOwner(String username);

}


