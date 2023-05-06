package me.day10.smartstore.customer;

public class CustomerRepository {

    private static class InstanceHolder {
        private static final CustomerRepository INSTANCE = new CustomerRepository();
    }
    public static CustomerRepository getInstance() { return InstanceHolder.INSTANCE; }
    private CustomerRepository() {}

    private final List<Customer> customerList = new List<>();
    private static Customer tempCustomer;

    public void checkIfCanAddMore() throws MaxCapacityReachedException {
        if (customerList.isReachedMaxCapacity())
            throw new MaxCapacityReachedException("Repository reached the max capacity.\n");
    }

    public void resetTempCustomer() {
        tempCustomer = new Customer();
    }

    public void checkIfHasNoDuplicate(String id) throws DuplicateCustomerIdException {
        if (hasCustomerWithId(id))
            throw new DuplicateCustomerIdException("The customer already exists which has same id.");
    }

    private boolean hasCustomerWithId(String id) {
        return findIdxById(id) >= 0;
    }

    private int findIdxById(String id) {
        assert id != null;

        int size = customerList.size();
        for (int i = 1; i <= size; ++i)
            if (id.equals(customerList.get(i).getId()))
                return i;
        return -1;
    }

    public void setTempId(String id) throws InvalidCustomerIdException {
        tempCustomer.setId(id);
    }

    @Override
    public String toString() {
        if (customerList.isEmpty()) return "No Customers. Please input one first.\n";
        return super.toString();
    }
}
