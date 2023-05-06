package me.day10.smartstore.menu.customer;

import me.day10.smartstore.customer.CustomerRepository;
import me.day10.smartstore.customer.DuplicateCustomerIdException;
import me.day10.smartstore.customer.InvalidCustomerIdException;
import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.exception.InputIsEndException;
import me.day10.smartstore.menu.topic.AddCustomerMenu;

import static me.day10.smartstore.customer.Customer.ID_FORMAT;

public class InputCustomerIdMenu extends Menu {

    private static final String ID_INPUT =
                    "Input " + "Customer" + "'s " + "ID" + ":\n" +
                    ID_FORMAT + '\n' +
                    END_INPUT;

    private static class InstanceHolder {
        private static final InputCustomerIdMenu INSTANCE = new InputCustomerIdMenu();
    }
    private InputCustomerIdMenu() { super(); }
    public static InputCustomerIdMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        while (true) {
            print(ID_INPUT);
            try {
                String id = inputCustomerIdOrEnd();
                CustomerRepository.getInstance().setTempId(id);
                return getBackMenu();
            } catch (InputIsEndException e) {
                print(e.getMessage());
                return getBackMenu();
            } catch (DuplicateCustomerIdException | InvalidCustomerIdException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, AddCustomerMenu.getInstance());
    }

    private String inputCustomerIdOrEnd() throws InputIsEndException, DuplicateCustomerIdException {
        String id = inputStringOrEnd();
        CustomerRepository.getInstance().checkIfHasNoDuplicate(id);
        return id;
    }
}
