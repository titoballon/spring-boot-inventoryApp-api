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

import com.revature.models.Level;
import com.revature.services.LevelService;

@RestController
@RequestMapping("api/levels")
public class LevelController {
	
	private LevelService levelService;

	public LevelController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public LevelController(LevelService ls) {
		this.levelService = ls;
	}
	
//	@GetMapping
//	public List<Level> findAll(){
//		return levelService.findAll();
//	}
//	
//	@GetMapping("level/{level}")
//	public List<Level> findByLevel(@PathVariable String level) {
//		return levelService.findByLevel(level);
//	}
//	
//	@GetMapping("{id}")
//	public Level findById(@PathVariable Integer id) {
//		return levelService.findById(id);
//	}
//	
//	@PostMapping
//	public Level save(@Valid @RequestBody Level level) {
//		return levelService.save(level);
//	}
//	
//	@DeleteMapping
//	public void delete(@Valid @RequestBody Level level) {
//		levelService.delete(level);
//	}
//	
//	@PatchMapping
//	public Level updateArea(@Valid @RequestBody Level level) {
//		return levelService.save(level);		
//	}
}
