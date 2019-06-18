package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{
	
	public List<Permission> findByUserUsername(String username);
	
	public List<Permission> findByInventoryInventory(String inventory);
	
	public List<Permission> findByLevelLevel(String level);
	
	//public Permission updatePermission(Permission permission);
	
	public List<Permission> findByUserId(Integer id);

}
