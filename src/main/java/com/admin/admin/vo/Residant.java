package com.admin.admin.vo;

public class Residant {
	private Long residentId;
	 private String residantName ;
	 private String discription;

	

	public String getResidantName() {
		return residantName;
	}

	public void setResidantName(String residantName) {
		this.residantName = residantName;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Residant() {
		super();
	}
	public Residant(String residantName, String discription) {
		super();
		this.residantName = residantName;
		this.discription = discription;
	}
	
	 
}
