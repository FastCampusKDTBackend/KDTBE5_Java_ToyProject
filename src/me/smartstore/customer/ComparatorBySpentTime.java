package me.smartstore.customer;

import java.util.Comparator;

public class ComparatorBySpentTime implements Comparator<Customer> {
	@Override
	public int compare(Customer o1, Customer o2) {
		return o1.getSpentTime().compareTo(o2.getSpentTime());
	}
}
