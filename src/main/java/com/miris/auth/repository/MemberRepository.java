package com.miris.auth.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miris.auth.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	@Query("select m from Member m where m.username = :username")
	Member findByName(@Param("username") String username);
}