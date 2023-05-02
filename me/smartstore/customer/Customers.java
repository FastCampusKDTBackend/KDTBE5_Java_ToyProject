package smartstore.customer;

import smartstore.arrays.SArray;
import smartstore.group.Groups;

public class Customers  {

    private final SArray<Customer> customerSArray = new SArray<>();
    private static final Customers INSTANCE = new Customers();

    private Customers() {

    }

    public SArray<Customer> getCustomerSArray() {
        return customerSArray;
    }

    public static Customers getInstance() {
        return INSTANCE;
    }

    public void refresh(Groups groups) {

    }

    @Override
    public String toString() {
        return "Customers{" +
                "customerSArray=" + customerSArray +
                '}';
    }
}
