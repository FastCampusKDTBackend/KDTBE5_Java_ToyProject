package me.smartstore.customer;

import java.util.Comparator;

import me.smartstore.util.SortOrder;

public class ComparatorBySpentTime implements Comparator<Customer> {
	private SortOrder sortOrder;

	public ComparatorBySpentTime(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	// null처리 -> ASCENDING, DESCENDING과 관계없이 Null Last
	@Override
	public int compare(Customer o1, Customer o2) {
		if (SortOrder.ASCENDING.equals(sortOrder)) {
			return Comparator
				.comparing(Customer::getSpentTime, Comparator.nullsLast(Comparator.naturalOrder()))
				.compare(o1, o2);
		} else {
			return Comparator
				.comparing(Customer::getSpentTime, Comparator.nullsLast(Comparator.reverseOrder()))
				.compare(o1, o2);
		}
	}
}
