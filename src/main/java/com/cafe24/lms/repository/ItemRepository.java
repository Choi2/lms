package com.cafe24.lms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {
	@Query(value="select i from Item i where i.no > :startNo", nativeQuery=false)
	Page<Item> findAllByStartNo(@Param("startNo") Long startNo, Pageable pageable); //JPQL
}
