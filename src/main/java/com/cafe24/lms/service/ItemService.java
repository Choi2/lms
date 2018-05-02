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

	public Page<Item> getAllItem(Long page) {
		PageRequest pageRequest = new PageRequest(0, 5, new Sort(Direction.DESC, "no"));
		Page<Item> list = itemRepository.findAllByStartNo(page, pageRequest);
		pager = new Pager();
		pager.setPage(page);
		pager.setTotalCount(list.getTotalPages());
		pager.calculate(pager.getPage());
		return list;
	}
	
}
