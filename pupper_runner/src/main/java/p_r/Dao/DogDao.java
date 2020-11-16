package p_r.Dao;

import java.util.List;

import p_r.pojos.Dog;


public interface DogDao {
	public void createDog(Dog dog);
	
	public Dog readDog(int dogId);
	
	public List<Dog> readAllDogs();
	
	public Dog updateDog(int dogId, Dog dog);
	
	public void deleteDog(int dogid);

}
