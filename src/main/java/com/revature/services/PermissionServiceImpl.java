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
}
