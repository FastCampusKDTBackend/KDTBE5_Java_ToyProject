package service;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;

import java.util.*;

public class SummaryService {

    private final CustomerService customerService;
    private final GroupService groupService;

    private ArrayList<ArrayList<Customer>> allCustomers;

    public SummaryService(CustomerService customerService, GroupService groupService) {
        this.customerService = customerService;
        this.groupService = groupService;
        initClassifiedCustomers();
    }

    public void refreshClassifiedCustomers(){
        initClassifiedCustomers();
    }

    private void initClassifiedCustomers() {
        List<Group> sortGroups = groupService.sortGroup();
        List<Customer> customerList = customerService.selectAllCustomer().toList();

        allCustomers = new ArrayList<>();

        for (int i = 0; i < sortGroups.size(); i++) {
            allCustomers.add(new ArrayList<>());
            allCustomers.set(i, initClassifiedCustomersForGroup(customerList, sortGroups.get(i)));
        }
    }

    private ArrayList<Customer> initClassifiedCustomersForGroup(List<Customer> customerList, Group group){
        ArrayList<Customer> customerGroup = new ArrayList<>();

        for (int i = customerList.size()-1; i >= 0; i--) {
            if (customerList.get(i).getStoreUsageTime() >= group.getMinTime()
                    && customerList.get(i).getTotalPaymentAmount() >= group.getMinPay()) {
                customerGroup.add(customerList.remove(i));
            }
        }
        return customerGroup;
    }

    public ArrayList<ArrayList<Customer>> summaryDefault(){
        return allCustomers;
    }

    public ArrayList<ArrayList<Customer>> summarySortedByName(boolean desc){
        arraySort(Comparator.comparing(Customer::getName), desc);
        return allCustomers;
    }

    public ArrayList<ArrayList<Customer>> summarySortedBySpentTime(boolean desc){
        arraySort(Comparator.comparing(Customer::getStoreUsageTime), desc);
        return allCustomers;
    }

    public ArrayList<ArrayList<Customer>> summarySortedByTotalPayment(boolean desc){
        arraySort(Comparator.comparing(Customer::getTotalPaymentAmount), desc);
        return allCustomers;
    }

    public void arraySort(Comparator<Customer> comparator, boolean desc) {
        if (desc) comparator = comparator.reversed();
        for (ArrayList<Customer> customerArrayList : allCustomers)
            customerArrayList.sort(comparator);
    }
}
