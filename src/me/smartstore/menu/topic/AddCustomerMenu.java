package me.smartstore.menu.topic;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.customer.MaxCapacityReachedException;
import me.smartstore.menu.Menu;
import me.smartstore.menu.customer.*;

public class AddCustomerMenu extends TopicIntroMenu {

    private static final String ADD_CUSTOMER_MENU_OUTPUT =
                            '\n' +
                            "======= " + "Customer " + "Info. =======" + '\n' +
                            " 1. " + "Customer " + "ID" + '\n' +
                            " 2. " + "Customer " + "Name" + '\n' +
                            " 3. " + "Customer " + "Spent Hours" + '\n' +
                            " 4. " + "Customer " + "Total Amount Paid" + '\n' +
                            " 5. " + "Confirm" + '\n' +
                            " 6. " + "Cancel(Back)" + '\n' +
                            "==============================" + '\n' +
                            "Choose One: ";

    private static class InstanceHolder {
        private static final AddCustomerMenu INSTANCE = new AddCustomerMenu();
    }

    private AddCustomerMenu() { super(ADD_CUSTOMER_MENU_OUTPUT); }

    public static AddCustomerMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        CustomerRepository repository = CustomerRepository.getInstance();
        setNextMenus();
        try {
            repository.checkIfCanAddMore();
        } catch (MaxCapacityReachedException e) {
            print(e.getMessage());
            return getBackMenu();
        }
        if (getPrevMenu() == CustomerMenu.getInstance())
            repository.resetTempCustomer();
        return inputMenuAndMoveToNextMenu();
    }

    @Override
    protected void setNextMenus() {
        Menu[] nextMenus = {
                InputCustomerIdMenu.getInstance(),              // id
                InputCustomerNameMenu.getInstance(),            // name
                InputCustomerSpentHoursMenu.getInstance(),      // spent hours
                InputCustomerTotalPaidAmountMenu.getInstance(), // total amount paid
                AddCustomerConfirmMenu.getInstance(),           // confirm
                CustomerMenu.getInstance()                      // cancel(back) => CustomerMenu
        };
        for (int i = 0; i <= 3; ++i)
            nextMenus[i].setNextMenus(null, this);
        setNextMenus(nextMenus);
    }
}
