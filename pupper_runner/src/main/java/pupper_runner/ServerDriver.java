package pupper_runner;

import org.apache.log4j.Logger;

import io.javalin.Javalin;
import p_r.controller.DogController;
import p_r.controller.OwnerController;
import p_r.controller.RunnerController;
public class ServerDriver {
	
	private static OwnerController ownerController = new OwnerController();
	private static RunnerController runnerController = new RunnerController();
	private static DogController dogController = new DogController();
	private static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(9096); //sets up and starts our server
		log.info("program has started");
		app.get("/HelloHannah", ctx -> ctx.html("Welcome to PupperRunner!"));
		
		//CRUD for Owner
		app.post("/createowner", ctx -> ownerController.createOwner(ctx));
		app.put("/updateowner", ctx -> ownerController.updateOwner(ctx));
		app.delete("/deleteowner", ctx -> ownerController.deleteOwner(ctx));
		app.get("/readowner", ctx -> ownerController.readOwner(ctx));
		app.get("readallowners", ctx -> ownerController.readAllOwners(ctx));
		
		//CRUD for Runner
		app.post("/createrunner", ctx -> runnerController.createRunner(ctx));
		app.put("/updaterunner", ctx -> runnerController.updateRunner(ctx));
		app.delete("/deleterunner", ctx -> runnerController.deleteRunner(ctx));
		app.get("/readrunner", ctx -> runnerController.readRunner(ctx));
		app.get("/readallrunners", ctx -> runnerController.readAllRunners(ctx));
		
		//CRUD for Dog
		app.post("/createdog", ctx -> dogController.createDog(ctx));
		app.put("/updatedog", ctx -> dogController.updateDog(ctx));
		app.delete("/deletedog", ctx -> dogController.deleteDog(ctx));
		app.get("/readdog", ctx -> dogController.readDog(ctx));
		app.get("/readalldogs", ctx -> dogController.readAllDogs(ctx));
	}
}
