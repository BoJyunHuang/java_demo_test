package com.example.java_demo_test.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponse {

	private String message;
	
	private LocalDateTime regTime;

	@JsonProperty("session_id")
	private String SessionId;
	
	@JsonProperty("verify_code")
	private int verifyCode;
	
	public RegisterResponse() {
		super();
	}

	public RegisterResponse(String message) {
		super();
		this.message = message;
	}

	public RegisterResponse(LocalDateTime regTime, String message) {
		super();
		this.regTime = regTime;
		this.message = message;
	}

	public RegisterResponse(String sessionId, int verifyCode, String message) {
		super();
		SessionId = sessionId;
		this.verifyCode = verifyCode;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

	public String getSessionId() {
		return SessionId;
	}

	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}

	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}

}
