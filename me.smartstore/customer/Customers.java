package customer;

import group.Groups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customers {
    private static Customers instance;
    private final List<Customer> customers;
    private int minTotalTime;
    private int minTotalPay;
    private final Map<Customer, String> customerGroup; // 추가된 필드

    private Customers() {
        customers = new ArrayList<>();
        customerGroup = new HashMap<>(); // 초기화
    }

    public static Customers getInstance() {
        if (instance == null) {
            instance = new Customers();
        }
        return instance;
    }

    public void add(Customer customer) {
        customers.add(customer);
    }

    public boolean remove(Customer customer) {
        return customers.remove(customer);
    }

    public List<Customer> getList() {
        return customers;
    }

    public void setMinTotalTime(int minTotalTime) {
        this.minTotalTime = minTotalTime;
    }

    public void setMinTotalPay(int minTotalPay) {
        this.minTotalPay = minTotalPay;
    }

    public void groupCustomers() {
        customerGroup.clear(); // 기존 분류 정보 초기화
        for (Customer customer : customers) {
            if (customer.getCusTotalTime() >= minTotalTime && customer.getCusTotalPay() >= minTotalPay) {
                customerGroup.put(customer, "VVIP");
            } else if (customer.getCusTotalTime() >= minTotalTime) {
                customerGroup.put(customer, "VIP");
            } else {
                customerGroup.put(customer, "General");
            }
        }
    }

    public void printGroupedCustomers() {
        groupCustomers();
        for (String group : new String[]{"General", "VIP", "VVIP"}) {
            System.out.println(group + " Customers:");
            for (Customer customer : customerGroup.keySet()) {
                if (customerGroup.get(customer).equals(group)) {
                    System.out.println(customer);
                }
            }
            System.out.println();
        }
    }

    public void addPurchase(String name, int pay) {
        for (Customer customer : customers) {
            if (customer.getCusName().equals(name)) {
                customer.addPurchase(pay);
                return;
            }
        }
        System.out.println("No such customer exists.");
    }

    public void addTime(String name, int time) {
        for (Customer customer : customers) {
            if (customer.getCusName().equals(name)) {
                customer.addTime(time);
                return;
            }
        }
        System.out.println("No such customer exists.");
    }

    public void printAll() {
    }

    public Customer find(String toLowerCase) {
        return null;
    }

    public void refresh(Groups allGroups) {
    }

    public boolean isEmpty() {
        return false;
    }
}
