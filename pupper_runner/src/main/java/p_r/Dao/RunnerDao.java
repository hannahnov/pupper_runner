package p_r.Dao;

import java.util.List;

import p_r.pojos.Runner;

public interface RunnerDao {
	
	public void createRunner(Runner runner);
	
	public Runner readRunner(String runnerId);
	
	public List<Runner> readAllRunners();
	
	public Runner updateRunner(String runnerId, Runner runner);
	
	public int deleteRunner(String username);


}
