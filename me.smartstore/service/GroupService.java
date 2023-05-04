package service;

import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import exception.ArrayEmptyException;
import exception.GroupNotFoundException;
import exception.GroupSetAlreadyException;
import view.Message;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GroupService {

    private static GroupService groupService;
    private static final Groups groups = Groups.getInstance();

    public static GroupService getInstance() {
        if (Objects.isNull(groupService)){
            groupService = new GroupService();
        }
        return groupService;
    }

    private GroupService() {

    }

    public void checkInvalidGroup(GroupType groupType) throws GroupSetAlreadyException {
        if (groups.size() != 0 && groups.find(groupType) != null)
            throw new GroupSetAlreadyException(groupType.toString());
    }

    public Group selectGroupByGroupType(GroupType groupType) throws ArrayEmptyException, GroupNotFoundException {
        checkGroupEmpty();
        Group group = groups.find(groupType);
        if (Objects.isNull(group)) throw new GroupNotFoundException();
        return group;
    }

    public void insertGroup(Group group) {
        groups.add(group);
    }

    public Groups selectAllGroup() throws ArrayEmptyException {
        checkGroupEmpty();
        return groups;
    }

    public void checkGroupEmpty() {
        if (groups.size() == 0) throw new ArrayEmptyException(Message.ERR_MSG_GROUP_ARR_EMPTY);
    }

    public List<Group> sortGroup(){
        List<Group> list = groups.toList();
        list.sort(Comparator.comparingInt(group -> group.getGroupType().getRank()));
        return list;
    }
}
