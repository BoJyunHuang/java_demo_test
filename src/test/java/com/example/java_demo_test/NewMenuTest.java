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
import com.example.java_demo_test.repository.PersonInfoDao;
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
	
	@Autowired
	private PersonInfoDao pDao;
	
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
	public void arrayTest() {
		String t = "^(?=.*[\\S^\\w])[\\S]{8,12}$";
		String t1 = "^(?=.*[\\p{Punct}])[\\S]{8,12}$";
		String s = "123@45678AA";
		System.out.println(s.matches(t1));
	}
	
	@Test
	public void updateNameByIdTest() {
		int res = pDao.updateNameById("A001", "Susan"); // ­ì¥»Susan
		System.out.println(res);
	}
	
}
