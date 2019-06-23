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

import com.revature.models.Inventory;
import com.revature.models.Permission;
import com.revature.services.PermissionService;

@RestController
@RequestMapping("api/permissions")
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
	
	@GetMapping("level/{level}")
	public List<Permission> findByLevel(@PathVariable String level) {
		return permissionService.findByLevel(level);
	}
	
	@GetMapping("{permissionId}")
	public Permission findById(@PathVariable Integer permissionId) {
		return permissionService.findById(permissionId);
	}
	
	@GetMapping("user/{userId}")
	public List<Permission> findByUserId(@PathVariable Integer userId) {
		return permissionService.findByUserId(userId);
	}
	
	@PostMapping
	public Permission save(@Valid @RequestBody Permission permission) {
		return permissionService.save(permission);
	}
	
	@DeleteMapping("{permissionId}")
	public ResponseEntity delete(@PathVariable Integer permissionId) {
		permissionService.delete(permissionId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PatchMapping
	public Permission updatePermission(@Valid @RequestBody Permission permission) {
		return permissionService.save(permission);		
	}
	
//	@PostMapping("users/username/{username}")
//	public ResponseEntity<Permission> sharePermissionToUser(@Valid @RequestBody Inventory inventory,@PathVariable String username) {
//		
//		return new ResponseEntity<Permission>(permissionService.sharePermissionToUser(inventory,username),HttpStatus.OK);
//				
//	}
	
	@PostMapping("users/username/{username}/level/{levelId}")
	public ResponseEntity<Permission> sharePermissionToUser(@Valid @RequestBody Inventory inventory,@PathVariable String username,
								@PathVariable Integer levelId) {
		
		return new ResponseEntity<Permission>(permissionService.sharePermissionToUser(inventory,username,levelId),HttpStatus.OK);
				
	}
}
