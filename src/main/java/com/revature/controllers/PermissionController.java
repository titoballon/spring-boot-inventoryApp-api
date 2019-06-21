package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Area;
import com.revature.models.Inventory;
import com.revature.models.Permission;
import com.revature.services.PermissionService;
import com.revature.util.NullPropertiesHandler;

@RestController
@RequestMapping("permissions")
public class PermissionController {
	
	private PermissionService permissionService;

	public PermissionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public PermissionController(PermissionService ps) {
		this.permissionService = ps;
	}
	
	@GetMapping
	public List<Permission> findAll(){
		return permissionService.findAll();
	}
	
	@GetMapping("username/{username}")
	public List<Permission> findByUsername(@PathVariable String username) {
		return permissionService.findByUsername(username);
	}
	
	@GetMapping("inventory/{inventory}")
	public List<Permission> findByInventoryName(@PathVariable String inventory) {
		return permissionService.findByInventoryName(inventory);
	}
	
	@GetMapping("level/{level}")
	public List<Permission> findByLevel(@PathVariable String level) {
		return permissionService.findByLevel(level);
	}
	
//	@GetMapping("{id}")
//	public Permission findById(@PathVariable Integer id) {
//		return permissionService.findById(id);
//	}
	
	@GetMapping("{permissionId}")
	public Permission findById(@PathVariable Integer permissionId) {
		return permissionService.findById(permissionId);
	}
	
//	@GetMapping("user/{id}")
//	public List<Permission> findByUserId(@PathVariable Integer id) {
//		return permissionService.findByUserId(id);
//	}
	
	@GetMapping("user/{userId}")
	public List<Permission> findByUserId(@PathVariable Integer userId) {
		return permissionService.findByUserId(userId);
	}
	

	@PostMapping
	public Permission save(@Valid @RequestBody Permission permission) {
		return permissionService.save(permission);
	}
	
//	@DeleteMapping
//	public void delete(@Valid @RequestBody Permission permission) {
//		permissionService.delete(permission);
//	}
	
	@DeleteMapping("{permissionId}")
	public void deleteById(@PathVariable Integer permissionId) {
		permissionService.deleteById(permissionId);
	}
	
	@PatchMapping
	public Permission updatePermission(@Valid @RequestBody Permission permission) {
		Permission existingPermission = permissionService.getOne(permission.getId());
		NullPropertiesHandler.myCopyProperties(permission, existingPermission);
		return permissionService.save(existingPermission);	
	}
	
	@PostMapping("users/username/{username}")
	public ResponseEntity<Permission> sharePermissionToUser(@Valid @RequestBody Inventory inventory,@PathVariable String username) {
		return new ResponseEntity<Permission>(permissionService.sharePermissionToUser(inventory,username),HttpStatus.OK);
	}
}
