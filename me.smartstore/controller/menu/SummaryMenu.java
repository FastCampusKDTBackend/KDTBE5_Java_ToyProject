package controller.menu;

import domain.customer.Customer;
import exception.ArrayEmptyException;
import exception.InputEndException;
import service.GroupService;
import service.SummaryService;
import view.Input;
import view.Output;

import java.util.ArrayList;
import java.util.Objects;

public class SummaryMenu implements Menu {

    private static final String[] MENU_ITEMS;
    private static final int MENU_ITEMS_MAX_NUM;
    private static final int MENU_ITEMS_MIN_NUM;

    private static SummaryMenu summaryMenu;
    private static final SummaryService summaryService;
    private static final GroupService groupService;

    static {
        MENU_ITEMS = new String[]{"Summary", "Summary (Sorted By Name)",
                "Summary (Sorted By Spent Time)", "Summary (Sorted By Total Payment)", "Back"};
        MENU_ITEMS_MAX_NUM = MENU_ITEMS.length;
        MENU_ITEMS_MIN_NUM = 1;
        summaryService = SummaryService.getInstance();
        groupService = GroupService.getInstance();
    }

    public static SummaryMenu getInstance() {
        if (Objects.isNull(summaryMenu)){
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {

    }

    @Override
    public String[] items() {
        return MENU_ITEMS;
    }

    @Override
    public int menuItemsMaxNum() {
        return MENU_ITEMS_MAX_NUM;
    }

    @Override
    public int menuItemsMinNum() {
        return MENU_ITEMS_MIN_NUM;
    }

    @Override
    public void service(int menuNum) {
        try{
            if (menuNum == 1) printSummaryDefault();
            if (menuNum == 2) printSummarySortedByName();
            if (menuNum == 3) printSummarySortedBySpentTime();
            if (menuNum == 4) printSummarySortedByTotalPayment();
        } catch (InputEndException | ArrayEmptyException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void printSummary(ArrayList<ArrayList<Customer>> allCustomers) {
        try{
            Output.customerClassifiedList(allCustomers, groupService.sortGroup());
        }catch (ArrayEmptyException exception) {
            Output.printErrorMessage(exception.getMessage());
        }
    }

    private void printSummaryDefault() {
        ArrayList<ArrayList<Customer>> allCustomers = summaryService.summaryDefault();
        printSummary(allCustomers);
    }

    private void printSummarySortedByName(){
        ArrayList<ArrayList<Customer>> allCustomers = summaryService.summarySortedByName(Input.isSummarySortOrderDesc());
        printSummary(allCustomers);
    }

    private void printSummarySortedBySpentTime(){
        ArrayList<ArrayList<Customer>> allCustomers = summaryService.summarySortedBySpentTime(Input.isSummarySortOrderDesc());
        printSummary(allCustomers);
    }

    private void printSummarySortedByTotalPayment() {
        ArrayList<ArrayList<Customer>> allCustomers = summaryService.summarySortedByTotalPayment(Input.isSummarySortOrderDesc());
        printSummary(allCustomers);
    }
}
