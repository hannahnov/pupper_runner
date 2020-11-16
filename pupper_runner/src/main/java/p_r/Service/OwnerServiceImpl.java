package p_r.Service;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import p_r.pojos.Dog;
import p_r.pojos.Owner;
import src.main.java.HotelReservationJavalin.service.CustomCacheService;
import src.main.java.HotelReservationJavalin.service.CustomCacheServiceSimpleInMemory;
import src.main.java.HotelReservationJavalin.service.Guest;

public class OwnerServiceImpl implements OwnerService {
	private CustomCacheService<Owner> ownerCache = new CustomCacheServiceSimpleInMemory<Owner>();
	private CustomCacheService<Dog> dogCache = new CustomCacheServiceSimpleInMemory<Dog>();
	private static Logger log = Logger.getRootLogger();
	public OwnerServiceImpl() {
		
	}
	
	public String askName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your username");
		String name = sc.next();
		return name;
	}
	
	public int askOwnerID() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your password");
		
		int ID = sc.nextInt();
		return ID;
	}
	
	public int askNumDogs() {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many dogs do you have?");
			int numDogs = sc.nextInt();
			return numDogs;
	}
	
	public String askDogName(int i) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter dog " + (i + 1) + "'s name"); 
		String name = sc.next();
		return name;
	}
	
	
	
	public int askDogLevel(int i) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter dog " + (i + 1) + "'s skill level \n 1: BEGINNER, 2: INTERMEDIATE, 3: EXPERT");	
		int skillLevel = sc.nextInt();
		
		return skillLevel;
	}
	
	public Owner logOwnerIn(String username, int password) {
		int p = ownerCache.size();
		
		for (int i = 0; i < ownerCache.size(); i++) {
			String user = ownerCache.at(i).getName();
			int pass = ownerCache.at(i).getPassword();
			if (user.compareTo(username) == 0) {
				if (pass == password) {
				System.out.println("Signed in.");
				return ownerCache.at(i);
			}
			}
		}
		System.out.println("Invalid username or password");
		log.info("User not found");
		return null;
		
	}
	
	public Owner createOwner(Owner owner) {
		ownerCache.addToCache(owner);
		return owner;
	}
	//Creates a new owner with given information. Adds new owner and their dogs to database
	public Owner getOwnerInfo() {
		OwnerServiceImpl ownerS = new OwnerServiceImpl();
		Owner owner = new Owner();
		owner.setName(ownerS.askName());
		owner.setPassword(askOwnerID());
		owner.setNumDogs(askNumDogs());
		for (int i = 0; i < owner.getNumDogs(); i++) {
			Dog dog = new Dog();
			dog.setDogName(askDogName(i));
			dog.setDogOwner(owner);
			dog.setSkillLevelDog(askDogLevel(i));
			dog.changeDogAvailability(true);
			owner.addDog(dog);
			log.info("dog added to owner.dogs()");
			dogCache.addToCache(dog);
			log.info("dog added to database.dogs()");
		}
		ownerCache.addToCache(owner);
		log.info("Owner added to database.owners()");
		System.out.println("Account created");
		return owner;
	}

	@Override
	public List<Owner> getAllOwners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Owner> getAllOwnersByDog(int dogId) {
		// TODO Auto-generated method stub
		return null;
	}

}
