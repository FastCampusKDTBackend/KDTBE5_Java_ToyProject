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

import static resources.Message.END_MSG;
import static resources.Message.ERR_MSG_INVALID_INPUT_RANGE;

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
     * @param groupType   : GroupType
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
     * @param sortOrder : 정렬 방법 - ASC : false / DESC : true
     * @param groups    : 그룹별 정렬을 위한 Groups 인스턴스
     */
    //@TODO 코드 중복을 많이 줄였지만 이걸 더 추상화 할 수 없을까?
    public void showByName(Customers customers, boolean sortOrder, Groups groups) {
        Comparator<Customer> byName = Comparator.comparing(Customer::getCusName);
        showCommonSorted(byName, customers, sortOrder, groups);
    }

    public void showByTime(Customers customers, boolean sortOrder, Groups groups) {
        Comparator<Customer> byTime = Comparator.comparing(Customer::getCusTotalTime);
        showCommonSorted(byTime, customers, sortOrder, groups);
    }

    public void showByPayment(Customers customers, boolean sortOrder, Groups groups) {
        Comparator<Customer> byPayment = Comparator.comparing(Customer::getCusTotalPay);
        showCommonSorted(byPayment, customers, sortOrder, groups);
    }

    private void showCommonSorted(Comparator<Customer> comparator,
                                  Customers customers, boolean sortOrder, Groups groups) {
        Customer[] customerArr = arraySort(comparator, sortOrder, customers);
        showSummary(customerArr, groups);
    }

    /**
     * comparator를 모듈화 하여 정렬 로직을 재사용.
     * Stream을 적극 활용함으로서 Null safe한 코드로 재탄생
     *
     * @param comparator : lambda expression
     * @param sortOrder  : false - ASC(default) / true - DESC
     * @param customers  : Customers Object 객체
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
