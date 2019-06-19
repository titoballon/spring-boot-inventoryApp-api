package com.revature.services;

import java.util.List;

import com.revature.models.Area;

public interface AreaService {
	
	public List<Area> findAll();
	
	public List<Area> findByName(String name);
	
	public List<Area> findByInventoryName(String inventory);
	
	public Area findById(Integer id);
	
	public Area save(Area area);
	
	public void delete(Integer areaId);
	
	public List<Area> findByInventoryId(Integer id);
}
