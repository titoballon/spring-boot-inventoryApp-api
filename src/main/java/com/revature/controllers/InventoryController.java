package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.revature.services.InventoryService;
import com.revature.util.NullPropertiesHandler;

@RestController
@RequestMapping("inventories")
public class InventoryController {
	
	private InventoryService inventoryService;

	public InventoryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public InventoryController(InventoryService is) {
		this.inventoryService = is;
	}
	
	@GetMapping
	public List<Inventory> findAll(){
		return inventoryService.findAll();
	}
	
	@GetMapping("inventory/{inventory}")
	public List<Inventory> findByName(@PathVariable String inventory) {
		return inventoryService.findByName(inventory);
	}
	
//	@GetMapping("{id}")
//	public Inventory findById(@PathVariable Integer id) {
//		return inventoryService.findById(id);
//	}
	
	@PostMapping
	public Inventory save(@Valid @RequestBody Inventory inventory) {
		return inventoryService.save(inventory);
	}
	
//	@DeleteMapping
//	public void delete(@Valid @RequestBody Inventory inventory) {
//		inventoryService.delete(inventory);
//	}
	
	@DeleteMapping("{inventoryId}")
	public void deleteById(@PathVariable Integer inventoryId) {
		inventoryService.deleteById(inventoryId);
	}
	
	@PatchMapping
	public Inventory updateInventory(@Valid @RequestBody Inventory inventory) {
		Inventory existingInventory = inventoryService.getOne(inventory.getId());
		NullPropertiesHandler.myCopyProperties(inventory, existingInventory);
		return inventoryService.save(existingInventory);		
	}
	
	@GetMapping("{inventoryId}")
	public Inventory findById(@PathVariable Integer inventoryId) {
		return inventoryService.findById(inventoryId);
	}
	
	@GetMapping("users/{userId}")
	public List<Inventory> findInventoryByUserId(@PathVariable Integer userId) {
		return inventoryService.findInventoryByUserId(userId);
	}
	
	@PostMapping("users/{userId}")
	public Inventory saveInventoryByUserId(@Valid @RequestBody Inventory inventory, @PathVariable Integer userId) {
		return inventoryService.saveInventoryByUserId(inventory, userId);
	}
	
}
