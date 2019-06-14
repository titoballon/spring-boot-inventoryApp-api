package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Permission;
import com.revature.repositories.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService{
	
	private PermissionRepository permissionRepository;
	
	@Autowired
	public PermissionServiceImpl(PermissionRepository pr) {
		this.permissionRepository = pr;
	}

	@Override
	public List<Permission> findAll() {
		// TODO Auto-generated method stub
		return permissionRepository.findAll();
	}

	@Override
	public Permission findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Permission> res = permissionRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		} else {
			return null;
		}
	}

	@Override
	public Permission save(Permission permission) {
		// TODO Auto-generated method stub
		return permissionRepository.save(permission);
	}

	@Override
	public void delete(Permission permission) {
		// TODO Auto-generated method stub
		permissionRepository.delete(permission);
	}

	@Override
	public List<Permission> findByLevel(String level) {
		// TODO Auto-generated method stub
		return permissionRepository.findByLevelLevel(level);
	}

	@Override
	public List<Permission> findByUser(String username) {
		// TODO Auto-generated method stub
		return permissionRepository.findByUserUsername(username);
	}

	@Override
	public List<Permission> findByInventory(String inventory) {
		// TODO Auto-generated method stub
		return permissionRepository.findByInventoryInventory(inventory);
	}
	
//	@Override
//	public Permission updatePermission(Permission permission) {
//		return permissionRepository.save(permission);
//	}

}
