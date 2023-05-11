package com.example.java_demo_test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.java_demo_test.entity.NewMenu;
import com.example.java_demo_test.entity.NewMenu2;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.repository.NewMenu2Dao;
import com.example.java_demo_test.repository.NewMenuDao;
import com.example.java_demo_test.repository.PersonInfoDao;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class NewMenuTest {

	@Autowired
	private NewMenuDao newMenuDao;

	@Autowired
	private NewMenu2Dao newMenu2Dao;

	@Autowired
	private PersonInfoDao pDao;

	@Test
	public void addNewMenuTest() {
		NewMenu nm1 = new NewMenu("beef", "row", 100);
		newMenuDao.save(nm1);
	}

	@Test
	public void addNewMenu2Test() {
		NewMenu2 nm1 = new NewMenu2("beef", "row", 100);
		newMenu2Dao.save(nm1);
	}

	@Test
	public void arrayTest() {
//		String t = "^(?=.*[\\S^\\w])[\\S]{8,12}$";
		String t1 = "^(?=.*[\\p{Punct}])[\\S]{8,12}$";
		String s = "123@45678AA";
		System.out.println(s.matches(t1));
	}

	@Test
	public void updateNameByIdTest() {
		int res = pDao.updateNameById("A001", "Susan"); // ­ì¥»Susan
		System.out.println(res);
	}

	@Test
	public void doQueryByAgeTest() {
		List<PersonInfo> res = pDao.doQueryByAge(25);
		System.out.println(res.size());
	}
}
