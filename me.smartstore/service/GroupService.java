package service;

import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.group.Parameter;
import exception.ArrayEmptyException;
import exception.GroupSetAlreadyException;
import exception.InputEndException;
import view.Input;

import java.util.Objects;

public class GroupService {

    private static GroupService groupService;
    private static final Groups groups = Groups.getInstance();
    private static final String[] MENU_ITEMS = new String[]{"Minimum Spent Time", "Minimum Total Pay", "Back"};

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

    public Group selectGroupByGroupType(GroupType groupType) throws ArrayEmptyException {
        if (groups.size() == 0) throw new ArrayEmptyException();
        return groups.find(groupType);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }



//    public void addGroup() {
//        while (true) {
//            GroupType groupType = null;
//            try{
//                groupType = Input.chooseGroup();
//                checkInvalidGroup(groupType);
//                Group group = new Group(new Parameter(), groupType);
//                setParameter(group.getParameter());
//                groups.add(group);
//
//            } catch (IllegalArgumentException exception) {
//                System.out.println(groupType + " group is already exists");
//                System.out.println(groups.find(groupType));
//
//            } catch (InputEndException exception) {
//                System.out.println(exception.getMessage());
//                break;
//            }
//        }
//    }
//
//    public void viewParameter() {
//        System.out.println(groups);
//    }
//
//    public void updateParameter() {
//        while (true) {
//            try {
//                setParameter(groups.find(Input.chooseGroup()).getParameter());
//            } catch (InputEndException exception) {
//                System.out.println(exception.getMessage());
//                break;
//            }
//        }
//    }
}
