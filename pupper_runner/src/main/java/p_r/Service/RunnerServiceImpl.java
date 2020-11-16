package p_r.Service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import p_r.pojos.Dog;
import p_r.pojos.Runner;
import prService.CustomCacheService;
import prService.CustomCacheServiceSimpleInMemory;

public class RunnerServiceImpl implements RunnerService {
	
	private CustomCacheService<Runner> runnerCache = new CustomCacheServiceSimpleInMemory<Runner>();
	
	private static Logger log = Logger.getRootLogger();

	public RunnerServiceImpl() {
		
	}
	
	public String askName() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter your username");
			String name = sc.next();
			return name;
		}
	
	
	public int askRunnerID() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter your password");
			
			int ID = sc.nextInt();
			return ID;
		}
	
	
	public int askAge() {
		Scanner sc = new Scanner(System.in);
			System.out.println("Please enter your age"); 
			
			int age = sc.nextInt();
			return age;
		}
	
	
	public int askSkillLevel() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter your skill level \n 1: BEGINNER, 2: INTERMEDIATE, 3: EXPERT");	
			int skillLevel = sc.nextInt();
			
			return skillLevel;
		}
	
	public Runner logRunnerIn(String username, int password) {
		for (int i = 0; i < runnerCache.size(); i++) {
			if ((runnerCache.at(i).getName().compareTo(username)  == 0 )&& runnerCache.at(i).getPassword() == password) {
				System.out.println("Signed in.");
				return runnerCache.at(i);
			}
		}
		System.out.println("Invalid username or password");
		log.info("no user found");
		return null;
	}
	
	public Runner createRunner(Runner runner) {
		System.out.println("Creating" + runner + "object");
		runnerCache.addToCache(runner);
		return runner;
	}
	public Runner getRunnerInfo() {
		Runner runner = new Runner();
		
		runner.setName(askName());
		runner.setPassword(askRunnerID());
		runner.setAge(askAge());
		runner.changeRunnerAvailability(true);
		runner.setSkillLevel(askSkillLevel());
		
		data.addRunner(runner);
		System.out.println("Account created");
		log.info("runner added to database.runners");
		return runner;
	}

	public String readRunner(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object readAllRunners() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteRunner(String username) {
		// TODO Auto-generated method stub
		
	}

}
