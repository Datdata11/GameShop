package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;
import com.poly.entity.Authority;

public interface AuthorityDAO extends JpaRepository<Authority, Integer> {

	List<Authority> findByAccount(Account account);

	@Query(value = "INSERT INTO authority (username, role_id) VALUES ( ?1 , ?2 )", nativeQuery = true)
	Authority save(String username, String role);

}
