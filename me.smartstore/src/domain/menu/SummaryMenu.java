package domain.menu;

import Service.SummaryService;
import handler.exception.InputEmptyException;
import handler.exception.InputEndException;
import handler.exception.InputFormatException;
import handler.exception.InputRangeException;

import java.util.function.Predicate;

import static resources.Message.END_MSG;

public class SummaryMenu implements Menu {

    private static final SummaryService summaryService = SummaryService.getInstance();
    private static SummaryMenu summaryMenu;

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {}

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
                if (choice == 2) summaryService.showByName(getSortOrder());
                if (choice == 3) summaryService.showByTime(getSortOrder());
                if (choice == 4) summaryService.showByPayment(getSortOrder());
                if (choice == 5) break;
            } catch (InputRangeException | InputFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @return sortOrder - ASC : false / DESC : ture
     * @throws InputEndException
     */
    private boolean getSortOrder() {
        while (true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String order = summaryMenu.nextLineUpper(END_MSG);
                return isOrderName.test(order);
            } catch (InputEmptyException | InputFormatException | InputRangeException e) {
                System.out.println(e.getMessage());
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        throw new InputRangeException();
    }

    Predicate<String> isOrderName = input -> {
        if (input.isEmpty()) {
            throw new InputEmptyException();
        }
        if (input.equals("A")) {
            return false;
        }
        if (input.equals("D")) {
            return true;
        }
        throw new InputRangeException();
    };
}
