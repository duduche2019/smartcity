package com.smartcity.smartcity.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcity.smartcity.model.City;

@Repository
public interface CityDAO extends JpaRepository<City, Integer> {

	City getCityByCityName(String name);
	
	List<City> findAllByOrderByCityNameAsc();
	
	List<City> findAllByUserUserId(int userId);

	void deleteByCityId(int cityId);

	

	/*
	 * @Modifying
	 * 
	 * @Query("delete from City c where c.cityId = ?1") void deleteCityByCityId(int
	 * cityId);
	 */

}
