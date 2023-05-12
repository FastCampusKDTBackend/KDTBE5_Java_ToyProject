package me.smartstore.customer;

import me.smartstore.customer.exception.DuplicateCustomerIdException;
import me.smartstore.customer.exception.InvalidCustomerIdException;
import me.smartstore.customer.exception.InvalidCustomerNameException;
import me.smartstore.group.Group;

public class CustomerRepository extends List<Customer> {

    private static class InstanceHolder {
        private static final CustomerRepository INSTANCE = new CustomerRepository();
    }
    public static CustomerRepository getInstance() { return InstanceHolder.INSTANCE; }
    private CustomerRepository() {}

    private Customer tempCustomer;
    private int tempIdx;

    public void resetTempCustomer() { tempCustomer = new Customer(null, null); }

    public void checkIfHasNoDuplicate(String id) throws DuplicateCustomerIdException {
        if (hasCustomerWithId(id))
            throw new DuplicateCustomerIdException("The customer already exists which has same id.");
    }

    private boolean hasCustomerWithId(String id) {
        assert id != null;
        for (Customer customer : this)
            if (id.equals(customer.getId()))
                return true;
        return false;
    }

    public void setTempId(String id) throws InvalidCustomerIdException, DuplicateCustomerIdException {
        Customer.checkIfIdIsValid(id);
        checkIfHasNoDuplicate(id);
        tempCustomer.setId(id, true);
    }

    public void setTempName(String name) throws InvalidCustomerNameException {
        tempCustomer.setName(name);
    }

    public void setTempSpentHours(Integer spentHours) { tempCustomer.setSpentHours(spentHours); }

    public void setTempTotalPaidAmount(Integer totalPaidAmount) { tempCustomer.setTotalPaidAmount(totalPaidAmount); }

    public String getTempInfo() {
        updateGroupOfTempCustomer();
        return tempCustomer.toString();
    }

    public void addTempIntoRepository() {
        // app logic에 따라 다음 두 구문이 true 임을 보장합니다.
        assert tempCustomer != null;
        assert !isReachedMaxCapacity();

        add(tempCustomer);
        tempCustomer = null;
    }

    public boolean isTempIdNull() {return tempCustomer.getId() == null; }

    public String deleteAndGetInfoOf(int num) {
        int idx = num - 1;
        return remove(idx).toString();
    }

    public String setTempAndGetInfoOf(int num) {
        int idx = num - 1;
        Customer updatingCustomer = get(idx);
        tempCustomer = new Customer(updatingCustomer);
        tempIdx = idx;
        return updatingCustomer.toString();
    }

    public void updateTempInRepository() { get(tempIdx).copy(tempCustomer); }

    public String getUpdateBeforeAndAfterInfo() {
        Customer before = get(tempIdx);
        Customer after = tempCustomer;
        updateGroupOfTempCustomer();
        return String.format("\nNo. %2d\n", tempIdx) +
                "Before: " + before + '\n'+
                "After : " + after;
    }

    public void updateGroupOfTempCustomer() { tempCustomer.updateGroup(); }

    public void updateGroupIn(Group group) {
        Group[] groups = Group.values();
        int idx = 0;
        while (groups[idx] != group)
            idx++;
        assert idx > 0;
        Group rightLowGroup = groups[idx - 1];
        for (Customer customer : this) {
            Group g = customer.getGroup();
            if (g == group || g == rightLowGroup)
                customer.updateGroup();
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) return "No Customers." + " Please input one first.\n";
        return super.toString();
    }
}
