package me.smartstore.menu;

import me.smartstore.customer.Customers;

public class CustomerMenu implements Menu{
	private static Customers allCustomers = Customers.getInstance();

	private static CustomerMenu customerMenu;

	private CustomerMenu() {

	}

	public static CustomerMenu getInstance() {
		if (customerMenu == null) {
			customerMenu = new CustomerMenu();
		}

		return customerMenu;
	}


	@Override
	public void show() {
		while (true) {
			int choice = chooseMenu(new String[]{
				"Add Customer Data",
				"View Customer Data",
				"Update Customer Data",
				"Delete Customer Data",
				"Back"
			});

			if (choice == 1) {

			} else if (choice == 2) {

			} else if (choice == 3) {

			} else if (choice == 4) {

			} else if (choice == 5) {
				break;
			}
		}
	}
}
