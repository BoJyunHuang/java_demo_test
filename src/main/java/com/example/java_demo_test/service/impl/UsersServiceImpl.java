package com.example.java_demo_test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_demo_test.entity.Users;
import com.example.java_demo_test.repository.UsersDao;

@Service
public class UsersServiceImpl {

	@Autowired
	private UsersDao usersDao;

	public String addUsers(String name) {
		Users i = new Users();
		 Integer x = usersDao.getCurrentId();
		i.setName(name + "_" + x);
		usersDao.save(i);
		return "Success";
	}

}
