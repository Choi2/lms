package com.cafe24.lms.service;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Pager;
import com.cafe24.lms.domain.Reverse;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.repository.ReverseRepository;

@Service
@Transactional
public class ReverseService {

	@Autowired
	private ReverseRepository reverseRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	private Pager pager;

	public Pager getPager() {
		return pager;
	}
	
	public Page<Reverse> getAllRent(Long page) {
		PageRequest pageRequest = new PageRequest(0, 5, new Sort(Direction.DESC, "no"));
		Page<Reverse> list = reverseRepository.findAllByStartNo(page, pageRequest);
		pager = new Pager();
		pager.setPage(page);
		pager.setTotalCount(list.getTotalPages());
		pager.calculate(pager.getPage());
		return list;
	}

	public void addReverse(Long itemNo, User user) {
		
		Reverse reverse = reverseRepository.findByItemNoWithMaxReturndate(itemNo);
		
		System.out.println(reverse);
		
		if(reverse == null) {
			reverse = new Reverse();
			Item item = itemRepository.findOne(itemNo);
			reverse.setItem(item);			
		}
		
		reverse.setUser(user);
		reverse.setReverseRank(reverse.getReverseRank() + 1);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(reverse.getReturnDate());
		reverse.setBorrowDate(cal.getTime());	
		cal.add(Calendar.DAY_OF_MONTH, 7);
		reverse.setReturnDate(cal.getTime());
		
		reverseRepository.save(reverse);
	} //사용자 예약
}
