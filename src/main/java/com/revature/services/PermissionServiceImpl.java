package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ApiException;
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
}
