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

	public Page<Item> getAllItem(Pager pager) {
		
		Page<Item> list = null;
		pager.setPage(pager.getPage());
		PageRequest pageRequest = new PageRequest((int)(pager.getPage() - 1), 5, new Sort(Direction.DESC, "no"));
		
		if(pager.getWord() == null || pager.getWord().equals("") ) {
			list = itemRepository.findAll(pageRequest);
			pager.setTotalCount(itemRepository.count());
		} else {
			
			list = itemRepository.findAllWithWord(pager.getWord(), pageRequest);
			pager.setTotalCount((long) (list.getTotalPages() * 5));
		}
		
		pager.calculate(pager.getPage());
		return list;
	}
	
}
