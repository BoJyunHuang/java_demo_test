package com.example.java_demo_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Register;

@Repository
public interface RegisterDao extends JpaRepository<Register, String> {

	public Register findByAccountAndPwd(String account, String pwd);
	
	public Register findByAccountAndPwdAndIsActive(String account, String pwd, boolean isActive);

}
