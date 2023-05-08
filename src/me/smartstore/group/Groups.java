package me.smartstore.group;

import me.smartstore.array.Array;
import me.smartstore.exception.ElementNotFoundException;

public class Groups extends Array<Group> {
	private static Groups allGroups;

	private Groups() {}

	public static Groups getInstance() {
		if (allGroups == null) {
			allGroups = new Groups();
		}

		return allGroups;
	}

	public Group findByGroupType(GroupType groupType) throws ElementNotFoundException {
		for (int i = 0; i < allGroups.size(); i++) {
			if (allGroups.get(i).getGroupType() == groupType) return allGroups.get(i);
		}

		return null;
	}
}
