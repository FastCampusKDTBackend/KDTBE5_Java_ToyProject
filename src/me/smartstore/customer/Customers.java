package me.smartstore.customer;

import java.util.Arrays;
import java.util.Comparator;

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

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < size(); i++) {
			output.append("No. " + (i + 1) + " => ");
			output.append(get(i) + "\n");
		}

		return output.toString();
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

	public void refresh(Customer customer) {
		for (int i = 0; i < allGroups.size(); i++) {
			Group group = allGroups.get(i);

			if (customer.getUseHours() >= group.getMinHours() && customer.getCustomerPay() >= group.getMinPay()) {
				customer.setCustomerGroup(group);
			}
		}
	}

	public Customers findByGroup(Group group) {
		Customers findCustomres = new Customers();

		for (int i = 0; i < size(); i++) {
			if (get(i).getCustomerGroup().equals(group)) {
				findCustomres.add(get(i));
			}
		}

		return findCustomres;
	}

	public Customer[] parseToArray(Customers customers) {
		Customer[] array = new Customer[customers.size()];

		for (int i = 0; i < customers.size(); i++) {
			array[i] = customers.get(i);
		}

		return array;
	}

	public Customers sort(Customers customers, Comparator<Customer> comparator, String type) {
		Customer[] customerArray = parseToArray(customers);

		if (type.equals("A") || type.equals("ASCENDING")) {
			customerArray = Arrays.stream(customerArray).sorted(comparator).toArray(Customer[]::new);
		} else {
			customerArray = Arrays.stream(customerArray).sorted(comparator.reversed())
				.toArray(Customer[]::new);
		}
		customers.arrays = customerArray;

		return customers;
	}

	public Comparator<Customer> comparatorByName() {
		Comparator<Customer> comparator = new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				return o1.getCustomerName().compareTo(o2.getCustomerName());
			}
		};

		return comparator;
	}

	public Comparator<Customer> comparatorByTime() {
		Comparator<Customer> comparator = new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				int returnValue = o1.getUseHours() - o2.getUseHours();

				if (returnValue == 0) {
					return o1.getCustomerName().compareTo(o2.getCustomerName());
				} else {
					return returnValue;
				}
			}
		};

		return comparator;
	}

	public Comparator<Customer> comparatorByPay() {
		Comparator<Customer> comparator = new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				int returnValue = o1.getCustomerPay() - o2.getCustomerPay();

				if (returnValue == 0) {
					return o1.getCustomerName().compareTo(o2.getCustomerName());
				} else {
					return returnValue;
				}
			}
		};

		return comparator;
	}
}
