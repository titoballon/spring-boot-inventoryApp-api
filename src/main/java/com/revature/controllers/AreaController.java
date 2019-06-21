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

import com.revature.converters.AreaItems;
import com.revature.models.Area;
import com.revature.services.AreaService;
import com.revature.util.NullPropertiesHandler;

@RestController
@RequestMapping("inventories/areas")
public class AreaController {
	
	private AreaService areaService;

	public AreaController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public AreaController(AreaService a) {
		this.areaService = a;
	}
	
	@GetMapping
	public List<Area> findAll(){
		return areaService.findAll();
	}
	
	@GetMapping("area/{area}")
	public List<Area> findByName(@PathVariable String area) {
		return areaService.findByName(area);
	}
	
//	@GetMapping("inventory/{inventory}")
//	public List<Area> findByInventoryName(@PathVariable String inventory) {
//		return areaService.findByInventoryName(inventory);
//	}
	
	@GetMapping("{id}")
	public Area findById(@PathVariable Integer id) {
		return areaService.findById(id);
	}
	
//	@GetMapping("inventoryid/{id}")
//	public List<Area> findByInventoryId(@PathVariable Integer id) {
//		return areaService.findByInventoryId(id);
//	}
	
	@PostMapping
	public Area save(@Valid @RequestBody Area area) {
		return areaService.save(area);
	}
	
//	@DeleteMapping
//	public void delete(@Valid @RequestBody Area area) {
//		areaService.delete(area);
//	}
	
	@DeleteMapping("{areaId}")
	public void deleteById(@PathVariable Integer areaId) {
		areaService.deleteById(areaId);
	}
	
	@PatchMapping
	public Area updateArea(@Valid @RequestBody Area area) {		
		Area existingArea = areaService.getOne(area.getId());
		NullPropertiesHandler.myCopyProperties(area, existingArea);
		return areaService.save(existingArea);	
	}
	
	@GetMapping("inventory/{inventoryId}")
	public List<Area> findByInventoryId(@PathVariable Integer inventoryId) {
		return areaService.findByInventoryId(inventoryId);
	}
	
	@GetMapping("{inventoryId}")
	public List<AreaItems> findAreaItemsByInventoryId(@PathVariable Integer inventoryId){
		return areaService.findAreaItemsByInventoryId(inventoryId);
	}
}
