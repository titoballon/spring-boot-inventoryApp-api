package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ApiException;
import com.revature.models.Item;
import com.revature.repositories.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{
	
	private ItemRepository itemRepository;
	
	@Autowired
	public ItemServiceImpl(ItemRepository i) {
		this.itemRepository = i;
	}

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		List<Item> allItems = itemRepository.findAll();
		if(allItems.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No items found");
		return allItems;
	}

	@Override
	public List<Item> findByName(String name) {
		// TODO Auto-generated method stub
		List<Item> items = itemRepository.findByName(name);
		if(items.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No items found");
		return items;
	}

	@Override
	public Item findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Item> res = itemRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, "item not found");
		}
	}
	
	@Override
	public List<Item> findByAreaId(Integer id) {
		// TODO Auto-generated method stub
		return itemRepository.findByAreaId(id);
	}

	@Override
	public Item save(Item item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}

	@Override
	public void delete(Integer itemId) {
		// TODO Auto-generated method stub
		itemRepository.deleteById(itemId);
	}

}
