package com.example.java_demo_test.entity;

import java.io.Serializable;

public class NewMenu2Id implements Serializable{ // 實行序列化，複合Id用class
	
	private static final long serialVersionUID = 1L; // 自動產生

	private String category; // 雙主key
	
	private String item; // 雙主key

	public NewMenu2Id() {
		super();
	}

	public NewMenu2Id(String category, String item) {
		super();
		this.category = category;
		this.item = item;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	
}
