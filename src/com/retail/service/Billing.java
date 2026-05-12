package com.retail.service;

import java.util.Scanner;

import com.retail.DAO.DAOLayer;

import java.util.*;

public class Billing {

	public List<BillItem> billingSer() {

	    List<BillItem> billItems = new ArrayList<>();
	    Scanner sc = new Scanner(System.in);
	    
	    while (true) {
	        System.out.print("Enter Product ID: ");
	        int pid = sc.nextInt();

	        System.out.print("Enter Quantity: ");
	        int qty = sc.nextInt();

	        billItems.add(new BillItem(pid, qty));

	        System.out.print("Do you want to add more products? (yes/no): ");
	        String choice = sc.next();

	        if (choice.equalsIgnoreCase("no")) {
	            break;
	        }
	    }

	    return billItems;
	}

}
