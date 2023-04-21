package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;

public class PersonInfoResponse {

	private String message;
	private PersonInfo personInfo;
	private List<PersonInfo> personInfoList;
	
	public PersonInfoResponse() {
		super();
	}
	
	public PersonInfoResponse(String message) {
		super();
		this.message = message;
	}

	public PersonInfoResponse(PersonInfo personInfo, String message) {
		super();
		this.personInfo = personInfo;
		this.message = message;
	}

	public PersonInfoResponse(List<PersonInfo> personInfoList, String message) {
		super();
		this.personInfoList = personInfoList;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public List<PersonInfo> getPersonInfoList() {
		return personInfoList;
	}

	public void setPersonInfoList(List<PersonInfo> personInfoList) {
		this.personInfoList = personInfoList;
	}
	
}
