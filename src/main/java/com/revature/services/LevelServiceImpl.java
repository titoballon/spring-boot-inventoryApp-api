package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Level;
import com.revature.repositories.LevelRepository;

@Service
public class LevelServiceImpl implements LevelService{
	
	private LevelRepository levelRepository;

	@Autowired
	public LevelServiceImpl(LevelRepository lr) {
		this.levelRepository = lr;
	}

	@Override
	public List<Level> findByLevel(String level) {
		// TODO Auto-generated method stub
		return levelRepository.findByLevel(level);
	}

	@Override
	public Level findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Level> res = levelRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Level> findAll() {
		// TODO Auto-generated method stub
		return levelRepository.findAll();
	}

	@Override
	public void delete(Level level) {
		// TODO Auto-generated method stub
		levelRepository.delete(level);
	}

	@Override
	public Level save(Level level) {
		// TODO Auto-generated method stub
		return levelRepository.save(level);
	}
}
