package com.retail.service;

import java.util.List;
import java.util.Scanner;

import com.retail.DAO.DAOLayer;
import com.retail.product.Product;

public class BillPrint {

	static Scanner sc = new Scanner(System.in);

	public BillSummary printBill(List<BillItem> billItems) {

		DAOLayer dao = new DAOLayer();
		double total = 0;
		String mode = null;

		System.out.println("------- BILL -------");

		for (BillItem item : billItems) {

			int pid = item.getProductId();
			int qty = item.getProductQty();

			Product p = dao.getProductById(pid);

			if (p != null) {
				double amount = p.getPrice() * qty;
				total += amount;
				System.out.println(
						p.getProductName() + " | Qty: " + qty + " | Price: " + p.getPrice() + " | Amount: " + amount);
			} else {
				System.out.println("Product ID " + pid + " not found!");
				break;
			}
		}

		System.out.println("--------------------");
		System.out.println("TOTAL: " + total);

		System.out.println("\nEnter the payment method: (Cash / UPI)");
		System.out.print("Enter: ");
		mode = sc.next();
		if (mode.equalsIgnoreCase("cash") || mode.equalsIgnoreCase("upi")) {
			System.out.println("\n====== ThankYou For Purchase ======");
		}

		return new BillSummary(total, mode);
	}
}
