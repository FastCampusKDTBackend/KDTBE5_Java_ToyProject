package me.smartstore.group;

import java.util.Arrays;

import me.smartstore.array.Array;
import me.smartstore.exception.ElementNotFoundException;

public class Groups extends Array<Group> {
	private static Groups allGroups;

	private Groups() {
	}

	public static Groups getInstance() {
		if (allGroups == null) {
			allGroups = new Groups();
		}

		return allGroups;
	}

	public Group findByGroupType(GroupType groupType) throws ElementNotFoundException {
		// for (int i = 0; i < allGroups.size(); i++) {
		// 	if (allGroups.get(i).getGroupType() == groupType)
		// 		return allGroups.get(i);
		// }

		for (Group group : allGroups) {
			if (group.getGroupType() == groupType)
				return group;
		}

		return null;
	}

	public Group[] parseToArray() {
		Group[] groupArray = new Group[size()];

		for (int i = 0; i < size(); i++) {
			groupArray[i] = get(i);
		}

		return groupArray;
	}

	public void sort() {
		Group[] array = parseToArray();

		Arrays.sort(array);
		arrays = array;
	}
}
