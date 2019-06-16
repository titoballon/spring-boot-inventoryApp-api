package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ApiException;
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
		List<Area> allAreas = areaRepository.findAll();
		if(allAreas.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No areas found");
		return allAreas;
	}

	@Override
	public List<Area> findByArea(String area) {
		// TODO Auto-generated method stub
		List<Area> areas = areaRepository.findByArea(area);
		if(areas.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No areas found");
		return areas;
	}
	
	@Override
	public List<Area> findByInventory(String inventory) {
		// TODO Auto-generated method stub
		List<Area> areas = areaRepository.findByInventoryInventory(inventory);
		if(areas.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No areas found");
		return areas;
	}

	@Override
	public Area findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Area> res = areaRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, "area not found");
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
}
