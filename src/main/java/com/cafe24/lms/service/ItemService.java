package com.cafe24.lms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Pager;
import com.cafe24.lms.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	private Pager pager;
	
	public Pager getPager() {
		return pager;
	}

	public Page<Item> getAllItem(int page) {
		
		pager = new Pager();
		pager.setPage(page);
		pager.setTotalCount(itemRepository.count());
		pager.calculate(pager.getPage());
		
		if(page == 0) {page = 1;}
		
		PageRequest pageRequest = new PageRequest(page - 1, 5, new Sort(Direction.DESC, "no"));
		Page<Item> list = itemRepository.findAll(pageRequest);
		return list;
	}
	
}
