package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.revature.models.Permission;
import com.revature.services.AreaService;
import com.revature.util.NullPropertiesHandler;

@RestController
@RequestMapping("api/inventories/areas")
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
	
//	@GetMapping
//	public List<Area> findAll(){
//		return areaService.findAll();
//	}
//	
//	@GetMapping("area/{area}")
//	public List<Area> findByArea(@PathVariable String area) {
//		return areaService.findByArea(area);
//	}
//	
//	@GetMapping("inventory/{inventory}")
//	public List<Area> findByInventory(@PathVariable String inventory) {
//		return areaService.findByInventory(inventory);
//	}
//	
//	@GetMapping("{id}")
//	public Area findById(@PathVariable Integer id) {
//		return areaService.findById(id);
//	}
//	
	@GetMapping("inventory/{inventoryId}")
	public List<Area> findByInventoryId(@PathVariable Integer inventoryId) {
		return areaService.findByInventoryId(inventoryId);
	}
	
	@PostMapping
	public Area save(@Valid @RequestBody Area area) {
		return areaService.save(area);
	}
	
	@DeleteMapping("{areaId}")
	public ResponseEntity delete(@PathVariable Integer areaId) {
		areaService.delete(areaId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<Area> updateArea(@Valid @RequestBody Area area) {
		Area existingArea = areaService.getOne(area.getId());
		NullPropertiesHandler.myCopyProperties(area, existingArea);
		return new ResponseEntity<Area>(areaService.save(existingArea),HttpStatus.OK);
	}
	
	@GetMapping("{inventoryId}")
	public List<AreaItems> findAreaItemsByInventoryId(@PathVariable Integer inventoryId){
		return areaService.findAreaItemsByInventoryId(inventoryId);
	}
}
