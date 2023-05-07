package me.day10.smartstore.customer;

public class CustomerRepository {

    private static class InstanceHolder {
        private static final CustomerRepository INSTANCE = new CustomerRepository();
    }
    public static CustomerRepository getInstance() { return InstanceHolder.INSTANCE; }
    private CustomerRepository() {}

    private final List<Customer> customerList = new List<>();
    private static Customer tempCustomer;
    private static int tempIdx;

    public void checkIfCanAddMore() throws MaxCapacityReachedException {
        customerList.checkIfReachedMaxCapacity();
    }

    public void resetTempCustomer() {
        tempCustomer = new Customer(null, null);
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

    public void setTempId(String id) throws InvalidCustomerIdException, DuplicateCustomerIdException {
        Customer.checkIfIdIsValid(id);
        checkIfHasNoDuplicate(id);
        tempCustomer.setId(id);
    }

    public void setTempName(String name) throws InvalidCustomerNameException {
        Customer.checkIfNameIsValid(name);
        tempCustomer.setName(name);
    }

    public void setTempSpentHours(Integer spentHours) {
        tempCustomer.setSpentHours(spentHours);
    }

    public void setTempTotalAmountPaid(Integer totalAmountPaid) {
        tempCustomer.setTotalAmountPaid(totalAmountPaid);
    }

    public String getTempInfo() {
        return tempCustomer.toString();
    }

    public void addTempIntoRepository() {
        // app logic에 따라 다음 두 구문이 true 임을 보장합니다.
        assert tempCustomer != null;
        assert !customerList.isReachedMaxCapacity();

        customerList.add(tempCustomer);
        tempCustomer = null;
    }

    public boolean isTempIdNull() {
        return tempCustomer.getId() == null;
    }

    public int size() {
        return customerList.size();
    }

    public String deleteAndGetInfoOf(int num) {
        return customerList.remove(num).toString();
    }

    public String setTempAndGetInfoOf(int num) {
        Customer updatingCustomer = customerList.get(num);
        tempCustomer = new Customer(updatingCustomer);
        tempIdx = num;
        return updatingCustomer.toString();
    }

    public void updateTempInRepository() {
        customerList.get(tempIdx).copy(tempCustomer);
    }

    public String getUpdateBeforeAndAfterInfo() {
        Customer before = customerList.get(tempIdx);
        Customer after = tempCustomer;
        return String.format("\nNo. %2d\n", tempIdx) +
                "Before: " + before.toString() + '\n'+
                "After : " + after.toString();
    }

    @Override
    public String toString() {
        if (customerList.isEmpty()) return "No Customers. Please input one first.\n";
        return customerList.toString();
    }
}
