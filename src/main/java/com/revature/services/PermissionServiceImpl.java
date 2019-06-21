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
import com.revature.repositories.LevelRepository;
import com.revature.repositories.PermissionRepository;
import com.revature.repositories.UserRepository;

@Service
public class PermissionServiceImpl implements PermissionService{
	
	private PermissionRepository permissionRepository;
	private UserRepository userRepository;
	private LevelRepository levelRepository;
	
	@Autowired
	public PermissionServiceImpl(PermissionRepository pr, UserRepository ur, LevelRepository lr) {
		this.permissionRepository = pr;
		this.userRepository = ur;
		this.levelRepository = lr;
	}

	@Override
	public List<Permission> findAll() {
		// TODO Auto-generated method stub
		List<Permission> allPermissions = permissionRepository.findAll();
		if(allPermissions.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No permissions found");
		return allPermissions;
	}

	@Override
	public Permission findById(Integer id) {
		// TODO Auto-generated method stub
		//Permission res = permissionRepository.findById(id).orElse(null);
		//if (res != null)
		Optional<Permission> res = permissionRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
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

//	@Override
//	public void delete(Permission permission) {
//		// TODO Auto-generated method stub
//		permissionRepository.delete(permission);
//	}

	@Override
	public List<Permission> findByLevel(String level) {
		// TODO Auto-generated method stub
		List<Permission> permissions = permissionRepository.findByLevelLevel(level);
		if(permissions.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No permissions found");
		return permissions;
	}

	@Override
	public List<Permission> findByUsername(String username) {
		// TODO Auto-generated method stub
		List<Permission> permissions = permissionRepository.findByUserUsername(username);
		if(permissions.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No permissions found");
		return permissions;
	}

	@Override
	public List<Permission> findByInventoryName(String inventory) {
		// TODO Auto-generated method stub
		List<Permission> permissions = permissionRepository.findByInventoryName(inventory);
		if(permissions.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No permissions found");
		return permissions;
	}

	@Override
	public Permission getOne(Integer id) {
		// TODO Auto-generated method stub
		return permissionRepository.getOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		permissionRepository.deleteById(id);
	}
	
	@Override
	public Permission sharePermissionToUser(Inventory inventory, String username) {
		User user = userRepository.findByUsername(username);
		if(user == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "Username not found in database");
		Level level = levelRepository.findById(1).orElse(null);
		Permission permission = new Permission();
		permission.setInventory(inventory);
		permission.setLevel(level);
		permission.setUser(user);
		permissionRepository.save(permission);
		return permission;
	}
}
