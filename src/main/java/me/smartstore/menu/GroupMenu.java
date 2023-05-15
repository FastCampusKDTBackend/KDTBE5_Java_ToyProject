package me.smartstore.menu;

import me.smartstore.collections.MyArrayList;
import me.smartstore.customer.Customer;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Parameter;

import java.io.IOException;
import java.util.NoSuchElementException;

public class GroupMenu extends Menu implements DataCRUD {
    private static GroupMenu groupMenu;

    private GroupMenu() {
    }

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    public void run() {
        int groupMenu = 0;
        while (groupMenu != 4) {
            printGroupInitMenu();
            groupMenu = readNumber(4);
            if (groupMenu == 1) {
                addData();
            } else if (groupMenu == 2) {
                viewData();
            } else if (groupMenu == 3) {
                updateData();
            }
        }
    }

    @Override
    public void addData() {
        //변경할 등급 선택
        printGroupSelectionMessage();
        int groupNumber = readNumber(3);
        //groupNumber to group
        GroupType groupType = groupNumberToGroupType(groupNumber);
        //groupList에서 입력받은 등급에 해당하는 요소를 찾음
        insert(groupType);
    }


    private void printGroupSelectionMessage() {
        System.out.println("다음 등급 중 하나를 선택해주세요.");
        System.out.println("1. General");
        System.out.println("2. VIP");
        System.out.println("3. VVIP");
        System.out.println("==============================");
        System.out.print("번호를 입력해주세요: ");
    }

    private GroupType groupNumberToGroupType(int groupNumber) {
        if (groupNumber == 1) {
            return GroupType.General;
        }
        if (groupNumber == 2) {
            return GroupType.VIP;
        }
        return GroupType.VVIP;
    }

    private void insert(GroupType groupType) {
        Parameter parameter = findParameter(groupType);
        //찾은 요소가 이미 입력이 된 경우 return
        if (parameter.getMinimumHours() != 0 || parameter.getMinimumTotalAmount() != 0) {
            System.out.println("이미 기준이 등록된 등급입니다.");
            return;
        }
        insertInputParameter(parameter);
    }

    private void insertInputParameter(Parameter parameter) {
        //기준을 입력할 메뉴 입력 1: 누적 시간/ 2: 누적 결제 금액
        printInsertParameterMessage();
        int parameterNumber = readNumber(3);
        try {
            //누적 시간 입력 받음
            if (parameterNumber == 1) {
                System.out.println("누적 이용 시간을 입력해주세요.");
                int hours = Integer.parseInt(br.readLine());
                parameter.setMinimumHours(hours);

            } else if (parameterNumber == 2) {
                //누적 결제 금액 입력 받음
                System.out.println("누적 결제 금액을 입력해주세요.");
                int totalAmount = Integer.parseInt(br.readLine());
                parameter.setMinimumTotalAmount(totalAmount);
            } else {
                customers.refreshCustomersGroup();
                return;
            }
            //3이 입력되기 전까지 재귀 호출해서 메뉴 실행 상태 유지
            insertInputParameter(parameter);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printInsertParameterMessage() {
        System.out.println("==============================");
        System.out.println("다음 중 입력할 기준을 선택해주세요.");
        System.out.println("1. 누적 이용 시간");
        System.out.println("2. 누적 결제 금액");
        System.out.println("3. 뒤로 가기");
        System.out.println("==============================");
    }

    private Parameter findParameter(GroupType groupType) {
        if (validator.isGroupListEmpty()) {
            return null;
        }
        MyArrayList<Group> groupList = groups.getGroups();
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getCustomerGroup() == groupType) {
                return groupList.get(i).getParameter();
            }
        }
        throw new NoSuchElementException("해당하는 등급이 존재하지 않습니다.");
    }

    @Override
    public void viewData() {
        printGroupSelectionMessage();
        int groupNumber = readNumber(3);
        //groupNumber to group
        GroupType groupType = groupNumberToGroupType(groupNumber);
        Parameter parameter = findParameter(groupType);
        System.out.println(parameter);
    }

    @Override
    public void updateData() {
        printGroupSelectionMessage();
        int groupNumber = readNumber(3);
        GroupType groupType = groupNumberToGroupType(groupNumber);
        Parameter parameter = findParameter(groupType);
        insertInputParameter(parameter);
    }

    @Override
    public void deleteData() {

    }

    public void printGroupInitMenu() {
        System.out.println("==============================");
        System.out.println("1. 고객 등급 기준 추가");
        System.out.println("2. 고객 등급 기준 조회");
        System.out.println("3. 고객 등급 기준 수정");
        System.out.println("4. 뒤로 가기");
        System.out.println("==============================");
        System.out.print("메뉴번호를 입력해주세요: ");
    }
}
