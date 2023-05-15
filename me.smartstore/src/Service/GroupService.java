package Service;

import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.group.Parameter;
import domain.menu.GroupMenu;
import handler.exception.*;

import java.util.Arrays;

import static resources.Message.END_MSG;
import static resources.Message.ERR_MSG_INVALID_GROUP_EMPTY;

public class GroupService {

    private static final Groups groups = Groups.getInstance();
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final GroupMenu groupMenu = GroupMenu.getInstance();

    private static GroupService groupService;

    public static GroupService getInstance() {
        if (groupService == null) {
            groupService = new GroupService();
        }
        return groupService;
    }

    private GroupService() {
    }

    public void setParameter() {
        //Parameter 신규 등록
        while (true) {
            try {
                GroupType groupType = selectGroup();
                // GroupType에 해당하는 group 객체를 찾아야 함
                System.out.println("Select Group : " + groupType);

                //Groups에서 입력한 group이 등록되어 있는지 확인
                Group findGroup = groups.findGroup(groupType);
                //GroupType이 null이 아니라면 이미 초기화 되어있음
                if (findGroup != null) {
                    System.out.println("\n" + findGroup.getGroupType() + " group already exists.");
                    System.out.println("\n" + findGroup);
                    break;
                } else {
                    setParameterDetail(groupType);
                }
                //groups에 해당 그룹이 등록되어 있지 않다면 등록 메뉴로 이동
            } catch (NoSuchGroupException | InputEmptyException | InputRangeException e) {
                System.out.println(e.getMessage());
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (NullPointerException e) {
                throw new NoSuchGroupException();
            }
        }
    }


    private void setParameterDetail(GroupType groupType) {
        // time, pay 사용자 입력받은 후 groups에 add
        Parameter parameter = getParameterValue();
        groups.add(new Group(parameter, groupType));

        // 파라미터가 변경되었거나 추가되는 경우, 고객 등급 분류를 다시 해야 한다.
        customerService.refresh();
    }


    private GroupType selectGroup() {
        try {
            System.out.println("Which Group (GENERAL (G), VIP (V), VVIP (VV))?");
            String choice = groupMenu.nextLineUpper(END_MSG);
            if (choice.isEmpty()) throw new InputEmptyException();

            //입력한 group명이 null 값이 아니라면 GroupType을 찾아서 return
            GroupType searchGroupType = searchByInput(choice);
            //입력한 GroupType을 찾지 못했다면 잘못된 입력이므로 예외 발생
            if (searchGroupType == null) throw new InputRangeException();
            //아니라면 찾은 GroupType을 return
            return searchGroupType;
        } catch (NoSuchGroupException | IllegalStateException | NullPointerException e) {
            throw new InputRangeException();
        }
    }

    private Parameter getParameterValue() { //초기화 할 때만 호출 가능
        Integer minTime = null;
        Integer minPay = null;

        while (true) {
            try {
                int choice = getParamMenuNum();

                if (choice == 1) minTime = getParamMinTime();
                if (choice == 2) minPay = getParamMinPay();
                if (choice == 3) {
                    if (minTime != null && minPay != null) {
                        break;
                    }
                    throw new InputEmptyException();
                }
            } catch (InputEmptyException | InputFormatException | InputEndException e) {
                System.out.println(e.getMessage());
            }
        }
        return new Parameter(minTime, minPay);
    }

    private void setParameterValue(Parameter parameter) {

        while (true) {
            try {
                int choice = getParamMenuNum();

                if (choice == 1) parameter.setMinTime(getParamMinTime());
                if (choice == 2) parameter.setMinPay(getParamMinPay());
                if (choice == 3) {
                    if (parameter.getMinTime() == null || parameter.getMinPay() == null) {
                        System.out.println("[ERROR] => Null value in the parameter." + parameter);
                        throw new InputEmptyException();
                    }
                    break;
                }
            } catch (InputEmptyException | InputFormatException | InputRangeException e) {
                System.out.println(e.getMessage());
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    private int getParamMenuNum() {
        return groupMenu.selectMenu(new String[]{
                "Minimum Spent Time",
                "Minimum Total Pay",
                "Back"
        });
    }

    private Integer getParamMinTime() throws InputEndException {
        try {
            System.out.println("Input Minimum Spent Time");
            return groupMenu.nextInt(END_MSG);
        } catch (InputFormatException e) {
            throw new InputFormatException();
        }
    }

    private Integer getParamMinPay() throws InputEndException {
        try {
            System.out.println("Input Minimum Total Pay");
            return groupMenu.nextInt(END_MSG);
        } catch (InputFormatException e) {
            throw new InputFormatException();
        }
    }

    public GroupType searchByInput(String input) {
        //GroupType ENUM을 배열 형태로 변환 후 0번 index NONE은 스킵하고
        //사용자가 입력한 문자에 해당하는 GroupType을 찾아서 반환한다.
        return Arrays.stream(GroupType.values())
                .skip(1)    // NONE은 건너뜀
                .filter(type -> type.name().equals(input) || type.getShortName().equals(input))
                .findFirst()
                .orElseThrow(NoSuchGroupException::new);
    }

    public void updateParameter() {
        while (true) {
            try {
                GroupType groupType = selectGroup();
                System.out.println("Select Group : " + groupType);

                Group target = groups.findGroup(groupType);
                //Group 검색 결과가 null이면 존재하지 않는 Group이므로 등록을 유도
                if (target == null) throw new NoSuchGroupException();

                Parameter parameter = target.getParameter();

                //Parameter 검색 결과가 null이면 존재하지 않는 Group이므로 등록을 유도
                if (parameter == null) throw new NoSuchGroupException();

                //Parameter가 존재한다면 세부 항목 입력 메서드로 이동
                setParameterValue(parameter);
            } catch (InputEndException | NoSuchGroupException e) {
                System.out.println((e.getMessage()));
                break;
            } catch (InputEmptyException | InputRangeException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                throw new NoSuchGroupException();
            }
        }
    }

    public void showParameter() {
        while (true) {
            try {
                GroupType groupType = selectGroup();
                System.out.println("Select Group : " + groupType);

                Group target = groups.findGroup(groupType);

                //찾은 그룹이 null이거나 예외가 발생했다면 없는 그룹이므로 등록을 유도
                if (target == null) throw new NoSuchGroupException();

                //해당 그룹을 찾았다면 출력
                System.out.println("====================================");
                System.out.println("GroupType: " + groupType);
                System.out.println(target.getParameter());
                System.out.println("====================================");
            } catch (InputEndException | NoSuchGroupException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

