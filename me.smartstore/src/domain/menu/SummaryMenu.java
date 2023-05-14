package domain.menu;

import Service.SummaryService;
import domain.customer.Customers;
import domain.group.Groups;
import handler.exception.InputEndException;
import handler.exception.InputFormatException;

import static resources.Message.END_MSG;
import static resources.Message.ERR_MSG_INVALID_INPUT_RANGE;

public class SummaryMenu implements Menu {

    // singleton
    private final SummaryService summaryService = SummaryService.getInstance();
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
            } catch (InputEndException | InputFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 하위 메서드에서 발생한 Exception을 상위 메서드로 전달하려면 어떤 방법이 더 좋을까?
     * break로 제어를 하는게 좋은 방법일까?
     * @return sortOrder - ASC : false / DESC : ture
     * @throws InputEndException
     */
    private boolean getSortOrder() throws InputEndException {
        try {
            System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
            String order = summaryMenu.nextLineUpper(END_MSG);
            if (order.equals("A")) return false;
            if (order.equals("D")) return true;
        } catch (InputFormatException e) {
            System.out.println(e.getMessage());
        }
        //정확한 입력이 아니랴면 재귀적 호출
        System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
        return getSortOrder();
    }

//    private boolean getSortOrder() throws InputEndException {
//        boolean sortOrder = false;
//        while (true) {
//            try {
//                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
//                String order = summaryMenu.nextLineUpper(END_MSG);
//                if (order.equals("A")) {
//                    break;
//                }
//                if (order.equals("D")) {
//                    sortOrder = true;
//                    break;
//                }
//            } catch (InputFormatException e) {
//                System.out.println(e.getMessage());
//            } catch (InputEndException e) {
//                throw new InputEndException();
//            }
//            System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
//        }
//        return sortOrder;
//    }
}

