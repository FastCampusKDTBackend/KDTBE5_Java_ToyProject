package me.smartstore.customer;

import java.util.NoSuchElementException;

import me.smartstore.collections.DArray;
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
			if (this.get(i).getId().equals(id)) {
				return this.get(i);
			}
		}
		throw new NoSuchElementException();
	}

	public Boolean ispresent(String id) {
		for (int i = 0; i < this.size; i++) {
			if (this.get(i).getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public void refreshGroupType(Groups groups) {
		// customers 순회하며 GroupType 갱신
		for (int i = 0; i < this.size; i++) {
			this.get(i).classifyGroupType(groups);
		}
	}

	@Override
	public String toString() {
		String toStr = "";
		for (int i = 0; i < this.size; i++) {
			toStr += ("No. " + i + 1 + " => " + this.get(i) + "\n");
		}
		return toStr;
	}
}
