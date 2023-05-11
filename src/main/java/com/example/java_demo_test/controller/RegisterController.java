package com.example.java_demo_test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@RestController
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@PostMapping(value = "register")
	public RegisterResponse register(@RequestBody RegisterRequest request) {
		return registerService.register(request.getAccount(), request.getPwd());
	}

	@GetMapping(value = "active")
	public RegisterResponse active(@RequestBody RegisterRequest request) {
		return registerService.avtive(request.getAccount(), request.getPwd());
	}

	@GetMapping(value = "login")
	public RegisterResponse login(@RequestBody RegisterRequest request, HttpSession session) {
		RegisterResponse res = registerService.login(request.getAccount(), request.getPwd());
		if (res.getMessage().equalsIgnoreCase("success")) {
			double random = Math.random() * 10000;
			int verifyCode = (int) Math.round(random);
			session.setAttribute("verifyCode", verifyCode);
			session.setAttribute("account", request.getAccount());
			session.setAttribute("pwd", request.getPwd());
			session.setMaxInactiveInterval(300); // ³æ¦ì¬O¬í
			res.setSessionId(session.getId());
			res.setVerifyCode(verifyCode);
		}
		return res;
	}

	@GetMapping(value = "get_reg_time")
	public RegisterResponse getRegTime(@RequestBody RegisterRequest request, HttpSession session) {
		String account = (String) session.getAttribute("account");
		String pwd = (String) session.getAttribute("pwd");
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("Please login");
		}
		Integer verifyCode = (Integer) session.getAttribute("verifyCode");
		if (verifyCode == null || verifyCode != request.getVeryfyCode()) {
			return new RegisterResponse("Verify code incorrect");
		}
		return registerService.getRegTime(account, pwd);
	}

	@GetMapping(value = "get_reg_time2")
	public RegisterResponse getRegTime2(@RequestBody RegisterRequest request, HttpSession session) {
		String account = (String) session.getAttribute("account");
		String pwd = (String) session.getAttribute("pwd");
		Integer verifyCode = (Integer) session.getAttribute("verifyCode");
		return registerService.getRegTime2(request, account, pwd, verifyCode);
	}

}
