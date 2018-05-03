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
import com.cafe24.lms.domain.Reserve;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.repository.ReserveRepository;

@Service
@Transactional
public class ReserveService {

	@Autowired
	private ReserveRepository reserveRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	private Pager pager;

	public Pager getPager() {
		return pager;
	}
	
	public Page<Reserve> getAllReserve(Long page) {
		
		pager = new Pager();
		pager.setPage(page);
		pager.setTotalCount(reserveRepository.count());
		pager.calculate(pager.getPage());
		
		if(page == 0) {page = 1L;}
		
		PageRequest pageRequest = new PageRequest((int) (page - 1), 5, new Sort(Direction.DESC, "no"));
		Page<Reserve> list = reserveRepository.findAll(pageRequest);
		
		return list;
	}

	public void addReverse(Long itemNo, User user) {
		
		Reserve reserve = reserveRepository.findByItemNoWithMaxReturndate(itemNo);
		Calendar cal = Calendar.getInstance();
		
		if(reserve == null) {
			reserve = new Reserve();
			Item item = itemRepository.findOne(itemNo);
			reserve.setItem(item);
			cal.add(Calendar.DAY_OF_MONTH, 7);
		} else {
			reserve.setNo(reserve.getNo() + 1);
			cal.setTime(reserve.getReturnDate());
		}
		
		reserve.setUser(user);
		reserve.setReserveRank(reserve.getReserveRank() + 1);
		
	
		reserve.setBorrowDate(cal.getTime());	
		cal.add(Calendar.DAY_OF_MONTH, 7);
		reserve.setReturnDate(cal.getTime());
		
		reserveRepository.save(reserve);
	} //사용자 예약
}
