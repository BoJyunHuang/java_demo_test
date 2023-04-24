package com.example.java_demo_test.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.repository.LoginDao;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginResponse;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	// 帳號長度3~8，不能有空白
	private String accountPattern = "\\S{3,8}";
	// 密碼長度8~12，至少一個特殊符號，不能空白、Tab、定位、換行、換頁等符號
	/*
	 * ^ 表示匹配開始位置
	 * (?=.*[!@#$%^&*+=])判斷式表示必須要存在至少一個特殊符號，?=表式判斷，此小括號不暫字元寬度，僅做判斷
	 * (?!.*\\s)判斷式表示不能存在任何空白、Tab、定位、換行、換頁等符號
	 * $ 表示匹配結束位置
	 */
	private String passwordPattern = "^(?=.*[!@#$%^&*+=])(?!.*\\s)[\\S]{8,12}$";

	@Override
	public LoginResponse register(Login login) {
		// 防呆
		LoginResponse signUp = signUpCheck(login);
		if (signUp.getLogin() == null) {
			return signUp;
		}
		// 檢查帳號是否已存在
		Optional<Login> acop = loginDao.findById(login.getAccount());
		if (acop.isPresent()) {
			return new LoginResponse("帳號已存在!");
		}
		// 執行註冊，紀錄註冊時間
		login.setRegisterTime(LocalDateTime.now());
		loginDao.save(login);
		return new LoginResponse(hidePassword(login), "帳號註冊成功!");
	}

	@Override
	public LoginResponse activate(Login login) {
		// 防呆
		LoginResponse signUp = signInCheck(login);
		if (signUp.getLogin() == null) {
			return signUp;
		}
		// 比對帳號密碼
		LoginResponse res = accountAndPasswordCheck(login);
		if (res.getLogin() == null) {
			return res;
		}
		// 激活帳號
		res.getLogin().setActive(true);
		loginDao.save(res.getLogin());
		return new LoginResponse(hidePassword(login), "帳號啟用成功!");
	}

	@Override
	public LoginResponse signIn(Login login) {
		// 防呆
		LoginResponse signUp = signInCheck(login);
		if (signUp.getLogin() == null) {
			return signUp;
		}
		// 比對帳號密碼
		LoginResponse res = accountAndPasswordCheck(login);
		if (res.getLogin() == null) {
			return res;
		}
		// 確認是否激活
		if (res.getLogin().isActive() != true) {
			return new LoginResponse("帳號未啟用，請啟用帳號!");
		}
		return new LoginResponse("登入成功!");
	}

	@Override
	public LoginResponse findUsersByCity(String city) {
		// 防呆
		if (!StringUtils.hasText(city)) {
			return new LoginResponse("查詢城市不能為空!");
		}
		// 查找資料
		List<Login> res = loginDao.findByCityOrderByAgeDesc(city);
		if (res.isEmpty()) {
			return new LoginResponse("查無資料!");
		}
		// 隱蔽部分資訊
		for (Login r:res) {
			r.setPassword("********");
			r.setRegisterTime(null);
		}
		return new LoginResponse(res, "查詢成功!");
	}

	// 註冊:輸入資料確認
	private LoginResponse signUpCheck(Login login) {
		// 輸入資料為空
		if (login == null) {
			return new LoginResponse("輸入資料不能為空!");
		}
		// 輸入內容為空
		if (login.getAccount().isEmpty() || login.getPassword().isEmpty() || login.getName().isEmpty()
				|| login.getAge() < 0 || login.getCity().isEmpty()) {
			return new LoginResponse("輸入資料內容空白!");
		}
		// 檢查帳號格式
		if (!login.getAccount().matches(accountPattern)) {
			return new LoginResponse("輸入帳號不符格式!");
		}
		// 檢查密碼格式
		if (!login.getPassword().matches(passwordPattern)) {
			return new LoginResponse("輸入密碼不符格式!");
		}
		return new LoginResponse(login, "資料通過檢查!");
	}

	// 登入:輸入資料確認
	private LoginResponse signInCheck(Login login) {
		// 輸入資料為空
		if (login == null) {
			return new LoginResponse("輸入資料不能為空!");
		}
		// 輸入內容為空
		if (login.getAccount().isEmpty() || login.getPassword().isEmpty()) {
			return new LoginResponse("輸入資料內容空白!");
		}
		// 檢查帳號格式
		if (!login.getAccount().matches(accountPattern)) {
			return new LoginResponse("輸入帳號不符格式!");
		}
		// 檢查密碼格式
		if (!login.getPassword().matches(passwordPattern)) {
			return new LoginResponse("輸入密碼不符格式!");
		}
		return new LoginResponse(login, "資料通過檢查!");
	}

	// 檢查帳號密碼
	private LoginResponse accountAndPasswordCheck(Login login) {
		Optional<Login> acop = loginDao.findById(login.getAccount());
		if (!acop.isPresent()) {
			return new LoginResponse("無此帳號!");
		}
		if (!acop.get().getPassword().equals(login.getPassword())) {
			return new LoginResponse("密碼錯誤!");
		}
		return new LoginResponse(acop.get(), "帳號密碼正確!");
	}
	
	// 遮蔽密碼
	private Login hidePassword(Login login) {
		Login loginInfo = login;
		loginInfo.setPassword("********");
		return loginInfo;
	}

}
