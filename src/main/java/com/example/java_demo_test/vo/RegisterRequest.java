package com.example.java_demo_test.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {

	private String account;

	private String pwd;
	
	@JsonProperty("verify_code")
	private int veryfyCode;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getVeryfyCode() {
		return veryfyCode;
	}

	public void setVeryfyCode(int veryfyCode) {
		this.veryfyCode = veryfyCode;
	}

}
