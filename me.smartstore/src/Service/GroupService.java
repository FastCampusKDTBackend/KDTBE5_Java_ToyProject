package Service;

import domain.group.GroupType;
import domain.group.Groups;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class GroupService {

    private static GroupService groupService;

    public static GroupService getInstance() {
        if (groupService == null) {
            groupService = new GroupService();
        }
        return groupService;
    }

    public GroupService() {}

    public GroupType searchByInput(String input) {
        return Arrays.stream(GroupType.values())
                .skip(1)    // NONE은 건너뜀
                .filter(t -> t.name().equals(input) || t.getShortName().equals(input))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

}
