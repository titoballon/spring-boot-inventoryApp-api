package com.revature.services;

import java.util.List;

import com.revature.models.Item;

public interface ItemService {
	
	public List<Item> findAll();
	
	public List<Item> findByItem(String item);
	
	public List<Item> findByArea(String area);
	
	public Item findById(Integer id);
	
	public Item save(Item item);
	
	public void delete(Item item);
}

