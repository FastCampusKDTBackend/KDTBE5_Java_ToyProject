package me.smartstore.domain.customer;

import java.util.Arrays;
import java.util.Objects;
import me.smartstore.domain.group.Group;

public class ClassifiedCustomers extends Customers {
	private Group group;

	public ClassifiedCustomers() {}

	public ClassifiedCustomers(Group group) {
		this.group = group;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true; 
		if (o == null || getClass() != o.getClass())
			return false; 
		ClassifiedCustomers that = (ClassifiedCustomers)o;
		return Objects.equals(this.group, that.group);
	}
	
	public int hashCode() {
		return Objects.hash(new Object[] { this.group });
	}

	public String toString() {
		return "ClassifiedCustomers{group=" + this.group + ", size=" + this.size + ", customers=" + Arrays.toString((Object[])this.customers) + "}";
  }
	
}

