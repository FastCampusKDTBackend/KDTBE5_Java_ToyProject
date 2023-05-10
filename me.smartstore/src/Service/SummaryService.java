package Service;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.menu.SummaryMenu;
import handler.exception.ArrayEmptyException;
import handler.exception.InputEndException;
import handler.exception.InputFormatException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static resources.Message.*;

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

    public void showDefaultSummary(Customers customers, Groups groups) {

        Customer[] customerArr = customers.getCustomers();
        showSummary(customerArr, groups);

    }

    private void showSummary(Customer[] customerArr, Groups groups) {

        try {
            for (int i = 0; i < groups.size(); i++) {
                Group group = groups.get(i);
                System.out.println(showSummaryHeader(group));

                Customer[] resultArr = arrayByGroupType(group.getGroupType(), customerArr);

                if (resultArr.length == 0) {
                    throw new ArrayEmptyException();
                }

                AtomicInteger index = new AtomicInteger();
                Arrays.stream(resultArr)
                        .map(customer -> "No.\t" + (index.getAndIncrement() + 1) + " => " + customer)
                        .forEach(System.out::println);
            }
        } catch (IndexOutOfBoundsException | ArrayEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Groups에서 group type에 일치하는 배열을 만들어 리턴
     *
     * @param groupType : GroupType
     * @param customerArr : groups에서 추출한 원본 배열
     * @return : Customer[]
     */
    private Customer[] arrayByGroupType(GroupType groupType, Customer[] customerArr) {

        return Arrays.stream(customerArr)
                .filter(customer -> groupType == customer.getGroupType())
                .toArray(Customer[]::new);
    }

    /**
     * 이름순으로 정렬하여 Summary 출력
     *
     * @param customers : Customers 인스턴스
     * @param summaryMenu : Scanner 입력을 위한 SummaryMenu 인스턴스
     * @param groups : 그룹별 정렬을 위한 Groups 인스턴스
     */
    public void showByName(Customers customers, SummaryMenu summaryMenu, Groups groups) {
        Comparator<Customer> byName = Comparator.comparing(Customer::getCusName);
        boolean sortOrder = sortOrder(summaryMenu);
        Customer[] customerArr = arraySort(byName, sortOrder, customers);
        showSummary(customerArr, groups);
    }

    //@TODO 같은 중북 코드 제거 할 좋은 아이디어는?
    public void showByTime(Customers customers, SummaryMenu summaryMenu, Groups groups) {
        Comparator<Customer> byTime = Comparator.comparing(Customer::getCusTotalTime);
        boolean sortOrder = sortOrder(summaryMenu);
        Customer[] customerArr = arraySort(byTime, sortOrder, customers);
        showSummary(customerArr, groups);
    }

    public void showByPayment(Customers customers, SummaryMenu summaryMenu, Groups groups) {
        Comparator<Customer> byPayment = Comparator.comparing(Customer::getCusTotalPay);
        boolean sortOrder = sortOrder(summaryMenu);
        Customer[] customerArr = arraySort(byPayment, sortOrder, customers);
        showSummary(customerArr, groups);
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
        System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
        return sortOrder(summaryMenu); //정상적인 값이 들어올 때까지 재귀적 호출
    }

    /**
     * comparator를 모듈화 하여 정렬 로직을 재사용.
     * Stream을 적극 활용함으로서 Null safe한 코드가 되었음.
     *
     * @param comparator = functional lambda expression
     * @param sortOrder  = false : ASC(default) / true : DESC
     * @param customers  = Customers 인스턴스
     * @return Customer[]
     */
    private Customer[] arraySort(Comparator<Customer> comparator, boolean sortOrder, Customers customers) {
        if (sortOrder) comparator = comparator.reversed();
        return Arrays.stream(customers.getCustomers())
                .filter(Objects::nonNull)
                .sorted(comparator)
                .toArray(Customer[]::new);
    }
}
