//BikeServiceImpl.java
package com.vit.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vit.entity.Bike;
import com.vit.repo.IBikeRepo;

@Service("bikeService")
public class BikeServiceImpl implements IBikeService {

	@Autowired
	private IBikeRepo bikeRepo;
	
	public BikeServiceImpl() {
		System.out.println("BikeServiceImpl.BikeServiceImpl()");
	}

	@Override
	public Iterable<Bike> getAllBikesByOrder(boolean order, String... props) {
		
		Sort sort = Sort.by(order?Direction.ASC:Direction.DESC, props);
		
		return bikeRepo.findAll(sort);
	}

	@Override
	public Page<Bike> getBikesReport(int pageNo, int pageSize) {

		Pageable pg = PageRequest.of(pageNo, pageSize);		
		
		return bikeRepo.findAll(pg);
	}

	@Override
	public Page<Bike> getBikesReportByOrder(int pageNo, int pageSize, boolean order, String... props) {
		
		Sort sort = Sort.by(order? Direction.ASC : Direction.DESC, props);
		Pageable pg = PageRequest.of(pageNo, pageSize, sort);
		
		return bikeRepo.findAll(pg);
		
	}

	//displaying all records of each page
	@Override
	public void generateReport(int pageSize) {

		long count = bikeRepo.count();
		
		long pagesCount = 0;
		
		if(pageSize>0) {
			pagesCount = count/pageSize;
		
			pagesCount = count%pageSize==0 ? pagesCount : ++pagesCount;
		}else {
			throw new ArithmeticException("Page size cannot be zero");
		}
		
		for(int pageNo = 0; pageNo < pagesCount; ++pageNo) {
			Pageable pg = PageRequest.of(pageNo, pageSize);
			
			Page<Bike> page = bikeRepo.findAll(pg);
			
			List<Bike> bikes = page.getContent();
			
			System.out.println("<=====Page no :: "+(pageNo+1)+"=========pageSize :: "+pageSize+"======>");
			bikes.forEach(System.out::println);
			System.out.println();
		}	
	}
	
}//class