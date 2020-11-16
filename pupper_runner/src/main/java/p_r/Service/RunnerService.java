package p_r.Service;


import java.util.List;

import p_r.pojos.Runner;

public interface RunnerService {

public Runner createRunner(Runner runner);

public Runner readRunner(String userName);

public List<Runner> readAllRunners();

public Runner updateRunner(String userName, Runner runner);

public void deleteRunner(String userName);

public List<Runner> getAllRunners();

public List<Runner> getRunnersBySkillLevel(int skillLevel);

}
