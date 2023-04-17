package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class Menu {
	
	@Id
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private int price;

	public Menu(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
