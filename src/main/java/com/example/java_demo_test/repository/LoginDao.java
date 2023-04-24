package com.example.java_demo_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Login;

@Repository
public interface LoginDao extends JpaRepository<Login, String>{
	
	public List<Login> findByCityOrderByAgeDesc(String city);
}
