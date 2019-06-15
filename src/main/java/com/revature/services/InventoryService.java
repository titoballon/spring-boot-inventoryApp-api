package com.revature.services;

import java.util.List;

import com.revature.models.Inventory;

public interface InventoryService {
	
	public List<Inventory> findByInventory(String inventory);
	
	public Inventory findById(Integer id);
	
	public List<Inventory> findAll();
	
	public void delete(Inventory inventory);
	
	public Inventory save(Inventory inventory);
}
