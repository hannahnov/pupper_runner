package p_r.Service;

import java.util.List;

import org.apache.log4j.Logger;

import p_r.Dao.DogDao;
import p_r.Dao.DogDaoPostgres;
import p_r.pojos.Dog;

public class DogServiceFullStack implements DogService {

	private static Logger log = Logger.getRootLogger();
	
	DogDao dogDao = new DogDaoPostgres();
	@Override
	public Dog createDog(Dog dog) {
		log.info("Dog Service: create dog");
		System.out.println("Dog service full stack owner name is " + dog.getOwnerName());
		System.out.println("Dog service fullstack dog name is " + dog.getDogName());
		dogDao.createDog(dog);
		return dog;
	}

	@Override
	public Dog readDog(int dogId) {
		log.info("Dog Service: read dog");
		return dogDao.readDog(dogId);
	}

	@Override
	public List<Dog> readAllDogs() {
		log.info("Dog Service: read all dogs");
		return dogDao.readAllDogs();
	}

	@Override
	public Dog updateDog(int dogId, Dog dog) {
		log.info("Dog Service: update dog");
		System.out.println("Full Stack dog skill level is " + dog.getSkillLevelDog());
		dogDao.updateDog(dogId, dog);
		return dog;
	}

	@Override
	public void deleteDog(int dogid) {
		log.info("Dog Service: delete dog");
		dogDao.deleteDog(dogid);
		
	}

	@Override
	public List<Dog> getAllDogs() {
		return dogDao.readAllDogs();

	}

	@Override
	public List<Dog> getDogsBySkillLevel(int skillLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dog> getDogsByOwner(String ownerName) {
		// TODO Auto-generated method stub
		return null;
	}




}
