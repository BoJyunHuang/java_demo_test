package com.example.java_demo_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Login;

@Repository
public interface LoginDao extends JpaRepository<Login, String>{
	
	// 確認帳密
	public Login findByAccountAndPassword(String account,String password);
	
	// 尋找使用者
	public List<Login> findByCityOrderByAgeDesc(String city);
}
