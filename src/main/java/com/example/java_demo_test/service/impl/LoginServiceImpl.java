package com.example.java_demo_test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.repository.LoginDao;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	// 帳號長度3~8，不能有空白
	private String accountPattern = "\\S{3,8}";
	// 密碼長度8~12，至少一個特殊符號，不能空白、Tab、定位、換行、換頁等符號
	/*
	 * ^ 表示匹配開始位置，(?=.*[!@#$%^&*+=])判斷式表示必須要存在至少一個特殊符號，?=表式判斷，此小括號不暫字元寬度，僅做判斷
	 * (?!.*\\s)判斷式表示不能存在任何空白、Tab、定位、換行、換頁等符號，$ 表示匹配結束位置
	 */
	private String passwordPattern = "^(?=.*[\\S^\\w])[\\S]{8,12}$";

	@Override
	public LoginResponse register(LoginRequest register) {
		// 防呆
		LoginResponse signUp = signUpCheck(register);
		if (signUp.getRequest() == null) {
			return signUp;
		}
		// 檢查帳號是否已存在
		boolean reop = loginDao.existsById(register.getAccount());
		if (reop) {
			return new LoginResponse(RtnCode.ALREADY_EXISTED.getMessage());
		}
		// 執行註冊，紀錄註冊時間
		Login login = new Login(register.getAccount(), register.getPassword(), register.getName(), register.getAge(),
				register.getCity());
		loginDao.save(login);
		// 隱藏密碼，不顯示
		login.setPassword(null);
		return new LoginResponse(login, RtnCode.SUCCESSFULLY_REPLY.getMessage());
	}

	@Override
	public LoginResponse activate(String account, String password) {
		// 登入確認
		LoginResponse res = signInCheck(account, password);
		if (res.getLogin() == null) {
			return res;
		}
		// 激活帳號
		res.getLogin().setActive(true);
		loginDao.save(res.getLogin());
		// 隱藏密碼，不顯示
		res.getLogin().setPassword(null);
		return new LoginResponse(res.getLogin(), RtnCode.SUCCESSFULLY_REPLY.getMessage());
	}

	@Override
	public LoginResponse signIn(String account, String password) {
		// 登入確認
		LoginResponse res = signInCheck(account, password);
		if (res.getLogin() == null) {
			return res;
		}
		// 確認是否激活
		if (res.getLogin().isActive() != true) {
			return new LoginResponse("帳號未啟用，請啟用帳號!");
		}
		return new LoginResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public LoginResponse findUsersByCity(String city) {
		// 防呆
		if (!StringUtils.hasText(city)) {
			return new LoginResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// 查找資料
		List<Login> res = loginDao.findByCityOrderByAgeDesc(city);
		if (res.isEmpty()) {
			return new LoginResponse(RtnCode.ALREADY_EXISTED.getMessage());
		}
		// 隱蔽部分資訊
		for (Login r : res) {
			r.setPassword(null);
			r.setRegisterTime(null);
		}
		return new LoginResponse(res, RtnCode.SUCCESSFULLY_REPLY.getMessage());
	}

	// 註冊:輸入資料確認
	private LoginResponse signUpCheck(LoginRequest register) {
		// 登入:輸入資料確認
		if (register == null) {
			return new LoginResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// 註冊資料內容確認
		if (!StringUtils.hasText(register.getAccount()) || !StringUtils.hasText(register.getPassword())
				|| !StringUtils.hasText(register.getName()) || register.getAge() < 0
				|| !StringUtils.hasText(register.getCity())) {
			return new LoginResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// 檢查帳號格式
		if (!register.getAccount().matches(accountPattern)) {
			return new LoginResponse(RtnCode.PATTERNISNOTMATCH.getMessage());
		}
		// 檢查密碼格式
		if (!register.getPassword().matches(passwordPattern)) {
			return new LoginResponse(RtnCode.PATTERNISNOTMATCH.getMessage());
		}
		return new LoginResponse(register, RtnCode.SUCCESSFULLY_REPLY.getMessage());
	}

	// 登入檢查
	private LoginResponse signInCheck(String account, String password) {
		// 防呆
		if (!StringUtils.hasText(account) || !StringUtils.hasText(password)) {
			return new LoginResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// 比對帳號密碼
		Login res = loginDao.findByAccountAndPassword(account, password);
		if (res == null) {
			return new LoginResponse(RtnCode.INCORRECT.getMessage());
		}
		return new LoginResponse(res, RtnCode.SUCCESSFULLY_REPLY.getMessage());
	}

}
