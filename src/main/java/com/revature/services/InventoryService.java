package com.revature.services;

import java.util.List;

import com.revature.converters.InventoryLevel;
import com.revature.models.Inventory;

public interface InventoryService {
	
	public List<Inventory> findByName(String inventory);
	
	public Inventory findById(Integer id);
	
	public void deleteById(Integer id);
	
	public List<Inventory> findAll();
	
	//public List<Inventory> findInventoryByUserId(Integer userId);
	
	public List<InventoryLevel> findInventoryByUserId(Integer userId);
	
	//public void delete(Inventory inventory);
	
	public Inventory save(Inventory inventory);
	
	public Inventory getOne(Integer id);
	
	public Inventory saveInventoryByUserId(Inventory inventory, Integer userId);
}
