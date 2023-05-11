package me.project;


import me.project.menu.CustomerMenu;
import me.project.menu.ParameterMenu;
import me.project.menu.Menu;
import me.project.menu.SummaryMenu;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        System.out.println("토이프로젝트 스마트스토어");

        while(true) {
            try {
                int choice = Menu.displayMainMenu();
                if (choice == 1) {
                    ParameterMenu.choiceParameterMenu();
                } else if (choice == 2) {
                    CustomerMenu.choiceCustomerMenu();
                } else if (choice == 3) {
                    SummaryMenu.choiceSummaryMenu();
                } else {
                    if(choice == 4) {
                        System.out.println("프로그램을 종료합니다.");
                        break;
                    }
                    else {
                        System.out.println("올바른 번호를 입력해주세요.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.print("유효하지 않은 입력입니다. 다시 입력하세요.\n");
                Menu.sc.next();
            }
        }
    }
}