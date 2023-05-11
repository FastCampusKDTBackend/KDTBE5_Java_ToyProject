package me.smartstore.customer;

import java.lang.reflect.Field;
import java.util.Objects;

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
				return false;
			}
		}
		return true;    //모든 파라미터가 null이면 빈 객체
	}

	// GroupType 지정 메서드. customer 등록, 수정 시 / Group 등록,수정으로 인한 refresh 과정에서 호출
	public void assignGroupType(Groups groups) {
		this.groupType = groups.findByParameter(new Parameter(this.spentTime, this.totalPay)).getGroupType();
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
