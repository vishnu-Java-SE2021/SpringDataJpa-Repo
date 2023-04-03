//CrudRepoRunnerInsertSelectRunner.java
package com.vit.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vit.entity.Bike;
import com.vit.service.IBikeService;

@Component
public class CrudRepoRunnerInsertSelectTest implements CommandLineRunner {

	@Autowired
	private IBikeService bikeService;

	public CrudRepoRunnerInsertSelectTest() {
		System.out.println("CrudRepoRunnerInsertSelectTest.CrudRepoRunnerInsertSelectTest()");
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("CrudRepoRunnerInsertSelectTest.run()");
		
		//inserting a record
		System.out.println("=======================Insert operation======================");
		try (Scanner sc = new Scanner(System.in)){
			Bike bike = new Bike();
			
			System.out.print("Enter Bike name :: ");
			String name = sc.nextLine();
			
			System.out.print("Enter Bike brand name :: ");
			String brandName = sc.nextLine();
			
			System.out.print("Enter Bike rating :: ");
			Double rating = sc.nextDouble();
			
			bike.setBName(name);
			bike.setBrand(brandName);
			bike.setRating(rating);
			
			String result = bikeService.registerBike(bike);
			
			System.out.println(result);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//inserting multiple records
		System.out.println();
		System.out.println("===============saveAll()===================");
			
		try {
				String msg = bikeService.groupRegistration(List.of(new Bike("Yamaha R15","Yamaha",4.5),
																											new Bike("Activa 5G","Honda",4.8),
																											new Bike("Royal Enfield","Enfield",4.6)));
					
				System.out.println(msg);
					
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		System.out.println("==========================Select Operation=========================");

		//checking record is present or not
		System.out.println();
		System.out.println("Is bike with id - 4 is present :: "+bikeService.checkBikeById(4));
		System.out.println("===========================================");
		
		//fetching count of records
		System.out.println();
		System.out.println("Total bikes count :: "+bikeService.fetchBikesCount());
		
		//fetching all records and processing the data
		System.out.println();
		System.out.println("================findAll()===============");
		Iterable<Bike> bikes = bikeService.fetchAllBikes();
		
		System.out.println();
		System.out.println("==============enhanced for loop==================");
		for(Bike b : bikes) {
			System.out.println(b);
		}
		
		System.out.println();
		System.out.println("================forEach method + LAMBDA exp===================");
		bikes.forEach(bike->{
			System.out.println(bike);
		});
		
		System.out.println();
		System.out.println("=================forEach method + static method reference==================");
		bikes.forEach(System.out::println);
		
		System.out.println();
		System.out.println("=================Stream API + forEach method + static method reference==================");
		Arrays.asList(bikes).stream().forEach(System.out::println);
			
		
		//fetching the records by PK column values as id's
		System.out.println();
		System.out.println("================findAllById(Ids)==================");
		
		List<Integer> ids = new ArrayList<>();
		ids.add(4); ids.add(5);
		
		System.out.println("Fetching all bikes using id's :: "+bikeService.fetchAllBikesById(ids));
		System.out.println();
		
		System.out.println("=================================");
		System.out.println(bikeService.fetchAllBikesById(List.of(4,5)));   //Java 9v static factory method, immutable list
		System.out.println();
		
		System.out.println("=================================");
		System.out.println(bikeService.fetchAllBikesById(Arrays.asList(4,5)));
		
		//fetching the single record by PK column value as id
		System.out.println();
		System.out.println("===============findById(id)===================");
		
		try {
			System.out.println(bikeService.fetchBikeById(5));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		System.out.println();
		System.out.println("===============findById(id) with Optional<T> as return type===================");
		
		try {
			Optional<?> opt = bikeService.searchBikeById(5);
			
			if(opt.isPresent()) {
				System.out.println(opt.get());
			}
			else {
				throw new IllegalArgumentException("No record found");
			}
				
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}//run

}// class
