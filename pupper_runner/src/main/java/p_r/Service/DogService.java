package p_r.Service;

import java.util.List;

import p_r.pojos.Dog;

public interface DogService {
	public Dog createDog(Dog dog);

	public Dog readDog(int dogId);

	public List<Dog> readAllDogs();

	public Dog updateDog(int dogId, Dog dog);

	public void deleteDog(int dogId);

	public List<Dog> getAllDogs();

	public List<Dog> getDogsBySkillLevel(int skillLevel);
	
	public List<Dog> getDogsByOwner(String ownerName);

}
