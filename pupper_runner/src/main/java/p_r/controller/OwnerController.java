package p_r.controller;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import io.javalin.http.Context;
import p_r.Service.OwnerService;
import p_r.Service.OwnerServiceFullStack;
import p_r.pojos.Owner;

public class OwnerController {
	
	OwnerService ownerService = new OwnerServiceFullStack();
	
	private static Logger log = Logger.getRootLogger();
	
	public void createOwner(Context ctx) {
		
		System.out.println("Responding to post owner request");
		
		log.info("Controller: create owner");
		
		String userName = ctx.formParam("ownerusername");
		
		int password = Integer.valueOf(ctx.formParam("password"));
		
		int numDogs = Integer.valueOf(ctx.formParam("numdogs"));
		
		Owner owner = new Owner(userName, password, numDogs);
		
		ownerService.createOwner(owner);
		
		ctx.html(owner.getName());
		
	}
	
	public void readOwner(Context ctx) {
		log.info("Controller: read an owner");
		
		String userName = (ctx.formParam("ownerusername"));
		
		Owner owner = ownerService.readOwner(userName);
		
		ctx.html("Owner with the username: " + userName + " has " + owner.getNumDogs() + " dogs.");
	}
	
	public void readAllOwners(Context ctx) {
		log.info("Controller: read all owners");
		
		System.out.print("Responding to post read all owners request");
		
		List<Owner> ownerList = new ArrayList<>();
		ownerList = ownerService.getAllOwners();
		String str = "";
		
		for (int i = 0; i < ownerList.size(); i++) {
			Owner owner = ownerList.get(i);
			str += ("Owner with the username: " + owner.getName() + " has " 
			+ owner.getNumDogs() + " dogs. \n");
		}
		
		ctx.html(str);
	}
	
	public void updateOwner(Context ctx) {
		System.out.print("Responding to update owner request");
		
		log.info("Controller: update owner");
		String userName = ctx.formParam("ownerusername");
		
		int password = Integer.valueOf(ctx.formParam("password"));
		
		int numDogs = Integer.valueOf(ctx.formParam("numdogs"));
		
		Owner owner = new Owner(userName, password, numDogs);
		
		ownerService.updateOwner(userName, owner);
	}
	
	public void deleteOwner(Context ctx) {
		System.out.println("Responding to post delete owner");
		
		log.info("Controller: delete owner");

		String userName = ctx.formParam("ownerusername");
		ownerService.deleteOwner(userName);
		
	}

}
