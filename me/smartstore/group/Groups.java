package smartstore.group;

import smartstore.arrays.SArray;

public class Groups {

    private final SArray<Group> groupSArray = new SArray<>();
    private static final Groups INSTANCE = new Groups();

    public static Groups getInstance() {
        return INSTANCE;
    }

    private Groups(){

    }

    public SArray<Group> getGroupSArray() {
        return groupSArray;
    }

    public Group findGroup(GroupType groupType) {
        for(int i = 0; i < groupSArray.size(); i++) {
            if(groupSArray.get(i).getGroupType() == groupType) {
                return groupSArray.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "groupSArray=" + groupSArray +
                '}';
    }
}
