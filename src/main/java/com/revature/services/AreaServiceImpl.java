package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Area;
import com.revature.repositories.AreaRepository;

@Service
public class AreaServiceImpl implements AreaService{
	
	private AreaRepository areaRepository;
	
	@Autowired
	public AreaServiceImpl(AreaRepository a) {
		this.areaRepository = a;
	}

	@Override
	public List<Area> findAll() {
		// TODO Auto-generated method stub
		return areaRepository.findAll();
	}

	@Override
	public List<Area> findByArea(String area) {
		// TODO Auto-generated method stub
		return areaRepository.findByArea(area);
	}
	
	@Override
	public List<Area> findByInventory(String inventory) {
		// TODO Auto-generated method stub
		return areaRepository.findByInventoryInventory(inventory);
	}

	@Override
	public Area findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Area> res = areaRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		} else {
			return null;
		}
	}

	@Override
	public Area save(Area area) {
		// TODO Auto-generated method stub
		return areaRepository.save(area);
	}

	@Override
	public void delete(Area area) {
		// TODO Auto-generated method stub
		areaRepository.delete(area);
	}

//	@Override
//	public Area updateArea(Area area) {
//		// TODO Auto-generated method stub
//		return areaRepository.save(area);
//	}
}
