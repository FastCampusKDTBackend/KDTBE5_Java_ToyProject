package me.smartstore.menu;

import me.smartstore.collections.MyArrayList;
import me.smartstore.customer.Customer;
import me.smartstore.customer.SortBy;
import me.smartstore.group.GroupType;

import java.io.IOException;

public class SummaryMenu extends Menu {
    private static SummaryMenu summaryMenu;

    private SummaryMenu() {
    }

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    public void run(){
        classifiedCustomers.refresh();
        printSummaryInitMenu();
        int summaryNumber = readNumber(5);
        if(summaryNumber==1){
            summary(OrderType.NONE);
            return;
        }
        if(summaryNumber==2){
            summarySortByName();
            return;
        }
        if(summaryNumber==3){
            summarySortByHours();
            return;
        }
        summarySortByTotalAmount();
    }
    private void summary(OrderType orderType) {
        GroupType[] groupTypes = GroupType.values();
        MyArrayList<MyArrayList<Customer>> classifications = classifiedCustomers.getClassifications();
        printCustomerSummary(orderType, groupTypes, classifications);
    }

    private void printCustomerSummary(OrderType orderType, GroupType[] groupTypes, MyArrayList<MyArrayList<Customer>> classifications) {
        for (int i = 0; i < GroupType.values().length; i++) {
            System.out.println("==============================");
            System.out.printf("그룹: %s", groupTypes[i]);
            System.out.println("==============================");
            MyArrayList<Customer> currentClassification = classifications.get(i);
            if (orderType != OrderType.NONE) {
                currentClassification.sort(orderType);
            }
            for (int j = 0; j < currentClassification.size(); i++) {
                System.out.println(j + ": " + currentClassification.get(j));
            }
        }
        System.out.println("==============================");
    }

    private void summarySortByName() {
        OrderType orderType = inputOrderType();
        //등급 기준에 따라 정렬
        classifiedCustomers.setSortBy(SortBy.NAME);
        summary(orderType);
    }

    private void summarySortByHours() {
        OrderType orderType = inputOrderType();
        classifiedCustomers.setSortBy(SortBy.HOURS);
        summary(orderType);
    }

    private void summarySortByTotalAmount() {
        OrderType orderType = inputOrderType();
        classifiedCustomers.setSortBy(SortBy.TOTAL_AMOUNT);
        summary(orderType);
    }

    private OrderType inputOrderType() {
        //오름차순, 내림차순 입력(OrderType이용)
        int ascendingNumber = 0;
        while (ascendingNumber == 0) {
            try {
                int input = Integer.parseInt(br.readLine());
                if (input == 1 || input == 2) {
                    ascendingNumber = input;
                }
                System.out.println("1또는 2를 입력해주세요.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return OrderType.getOrderType(ascendingNumber);
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
