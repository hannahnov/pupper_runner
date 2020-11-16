package p_r.Service;

import java.util.List;

import org.apache.log4j.Logger;

import p_r.Dao.DogDao;
import p_r.Dao.DogDaoPostgres;
import p_r.Dao.RunnerDao;
import p_r.Dao.RunnerDaoPostgres;
import p_r.pojos.Runner;

public class RunnerServiceFullStack implements RunnerService {
	
	RunnerDao runnerDao = new RunnerDaoPostgres();
	DogDao dogDao = new DogDaoPostgres();

	private static Logger log = Logger.getRootLogger();

	@Override
	public Runner createRunner(Runner runner) {
		log.info("Runner Service: create runner");

		runnerDao.createRunner(runner);
		return runner;
	}

	@Override
	public Runner readRunner(String userName) {
		log.info("Runner Service: read runner");
		return runnerDao.readRunner(userName);
	}

	@Override
	public List<Runner> readAllRunners() {
		log.info("Runner Service: read all runners");
		return runnerDao.readAllRunners();
	}
	
	@Override
	public Runner updateRunner(String userName, Runner runner) {
		log.info("Runner Service: update runner");
		runnerDao.updateRunner(userName, runner);
		return runner;
	}

	@Override
	public void deleteRunner(String username) {
		log.info("Account Service: delete account");
		runnerDao.deleteRunner(username);
	}

	@Override
	public List<Runner> getAllRunners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Runner> getRunnersBySkillLevel(int skillLevel) {
		// TODO Auto-generated method stub
		return null;
	}

}

