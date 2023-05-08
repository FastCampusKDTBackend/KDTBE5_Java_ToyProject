package me.day10.smartstore.menu.customer;

import me.day10.smartstore.customer.CustomerRepository;
import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.CustomerMenu;

import java.util.InputMismatchException;

public class DeleteCustomerMenu extends Menu {

    private static class InstanceHolder {
        private static final DeleteCustomerMenu INSTANCE = new DeleteCustomerMenu();
    }
    private DeleteCustomerMenu() {}
    public static DeleteCustomerMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        CustomerRepository repository = CustomerRepository.getInstance();
        int size = repository.size();
        print('\n' + repository.toString());
        final String NUM_TO_DELETE_INPUT = "Which customer ( 1 ~ " + size + " )? ";
        while (true) {
            print(NUM_TO_DELETE_INPUT);
            try {
                int num = inputIntegerRanged(1, size);
                String deletedCustomerInfo = repository.deleteAndGetInfoOf(num);
                print("\nDelete\n No. " + num + ' ' + deletedCustomerInfo + "\n\n" + repository);
                return getBackMenu();
            } catch (InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(CustomerMenu.getInstance());
    }
}
