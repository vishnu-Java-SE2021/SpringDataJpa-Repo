//BikeServiceImpl.java
package com.vit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	//=======================Insert Operations==================
	@Override
	public String registerBike(Bike bike) {
		System.out.println("Repo Impl class :: "+bikeRepo.getClass());
		System.out.println("Repo Impl class extended interfaces :: "+Arrays.toString(bikeRepo.getClass().getInterfaces()));
		Bike registeredBike = bikeRepo.save(bike);
		return "Bike is registered with id :: "+registeredBike.getBId();
	}
	
	@Override
	public String groupRegistration(List<Bike> bikeList) {
		Iterable<Bike> savedBikes = bikeRepo.saveAll(bikeList);
		List<Integer> ids = new ArrayList<>();
		
		for(Bike b : savedBikes) {
			ids.add(b.getBId());
		}
		return "These id's "+ids.toString()+ " related bikes are saved";
	}

	//======================Select Operations==========================
	@Override
	public boolean checkBikeById(Integer id){
		return bikeRepo.existsById(id);
	}

	@Override
	public Integer fetchBikesCount() {
		return (int)bikeRepo.count();
	}

	@Override
	public Iterable<Bike> fetchAllBikes() {
		return bikeRepo.findAll();
	}

	@Override
	public Iterable<Bike> fetchAllBikesById(List<Integer> ids) {
		return bikeRepo.findAllById(ids);
	}

	@Override
	public Bike fetchBikeById(Integer id) {
//		Optional<Bike> opt = bikeRepo.findById(id);
		
//		if(opt.isPresent()) {
//			return opt.get();
//		}
//		else {			
//			throw new IllegalArgumentException("No record present");
//		}
		
		return bikeRepo.findById(id).get();    //returns object if id is found or else throws NoSuchElementFoundException
//		return bikeRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("No record found"));  //returns object if found or else throws Exception
//		return bikeRepo.findById(id).orElse(new Bike());	//return object if found or else throws new object with all default values
	}

	@Override
	public Optional<Bike> searchBikeById(Integer id) {
		Optional<Bike> opt = bikeRepo.findById(id);
		return opt.isEmpty()? Optional.empty() : opt;
	}

	
	//=============================Update Operations============================
	@Override
	public String updateBikeDetails(Integer id, String brand, Double rating) {

		Optional<Bike> opt = bikeRepo.findById(id);
		
		if(opt.isPresent()) {
			Bike b = opt.get();
			b.setBName(brand);
			b.setRating(rating);
			bikeRepo.save(b);
			return "Bike updated";
		}else {
			throw new IllegalArgumentException("No bike found with id :: "+id);
		}
	}

	@Override
	public String updateBike(Bike b) {
		Optional<Bike> opt = bikeRepo.findById(b.getBId());
		
		if(opt.isPresent()) {
			bikeRepo.save(b);
			return "Bike Updated";
		}else {
			throw new IllegalArgumentException("No bike found with id :: "+b.getBId());
			}
	}
	
	/*
	 * @Override 
	 * public String updateBike(Bike b) { 
	 * 	bikeRepo.save(b); 
	 * 	return "Bike Updated"; 
	 * }
	 */

	//============================Delete Operations=================================
	@Override
	public String removeBikeById(Integer id) {
		bikeRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("No bike found with id :: "+id));
		bikeRepo.deleteById(id);
		return "Bike deleted";
	}

	@Override
	public String removeBike(Bike b) {
		bikeRepo.findById(b.getBId()).orElseThrow(()-> new IllegalArgumentException("No bike found with id :: "+b.getBId()));
		bikeRepo.delete(b);
		return "Bike deleted";
	}
	
	/* Use this method when record is definitely present
	 * @Override 
	 * public String removeBike(Bike b) { 
	 * 	bikeRepo.delete(b); 
	 * 	return "Bike deleted"; 
	 * }
	 */
	
	@Override
	public String removeAllBikesByIds(Iterable<Integer> ids) {
		Iterable<Bike> bikesList = bikeRepo.findAllById(ids);
		
		int count = ((List<Bike>)bikesList).size();
		
		if(count>0 && bikesList!=null) {
			bikeRepo.deleteAllById(ids);
			return count+" bikes are deleted";
		}else {
			throw new IllegalArgumentException("No bikes found with given ids :: "+ids.toString());
		}
	}
	
	@Override
	public String removeAllBikes(Iterable<Bike> bikes) {
		
		List<Integer> ids = new ArrayList<>();
		
		for(Bike b : bikes) {
			ids.add(b.getBId());
		}
		
		Iterable<Bike> bikesList = bikeRepo.findAllById(ids);
		
		long count = ((List<Bike>) bikesList).size();
		
		if(count>0 && ((List<Bike>) bikes).size()>0) {
			bikeRepo.deleteAllById(ids);
			return ids.toString()+" id's related bikes are deleted";
		}else
			throw new IllegalArgumentException("No records found to delete");
	}
	
	@Override
	public String removeAllBikes() {
		
		long count = bikeRepo.count();
		
		if(count>0) {
			bikeRepo.deleteAll();
			return count+" records are deleted";
		}else {
			return "No records found to delete";
		}
	}

}//class