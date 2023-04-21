package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonInfoRequest {

	private String message;
	private String str;
	private String id;
	private int age;
	@JsonProperty("another_age")
	private int anotherAge;
	@JsonProperty("person_info")
	private PersonInfo personInfo;
	@JsonProperty("person_info_list") // 小駝峰轉其他命名方式時才需要加
	private List<PersonInfo> personInfoList;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAnotherAge() {
		return anotherAge;
	}

	public void setAnotherAge(int anotherAge) {
		this.anotherAge = anotherAge;
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
