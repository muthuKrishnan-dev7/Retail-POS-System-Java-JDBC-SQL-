package com.retail.service;

public class BillSummary {
	private double total;
	private String mode;
	
	public BillSummary(double total, String mode) {
		this.total = total;
		this.mode = mode;
	}
	
	public double getTotal() {
		return total;
	}
	public String getMode() {
		return mode;
	}
	
}
