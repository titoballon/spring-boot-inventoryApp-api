package com.revature.services;

import java.util.List;

import com.revature.models.Inventory;
import com.revature.models.Permission;

public interface PermissionService {
	
	
	public Permission findById(Integer id);
	
	public Permission save(Permission permission);
	
	public void delete(Integer permissionId);	
	
	public List<Permission> findByInventoryName(String name);
	
	public List<Permission> findByLevel(String level);
	
	public List<Permission> findByUserId(Integer id);

	public Permission sharePermissionToUser(Inventory inventory,String userName);
	
	//public Permission updatePermission(Permission permission);
	
	//public List<Permission> findByInventory(Inventory inventory);
}
