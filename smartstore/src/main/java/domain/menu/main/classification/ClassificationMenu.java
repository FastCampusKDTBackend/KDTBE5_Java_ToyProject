package domain.menu.main.classification;

import domain.menu.Menu;
import util.comparator.customer.ComparatorByName;
import util.comparator.customer.ComparatorBySpentTime;
import util.comparator.customer.ComparatorByTotalPayment;

import java.util.Arrays;
import java.util.Optional;

public enum ClassificationMenu implements Menu {
    SUMMARY(
            1,
            "Summary",
            ClassificationMenuExecute.getMethod(
                    null
            )
    ),
    SORTED_BY_NAME(
            2,
            "Summary (Sorted By Name)",
            ClassificationMenuExecute.getMethod(
                    new ComparatorByName<>()
            )
    ),
    SORTED_BY_SPENT_TIME(
            3,
            "Summary (Sorted By Spent Time)",
            ClassificationMenuExecute.getMethod(
                    new ComparatorBySpentTime<>()
            )
    ),
    SORTED_BY_TOTAL_PAYMENT(
            4,
            "Summary (Sorted By Total Payment)",
            ClassificationMenuExecute.getMethod(
                    new ComparatorByTotalPayment<>()
            )
    );

    private final int menuNumber;
    private final String menuName;
    private final Runnable runnable;

    ClassificationMenu(int menuNumber, String menuName, Runnable runnable) {
        this.menuNumber = menuNumber;
        this.menuName = menuName;
        this.runnable = runnable;
    }

    public static Optional<ClassificationMenu> findByNumber(int menuNumber) {
        return Arrays.stream(ClassificationMenu.values())
                .filter(mainMenu -> mainMenu.menuNumber == menuNumber)
                .findFirst();
    }

    public static boolean isQuit(int menuNumber) {
        return ClassificationMenu.values().length + 1 == menuNumber;
    }

    @Override
    public void execute() {
        runnable.run();
    }

    @Override
    public String toString() {
        return "  " + menuNumber + ". " + menuName + "\n";
    }
}
