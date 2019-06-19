package com.revature.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ApiException;
import com.revature.models.Inventory;
import com.revature.repositories.InventoryRepository;
import com.revature.repositories.PermissionRepository;

@Service
public class InventoryServiceImpl implements InventoryService{
	
	private InventoryRepository inventoryRepository;
	private PermissionRepository permissionRepository;

	@Autowired
	public InventoryServiceImpl(InventoryRepository ir,PermissionRepository pr) {
		this.inventoryRepository = ir;
		this.permissionRepository = pr;
	}

	@Override
	public List<Inventory> findByName(String name) {
		// TODO Auto-generated method stub
		List<Inventory> inventories = inventoryRepository.findByName(name);
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
	public void delete(Integer inventoryId) {
		// TODO Auto-generated method stub
		inventoryRepository.deleteById(inventoryId);
	}

	@Override
	public Inventory save(Inventory inventory) {
		// TODO Auto-generated method stub
		return inventoryRepository.save(inventory);
	}

	@Override
	public List<Inventory> findInventoryByUserId(Integer userId) {
		List<Inventory> listInventory = this.permissionRepository.findByUserId(userId).stream()
											.map((item)->item.getInventory())
											.collect(Collectors.toList());
		System.out.println(listInventory);
		return listInventory;
	}	
}
