package me.smartstore.group;

import java.util.NoSuchElementException;
import java.util.Objects;

import me.smartstore.collections.DArray;

public class Groups extends DArray<Group> {
	private static Groups groups;

	private Groups() {
	}

	public static Groups getInstance() {
		if (groups == null) {
			groups = new Groups();
			groups.add(new Group(GroupType.NONE));
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
		Group find = this.get(0);    // NONE

		for (int i = this.size - 1; -1 < i; i--) {
			Group group = this.get(i);
			Parameter groupParam = group.getParameter();
			if (groupParam == null) {
				continue;
			}

			int minTime = Objects.requireNonNullElse(groupParam.getMinimumSpentTime(), 0);
			int minPay = Objects.requireNonNullElse(groupParam.getMinimumTotalPay(), 0);

			if (param.getMinimumSpentTime() >= minTime && param.getMinimumTotalPay() >= minPay) {
				find = group;
				break;
			}
		}

		return find;
	}

}
