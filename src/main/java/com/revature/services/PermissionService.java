package com.revature.services;

import java.util.List;

import com.revature.models.Permission;

public interface PermissionService {
	
	public List<Permission> findAll();
	
	public Permission findById(Integer id);
	
	public Permission getOne(Integer id);
	
	public Permission save(Permission permission);
	
	//public void delete(Permission permission);
	
	public void deleteById(Integer id);
	
	public List<Permission> findByUsername(String username);
	
	public List<Permission> findByInventoryName(String inventory);
	
	public List<Permission> findByLevel(String level);
	
	public List<Permission> findByUserId(Integer id);
	
	//public Permission updatePermission(Permission permission);
}
