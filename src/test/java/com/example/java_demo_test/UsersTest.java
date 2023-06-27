package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.service.impl.UsersServiceImpl;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class UsersTest {

	@Autowired
	private UsersServiceImpl uI;

	@Test
	public void addBankInfo() {
		uI.addUsers("Yes");
	}

}
