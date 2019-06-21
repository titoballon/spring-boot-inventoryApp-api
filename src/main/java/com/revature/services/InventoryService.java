package com.revature.services;

import java.util.List;

import com.revature.converters.AreaItems;
import com.revature.models.Inventory;

public interface InventoryService {
	
	public List<Inventory> findByName(String name);
	
	public Inventory findById(Integer id);
	
	public List<Inventory> findAll();
	
	public void delete(Integer inventoryId);
	
	public Inventory save(Inventory inventory);
	
	//find inventory by userId
	public List<Inventory> findInventoryByUserId(Integer userId);
	
	public Inventory saveInventoryByUserId(Inventory inventory, Integer userId);
	
	//public List<AreaItems> findAreaItemsByInventoryId(Integer inventoryId);
	
}
