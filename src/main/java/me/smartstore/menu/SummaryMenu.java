package me.smartstore.menu;

import me.smartstore.collections.MyArrayList;
import me.smartstore.customer.Customer;
import me.smartstore.group.GroupType;

public class SummaryMenu extends Menu {
    public void summary() {
        classifiedCustomers.refresh();        //@Todo summary메뉴 진입 시 호출하는 걸로 변경 예정
        GroupType[] groupTypes = GroupType.values();
        MyArrayList<MyArrayList<Customer>> classifications = classifiedCustomers.getClassifications();

        for (int i = 0; i < GroupType.values().length; i++) {
            System.out.println("==============================");
            System.out.printf("그룹: %s", groupTypes[i]);
            System.out.println("==============================");
            MyArrayList<Customer> currentClassification = classifications.get(i);
            for (int j = 0; j < currentClassification.size(); i++) {
                System.out.println(j + ": " + currentClassification.get(j));
            }
        }
        System.out.println("==============================");
        System.out.println();
        System.out.println("==============================");
    }

    @Override
    public void back() {

    }

    public void printSummaryInitMenu() {
        System.out.println("==============================");
        System.out.println("1. 고객 정보 요약");
        System.out.println("2. 고객 정보 요약(이름 순)");
        System.out.println("3. 고객 정보 요약(누적 이용 시간 순)");
        System.out.println("4. 고객 정보 요약(누적 결제 금액 순)");
        System.out.println("5. 뒤로 가기");
        System.out.println("==============================");
        System.out.print("메뉴번호를 입력해주세요: ");
    }
}
