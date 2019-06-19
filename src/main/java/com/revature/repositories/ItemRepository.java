package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	public List<Item> findByName(String name);
	
	public List<Item> findByAreaName(String area);
	
	public List<Item> findByAreaId(Integer id);
}
