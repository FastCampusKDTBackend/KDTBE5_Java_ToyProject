package me.smartstore.group;

import me.smartstore.array.Array;

public class Groups extends Array<Group> {
	private static Groups allGroups;

	private Groups() {}

	public static Groups getInstance() {
		if (allGroups == null) {
			allGroups = new Groups();
		}

		return allGroups;
	}
}
