package domain.group;

import handler.exception.ArrayEmptyException;
import handler.exception.NoSuchGroupException;
import handler.exception.NullArgumentException;
import util.DArray;

public class Groups extends DArray<Group> {
    // singleton
    private static Groups groups;

    public static Groups getInstance() {
        if (groups == null) {
            groups = new Groups();
            //Groups 인스턴스 생성시 기본 NONE 그룹을 같이 생성.
            groups.add(new Group(new Parameter(0, 0), GroupType.NONE));
        }
        return groups;
    }

    private Groups() {

    }

    public Group findGroup(GroupType groupType) {
        //그룹 사이즈가 1이면 default로 등록한 NONE 그룹 뿐이므로 null return 후 그룹 등록을 유도
        if (groups.size() == 1) return null;

        try {
            int index = 0;
            for (int i = 0; i < groups.size; i++) {
                if (groups.get(i).getGroupType() == groupType) {
                    index = i;
    //                System.out.println("findGroup index : " + index);
                }
            }
            //검색 결과 index가 1보다 작다면 찾는 그룹이 없다는 뜻이므로 null return 후 그룹 등록을 유도
            if (index < 1) return null;
            else return groups.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchGroupException();
        }
    }
}
