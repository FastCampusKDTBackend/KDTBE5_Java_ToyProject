package smartstore.group;

import smartstore.arrays.DArray;

public class Groups extends DArray<Group> {
    // singleton
    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {}

    public Group find(GroupType groupType) {
        for (int i = 0; i < this.size; i++) {
        	if (this.get(i).getGroupType() == groupType) { // enum의 비교는 equals가 아님
                return this.get(i);
            }
        }
        return null;
    }
}
