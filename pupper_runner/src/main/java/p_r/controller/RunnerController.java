package p_r.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import io.javalin.http.Context;
import p_r.Service.DogService;
import p_r.Service.DogServiceFullStack;
import p_r.Service.RunnerService;
import p_r.Service.RunnerServiceFullStack;
import p_r.Service.RunnerServiceImpl;
import p_r.pojos.Owner;
import p_r.pojos.Runner;

public class RunnerController {
	
	RunnerService runnerService = new RunnerServiceFullStack();
	DogService dogService = new DogServiceFullStack();
	private static Logger log = Logger.getRootLogger();
	
	
	public void createRunner(Context ctx) {
		
		log.info("Controller: create a runner");
		
		System.out.println("Responding to post create Runner request");
		
		String userName = ctx.formParam("runnerusername");
		
		int age = Integer.parseInt(ctx.formParam("age"));
		
		int password = Integer.parseInt(ctx.formParam("password"));
		
		int skillLevel = Integer.parseInt(ctx.formParam("skilllevel"));
		
		boolean isAvailable = Boolean.parseBoolean(ctx.formParam("isavailable"));
		
		int dogId = Integer.valueOf(ctx.formParam("dogid"));
		
		Runner runner = new Runner(userName, age, password, skillLevel, isAvailable, dogId);
		runnerService.createRunner(runner);
		
		ctx.html(runner.getName());
		
	}
	
	public void readRunner(Context ctx) {
		log.info("Controller: read a runner");

		System.out.println("Responding to post read Runner request");
		
		String userName = ctx.formParam("runnerusername");
		
		Runner runner = runnerService.readRunner(userName);
		
		ctx.html("The runner with userName " + userName + " is a level " + runner.getSkillLevel() +
				" runner. They are " + runner.getAge() + " years old, and their running partner is: " +
				dogService.readDog(runner.getRunnerDogId()).getDogName() + "\n");
		
	}
	
	public void updateRunner(Context ctx) {
		System.out.print("Responding to update runner request");
		
		log.info("Controller: update runner");
		String userName = ctx.formParam("runnerusername");
		
		int password = Integer.parseInt(ctx.formParam("password"));
		
		int age = Integer.parseInt(ctx.formParam("age"));
		
		int skillLevel = Integer.parseInt(ctx.formParam("skilllevel"));
		
		boolean isAvailable = Boolean.parseBoolean(ctx.formParam("isavailable"));
		
		int dogId = Integer.valueOf(ctx.formParam("dogid"));
		
		Runner runner = new Runner(userName, age, password, skillLevel, isAvailable, dogId);
		
		runnerService.updateRunner(userName, runner);
	}
	public void readAllRunners(Context ctx) {
		System.out.println("Responding to post read all Runners request");
		log.info("Controller: read all runners");
		
		List<Runner> runnerList = new ArrayList<>();
		
		runnerList = runnerService.readAllRunners();
		
		String str = "";
		
		for (int i = 0; i < runnerList.size(); i++) {
			Runner runner = runnerList.get(i);
			str += ("The runner with userName " + runner.getName() + " is a level " + runner.getSkillLevel() +
			" runner. They are " + runner.getAge() + " years old, and their running partner is: " +
			dogService.readDog(runner.getRunnerDogId()).getDogName() + "\n");
		}
		
		ctx.html(str);
	}

	public void deleteRunner(Context ctx) {
		System.out.println("Responding to post delete Runner request");
		
		log.info("Controller: delete a runner");		
		
		String username = ctx.formParam("runnerusername");
		
		runnerService.deleteRunner(username);
	}
}
