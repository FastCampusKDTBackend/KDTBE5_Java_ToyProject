package domain.menu;

import Service.CustomerService;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.group.Parameter;
import handler.exception.InputEmptyException;
import handler.exception.InputEndException;
import handler.exception.NullArgumentException;

import java.util.NoSuchElementException;

public class GroupMenu implements Menu {

    private final Groups groups = Groups.getInstance();
    private final CustomerService customerService = CustomerService.getInstance();

    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {
    }

    @Override
    public void manage() {
        while (true) {
            int choice = groupMenu.selectMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"
            });

            if (choice == 1) setParameter();
            else if (choice == 2) showParameter();
            else if (choice == 3) updateParameter();
            else break;
        }
    }

    private GroupType selectGroup() {
        boolean repeat = true;
        while (repeat) {
            try {
                System.out.println("Which Group (GENERAL (G), VIP (V), VVIP (VV))?");
                String choice = this.nextLine();
                if (choice.isEmpty()) throw new InputEmptyException();
                return GroupType.valueOf(choice).shortToFull();
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                repeat = false;
            } catch (IllegalArgumentException | NoSuchElementException | InputEmptyException e) {
                System.out.println(e.getMessage());
            }
        }
        throw new InputEmptyException();
    }

    private void setParameter() {

        while (true) {
            GroupType groupType = selectGroup();

            // GroupType에 해당하는 group 객체를 찾아야 함
            Group group = groups.findGroup(groupType);
            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                Parameter parameter = new Parameter();
                // time, pay 사용자 입력받은 후, 설정 필요
                setParameterValue(parameter);
                customerService.refresh(groups); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
            }
        }
    }

    private void setParameterValue(Parameter parameter) { //초기화 할 때만 호출 가능
        while (true) {
            int choice = groupMenu.selectMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"
            });

            if (choice == 1) {
                System.out.println("Input Minimum Spent Time");
                parameter.setMinTime(Integer.valueOf(this.nextLine()));
            } else if (choice == 2) {
                System.out.println("Input Minimum Total Pay");
                parameter.setMinPay(Integer.valueOf(this.nextLine()));
            } else break;
        }
    }

    private void updateParameter() {
        while (true) {
            try {
                GroupType groupType = selectGroup();
                if (groupType == null) throw new NullArgumentException();
                Parameter parameter = groups.findGroup(groupType).getParameter();
                if (parameter != null) setParameterValue(parameter);
                else throw new InputEmptyException();

            } catch (NullArgumentException | InputEmptyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showParameter() {
        while (true) {
            GroupType groupType = selectGroup();
            if (groupType == null) break;
            Group target = groups.findGroup(groupType);
            System.out.println("GroupType: " + groupType);
            System.out.println("Parameter: " + target.getParameter());
        }
    }
}
