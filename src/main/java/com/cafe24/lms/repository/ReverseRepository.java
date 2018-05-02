package com.cafe24.lms.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Reverse;

public interface ReverseRepository extends JpaRepository<Reverse, Long> {
	@Query(value="select r from Reverse r where r.no > :startNo", nativeQuery=false)
	Page<Reverse> findAllByStartNo(@Param("startNo") Long startNo, Pageable pageable); //JPQL


	@Modifying
	@Query(value="INSERT INTO rent VALUES ("
			+ "null, :itemNo, :userNo, :borrowDate, :returnDate) ON DUPLICATE KEY "
			+ "UPDATE :reverseRank", nativeQuery=true)
	int save(@Param("itemNo") Long itemNo,
			 @Param("userNo") Long userNo,
			 @Param("borrowDate") Date borrowDate,
			 @Param("returnDate") Date returnDate,
			 @Param("reverseRank") int reverseRank);

	
	@Query(value="select r "
			+ "	  from Reverse r "
			+ "   where r.item.no = :itemNo "
			+ "   and r.returnDate = (select max(r.returnDate) from Reverse r where r.item.no = :itemNo)")
	Reverse findByItemNoWithMaxReturndate(@Param("itemNo") Long itemNo);

		
}
