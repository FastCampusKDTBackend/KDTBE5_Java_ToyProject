package me.smartstore.customer;

import java.lang.reflect.Field;
import java.util.Objects;

import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;

public class Customer {
	private String name;
	private String id;
	private Integer spentTime;
	private Integer totalPay;
	private GroupType groupType;

	public Customer() {
	}

	public Customer(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public Customer(String name, String id, Integer spentTime, Integer totalPay) {
		this.name = name;
		this.id = id;
		this.spentTime = spentTime;
		this.totalPay = totalPay;
	}

	public Boolean isEmpty() throws IllegalAccessException {
		for (Field f : getClass().getDeclaredFields()) {
			if (f.get(this) != null) {
				return false; // 하나라도 null이 아니면
			}
		}
		return true;
	}

	public void classifyGroupType(Groups groups) {
		Group group = groups.findByParameter(new Parameter(this.spentTime, this.totalPay));
		if (group == null) {
			this.groupType = null;
		} else {
			this.groupType = group.getGroupType();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSpentTime() {
		return spentTime;
	}

	public void setSpentTime(Integer spentTime) {
		this.spentTime = spentTime;
	}

	public Integer getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(Integer totalPay) {
		this.totalPay = totalPay;
	}

	public GroupType getGroupType() {
		return groupType;
	}

	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Customer customer = (Customer)o;
		return Objects.equals(id, customer.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Customer{" +
			"name='" + name + '\'' +
			", id='" + id + '\'' +
			", spentTime=" + spentTime +
			", totalPay=" + totalPay +
			", groupType=" + groupType +
			'}';
	}
}
