package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Level;

public interface LevelRepository extends JpaRepository<Level, Integer>{

		public List<Level> findByLevel(String level);
}
