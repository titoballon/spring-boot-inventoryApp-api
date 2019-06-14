package com.revature.services;

import java.util.List;

import com.revature.models.Area;

public interface AreaService {
	
	public List<Area> findAll();
	
	public List<Area> findByArea(String area);
	
	public List<Area> findByInventory(String inventory);
	
	public Area findById(Integer id);
	
	public Area save(Area area);
	
	public void delete(Area area);
}
