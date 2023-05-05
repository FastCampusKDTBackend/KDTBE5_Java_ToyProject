package me.smartstore.group;

import java.util.NoSuchElementException;

import me.smartstore.collections.DArray;

public class Groups extends DArray<Group> {
	private static Groups groups;

	private Groups() {
	}

	public static Groups getInstance() {
		if (groups == null) {
			groups = new Groups();
			groups.add(new Group(GroupType.GENERAL));
			groups.add(new Group(GroupType.VIP));
			groups.add(new Group(GroupType.VVIP));
		}
		return groups;
	}

	public Group findByGroupType(GroupType groupType) {
		for (int i = 0; i < this.size; i++) {
			if (this.get(i).getGroupType() == groupType) {
				return this.get(i);
			}
		}
		throw new NoSuchElementException();
	}

	public Group findByParameter(Parameter param) {
		Group find = null;

		for (int i = 0; i < this.size; i++) {
			Group group = this.get(i);
			int minTime = group.getParameter().getMinimumSpentTime();
			int minPay = group.getParameter().getMinimumTotalPay();

			if (param.getMinimumSpentTime() >= minTime && param.getMinimumTotalPay() >= minPay) {
				find = group;
			}
		}

		return find;
	}
}
