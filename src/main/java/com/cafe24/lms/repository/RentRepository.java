package com.cafe24.lms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Rent;

public interface RentRepository extends JpaRepository<Rent, Long> {
	
	@Query(value="select r from Rent r where r.no > :startNo", nativeQuery=false)
	Page<Rent> findAllByStartNo(@Param("startNo") Long startNo, Pageable pageable); //JPQL
}
