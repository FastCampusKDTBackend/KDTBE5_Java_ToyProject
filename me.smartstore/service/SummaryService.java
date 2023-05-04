package service;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import view.Output;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class SummaryService {

    private static SummaryService summaryService;
    private static Customers customers = Customers.getInstance();
    private static Groups groups = Groups.getInstance();

    private ArrayList<Customer> noneCustomer;
    private ArrayList<Customer> generalCustomer;
    private ArrayList<Customer> vipCustomer;
    private ArrayList<Customer> vvipCustomer;

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

        noneCustomer = new ArrayList<>();
        generalCustomer = new ArrayList<>();
        vipCustomer = new ArrayList<>();
        vvipCustomer = new ArrayList<>();

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
    }

    public void refreshClassifiedCustomers(){
        initClassifiedCustomers();
    }

    private void printSummary() {
        try {
            Output.customerClassifiedList(noneCustomer, groups.find(GroupType.NONE));
            Output.customerClassifiedList(generalCustomer, groups.find(GroupType.GENERAL));
            Output.customerClassifiedList(vipCustomer, groups.find(GroupType.VIP));
            Output.customerClassifiedList(vvipCustomer, groups.find(GroupType.VVIP));
        } catch (NullPointerException exception) {
            System.out.println("please Complete Groups setting");
        }
    }

    public void summaryDefault(){
        printSummary();
    }

    public void summarySortedByName(){
        noneCustomer.sort(Comparator.comparing(Customer::getName));
        generalCustomer.sort(Comparator.comparing(Customer::getName));
        vipCustomer.sort(Comparator.comparing(Customer::getName));
        vvipCustomer.sort(Comparator.comparing(Customer::getName));
        printSummary();
    }

    public void summarySortedBySpentTime(){
        noneCustomer.sort(Comparator.comparing(Customer::getStoreUsageTime));
        generalCustomer.sort(Comparator.comparing(Customer::getStoreUsageTime));
        vipCustomer.sort(Comparator.comparing(Customer::getStoreUsageTime));
        vvipCustomer.sort(Comparator.comparing(Customer::getStoreUsageTime));
        printSummary();
    }

    public void summarySortedByTotalPayment(){
        noneCustomer.sort(Comparator.comparing(Customer::getTotalPaymentAmount));
        generalCustomer.sort(Comparator.comparing(Customer::getTotalPaymentAmount));
        vipCustomer.sort(Comparator.comparing(Customer::getTotalPaymentAmount));
        vvipCustomer.sort(Comparator.comparing(Customer::getTotalPaymentAmount));
        printSummary();
    }
}
