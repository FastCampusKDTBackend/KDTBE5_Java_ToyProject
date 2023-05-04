package controller.menu;

import domain.customer.Customer;
import domain.group.Group;
import exception.ArrayEmptyException;
import exception.InputEndException;
import service.GroupService;
import service.SummaryService;
import view.Input;
import view.Output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
            if (menuNum == 2) summaryService.summarySortedByName(Input.isSummarySortOrderDesc());
            if (menuNum == 3) summaryService.summarySortedBySpentTime(Input.isSummarySortOrderDesc());
            if (menuNum == 4) summaryService.summarySortedByTotalPayment(Input.isSummarySortOrderDesc());
        } catch (InputEndException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void printSummaryDefault() {
        try {
            ArrayList<ArrayList<Customer>> allCustomers = summaryService.summaryDefault();
            printSummary(allCustomers);
        } catch (ArrayEmptyException exception) {
            Output.printErrorMessage(exception.getMessage());
        }
    }

    // 뭔가 잘못됨이 느껴지는 메서드 나중에 처리할 것
    private void printSummary(ArrayList<ArrayList<Customer>> allCustomers) {
        try {
            List<Group> sortGroup = groupService.sortGroup();
            Collections.reverse(allCustomers);
            Collections.reverse(sortGroup);
            Output.customerClassifiedList(allCustomers, sortGroup);
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            System.out.println("please Complete Groups setting");
        }
    }
}
