package Customer;

import Arrays.MyArray;
import Group.Group;
import Group.GroupType;
import Group.Groups;
import Group.Parameter;

import java.util.Arrays;
import java.util.Comparator;

public class Customers extends MyArray<Customer> {
    private static Group general;
    private static Group vip;
    private static Group vVip;
    private final Groups allGroups = Groups.getInstance();
    //singleton
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {

    }

    //예외처리,
    private void classifyCustomers(Customer customer) {
        Integer time = customer.getTotalTime();
        Integer pay = customer.getTotalPay();

        if (time >= vVip.getParameter().getMinTime() && pay >= vVip.getParameter().getMinPay()) {
            customer.setGroup(vVip);
        } else if (time >= vip.getParameter().getMinTime() && pay >= vip.getParameter().getMinPay()) {
            customer.setGroup(vip);
        } else if (time >= general.getParameter().getMinTime() && pay >= general.getParameter().getMinPay()) {
            customer.setGroup(general);
        }
    }

    private void refreshGroupsInfo() {
        general = allGroups.findGroup(GroupType.GENERAL);
        vip = allGroups.findGroup(GroupType.VIP);
        vVip = allGroups.findGroup(GroupType.VVIP);
    }

    public void refresh() {
        refreshGroupsInfo();
        for (int i = 0; i < allCustomers.size; i++) {
            classifyCustomers(allCustomers.get(i));
        }
    }


    //todo 스트림으로 변경
    public Customer[] separateOfRank(GroupType groupType) {
        int cnt = 0;
        for (int i = 0; i < allCustomers.size; i++) {
            if (allCustomers.get(i).getGroup().getGroupType().equals(groupType)) cnt++;
        }

        Customer[] groupArray = new Customer[cnt];
        int index = 0;

        for (int i = 0; i < allCustomers.size; i++) {
            if (allCustomers.get(i).getGroup().getGroupType().equals(groupType)){
                groupArray[index] = allCustomers.get(i);
                index++;
            }
        }
        return groupArray;

    }

    public void sortByPaySummary(Customer[] value, int mark) {
        Arrays.sort(value, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return (o1.getTotalPay().compareTo(o2.getTotalPay()) * mark);
            }
        });
    }

    public void sortByTimeSummary(Customer value[], int mark) {
        Arrays.sort(value, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return (o1.getTotalTime().compareTo(o2.getTotalTime()) * mark);
            }
        });
    }

    public void sortByNameSummary(Customer[] value, int mark) {
        Arrays.sort(value, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return (o1.getCtmName().compareTo(o2.getCtmName()) * mark);
            }
        });
    }
}
