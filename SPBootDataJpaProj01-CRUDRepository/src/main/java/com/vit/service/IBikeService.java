//IBikeService.java
package com.vit.service;

import java.util.List;
import java.util.Optional;

import com.vit.entity.Bike;

public interface IBikeService {

	public String registerBike(Bike bike);
	public boolean checkBikeById(Integer id);
	public Integer fetchBikesCount();
	public Iterable<Bike> fetchAllBikes();
	public Iterable<Bike> fetchAllBikesById(List<Integer> ids);
	public Bike fetchBikeById(Integer id);
	public Optional<Bike> searchBikeById(Integer id);
	public String groupRegistration(List<Bike> bikeList);
	public String updateBikeDetails(Integer id, String brand, Double rating);
	public String updateBike(Bike b);
	public String removeBikeById(Integer id);
	public String removeBike(Bike b);
	public String removeAllBikesByIds(Iterable<Integer> ids);
	public String removeAllBikes(Iterable<Bike> bikes);
	public String removeAllBikes();
	
}
