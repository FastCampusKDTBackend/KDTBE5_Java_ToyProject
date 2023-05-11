package me.smartstore.customer;

import java.util.Comparator;

import me.smartstore.util.SortOrder;

public class ComparatorByTotalPayment implements Comparator<Customer> {
	private SortOrder sortOrder;

	public ComparatorByTotalPayment(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	// null처리 -> ASCENDING, DESCENDING과 관계없이 Null Last
	@Override
	public int compare(Customer o1, Customer o2) {
		if (SortOrder.ASCENDING.equals(sortOrder)) {
			return Comparator
				.comparing(Customer::getTotalPay, Comparator.nullsLast(Comparator.naturalOrder()))
				.compare(o1, o2);
		} else {
			return Comparator
				.comparing(Customer::getTotalPay, Comparator.nullsLast(Comparator.reverseOrder()))
				.compare(o1, o2);
		}
	}
}