package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.CustomerIntroMenu;
import me.smartstore.menu.topic.UpdateCustomerSelectPropertyMenu;

import java.util.InputMismatchException;

public class UpdateCustomerMenu extends Menu {

    private static class InstanceHolder {
        private static final UpdateCustomerMenu INSTANCE = new UpdateCustomerMenu();
    }
    private UpdateCustomerMenu() {}
    public static UpdateCustomerMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        CustomerRepository repository = CustomerRepository.getInstance();
        int size = repository.size();
        print('\n' + repository.toString());
        final String NUM_TO_UPDATE_INPUT = "Which customer ( 1 ~ " + size + " )? ";
        while (true) {
            print(NUM_TO_UPDATE_INPUT);
            try {
                int num = inputIntegerRanged(1, size);
                String updatingCustomerInfo = repository.setTempAndGetInfoOf(num);
                print("======= Updating Customer Info. =======\n" + updatingCustomerInfo + '\n');
                return UpdateCustomerSelectPropertyMenu.getInstance();
            } catch (InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(CustomerIntroMenu.getInstance());
    }
}
