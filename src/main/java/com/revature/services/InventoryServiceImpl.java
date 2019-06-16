package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ApiException;
import com.revature.models.Inventory;
import com.revature.repositories.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService{
	
	private InventoryRepository inventoryRepository;

	@Autowired
	public InventoryServiceImpl(InventoryRepository ir) {
		this.inventoryRepository = ir;
	}

	@Override
	public List<Inventory> findByInventory(String inventory) {
		// TODO Auto-generated method stub
		List<Inventory> inventories = inventoryRepository.findByInventory(inventory);
		if(inventories.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No inventories found");
		return inventories;
	}

	@Override
	public Inventory findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Inventory> res = inventoryRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, "inventory not found");
		}
	}

	@Override
	public List<Inventory> findAll() {
		// TODO Auto-generated method stub
		List<Inventory> AllInventories = inventoryRepository.findAll();
		if(AllInventories.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No inventories found");
		return AllInventories;
	}

	@Override
	public void delete(Inventory inventory) {
		// TODO Auto-generated method stub
		inventoryRepository.delete(inventory);
	}

	@Override
	public Inventory save(Inventory inventory) {
		// TODO Auto-generated method stub
		return inventoryRepository.save(inventory);
	}	
}
