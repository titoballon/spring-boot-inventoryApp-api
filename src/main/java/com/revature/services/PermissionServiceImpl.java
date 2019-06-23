package com.revature.services;

import java.util.List;
import java.util.Optional;

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
public class PermissionServiceImpl implements PermissionService{
	
	private PermissionRepository permissionRepository;
	private UserRepository userRepository;
	private LevelRepository levelRepository;
	private InventoryRepository inventoryRepository;
	
	@Autowired
	public PermissionServiceImpl(
				PermissionRepository pr,
				UserRepository ur,
				LevelRepository lr,
				InventoryRepository ir) {
		this.permissionRepository = pr;
		this.userRepository = ur;
		this.levelRepository = lr;
		this.inventoryRepository = ir;
	}

	@Override
	public Permission findById(Integer id) {
		// TODO Auto-generated method stub
		Permission res = permissionRepository.findById(id).orElse(null);
		if(res != null) {
			return res;
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, "permission not found");
		}
	}
	
	@Override
	public List<Permission> findByUserId(Integer id) {
		// TODO Auto-generated method stub
		return permissionRepository.findByUserId(id);
	}

	@Override
	public Permission save(Permission permission) {
		// TODO Auto-generated method stub
		return permissionRepository.save(permission);
	}

	@Override
	public void delete(Integer permissionId) {
		// TODO Auto-generated method stub
		permissionRepository.deleteById(permissionId);
	}

	@Override
	public List<Permission> findByLevel(String level) {
		// TODO Auto-generated method stub
		List<Permission> permissions = permissionRepository.findByLevelLevel(level);
		if(permissions.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No permissions found");
		return permissions;
	}


	@Override
	public List<Permission> findByInventoryName(String inventoryName) {
		// TODO Auto-generated method stub
		List<Permission> permissions = permissionRepository.findByInventoryName(inventoryName);
		if(permissions.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No permissions found");
		return permissions;
	}

	@Override
	public Permission sharePermissionToUser(Inventory inventory, String userName, Integer levelId) {
		//TO DO: check if the user already has permission before adding
		List<User> user = userRepository.findByUsername(userName);
		if(user.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "Username not found in database");
		
		List<Permission> perm = permissionRepository.findByInventoryAndUser(inventory, user.get(0));
		
		Level level = levelRepository.findById(levelId).orElse(null);
		
		if(!perm.isEmpty()) {
			Permission permission = perm.get(0);
			if (permission.getLevel().getId() > level.getId()) {		
			permission.setInventory(inventory);
			permission.setLevel(level);
			permission.setUser(user.get(0));
			permissionRepository.save(permission);
			return permission;
			} else return permission;
		} else {
			Permission permission = new Permission();
			permission.setInventory(inventory);
			permission.setLevel(level);
			permission.setUser(user.get(0));
			permissionRepository.save(permission);
			return permission;
		}
	}
}
