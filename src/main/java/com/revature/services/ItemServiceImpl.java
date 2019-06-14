package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return itemRepository.findAll();
	}

	@Override
	public List<Item> findByItem(String item) {
		// TODO Auto-generated method stub
		return itemRepository.findByItem(item);
	}

	@Override
	public List<Item> findByArea(String area) {
		// TODO Auto-generated method stub
		return itemRepository.findByAreaArea(area);
	}

	@Override
	public Item findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Item> res = itemRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		} else {
			return null;
		}
	}

	@Override
	public Item save(Item item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}

	@Override
	public void delete(Item item) {
		// TODO Auto-generated method stub
		itemRepository.delete(item);
	}

}
