package me.smartstore.customer;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

import me.smartstore.collections.DArray;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

public class Customers extends DArray<Customer> {
	private static Customers customers;

	public static Customers getInstance() {
		if (customers == null) {
			customers = new Customers();
		}
		return customers;
	}

	public Customer findById(String id) {
		for (int i = 0; i < this.size; i++) {
			if (id.equals(this.get(i).getId())) {
				return this.get(i);
			}
		}
		throw new NoSuchElementException();
	}

	public Boolean ispresent(String id) {
		for (int i = 0; i < this.size; i++) {
			if (id.equals(this.get(i).getId())) {
				return true;
			}
		}
		return false;
	}

	public void refreshGroupType(Groups groups) {
		// customers 순회하며 GroupType 갱신
		for (int i = 0; i < this.size; i++) {
			this.get(i).assignGroupType(groups);
		}
	}

	public Customer[] arrayByGroupType(GroupType groupType) {
		Customer[] customerArray = new Customer[this.size];

		for (int i = 0; i < this.size; i++) {
			if (groupType == this.get(i).getGroupType()) {
				customerArray[i] = this.get(i);
			}
		}

		return Arrays.stream(customerArray).filter(Objects::nonNull).toArray(Customer[]::new);
	}

	@Override
	public String toString() {
		String toStr = "";
		for (int i = 0; i < this.size; i++) {
			toStr += ("No. " + (i + 1) + " => " + this.get(i) + "\n");
		}
		return toStr;
	}
}
