package me.smartstore.domain.group;

import me.smartstore.domain.customer.Customer;
import me.smartstore.utils.MyArray;
import me.smartstore.constant.GroupType;

public class Groups {
	private static Groups allGroups;
	private int size;
	private Group[] groups;

	public static Groups getInstance() {
		if (allGroups == null)
			allGroups = new Groups(); 
		return allGroups;
	}

	public Groups() {
		this.groups = new Group[GroupType.size()];
		initialize();
	}

	public Group[] getGroups() {
		return this.groups;
	}

	public void setGroups(Group[] groups) {
		this.groups = groups;
	}

	public int size() {
		return this.size;
	}
	
	public int length() {
		return this.groups.length;
	}

	private boolean isNull() {
		return (this.groups == null);
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}

	public void initialize() {
		for (int i = 0; i < GroupType.size(); i++) {
			this.groups[i] = new Group(GroupType.values()[i], null);
			this.size++;
		} 
	}

	public void add(Group group) {
		Group grp = find(group.getType());
		if (grp != null) {
			update(group);
		} else {
			this.groups[this.size] = group;
			this.size++;
		} 
	}

	public Group get(int i) {
		return this.groups[i];
	}

	public void update(Group group) {
		Group grp = find(group.getType());
		if (grp != null)
			grp.setParam(group.getParam()); 
	}
	
	public void print() {
		for (int i = 0; i < this.size; i++) {
			if (this.groups[i] != null)
				System.out.println(this.groups[i]); 
		} 
	}

	public Group find(GroupType groupType) {
		for (Group grp : this.groups) {
			if (grp.getType() == groupType)
				return grp; 
		} 
		return null;
	}

	public Group findGroupFor(Customer customer) {
		if (this.groups == null)
			return null; 
		if (MyArray.isAnyNUll(new Object[] { customer, customer.getSpentTime(), customer.getTotalPay() }))
			return null; 
		for (int i = this.size - 1; i >= 0; i--) {
			if (!MyArray.isAnyNUll(new Object[] { this.groups[i], this.groups[i].getParam() })) {
				Parameter param = this.groups[i].getParam();
				if (!MyArray.isAnyNUll(new Object[] { param, param.getMinimumSpentTime(), param.getMinimumTotalPay() }))
					if (customer.getSpentTime().intValue() >= param.getMinimumSpentTime().intValue() && customer
					.getTotalPay().intValue() >= param.getMinimumTotalPay().intValue())
						return this.groups[i];  
			} 
		} 
		return null;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < this.size; i++)
			str = str + " " + str + "\n"; 
		return str;
	}
}
