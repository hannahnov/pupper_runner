package p_r.pojos;

import org.apache.log4j.Logger;

import pr.pojos.Owner;
import pr.pojos.Pairing;
import pr.pojos.Runner;
import prService.Database;

public class Matcher {
	
	private static Logger log = Logger.getRootLogger();
	public Matcher() {
		
	}
	
	//Method needs to look at the availability and skill levels and match runners to dogs.
		//It creates a new pairing. If no match is found, it outputs a message
  public Pairing findMatchForRunner(Runner runner) {
	  Pairing pair = new Pairing();
		for (int j = 0; j < data.dogs.size(); j++) {
			if (runner.isRunnerAvailable() && data.dogs.get(j).isDogAvailable()) {
				if (runner.getSkillLevel() == data.dogs.get(j).getSkillLevelDog()) {
					pair = pair.createPairing(data.dogs.get(j), runner, data, "Runner");
					log.info("Match found");
					return pair;
						}
					}
				}
			System.out.println("Oh no! Looks like there aren't any available matches today. Try again tomorrow!");
			log.info("match not found");
			return null;
			}
  public Pairing findMatchForOwner(Owner owner) {
	  Pairing pair = new Pairing();
	  for (int j = 0; j < owner.getDogs().size(); j++) {
		  for (int i = 0; i < data.runners.size(); i++) {
			if (data.runners.get(i).isRunnerAvailable() && owner.getDogs().get(j).isDogAvailable()) {
				if (data.runners.get(i).getSkillLevel() == owner.getDogs().get(j).getSkillLevelDog()) {
					pair = pair.createPairing(data.dogs.get(j), data.runners.get(i), data, "Runner");
					log.info("Match found");
					return pair;
						}
						}
					}
	  			}
	  System.out.println("Oh no! Looks like there aren't any available matches today. Try again tomorrow!");
	  log.info("match not found");
	  return null;
  		}

}
