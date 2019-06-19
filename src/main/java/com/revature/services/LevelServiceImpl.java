package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ApiException;
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
		List<Level> levels = levelRepository.findByLevel(level);
		if(levels.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No levels found");
		return levels;
	}

	@Override
	public Level findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Level> res = levelRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, "level not found");
		}
	}

	@Override
	public List<Level> findAll() {
		// TODO Auto-generated method stub
		List<Level> allLevels = levelRepository.findAll();
		if(allLevels.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No levels found");
		return allLevels;
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

	@Override
	public Level getOne(Integer id) {
		// TODO Auto-generated method stub
		return levelRepository.getOne(id);
	}
}
