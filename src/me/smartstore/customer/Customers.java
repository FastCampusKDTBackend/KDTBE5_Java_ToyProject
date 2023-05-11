package me.smartstore.customer;

import java.util.Arrays;
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

	// public Customer findById(String id) {
	// 	for (int i = 0; i < this.size; i++) {
	// 		if (id.equals(this.get(i).getId())) {
	// 			return this.get(i);
	// 		}
	// 	}
	// 	throw new NoSuchElementException();
	// }

	// id 중복 검사
	public Boolean ispresent(String id) {
		for (int i = 0; i < this.size; i++) {
			if (id.equals(this.get(i).getId())) {
				return true;
			}
		}
		return false;
	}

	// Group 등록,수정 시 호출
	public void refreshGroupType(Groups groups) {
		// customers 순회하며 GroupType 갱신
		for (int i = 0; i < this.size; i++) {
			this.get(i).assignGroupType(groups);
		}
	}

	// 해당 GroupType인 customer들을 찾아 배열(array)로 리턴
	public Customer[] arrayByGroupType(GroupType groupType) {
		Customer[] customerArray = new Customer[this.size];

		for (int i = 0; i < this.size; i++) {
			if (groupType == this.get(i).getGroupType()) {
				customerArray[i] = this.get(i);
			}
		}

		// 일단 배열 크기를 customers의 size로 잡은 후 return 시 nonNull인 값들로만 새 배열 생성
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
