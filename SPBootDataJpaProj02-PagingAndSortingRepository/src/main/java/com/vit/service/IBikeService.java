//IBikeService.java
package com.vit.service;

import org.springframework.data.domain.Page;

import com.vit.entity.Bike;

public interface IBikeService {

	public Iterable<Bike> getAllBikesByOrder(boolean order,String... props);
	public Page<Bike> getBikesReport(int pageNo, int pageSize);
	public Page<Bike> getBikesReportByOrder(int pageNo, int pageSize, boolean order, String... props);
	public void generateReport(int pageSize);
}
