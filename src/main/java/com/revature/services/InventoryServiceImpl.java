package com.revature.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ApiException;
import com.revature.models.Inventory;
import com.revature.models.Level;
import com.revature.models.Permission;
import com.revature.models.User;
import com.revature.repositories.InventoryRepository;
import com.revature.repositories.LevelRepository;
import com.revature.repositories.PermissionRepository;
import com.revature.repositories.UserRepository;

@Service
public class InventoryServiceImpl implements InventoryService{
	
	private InventoryRepository inventoryRepository;
	
	private PermissionRepository permissionRepository;

	private UserRepository userRepository;
	private LevelRepository levelRepository;

	@Autowired
	public InventoryServiceImpl(InventoryRepository ir,PermissionRepository pr, UserRepository ur, LevelRepository lr) {
		this.inventoryRepository = ir;
		this.permissionRepository = pr;
		this.userRepository = ur;
		this.levelRepository = lr;
	}

	@Override
	public List<Inventory> findByName(String inventory) {
		// TODO Auto-generated method stub
		List<Inventory> inventories = inventoryRepository.findByName(inventory);
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

//	@Override
//	public void delete(Inventory inventory) {
//		// TODO Auto-generated method stub
//		inventoryRepository.delete(inventory);
//	}

	@Override
	public Inventory save(Inventory inventory) {
		// TODO Auto-generated method stub
		return inventoryRepository.save(inventory);
	}

	@Override
	public Inventory getOne(Integer id) {
		// TODO Auto-generated method stub
		return inventoryRepository.getOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		inventoryRepository.deleteById(id);
	}

	@Override
	public List<Inventory> findInventoryByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.permissionRepository.findByUserId(userId).stream()
				.map(item->item.getInventory())
				.collect(Collectors.toList());
	}	
	
	@Override
	public Inventory saveInventoryByUserId(Inventory inventory, Integer userId) {
		// TODO Auto-generated method stub

		Inventory newInventory = inventoryRepository.save(inventory);
		User user = userRepository.findById(userId).orElse(null);
		Level level = levelRepository.findById(1).orElse(null);

		Permission newPermission = new Permission();
		newPermission.setUser(user);
		newPermission.setInventory(newInventory);
		newPermission.setLevel(level);
		permissionRepository.save(newPermission);
		return newInventory;
	}
}
