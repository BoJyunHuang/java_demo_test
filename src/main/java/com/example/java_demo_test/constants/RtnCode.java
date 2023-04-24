package com.example.java_demo_test.constants;

public enum RtnCode {
	
	SUCCESSFULLY_REPLY ("200", "Successfully reply!"),
	SUCCESSFUL ("204", "Successful!"),
	CANNOT_EMPTY("400","Account or password is empty!"),
	INCORRECT("401"," Incorrect account or password!"),
	NOT_FOUND("404","Not found!"),
	ALREADY_EXISTED("409","Account has already existed!"),
	PATTERNISNOTMATCH("422", "Pattern is not match!");
	
	private String code;
	private String message;
	
	private RtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
