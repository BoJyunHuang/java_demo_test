package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.vo.LoginResponse;

public interface LoginService {

	// 註冊帳號
	public LoginResponse register(Login login);

	// 激活帳號
	public LoginResponse activate(Login login);

	// 登入(檢查帳號、密碼、帳號是否激活)
	public LoginResponse signIn(Login login);

	// 以城市為蒐尋線索找尋使用者，回傳不能含密碼，含排序依照年齡
	public LoginResponse findUsersByCity(String city);
}
