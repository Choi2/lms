package com.cafe24.lms.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.RentRepository;
import com.cafe24.lms.repository.ReserveRepository;
import com.cafe24.lms.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RentRepository rentRepository;
	
	@Autowired
	private ReserveRepository reserveRepository;
	
	public void join(User user) {
		user.setJoinDate(new Date());
		userRepository.save(user);
	}
	
	public User getUser(User user) {
		return userRepository.findAllByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	public boolean modifyUser(User user) {
		userRepository.save(user);
		return true;
	}

	public List<Integer> checkRent(User user) {
		List<Integer> list = rentRepository.findByUserNo(user.getNo());
		return list;
	}
	
	public List<Integer> checkReserve(User user) {
		List<Integer> list = reserveRepository.findByUserNo(user.getNo());
		System.out.println(list);
		return list;
	}

}
