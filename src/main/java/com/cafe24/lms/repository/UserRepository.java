package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafe24.lms.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findAllByEmailAndPassword(String email, String password);
	/*User findAllByEmailAndPassword(User user);*/
	User getByEmail(String email); 	
	
	@Modifying //벌크성 update, delete Query
	@Query(value="update User u set u.name=:#{#user.name} where u.no=:no", nativeQuery=true)
	int update(@Param("user") String name, @Param("no") Long no);
}

