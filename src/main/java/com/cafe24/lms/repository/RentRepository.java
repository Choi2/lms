package com.cafe24.lms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Rent;

public interface RentRepository extends JpaRepository<Rent, Long> {
	
	@Query(value="select r from Rent r", nativeQuery=false)
	Page<Rent> findAll(Pageable pageable); //JPQL

	@Query(value="select r.item.no from Rent r where r.user.no = :userNo", nativeQuery=false)
	List<Integer> findByUserNo(@Param("userNo")Long userNo);
}
