package Group;

import Arrays.MyArray;

public class Groups extends MyArray {

    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {

    }
}
