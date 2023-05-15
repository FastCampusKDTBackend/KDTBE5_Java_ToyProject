package me.smartstore;

import me.smartstore.domain.customer.Customer;
import me.smartstore.domain.customer.Customers;
import me.smartstore.domain.group.*;
import me.smartstore.constant.GroupType;
import me.smartstore.menu.CustomerMenu;
import me.smartstore.menu.GroupMenu;
import me.smartstore.menu.Menu;
import me.smartstore.menu.SummaryMenu;

public class SmartStoreApplication {
	private static SmartStoreApplication smartStoreApp;
	
	public static SmartStoreApplication getInstance() {
		if (smartStoreApp == null)
			smartStoreApp = new SmartStoreApplication();
		return smartStoreApp;
	}
	
	private final Groups allGroups = Groups.getInstance();
	
	private final Customers allCustomers = Customers.getInstance();
	
	private final Menu menu = Menu.getInstance();  
	
	private final CustomerMenu customerMenu = CustomerMenu.getInstance();
	
	private final GroupMenu groupMenu = GroupMenu.getInstance();
	
	private final SummaryMenu classifiedMenu = SummaryMenu.getInstance();
	
	public SmartStoreApplication test() {
		this.allGroups.add(new Group(GroupType.GENERAL, new Parameter(Integer.valueOf(10), Integer.valueOf(100000))));
		this.allGroups.add(new Group(GroupType.VIP, new Parameter(Integer.valueOf(20), Integer.valueOf(200000))));
		this.allGroups.add(new Group(GroupType.VVIP, new Parameter(Integer.valueOf(30), Integer.valueOf(300000))));
		for (int i = 0; i < 20; i++)
			this.allCustomers.add(new Customer(
					Character.toString((char)(97 + i)), "" + (char)(97 + i) + "123", ((int)(Math.random() * 5.0D) + 1) * 10, ((int)(Math.random() * 5.0D) + 1) * 100000)); 
		this.allCustomers.refresh(this.allGroups);
		return this;
		}
	  
	private void details() {
		System.out.println("\n\n===========================================");
	    System.out.println(" Title : SmartStore Customer Segmentation");
	    System.out.println(" Release Date : 23.04.24");
	    System.out.println(" Copyright 2023 Eunbin All rights reserved.");
	    System.out.println("===========================================\n");
	}
	
	public void run() {
		details();
		while (true) {
			int choice = this.menu.dispMenus(new String[] { "Parameter", "Customer Data", "Classification Summary", "Quit" });
			if (choice == 1) {
				this.groupMenu.manageParameterMenu();
				continue;
				} 
			if (choice == 2) {
				this.customerMenu.manageCustomerMenu();
				continue;
				} 
			if (choice == 3) {
				this.classifiedMenu.manageSummaryMenu();
				continue;
				} 
			if (choice == 4) {
				System.out.println("\nProgram Finished.");
				return;
			}
		}
	}
	
}
