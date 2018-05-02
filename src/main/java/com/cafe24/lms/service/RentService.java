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
import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.domain.Reverse;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.repository.RentRepository;
import com.cafe24.lms.repository.ReverseRepository;

@Service
@Transactional
public class RentService {
	
	@Autowired
	private RentRepository rentRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ReverseRepository reverseRepository;
	
	private Pager pager;

	public Pager getPager() {
		return pager;
	}
	
	public Page<Rent> getAllRent(Long page) {
		PageRequest pageRequest = new PageRequest(0, 5, new Sort(Direction.DESC, "no"));
		Page<Rent> list = rentRepository.findAllByStartNo(page, pageRequest);
		pager = new Pager();
		pager.setPage(page);
		pager.setTotalCount(list.getTotalPages());
		pager.calculate(pager.getPage());
		return list;
	}

	public void addRent(Long itemNo, User user) {
		
		Rent rent = new Rent();
		
		rent.setUser(user);
		
		Item item = itemRepository.findOne(itemNo);
		item.setIsRent(true);
		itemRepository.save(item); 
		rent.setItem(item); //이 상품은 이미 대여가 되었다는 것을 갱신하고 저장
	
		Calendar cal = Calendar.getInstance();
		rent.setBorrowDate(cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, 7);
		rent.setReturnDate(cal.getTime()); 
		rentRepository.save(rent); // 날짜 세팅 후 저장
		
	} //사용자 대여

}
