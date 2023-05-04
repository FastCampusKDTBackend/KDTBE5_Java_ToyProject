package Customer;

import Arrays.MyArray;
import Group.Group;
import Group.GroupType;
import Group.Groups;
import Group.Parameter;

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
    private void classify(Customer customer) {
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

    private void refreshGroups() {
        general = allGroups.findGroup(GroupType.GENERAL);
        vip = allGroups.findGroup(GroupType.VIP);
        vVip = allGroups.findGroup(GroupType.VVIP);
    }

    public void refresh() {
        refreshGroups();
        for (int i = 0; i < allCustomers.size; i++) {
            classify(allCustomers.get(i));
        }
    }


}
