package com.pile.model;

public class PointDomain {

	private String userName;
	private boolean birthDay;
	private long point;
	private int buyNums;
	private int backNums;
	private double buyMoney;
	private double backMoney;
	private int billThisMonth;

	public void recordPointLog(String userName, String type) {
		System.out.println("增加对" + userName + "的类型为" + type + "的积分操作记录");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isBirthDay() {
		return birthDay;
	}

	public void setBirthDay(boolean birthDay) {
		this.birthDay = birthDay;
	}

	public long getPoint() {
		return point;
	}

	public void setPoint(long point) {
		this.point = point;
	}

	public int getBuyNums() {
		return buyNums;
	}

	public void setBuyNums(int buyNums) {
		this.buyNums = buyNums;
	}

	public int getBackNums() {
		return backNums;
	}

	public void setBackNums(int backNums) {
		this.backNums = backNums;
	}

	public double getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(double buyMoney) {
		this.buyMoney = buyMoney;
	}

	public double getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(double backMoney) {
		this.backMoney = backMoney;
	}

	public int getBillThisMonth() {
		return billThisMonth;
	}

	public void setBillThisMonth(int billThisMonth) {
		this.billThisMonth = billThisMonth;
	}

}
