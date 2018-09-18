package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GoodsShop {

	public GoodsShop(){}

	@Id
	private int id;
	private String name;//名称
	private String number;//数量
	private String describes;//描述
	private String  money;//金额
	private String beatowal;//赠与
	private int userid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescribe() {
		return describes;
	}

	public void setDescribe(String describe) {
		this.describes = describe;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getBeatowal() {
		return beatowal;
	}

	public void setBeatowal(String beatowal) {
		this.beatowal = beatowal;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}
