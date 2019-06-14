package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Permission;
import com.revature.services.PermissionService;

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
	
	@GetMapping("user/{user}")
	public List<Permission> findByUser(@PathVariable String user) {
		return permissionService.findByUser(user);
	}
	
	@GetMapping("inventory/{inventory}")
	public List<Permission> findByInventory(@PathVariable String inventory) {
		return permissionService.findByInventory(inventory);
	}
	
	@GetMapping("level/{level}")
	public List<Permission> findByLevel(@PathVariable String level) {
		return permissionService.findByLevel(level);
	}
	
	@GetMapping("{id}")
	public Permission findById(@PathVariable Integer id) {
		return permissionService.findById(id);
	}
	

	@PostMapping
	public Permission save(@Valid Permission permission) {
		return permissionService.save(permission);
	}
	
	@DeleteMapping
	public void delete(@Valid Permission permission) {
		permissionService.delete(permission);
	}
	
	@PatchMapping
	public Permission updatePermission(@Valid Permission permission) {
		return permissionService.save(permission);		
	}
}
