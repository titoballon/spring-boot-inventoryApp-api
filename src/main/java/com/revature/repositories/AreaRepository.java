package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Area;

public interface AreaRepository extends JpaRepository<Area, Integer>{
	
	public List<Area> findByName(String area);
	
	public List<Area> findByInventoryName(String name);
	
	public List<Area> findByInventoryId(Integer id);
}
