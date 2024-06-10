package com.miris.auth.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miris.auth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select m from User m where m.username = :username")
	User findByName(@Param("username") String username);
}