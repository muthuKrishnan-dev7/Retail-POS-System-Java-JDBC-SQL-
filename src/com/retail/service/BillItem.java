package com.retail.service;

public class BillItem {
	private int productId;
	private int productQty;
	
	public BillItem(int productId, int productQty) {
		this.productId = productId;
		this.productQty = productQty;
	}

	public int getProductId() {
		return productId;
	}

	public int getProductQty() {
		return productQty;
	}

}