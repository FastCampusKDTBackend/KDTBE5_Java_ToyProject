package me.day10.smartstore.menu.topic;

import me.day10.smartstore.customer.CustomerRepository;
import me.day10.smartstore.customer.MaxCapacityReachedException;
import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.customer.*;

public class AddCustomerMenu extends TopicIntroMenu {

    private static final String ADD_CUSTOMER_MENU_OUTPUT =
                            '\n' +
                            "======= Customer Info. =======" + '\n' +
                            " 1. " + "Customer " + "ID" + '\n' +
                            " 2. " + "Customer " + "Name" + '\n' +
                            " 3. " + "Customer " + "Spent Hours" + '\n' +
                            " 4. " + "Customer " + "Total Amount Paid" + '\n' +
                            " 5. " + "Confirm" + '\n' +
                            " 6. " + "Cancel(Back)" + '\n' +
                            "==============================" + '\n' +
                            "Choose One: ";
    private static final AddCustomerMenu INSTANCE = new AddCustomerMenu(
            null,
            InputCustomerIdMenu.getInstance(),              // id
            InputCustomerNameMenu.getInstance(),            // name
            InputCustomerSpentHoursMenu.getInstance(),      // spent hours
            InputCustomerTotalAmountPaidMenu.getInstance(), // total amount paid
            AddCustomerConfirmMenu.getInstance(),           // confirm
            null                                            // cancel(back) => CustomerMenu
    );
    private AddCustomerMenu(Menu... nextMenus) { super(ADD_CUSTOMER_MENU_OUTPUT, nextMenus); }
    public static AddCustomerMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        CustomerRepository repository = CustomerRepository.getInstance();
        Menu backMenu = CustomerMenu.getInstance();
        setBackMenu(backMenu);
        try {
            repository.checkIfCanAddMore();
        } catch (MaxCapacityReachedException e) {
            print(e.getMessage());
            return backMenu;
        }
        repository.resetTempCustomer();
        return inputMenuAndMoveToNextMenu();
    }
}
