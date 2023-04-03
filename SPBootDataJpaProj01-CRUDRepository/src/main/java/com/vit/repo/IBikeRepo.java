//IBikeRepo.java
package com.vit.repo;

import org.springframework.data.repository.CrudRepository;

import com.vit.entity.Bike;

public interface IBikeRepo extends CrudRepository<Bike, Integer> {

}
