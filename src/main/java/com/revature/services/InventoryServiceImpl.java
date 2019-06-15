package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return inventoryRepository.findByInventory(inventory);
	}

	@Override
	public Inventory findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Inventory> res = inventoryRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Inventory> findAll() {
		// TODO Auto-generated method stub
		return inventoryRepository.findAll();
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
