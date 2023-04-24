package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.entity.NewMenu;
import com.example.java_demo_test.entity.NewMenu2;
import com.example.java_demo_test.repository.LoginDao;
import com.example.java_demo_test.repository.NewMenu2Dao;
import com.example.java_demo_test.repository.NewMenuDao;
import com.example.java_demo_test.service.ifs.LoginService;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class NewMenuTest {
	
	@Autowired
	private NewMenuDao newMenuDao;
	
	@Autowired
	private NewMenu2Dao newMenu2Dao;
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private LoginService loginService;
	
	@Test
	public void addNewMenuTest() {
		NewMenu nm1 = new NewMenu("beef","row",100);
		newMenuDao.save(nm1);
	}
	
	@Test
	public void addNewMenu2Test() {
		NewMenu2 nm1 = new NewMenu2("beef","row",100);
		newMenu2Dao.save(nm1);
	}
	
	@Test
	public void registerTest() {
		Login l = new Login("TK00001","00000000","³¯§µ¶¯",32,"°ª¶¯");
		loginService.register(l);
	}
	
	@Test
	public void arrayTest() {
		String t = "^(?=.*[!@#$%^&*+=])(?!.*\\s)[\\S]{8,12}$";
		String s = "12345(678AA";
		System.out.println(s.matches(t));
	}
	
}
