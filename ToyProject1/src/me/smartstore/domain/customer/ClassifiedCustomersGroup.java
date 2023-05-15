package me.smartstore.domain.customer;

import java.util.Arrays;
import java.util.Comparator;
import me.smartstore.constant.GroupType;
import me.smartstore.domain.group.Parameter;

public class ClassifiedCustomersGroup {
	private static ClassifiedCustomersGroup classifiedCustomersGroup;

	private ClassifiedCustomers[] classifiedCustomers;

	public static ClassifiedCustomersGroup getInstance() {
		if (classifiedCustomersGroup == null)
			classifiedCustomersGroup = new ClassifiedCustomersGroup(); 
		return classifiedCustomersGroup;
	}

	public ClassifiedCustomersGroup() {
		this.classifiedCustomers = new ClassifiedCustomers[GroupType.size()];
		for (int i = 0; i < GroupType.size(); i++)
			this.classifiedCustomers[i] = new ClassifiedCustomers(); 
	}

	public ClassifiedCustomers get(int i) {
		return this.classifiedCustomers[i];
	}

	public void set(int i, ClassifiedCustomers customers) {
		this.classifiedCustomers[i] = customers;
	}

	public int size() {
		return GroupType.size();
	}

	public void print() {
		for (int i = 0; i < this.classifiedCustomers.length; i++) {
			System.out.println("\n==============================");
			if (this.classifiedCustomers[i] == null)
				return; 
			GroupType groupType = this.classifiedCustomers[i].getGroup().getType();
			Parameter parameter = this.classifiedCustomers[i].getGroup().getParam();
			System.out.printf("Group : %s ( Time : %d, Pay : %d )\n", new Object[] { groupType, 
					(parameter != null) ? parameter.getMinimumSpentTime() : null, 
					(parameter != null) ? parameter.getMinimumTotalPay() : null });
			System.out.println("==============================");
			if (this.classifiedCustomers[i] == null || this.classifiedCustomers[i].isEmpty()) {
				System.out.println("Null.");
			} else {
				this.classifiedCustomers[i].print();
				System.out.println("==============================\n");
			} 
		} 
	}

	public void sort(Comparator<Customer> comparator) {
		for (int i = 0; i < classifiedCustomersGroup.size(); i++) {
			Customer[] customers = classifiedCustomersGroup.get(i).getCustomers();
			try {
				if (comparator == null) {
					Arrays.sort((Object[])customers);
				} else {
					Arrays.sort(customers, comparator);
				} 
				classifiedCustomersGroup.get(i).setCustomers(customers);
			} catch (NullPointerException e) {
				System.out.println("Elements in Array has null. Array can't be sorted.");
			} 
		} 
	}

	public boolean equals(Object o) {
		if (this == o)
			return true; 
		if (o == null || getClass() != o.getClass())
			return false; 
		ClassifiedCustomersGroup that = (ClassifiedCustomersGroup)o;
		return Arrays.equals((Object[])this.classifiedCustomers, (Object[])that.classifiedCustomers);
	}

	public int hashCode() {
		return Arrays.hashCode((Object[])this.classifiedCustomers);
	}

	public String toString() {
		return "ClassifiedCustomersGroup{classifiedCustomers=" + Arrays.toString((Object[])this.classifiedCustomers) + "}";
	}

}
