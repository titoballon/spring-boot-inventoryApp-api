package com.revature.services;

import java.util.List;

import com.revature.models.Permission;

public interface PermissionService {
	
	public List<Permission> findAll();
	
	public Permission findById(Integer id);
	
	public Permission save(Permission permission);
	
	public void delete(Permission permission);
	
	public List<Permission> findByUser(String username);
	
	public List<Permission> findByInventory(String inventory);
	
	public List<Permission> findByLevel(String level);
	
	//public Permission updatePermission(Permission permission);
}
