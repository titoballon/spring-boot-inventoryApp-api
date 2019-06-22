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
import com.revature.models.Item;
import com.revature.services.ItemService;
import com.revature.util.NullPropertiesHandler;

@RestController
@RequestMapping("api/inventories/areas/items/")
public class ItemController {
	
	private ItemService itemService;

	public ItemController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public ItemController(ItemService is) {
		this.itemService = is;
	} 
	
//	@GetMapping
//	public List<Item> findAll(){
//		return itemService.findAll();
//	}
	
//	@GetMapping("name/{name}")
//	public List<Item> findByItem(@PathVariable String name) {
//		return itemService.findByName(name);
//	}
	//list of items by areaId
	@GetMapping("area/{areaId}")
	public List<Item> findByAreaId(@PathVariable Integer areaId) {
		return itemService.findByAreaId(areaId);
	}
	
//	@GetMapping("{id}")
//	public Item findById(@PathVariable Integer id) {
//		return itemService.findById(id);
//	}
	
	@PostMapping
	public Item save(@Valid @RequestBody Item item) {
		return itemService.save(item);
	}
	
	@DeleteMapping("{itemId}")
	public ResponseEntity delete(@PathVariable Integer itemId) {
		itemService.delete(itemId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<Item> updateItem(@Valid @RequestBody Item item) {
		Item existingItem = itemService.getOne(item.getId());
		NullPropertiesHandler.myCopyProperties(item, existingItem);
		return new ResponseEntity<Item>(itemService.save(existingItem),HttpStatus.OK);
	}
}
