//IBikeRepo.java
package com.vit.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vit.entity.Bike;

public interface IBikeRepo extends PagingAndSortingRepository<Bike, Integer> {

}
