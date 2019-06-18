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

import com.revature.models.Item;
import com.revature.services.ItemService;

@RestController
@RequestMapping("items")
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
	
	@GetMapping
	public List<Item> findAll(){
		return itemService.findAll();
	}
	
	@GetMapping("item/{item}")
	public List<Item> findByItem(@PathVariable String item) {
		return itemService.findByItem(item);
	}
	
	@GetMapping("area/{area}")
	public List<Item> findByArea(@PathVariable String area) {
		return itemService.findByArea(area);
	}
	
	@GetMapping("{id}")
	public Item findById(@PathVariable Integer id) {
		return itemService.findById(id);
	}
	
	@GetMapping("areaid/{id}")
	public List<Item> findByAreaId(@PathVariable Integer id) {
		return itemService.findByAreaId(id);
	}
	
	@PostMapping
	public Item save(@Valid @RequestBody Item item) {
		return itemService.save(item);
	}
	
	@DeleteMapping
	public void delete(@Valid @RequestBody Item item) {
		itemService.delete(item);
	}
	
	@PatchMapping
	public Item updateItem(@Valid @RequestBody Item item) {
		return itemService.save(item);		
	}
}
