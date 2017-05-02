package com.xsx.ncd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsx.ncd.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByAccountAndPassword(String account, String password);
	
	@Query("select u from User u where u.account != :account")
	public List<User> readAllUserButOneUser(@Param("account")String account);
	
	@Query("select u.id from User u where u.account = :account")
	public Integer readUserIdByAccount(@Param("account")String account);
}
