package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	public List<Item> findByItem(String item);
	
	public List<Item> findByAreaArea(String area);
	
	public List<Item> findByAreaId(Integer id);
}
