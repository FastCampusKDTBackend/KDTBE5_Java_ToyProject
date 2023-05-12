package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.customer.ClassifiedCustomers;
import me.smartstore.group.Groups;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    protected BufferedReader br;
    protected Customers customers;
    protected Groups groups;
    protected ClassifiedCustomers classifiedCustomers;
    private static Menu menu;

    protected Menu() {
        br = new BufferedReader(new InputStreamReader(System.in));
        customers = Customers.getInstance();
        groups = Groups.getInstance();
        classifiedCustomers = ClassifiedCustomers.getInstance();
    }

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    protected void initMenu() {
        printInitMenu();
        int menuNumber = 0;
        while (menuNumber < 1 || menuNumber > 4) {
            try {
                menuNumber = readInt();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printInitMenu() {
        System.out.println("==============================");
        System.out.println("1. 고객 등급 기준 설정");
        System.out.println("2. 고객 정보");
        System.out.println("3. 등급별 고객 정보 요약");
        System.out.println("4. 프로그램 종료");
        System.out.println("==============================");
        System.out.print("메뉴번호를 입력해주세요: ");

    }

    //@Todo 예외처리
    private int readInt() throws IOException {
        String input = br.readLine();
        int newInt = Integer.parseInt(input);
        return newInt;
    }

    protected void back() {

    }
}
