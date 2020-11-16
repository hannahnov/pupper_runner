package p_r.pojos;

import org.apache.log4j.Logger;

import pr.pojos.Runner;

public class Pairing {

	private Dog dog;
	private Runner runner;
	private int pairingId;
	private static Logger log = Logger.getRootLogger();
	
	public Pairing() {
		
	}
	
	//non-default constructor
	public Pairing(Dog dog, Runner runner) {
		this.dog = dog;
		this.runner = runner;
	}
	
	//creates new pair. Outputs pair information, adds pair to database
	public Pairing createPairing(Dog dog, Runner runner, String user) {
		this.dog = dog;
		this.runner = runner;
		
		if (user == "Runner") {
		
		System.out.println("Looks like you found a match! " + runner.getName() + ", you will be running with " + dog.getDogName() 
		+". You are both level " + runner.getSkillLevel() + " runners. Their owner's name is " + dog.getDogOwner().getName() + ".");
		
		}
		
		if (user == "Owner") {
			System.out.println("Looks like your pooch found a match! " + dog.getDogOwner() + ", " + dog.getDogName() + "will be running with " 
					+ runner.getName() + ". They are both level " + runner.getSkillLevel() + " runners." );
		}
		
		dog.changeDogAvailability(false);
		runner.changeRunnerAvailability(false);
		Pairing pairing = new Pairing(dog, runner);
		data.addPairing(pairing);
		log.info("pairing added to database.pairings()");
		return pairing;
	}
	
	
	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}

	public int getPairingId() {
		return pairingId;
	}

	public void setPairingId(int pairingId) {
		this.pairingId = pairingId;
	}
	

}

