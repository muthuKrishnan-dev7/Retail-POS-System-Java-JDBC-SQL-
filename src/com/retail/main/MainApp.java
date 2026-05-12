package com.retail.main;

import java.sql.SQLException;
import java.util.List;

import com.retail.DAO.DAOLayer;
import com.retail.service.BillItem;
import com.retail.service.BillPrint;
import com.retail.service.BillSummary;
import com.retail.service.Billing;

public class MainApp {

	static void show() {
		System.out.println("=== Welcome to Our Billing System ===\n");
	}

	public static void main(String[] args) throws SQLException {

		DAOLayer daoLayer = new DAOLayer();
		Billing billing = new Billing();
		BillPrint bill = new BillPrint();

		show(); // Show MethodCall ---
		daoLayer.Authentication(); // Method for Authentication ---

		daoLayer.productShow(); // Product table MethodCall ---

		List<BillItem> items = billing.billingSer(); // Billing MethodCall ---
		BillSummary BillSum = bill.printBill(items);

		for (BillItem item : items) {
			daoLayer.setStock(item.getProductId(), item.getProductQty()); // SetStock MethodCall ---
		}

		int billId = daoLayer.BillTable(BillSum.getTotal(), BillSum.getMode()); // Bill table insert method + get Bill
																				// Id ---
		daoLayer.BillProduct(items, billId); // Method to store Bill Items ---
	}
}