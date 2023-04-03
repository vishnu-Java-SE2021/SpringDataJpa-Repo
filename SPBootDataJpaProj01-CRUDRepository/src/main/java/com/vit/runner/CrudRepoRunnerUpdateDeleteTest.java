//CrudRepoRunnerUpdateDeleteTest.java
package com.vit.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vit.entity.Bike;
import com.vit.service.IBikeService;

@Component
public class CrudRepoRunnerUpdateDeleteTest implements CommandLineRunner {

	@Autowired
	private IBikeService bikeService;

	public CrudRepoRunnerUpdateDeleteTest() {
		System.out.println("CrudRepoRunnerUpdateDeleteTest.CrudRepoRunnerUpdateDeleteTest()");
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("CrudRepoRunnerUpdateDeleteTest.run()");

		System.out.println("==================Update Operation================");
		//partial object update
		try {
				String msg = bikeService.updateBikeDetails(5,"Rtr",5.0);
				System.out.println(msg);
				System.out.println();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		//Full object update
		try {
			Bike b = new Bike();
			b.setBId(5);
			b.setBName("Apache");
			b.setBrand("TVS");
			b.setRating(4.9);
			String result = bikeService.updateBike(b);
			System.out.println(result);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("=================Delete Operation=================");
		//deleting by passing pk column value
		try {
			System.out.println(bikeService.removeBikeById(17));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//deleting by passing entity object
		try {
			Bike b = new Bike();
			b.setBId(15);
			System.out.println("Result :: "+bikeService.removeBike(b));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//deleting all entities by giving pk column values
		try {
			System.out.println(bikeService.removeAllBikesByIds(List.of(14,23)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		//deleting all records by passing entity objects
		try {
			System.out.println(bikeService.removeAllBikes(List.of(new Bike(14,"Yamaha R15","Yamaha",4.5),
																											new Bike(20,"Royal Enfield","Enfield",4.6))));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//deleting all records(truncate)
		System.out.println(bikeService.removeAllBikes());
		
	}//run

}// class
