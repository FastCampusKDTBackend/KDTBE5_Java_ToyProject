package service;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import view.Output;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SummaryService {

    private static SummaryService summaryService;
    private static Customers customers = Customers.getInstance();
    private static Groups groups = Groups.getInstance();

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

    private void initClassifiedCustomers(){
        Group general = groups.find(GroupType.GENERAL);
        Group vip = groups.find(GroupType.VIP);
        Group vvip = groups.find(GroupType.VVIP);

        ArrayList<Customer> noneCustomer = new ArrayList<>();
        ArrayList<Customer> generalCustomer = new ArrayList<>();
        ArrayList<Customer> vipCustomer = new ArrayList<>();
        ArrayList<Customer> vvipCustomer = new ArrayList<>();

        try {
            for (Customer customer : customers.toList()) {
                if (customer.getStoreUsageTime() >= vvip.getParameter().getMinTime() &&
                        customer.getTotalPaymentAmount() >= vvip.getParameter().getMinPay())
                    vvipCustomer.add(customer);
                else if (customer.getStoreUsageTime() >= vip.getParameter().getMinTime() &&
                        customer.getTotalPaymentAmount() >= vip.getParameter().getMinPay())
                    vipCustomer.add(customer);
                else if (customer.getStoreUsageTime() >= general.getParameter().getMinTime() &&
                        customer.getTotalPaymentAmount() >= general.getParameter().getMinPay())
                    generalCustomer.add(customer);
                else noneCustomer.add(customer);
            }
        } catch (NullPointerException ignored){ }
        allCustomers = new ArrayList<>(List.of(noneCustomer, generalCustomer, vipCustomer, vvipCustomer));
    }

    public void refreshClassifiedCustomers(){
        initClassifiedCustomers();
    }

    private void printSummary() {
        try {
            Output.customerClassifiedList(allCustomers, groups.toList());
        } catch (NullPointerException exception) {
            System.out.println("please Complete Groups setting");
        }
    }

    public void summaryDefault(){
        printSummary();
    }

    public void summarySortedByName(boolean desc){
        arraySort(Comparator.comparing(Customer::getName), desc);
    }

    public void summarySortedBySpentTime(boolean desc){
        arraySort(Comparator.comparing(Customer::getStoreUsageTime), desc);
    }

    public void summarySortedByTotalPayment(boolean desc){
        arraySort(Comparator.comparing(Customer::getTotalPaymentAmount), desc);
    }

    public void arraySort(Comparator<Customer> comparator, boolean desc) {
        if (desc) comparator = comparator.reversed();
        for (ArrayList<Customer> customerArrayList : allCustomers)
            customerArrayList.sort(comparator);
        printSummary();
    }
}
