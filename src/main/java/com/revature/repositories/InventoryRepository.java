package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
	
	public List<Inventory> findByInventory(String inventory);
}
