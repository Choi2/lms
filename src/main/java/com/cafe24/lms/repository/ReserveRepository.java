package com.cafe24.lms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Reserve;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
	
	@Query(value="select r from Reserve r", nativeQuery=false)
	Page<Reserve> findAll(Pageable pageable); //JPQL


	@Query(value="select r "
			+ "	  from Reserve r "
			+ "   where r.item.no = :itemNo "
			+ "   and r.returnDate = (select max(r.returnDate) from Reserve r where r.item.no = :itemNo)")
	Reserve findByItemNoWithMaxReturndate(@Param("itemNo") Long itemNo);

	@Query(value="select r.item.no from Reserve r where r.user.no = :userNo", nativeQuery=false)
	List<Integer> findByUserNo(@Param("userNo") Long userNo);

		
}
