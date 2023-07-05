package com.example.java_demo_test.service.impl;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.repository.RegisterDao;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@EnableScheduling
@Service
public class RegisterServiceImpl implements RegisterService {

	// 取用logger，不需要Autowired，因為在resource
	private Logger logger = LoggerFactory.getLogger(getClass()); // slf4j
	
	@Autowired
	private RegisterDao registerDao;
	
	@Override
	public RegisterResponse register(String account, String pwd) throws Exception{
		// 防呆
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			logger.error("rigister error!");
			throw new Exception(RtnCode.CANNOT_EMPTY.getCode() + RtnCode.CANNOT_EMPTY.getMessage());
//			return new RegisterResponse("incorrect");
		}
		// 檢查帳號是否已存在
		if (registerDao.existsById(account)) {
			return new RegisterResponse("incorrect");
		}
		// 執行註冊，紀錄註冊時間
		registerDao.save(new Register(account, pwd));
		return new RegisterResponse("success");
	}

	@Override
	public RegisterResponse avtive(String account, String pwd) {
		// 防呆
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("incorrect");
		}
		// 檢查帳號是否已存在
		Register res = registerDao.findByAccountAndPwd(account, pwd);
		if (res == null) {
			return new RegisterResponse("not found");
		}
		// 啟用帳號
		res.setActive(true);
		registerDao.save(res);
		return new RegisterResponse("success");
	}

	@Override
	public RegisterResponse login(String account, String pwd) {
		// 防呆
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("incorrect");
		}
		// 檢查帳號是否已存在(啟用)
		Register res = registerDao.findByAccountAndPwdAndIsActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("login failed");
		}
		return new RegisterResponse("success");
	}
	
	@Cacheable(value="account", key="#account")
	@Override
	public RegisterResponse getRegTime(String account, String pwd) {
		// 檢查帳號是否已存在(啟用)
		Register res = registerDao.findByAccountAndPwdAndIsActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("login failed");
		}
		// 回傳註冊時間
		return new RegisterResponse(res.getRegTime(), "success");
	}

	@Override
	public RegisterResponse getRegTime2(RegisterRequest request, String account, String pwd, Integer verifyCode) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("Please login");
		}
		if (verifyCode == null || verifyCode != request.getVeryfyCode()) {
			return new RegisterResponse("Verify code incorrect");
		}
		// 檢查帳號是否已存在(啟用)
		Register res = registerDao.findByAccountAndPwdAndIsActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("login failed");
		}
		// 回傳註冊時間
		return new RegisterResponse(res.getRegTime(), "success");
	}

	@Scheduled(cron = "0 * 14-16 * * *")
	public void scheduleTest() {
		System.out.println("now:" + LocalTime.now());
	}

}
