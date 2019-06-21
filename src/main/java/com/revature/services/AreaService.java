package com.revature.services;

import java.util.List;

import com.revature.converters.AreaItems;
import com.revature.models.Area;

public interface AreaService {
	
	public List<Area> findAll();
	
	public List<Area> findByName(String area);
	
	public List<Area> findByInventoryName(String inventory);
	
	public Area findById(Integer id);
	
	public void deleteById(Integer id);
	
	public Area save(Area area);
	
	//public void delete(Area area);
	
	public List<Area> findByInventoryId(Integer id);
	
	//public Area findOne(Area area);
	
	public Area getOne(Integer id);
	
	public List<AreaItems> findAreaItemsByInventoryId(Integer inventoryId);
}
