package service;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.Groups;

import java.util.*;

public class SummaryService {

    private static SummaryService summaryService;
    private static final Customers customers = Customers.getInstance();
    private static final Groups groups = Groups.getInstance();
    private static final GroupService groupService = GroupService.getInstance();

    private ArrayList<ArrayList<Customer>> allCustomers;

    public static SummaryService getInstance() {
        if (Objects.isNull(summaryService)){
            summaryService = new SummaryService();
        }
        return summaryService;
    }

    private SummaryService() {
        initClassifiedCustomers();
    }

    private void initClassifiedCustomers() {
        List<Group> sortGroups = groupService.sortGroup();
        List<Customer> customerList = customers.toList();

        allCustomers = new ArrayList<>();

        for (int i = 0; i < sortGroups.size(); i++) {
            allCustomers.add(new ArrayList<>());
            allCustomers.set(i, initClassifiedCustomersForGroup(customerList, sortGroups.get(i)));
        }
    }

    private ArrayList<Customer> initClassifiedCustomersForGroup(List<Customer> customerList, Group group){
        ArrayList<Customer> customerGroup = new ArrayList<>();

        for (int i = customerList.size()-1; i >= 0; i--) {
            if (customerList.get(i).getStoreUsageTime() >= group.getParameter().getMinTime()
                    && customerList.get(i).getTotalPaymentAmount() >= group.getParameter().getMinPay()) {
                customerGroup.add(customerList.remove(i));
            }
        }

        return customerGroup;
    }

    public void refreshClassifiedCustomers(){
        initClassifiedCustomers();
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
