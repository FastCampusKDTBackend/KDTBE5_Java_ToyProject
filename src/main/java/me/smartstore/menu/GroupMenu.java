package me.smartstore.menu;

import me.smartstore.collections.MyArrayList;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;

import java.io.IOException;

public class GroupMenu extends Menu implements DataCRUD {
    @Override
    public void addData() {
        //변경할 등급 선택
        printGroupSelectionMessage();
        int groupNumber = inputGroupNumber();
        //groupNumber to group
        GroupType groupType = groupNumberToGroupType(groupNumber);
        //groupList에서 입력받은 등급에 해당하는 요소를 찾음
        if (insertParameter(groupType)) {
            System.out.println("기준이 성공적으로 입력되었습니다.");
        }

    }


    private void printGroupSelectionMessage() {
        System.out.println("다음 등급 중 하나를 선택해주세요.");
        System.out.println("1. General");
        System.out.println("2. VIP");
        System.out.println("3. VVIP");
        System.out.println("==============================");
        System.out.print("번호를 입력해주세요: ");
    }

    private int inputGroupNumber() {
        int groupNumber = 0;
        while (groupNumber == 0) {
            try {
                int temp = Integer.parseInt(br.readLine());
                if (temp >= 1 && temp <= 3) {
                    groupNumber = temp;
                } else {
                    System.out.println("1부터 3까지의 숫자를 입력해주세요.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return groupNumber;
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

    private boolean insertParameter(GroupType groupType) {
        MyArrayList<Group> groupList = groups.getGroups();
        for (Group group : groupList) {
            if (group.getCustomerGroup() == groupType) {
                //찾은 요소가 이미 입력이 된 경우 return
                if (group.getParameter().getMinimumHours() != 0 || group.getParameter().getMinimumTotalAmount() != 0) {
                    System.out.println("이미 기준이 등록된 등급입니다.");
                    return false;
                }//기준을 입력할 메뉴 입력 1: 누적 시간/ 2: 누적 결제 금액
                try {
                    int parameterNumber = Integer.parseInt(br.readLine());
                    //누적 시간 입력 받음
                    if (parameterNumber == 1) {
                        int hours = Integer.parseInt(br.readLine());
                        group.getParameter().setMinimumHours(hours);
                        return true;
                    }
                    int totalAmount = Integer.parseInt(br.readLine());
                    group.getParameter().setMinimumTotalAmount(totalAmount);
                    return true;
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return false;
    }

    @Override
    public void viewData() {
        printGroupSelectionMessage();
        int groupNumber = inputGroupNumber();
        //groupNumber to group
        GroupType groupType = groupNumberToGroupType(groupNumber);
        MyArrayList<Group> groupList = groups.getGroups();
        for (Group group : groupList) {
            if (group.getCustomerGroup() == groupType) {
                System.out.println(group.getParameter());
            }
        }
    }

    @Override
    public void updateData() {

    }

    @Override
    public void deleteData() {

    }

    @Override
    public void back() {

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
