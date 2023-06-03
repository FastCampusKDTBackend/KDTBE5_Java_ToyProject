package domain.group;

import handler.exception.NoSuchGroupException;
import handler.exception.NullArgumentException;
import util.DArray;

public class Groups extends DArray<Group> {

    // singleton
    private static Groups groups;

    public static Groups getInstance() {
        if (groups == null) {
            // Groups 인스턴스 생성 시 default GroupType NONE을 add.
            groups = new Groups(
                    new Group(new Parameter(0, 0), GroupType.NONE)
            );
        }
        return groups;
    }

    private Groups() {}

    private Groups(Group group) {
        this.add(group);
    }

    public Group findGroup(GroupType groupType) {
        // 그룹 사이즈가 1이면 default로 등록한 NONE 그룹 뿐이므로 예외를 던져서 그룹 등록을 유도
        if (groups.size() == 1)
            throw new NoSuchGroupException();

        try {
            int index = 0;
            // GroupType NONE은 제외하고 GENERAL부터 탐색
            for (int i = 1; i < groups.size; i++) {
                if (groups.get(i).isGroupType(groupType)) {
                    index = i;
                }
            }
            //검색 결과 index가 1보다 작다면 찾는 그룹이 없다는 뜻이므로 예외를 던져서 그룹 등록을 유도
            if (index < 1) throw new NoSuchGroupException();

            return groups.get(index);
        } catch (IndexOutOfBoundsException | NullArgumentException e) {
            throw new NoSuchGroupException();
        }
    }
}
