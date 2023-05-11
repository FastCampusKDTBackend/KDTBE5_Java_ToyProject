package customer;

import arrays.DArray;
import group.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customers extends DArray<Customer> implements Iterable<Customer> {

    private static Customers allCustomers;
    private Groups allGroups;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {
        allGroups = Groups.getInstance();
    }

    // 분류 기준에 따라 모든 고객을 분류하고, 각 고객의 group 필드를 업데이트하는 함수
    public void refresh(Groups allGroups) {
        Groups groups = new Groups();
        groups.grouping(this);
    }

    // 새로운 고객을 추가하고, refresh() 함수를 호출하는 함수
    @Override
    public void add(Customer customer) {
        super.add(customer);
        refresh(allGroups);
    }

    // 고객 목록을 반환하는 메서드
    public List<Customer> getList() {
        List<Customer> customerList = new ArrayList<>();
        for (Customer customer : this) {
            customerList.add(customer);
        }
        return customerList;
    }

    public Customer find(Object o) {
        return null;
    }

    @Override
    public Iterator<Customer> iterator() {
        return new CustomerIterator();
    }

    private class CustomerIterator implements Iterator<Customer> {
        private int index;

        public CustomerIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Customer next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }
            return get(index++);
        }
    }

    public void printAll() {
        for (Customer customer : this) {
            System.out.println(customer);
        }
    }

}
