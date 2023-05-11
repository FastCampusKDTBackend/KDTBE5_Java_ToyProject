package me.smartstore.summary;

import me.smartstore.customer.Customer;
import me.smartstore.customer.CustomerRepository;
import me.smartstore.group.Group;

public abstract class Summary {

    protected static CustomerRepository repository = CustomerRepository.getInstance();

    public static String get() { return getSummary(); }

    public abstract String get(Order order);

    protected static String getSummary() {
        Group[] groups = Group.values();
        int len = groups.length;
        StringBuilder[] stringBuilders = new StringBuilder[len];
        for (int i = 0; i < len; ++i) {
            StringBuilder sb = new StringBuilder();
            sb.append('\n').append("==============================")
                    .append(groups[i])
                    .append("==============================").append('\n');
            stringBuilders[i] = sb;
        }

        int[] cnt = new int[len];
        for (Customer customer : repository) {
            Group group = customer.getGroup();
            for (int i = 0; i < len; ++i) {
                if (group == groups[i]) {
                    stringBuilders[i].append(customer).append('\n');
                    cnt[i]++;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            sb.append(stringBuilders[i]);
            if (cnt[i] == 0)
                sb.append("No Customers.").append('\n');
        }
        return sb.append('\n').toString();
    }
}
