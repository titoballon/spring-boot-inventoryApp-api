package com.revature.services;

import java.util.List;

import com.revature.models.Item;

public interface ItemService {
	
	public List<Item> findAll();
	
	public List<Item> findByName(String name);
	
	public Item findById(Integer id);
	
	public Item save(Item item);
	
	public void delete(Integer itemId);
	
	public List<Item> findByAreaId(Integer id);
}

