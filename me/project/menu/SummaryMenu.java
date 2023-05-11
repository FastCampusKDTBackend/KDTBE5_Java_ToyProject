package me.project.menu;

import me.project.customers.Customer;
import me.project.customers.Customers;
import me.project.exception.EmptyInputException;
import me.project.exception.InputRangeException;
import me.project.exception.NumberRangeException;
import me.project.groups.Grade;
import me.project.groups.Group;
import me.project.groups.Groups;

import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;

public class SummaryMenu extends Menu{
    public SummaryMenu() {
    }

    public static int displaySummaryMenu() {
        while (true) {
            try {
                System.out.println("==============================");
                System.out.println("1. 고객정보 요약");
                System.out.println("2. 고객정보 요약(이름순 정렬)");
                System.out.println("3. 고객정보 요약(이용시간순 정렬)");
                System.out.println("4. 고객정보 요약(결제금액순 정렬)");
                System.out.println("5. 이전");
                System.out.println("==============================");
                System.out.print("번호를 선택하시오 : ");
                int choice = Integer.parseInt(sc.next());
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    throw new NumberRangeException();
                }
            } catch (NumberFormatException e) {
                System.out.print("유효하지 않은 입력입니다. 다시 입력하세요.\n");
                sc.nextLine();
            } catch (NumberRangeException e) {
                System.out.print("유효하지 않은 범위의 숫자입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }

    public static void choiceSummaryMenu() {
        while(true) {
            int choice = displaySummaryMenu();
            if (choice == 1) {
                displayBasicSummary(classify());
            } else if (choice == 2) {
                sortedByName();
            } else if (choice == 3) {
                sortBySpentTime();
            } else if (choice == 4) {
                sortByTotalPay();
            } else {
                if (choice == 5) {
                    return;
                } else {
                    System.out.println("올바른 번호를 입력하시오.");
                }
            }
        }
    }

    public static Customers[] classify() {
        Customers[] customers = new Customers[Grade.values().length];
        for (int i = 0; i < Groups.getInstance().length(); i++) {
            Group group = Groups.getInstance().get(i);
            customers[i] = group.getCustomers();
        }
        return customers;
    }
    public static void displayBasicSummary(Customers[] customers) {

        for (int i = 0; i < Groups.getInstance().length(); i++) {
            Group grp = Groups.getInstance().get(i);
            int custcount;
            if (!customers[i].isNull() && !customers[i].isEmpty()) {
                custcount = customers[i].getCount();
            } else {
                custcount = 0;
            }
            System.out.println();
            System.out.println("============================================================");
            System.out.println(grp.getGrade().toString() + " : " + custcount + " customer(s)");
            if (grp.getParameter() == null) {
                System.out.println("[Parameter] : null");
            } else {
                System.out.println("[Parameter] : " + grp.getParameter().toString());
            }

            System.out.println("------------------------------------------------------------");
            if (!customers[i].isEmpty() && !customers[i].isNull()) {
                for (int j = 0; j < custcount; j++) {
                    Customer customer = customers[i].get(j);
                    if (customer != null) {
                        System.out.println("No. " + (j + 1) + " ==>\n" + customer + "\n");
                    }
                }
            }
            else {
                System.out.println("No Customer !!");
            }
            System.out.println("============================================================");
        }
    }

    public static String chooseSortType() {
        while (true) {
            try {
                System.out.println();
                System.out.println("** 'end'를 입력하시면 이전으로 돌아갑니다. **");
                System.out.print("어떻게 정렬하시겠습니까 (ASCENDING, DESCENDING)? : ");
                String choice = sc.next().toUpperCase();
                if (choice.equals("")) {
                    throw new EmptyInputException();
                }
                if(choice.equals("END")) {
                    return choice;
                }
                if (choice.equals("ASCENDING") || choice.equals("DESCENDING")) {
                    return choice;
                }

                throw new InputRangeException();
            } catch (EmptyInputException e) {
                System.out.print("아무것도 입력하지 않았습니다. 다시 입력하세요.\n");
                sc.nextLine();
            } catch (InputRangeException e) {
                System.out.println("잘못입력하셨습니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }
    public static void sortedByName() {

        String choice = chooseSortType();
        Customers[] customers = classify();
        if (choice.equals("END")) {
            return;
        }
        for (int i = 0; i < customers.length; i++) {
            Customer[] custarr = customers[i].getCustomers();
            if (choice.equals("ASCENDING")) {
                Arrays.sort(custarr);
            }
            else {
                Arrays.sort(custarr, (o1, o2) -> o1.compareTo(o2) * -1);
            }
            customers[i].setCustomers(custarr);
        }
        displayBasicSummary(customers);
    }

    public static void sortBySpentTime() {
        String choice = chooseSortType();
        Customers[] customers = classify();
        if (choice.equals("END")) {
            return;
        }
        for (int i = 0; i < customers.length; i++) {
            Customer[] custarr = customers[i].getCustomers();
            if (choice.equals("ASCENDING")) {
                Arrays.sort(custarr, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer o1, Customer o2) {
                        if (o1.getSpentTime() < o2.getSpentTime()) {
                            return -1;
                        } else if (o1.getSpentTime() == o2.getSpentTime()) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                });
            }
            else {
                Arrays.sort(custarr, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer o1, Customer o2) {
                        if (o1.getSpentTime() < o2.getSpentTime()) {
                            return 1;
                        } else if (o1.getSpentTime() == o2.getSpentTime()) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                });
            }
            customers[i].setCustomers(custarr);
        }
        displayBasicSummary(customers);
    }

    public static void sortByTotalPay() {
        String choice = chooseSortType();
        Customers[] customers = classify();
        if (choice.equals("END")) {
            return;
        }
        for (int i = 0; i < customers.length; i++) {
            Customer[] custarr = customers[i].getCustomers();
            if (choice.equals("ASCENDING")) {
                Arrays.sort(custarr, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer o1, Customer o2) {
                        if (o1.getTotalPay() < o2.getTotalPay()) {
                            return -1;
                        } else if (o1.getTotalPay() == o2.getTotalPay()) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                });
            }
            else {
                Arrays.sort(custarr, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer o1, Customer o2) {
                        if (o1.getTotalPay() < o2.getTotalPay()) {
                            return 1;
                        } else if (o1.getTotalPay() == o2.getTotalPay()) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                });
            }
            customers[i].setCustomers(custarr);
        }
        displayBasicSummary(customers);
    }
}