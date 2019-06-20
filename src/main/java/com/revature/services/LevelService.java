package com.revature.services;

import java.util.List;

import com.revature.models.Level;

public interface LevelService {
	
	public List<Level> findByLevel(String level);
	
	public Level findById(Integer id);
	
	public Level getOne(Integer id);
	
	public List<Level> findAll();
	
	//public void delete(Level level);
	
	public void deleteById(Integer id);
	
	public Level save(Level level);
}
