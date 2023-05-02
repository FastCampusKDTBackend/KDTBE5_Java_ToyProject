package Customer;

import Arrays.MyArray;

public class Customers extends MyArray {

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
}
