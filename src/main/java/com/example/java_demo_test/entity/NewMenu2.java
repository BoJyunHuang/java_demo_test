package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "new_menu2")
@IdClass(value = NewMenu2Id.class) // ©I¥sÂù¥Dkeyªºclass
public class NewMenu2 {

	@Id
	@Column(name = "category")
	private String category;
	@Id
	@Column(name = "item")
	private String item;

	@Column(name = "price")
	private int price;

	public NewMenu2() {
		super();
	}

	public NewMenu2(String category, String item, int price) {
		super();
		this.category = category;
		this.item = item;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
