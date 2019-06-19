package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
	
	public List<Inventory> findByName(String inventory);
	
	@Query(value = "from Inventory")
	public List<Inventory> findInventoryByUserId(@Param("userId") Integer userId);
}
