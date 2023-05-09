package menu.subMenu;

import domain.customer.Customer;
import exception.ArrayEmptyException;
import exception.InputEndException;
import exception.InputNumOfEndMenuException;
import menu.MenuManager;
import service.GroupService;
import service.SummaryService;
import util.view.Input;
import util.view.Output;

import java.util.ArrayList;

public class SummaryMenu implements SubMenu {

    private static final String[] MENU_ITEMS;

    static {
        MENU_ITEMS = new String[]{"Summary", "Summary (Sorted By Name)", "Summary (Sorted By Spent Time)", "Summary (Sorted By Total Payment)", "Back"};
    }

    private final SummaryService summaryService;
    private final GroupService groupService;

    public SummaryMenu(SummaryService summaryService, GroupService groupService) {
        this.summaryService = summaryService;
        this.groupService = groupService;
    }
    @Override
    public void service() {
        while (true) {
            try {
                int choiceMenuNum = MenuManager.chooseMenu(MENU_ITEMS);
                if (choiceMenuNum == 1) printSummaryDefault();
                if (choiceMenuNum == 2) printSummarySortedByName();
                if (choiceMenuNum == 3) printSummarySortedBySpentTime();
                if (choiceMenuNum == 4) printSummarySortedByTotalPayment();
            } catch (InputEndException | ArrayEmptyException exception) {
                System.out.println(exception.getMessage());
            } catch (InputNumOfEndMenuException exception) {
                break;
            }
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
