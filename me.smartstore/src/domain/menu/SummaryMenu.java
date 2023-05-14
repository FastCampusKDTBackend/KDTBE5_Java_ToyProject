package domain.menu;

import Service.SummaryService;
import domain.customer.Customers;
import domain.group.Groups;
import handler.exception.InputEmptyException;
import handler.exception.InputEndException;
import handler.exception.InputFormatException;
import handler.exception.InputRangeException;

import java.util.InputMismatchException;

import static resources.Message.END_MSG;
import static resources.Message.ERR_MSG_INVALID_INPUT_RANGE;

public class SummaryMenu implements Menu {

    private static final SummaryService summaryService = SummaryService.getInstance();
    private static SummaryMenu summaryMenu;

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {

    }

    @Override
    public void manage() {
        while (true) {
            try {
                int choice = summaryMenu.selectMenu(new String[]{
                        "Summary",
                        "Summary (Sorted By Name)",
                        "Summary (Sorted By Time)",
                        "Summary (Sorted By Total Payment)",
                        "Back"
                });

                if (choice == 1) summaryService.showDefaultSummary();
                if (choice == 2) {
                    boolean sortOrder = getSortOrder();
                    summaryService.showByName(sortOrder);
                }
                if (choice == 3) {
                    boolean sortOrder = getSortOrder();
                    summaryService.showByTime(sortOrder);
                }
                if (choice == 4) {
                    boolean sortOrder = getSortOrder();
                    summaryService.showByPayment(sortOrder);
                }
                if (choice == 5) break;
            } catch (InputRangeException | InputFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 하위 메서드에서 발생한 Exception을 상위 메서드로 전달하려면 어떤 방법이 더 좋을까?
     * break로 제어를 하는게 좋은 방법일까?
     *
     * @return sortOrder - ASC : false / DESC : ture
     * @throws InputEndException
     */
    private boolean getSortOrder() {
        try {
            System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
            String order = summaryMenu.nextLineUpper(END_MSG);
            if (order.equals("A")) return false;
            else if (order.equals("D")) return true;
            else throw new InputFormatException();
        } catch (InputEmptyException e) {
            throw new InputRangeException();
        }
    }
}
