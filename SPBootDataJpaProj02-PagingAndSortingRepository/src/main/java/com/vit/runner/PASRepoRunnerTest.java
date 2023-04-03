//PASRepoRunnerTest.java
package com.vit.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.vit.entity.Bike;
import com.vit.service.IBikeService;

@Component
public class PASRepoRunnerTest implements CommandLineRunner {

	@Autowired
	private IBikeService bikeService;

	public PASRepoRunnerTest() {
		System.out.println("PASRepoRunnerTest.PASRepoRunnerTest()");
	}

	@Override
	public void run(String... args) throws Exception {

		//fetching all records by order using property name
		System.out.println("===============Sorting===============");
		Iterable<Bike> bikes = bikeService.getAllBikesByOrder(true, "bName");
		bikes.forEach(System.out::println);

		//fetching all records by passing page number and page size
		System.out.println("===============Paging===============");
		Page<Bike> page = bikeService.getBikesReport(0, 3);
		List<Bike> bikesList = page.getContent();
		  
		System.out.println("===Page No===>"+(page.getNumber()+1)+"===Page Size===>"+page.getSize()); bikesList.forEach(System.out::println);
		  
		System.out.println("Number of records on current page :: "+page.getNumberOfElements());
		System.out.println("Number of total records :: "+page.getTotalElements());
		System.out.println("Is records present ? :: "+page.hasContent());
		System.out.println("Is page empty ? :: "+page.isEmpty());
		System.out.println("Is this first page ? :: "+ page.isFirst());
		System.out.println("Is this last page ? :: "+ page.isLast());
		System.out.println("Sort obj :: "+ page.getSort()); 

		//fetching all records by passing page number, page size, order with property name
		System.out.println();
		Page<Bike> pageByOrder = bikeService.getBikesReportByOrder(0, 4, true, "rating");
		List<Bike> bikesByOrder = pageByOrder.getContent();
		bikesByOrder.forEach(System.out::println);

		//To display all records by passing page size
		try {
			bikeService.generateReport(3);
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
		
	}// run

}// class
