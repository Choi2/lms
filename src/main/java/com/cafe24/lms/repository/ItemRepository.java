package com.cafe24.lms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cafe24.lms.domain.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {
	@Query(value="select i from Item i", nativeQuery=false)
	Page<Item> findAll(Pageable pageable); //JPQL
}
