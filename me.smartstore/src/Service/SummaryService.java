package Service;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.menu.SummaryMenu;
import handler.exception.*;

import java.util.*;
import java.util.stream.Stream;

public class SummaryService {

    private static SummaryService summaryService;

    public static SummaryService getInstance() {
        if (summaryService == null) {
            summaryService = new SummaryService();
        }
        return summaryService;
    }

    private SummaryService() {
    }

    private String showSummaryHeader(Group group) {
        String returnStr = "\n=============================="
                + "\nGroup : " + group.getGroupType();

        if (group.getParameter() == null) {
            returnStr += " ( Time : null, Pay : null )";
        } else {
            returnStr +=
                    " ( Time : " + group.getParameter().getMinTime() +
                            ", Pay : " + group.getParameter().getMinPay() + " )";
        }

        returnStr += "\n==============================";
        return returnStr;
    }

    public void showDefault(Customers customers, Groups groups) {

        try {
            for (int i = 0; i < groups.size(); i++) {
                Group group = groups.get(i);
                System.out.println(showSummaryHeader(group));

                Customer[] customerArr = arrayByGroupType(group.getGroupType(), customers);

                if (customerArr.length == 0) {
                    throw new ArrayEmptyException();
                }

                Arrays.stream(customerArr).forEach(System.out::println);
            }
        } catch (IndexOutOfBoundsException | ArrayEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    private Customer[] arrayByGroupType(GroupType groupType, Customers customers) {
        Customer[] customerArray = new Customer[customers.size()];

        for (int i = 0; i < customers.size(); i++) {
//            System.out.println("array by group type : " + customers.get(i).getGroupType());
            if (groupType == customers.get(i).getGroupType()) {
                customerArray[i] = customers.get(i);
            }
        }
        // 일단 배열 크기를 customers의 size로 잡은 후 return 시 nonNull인 값들로만 새 배열 생성
        return Arrays.stream(customerArray).filter(Objects::nonNull).toArray(Customer[]::new);
    }

    /**
     * 이름순으로 정렬하여 Summary 출력
     *
     * @param customers : Customers 인스턴스
     * @param summaryMenu : Scanner 입력을 위한 SummaryMenu 인스턴스
     * @param groups : 그룹별 정렬을 위한 Groups 인스턴스
     */
    public void sortByName(Customers customers, SummaryMenu summaryMenu, Groups groups) {

        Comparator<Customer> byName = Comparator.comparing(Customer::getCusName);
        boolean sortOrder = sortOrder(summaryMenu);
        arraySort(byName, sortOrder, customers);
        showDefault(customers, groups);

    }

    public void sortByTime(Customers customers, SummaryMenu summaryMenu, Groups groups) {
    }

    public void sortByPayment(Customers customers, SummaryMenu summaryMenu, Groups groups) {
    }

    private boolean sortOrder(SummaryMenu summaryMenu) {
        try {
            System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
            String order = summaryMenu.nextLineUpper();
            if (order.equals("A")) return false; //ASC
            if (order.equals("D")) return true; //DESC
        } catch (InputEndException e) {
            System.out.println(e.getMessage());
            return sortOrder(summaryMenu); //정상 값이 아니면 재귀적 호출
        } catch (InputFormatException e) {
            System.out.println(e.getMessage());
        }
        return sortOrder(summaryMenu);
    }

    /**
     *
     * @param comparator = functional lambda expression
     * @param sortOrder = false : ASC(default) / true : DESC
     * @param customers = Customers 인스턴스
     * @return Customers
     */
    private Customers arraySort(Comparator<Customer> comparator, boolean sortOrder, Customers customers) {
        if (sortOrder) comparator.reversed();
        Arrays.sort(customers.getCustomers(), comparator);
        Stream.of(customers).forEach(System.out::println);
        return customers;
    }
}
