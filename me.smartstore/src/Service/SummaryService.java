package Service;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import handler.exception.ArrayEmptyException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static resources.Message.*;

public class SummaryService {

    private static final Customers customers = Customers.getInstance();
    private static final Groups groups = Groups.getInstance();
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

        if (group.getGroupType() == GroupType.NONE) {
            returnStr += " ( Time : 0, Pay : 0 )";
        } else {
            returnStr +=
                    " ( Time : " + group.getParameter().getMinTime() +
                            ", Pay : " + group.getParameter().getMinPay() + " )";
        }

        returnStr += "\n==============================";
        return returnStr;
    }

    public void showDefaultSummary() {
        if (customers.size() == 0) {
            System.out.println(ERR_MSG_INVALID_ARR_EMPTY);
            return;
        }

        Customer[] customerArr = customers.getCustomers();
        showSummary(customerArr);
    }

    private void showSummary(Customer[] customerArr) {
        try {
            for (int i = 0; i < groups.size(); i++) {
                System.out.println(showSummaryHeader(groups.get(i)));
                Customer[] resultArr = arrayByGroupType(groups.get(i), customerArr);

                //그룹별로 분류한 배열의 사이즈가 0이면 해당 고객이 존재하지 않는 그룹이므로 넘기기
                if (resultArr.length == 0) {
                    System.out.println(ERR_MSG_GROUP_CUSTOMER_EMPTY);
                    continue;
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

    private void categorizeByGroup() {

    }

    /**
     * 매개변수로 넘겨 받은 Customer[] 배열에서 GroupType에 일치하는 Customer 객체를 배열로 만들어서 return.
     * @param group       : 그룹 객체
     * @param customerArr : Customer[] 객체 배열
     * @return 매개변수로 넘겨 받은 GroupType과 일치하는 Custmer[] 객체 배열
     */
    private Customer[] arrayByGroupType(Group group, Customer[] customerArr) {
        return Arrays.stream(customerArr)
                .filter(Objects::nonNull)
                .filter(customer -> group.getGroupType() == customer.getGroupType())
                .toArray(Customer[]::new);
    }

    /**
     * Type별로 정렬하여 Summary 출력
     * TODO 코드 중복을 많이 줄였지만 이걸 더 추상화 할 수 없을까?
     * @param sortOrder : 정렬 방법 - ASC : false / DESC : true
     */
    public void showByName(boolean sortOrder) {
        Comparator<Customer> byName = Comparator.comparing(Customer::getCusName);
        showCommonSorted(byName, sortOrder);
    }

    public void showByTime(boolean sortOrder) {
        Comparator<Customer> byTime = Comparator.comparing(Customer::getCusTotalTime);
        showCommonSorted(byTime, sortOrder);
    }

    public void showByPayment(boolean sortOrder) {
        Comparator<Customer> byPayment = Comparator.comparing(Customer::getCusTotalPay);
        showCommonSorted(byPayment, sortOrder);
    }

    private void showCommonSorted(Comparator<Customer> comparator, boolean sortOrder) {
        Customer[] customerArr = arraySort(comparator, sortOrder);
        showSummary(customerArr);
    }

    /**
     * comparator를 모듈화 하여 정렬 로직을 재사용.
     * Stream을 적극 활용함으로서 Null safe한 코드로 재탄생
     * @param comparator : lambda expression
     * @param sortOrder  : false - ASC(default) / true - DESC
     * @return Customer[]
     */
    private Customer[] arraySort(Comparator<Customer> comparator, boolean sortOrder) {
        if (sortOrder) comparator = comparator.reversed();
        return Arrays.stream(customers.getCustomers())
                .filter(Objects::nonNull) //null 객체는 제외
                .sorted(comparator)
                .toArray(Customer[]::new);
    }
}
