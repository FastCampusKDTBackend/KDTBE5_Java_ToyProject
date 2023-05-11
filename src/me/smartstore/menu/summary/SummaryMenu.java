package me.smartstore.menu.summary;

import me.smartstore.summary.Order;
import me.smartstore.menu.Menu;
import me.smartstore.menu.exception.InputIsEndException;

import java.util.InputMismatchException;

import static me.smartstore.summary.Order.getOrderByName;

public abstract class SummaryMenu extends Menu {
    private static final String ORDER_INPUT =
                "Which order (ASCENDING (A), DESCENDING (D))?\n" +
                END_INPUT;

    @Override
    public Menu printAndInputAndGetNextMenu() {
        while (true) {
            print(ORDER_INPUT);
            try {
                String orderName = inputStringOrEnd();
                Order order = getOrderByName(orderName);
                print(getSummary(order));
                return getPrevMenu();
            } catch (InputIsEndException e) {
                print(e.getMessage());
                return getPrevMenu();
            } catch (InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    protected void setNextMenus() {}

    protected abstract String getSummary(Order order);
}
