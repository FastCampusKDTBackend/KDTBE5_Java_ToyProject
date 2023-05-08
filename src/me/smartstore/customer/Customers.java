package me.smartstore.customer;

import me.smartstore.array.Array;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;

public class Customers extends Array<Customer> {
	private static Customers allCustomers;
	private static Groups allGroups = Groups.getInstance();

	private Customers() {}

	public static Customers getInstance() {
		if (allCustomers == null) {
			allCustomers = new Customers();
		}

		return allCustomers;
	}

	public void refresh() {
		for (int i = 0; i < allCustomers.size(); i++) {
			Customer customer = allCustomers.get(i);

			for (int j = 0; j < allGroups.size(); j++) {
				Group group = allGroups.get(j);

				if (customer.getUseHours() >= group.getMinHours() && customer.getCustomerPay() >= group.getMinPay()) {
					customer.setCustomerGroup(group);
				}
			}
		}
	}
}
