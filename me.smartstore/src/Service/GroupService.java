package Service;

import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.group.Parameter;
import domain.menu.GroupMenu;
import handler.exception.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static resources.Message.*;

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
        try {
            GroupType groupType = selectGroup();

            // GroupType에 해당하는 group 객체를 찾아야 함
            Group group = groups.findGroup(groupType);
            //GroupType이나 Parameter가 null 아니라면 이미 초기화 되어있음
            if (group != null && group.getParameter() != null) {
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                setParameterDetail(groupType);
            }
        } catch (InputTypeException | InputEndException | NoSuchGroupException e) {
            System.out.println(e.getMessage());
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
            return searchByInput(choice);
        } catch (NoSuchElementException | IllegalStateException | NullPointerException e) {
            throw new NoSuchGroupException();
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
            } catch (InputEmptyException e) {
                System.out.println(e.getMessage());
            }
        }
        return new Parameter(minTime, minPay);
    }

    private void setParameterValue(Parameter parameter) {

        while (true) {
            try {
                int choice = getParamMenuNum();

                if (choice == 1) setParamMinTime(parameter);
                if (choice == 2) setParamMinPay(parameter);
                if (choice == 3) {
                    if (parameter.getMinTime() != null && parameter.getMinPay() != null) {
                        break;
                    } else {
                        System.out.println("[ERROR] => Null value in the parameter." + parameter);
                    throw new InputEmptyException();
                    }
                }
            } catch (InputEmptyException | InputFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setParamMinTime(Parameter parameter) {
        while (true) {
            try {
                System.out.println("Input Minimum Spent Time");
                Integer minTime = groupMenu.nextInt(END_MSG);
                if (minTime != null) {
                    parameter.setMinTime(minTime);
                    break;
                } else throw new InputEmptyException();
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InputFormatException | InputEmptyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setParamMinPay(Parameter parameter) {
        while (true) {
            try {
                System.out.println("Input Minimum Spent Time");
                Integer minPay = groupMenu.nextInt(END_MSG);
                if (minPay != null) {
                    parameter.setMinPay(minPay);
                    break;
                } else throw new InputEmptyException();
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InputFormatException | InputEmptyException e) {
                System.out.println(e.getMessage());
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

    private Integer getParamMinTime() {
        try {
            System.out.println("Input Minimum Spent Time");
            return groupMenu.nextInt(END_MSG);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Integer getParamMinPay() {
        System.out.println("Input Minimum Total Pay");
        return groupMenu.nextInt(END_MSG);
    }

    public GroupType searchByInput(String input) {
        return Arrays.stream(GroupType.values())
                .skip(1)    // NONE은 건너뜀
                .filter(t -> t.name().equals(input) || t.getShortName().equals(input))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public void updateParameter() {
        while (true) {
            try {
                GroupType groupType = selectGroup();
                System.out.println("Select Group type " + groupType);
                Parameter parameter = groups.findGroup(groupType).getParameter();
                if (parameter != null) setParameterValue(parameter);
            } catch (InputEndException e) {
                System.out.println((e.getMessage()));
                break;
            } catch (InputEmptyException | InputRangeException | NoSuchGroupException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showParameter() {
        while (true) {
            try {
                GroupType groupType = selectGroup();
                Group target = groups.findGroup(groupType);
                System.out.println("====================================");
                System.out.println("GroupType: " + groupType);
                System.out.println(target.getParameter());
                System.out.println("====================================");
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (NoSuchGroupException e) {
                System.out.println(e.getMessage());
            } catch (ArrayEmptyException e) {
                System.out.println("[ERROR] => The group doesn't exist, please register first");
            }
        }
    }
}

