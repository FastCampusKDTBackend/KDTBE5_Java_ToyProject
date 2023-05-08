package me.smartstore.customer;

import java.util.Comparator;

public class ComparatorByTotalPayment implements Comparator<Customer> {
	@Override
	public int compare(Customer o1, Customer o2) {
		return o1.getTotalPay().compareTo(o2.getTotalPay());
	}
}