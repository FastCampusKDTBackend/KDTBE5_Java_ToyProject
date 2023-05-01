package me.smartstore.domain.customer;

import me.smartstore.domain.group.Group;
import me.smartstore.domain.group.Groups;
import me.smartstore.utils.MyArray;

import java.util.Arrays;
import java.util.Comparator;

import static me.smartstore.domain.group.GroupType.getTypeLevel;

public class Customers extends MyArray<Customer> {
    private static Customers allCustomers;
    private final Groups allGroups;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {
        this.allGroups = Groups.getInstance();
    }

    // TODO: Refresh 메서드 구현 (새로운 고객 추가, 분류기준 변경시 작동)
    public void refresh() {
        for (int i = 0; i < allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);
            Group group = findGroupOfCustomer(customer.getSpentTime(), customer.getTotalPay());
            customer.setGroup(group);
        }
    }

    public Group findGroupOfCustomer(int usageTime, int purchaseAmount) {
        Group group = allGroups.get(0); // 처음에는 NONE
        for (int i = 1; i < allGroups.size(); i++) {
            Group pickedGroup = allGroups.get(i);
            int minUsageTime = pickedGroup.getParameter().getMinUsageTime();
            int minPurchase = pickedGroup.getParameter().getMinPurchaseAmount();

            if (usageTime >= minUsageTime && purchaseAmount >= minPurchase) {
                if (getTypeLevel(group) < getTypeLevel(pickedGroup)) {
                    group = pickedGroup;
                }
            }
        }
        return group;
    }

    public Customer[] getCustomers() {
        Customer[] customers = new Customer[size];
        for (int i = 0; i < size; i++) {
            customers[i] = allCustomers.get(i);
        }
        return customers;
    }

    /**
     * 원하는 정렬 기준을 가진 Comparator를 파라미터로 받아 정렬된 Customer 배열을 얻어내는 메서드
     * @param comparator: 정렬하고자 하는 기준으로 Comparator를 만들어 파라미터로 입력하세요.
     * @return 원하는 정렬 기준으로 정렬된 Customer 배열을 리턴합니다.
     */
    public Customer[] getSortedCustomers(Comparator<Customer> comparator) {
        Customer[] customers = getCustomers();
        Arrays.sort(customers, comparator);
        return customers;
    }
}
