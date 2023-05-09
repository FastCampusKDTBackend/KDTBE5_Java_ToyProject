package domain.menu.main.parameter;

import domain.group.GroupType;
import util.common.exception.NotFoundException;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.Arrays;
import java.util.function.Consumer;

public enum ModifyParameterMenu {
    SET_SPENT_TIME(
            1,
            "Minumum Spent Time",
            (groupType) -> {
                System.out.println("Input Minimum Spent Time:\n");
                OutputView.viewExitGuide();
                String minimumSpentTime = InputScanner.get().nextLine();
                groupType.getParameter().setMinimumSpentTime(Integer.parseInt(minimumSpentTime));
            }
    ),
    SET_TOTAL_PAY(
            2,
            "Minimun Total Pay",
            (groupType) -> {
                System.out.println("Input Minimum Total Pay:\n");
                OutputView.viewExitGuide();
                String minimumSpentTime = InputScanner.get().nextLine();
                groupType.getParameter().setMinimumTotalPay(Integer.parseInt(minimumSpentTime));
            }
    );

    private final int menuNumber;
    private final String menuName;
    private final Consumer<GroupType> consumer;

    ModifyParameterMenu(int menuNumber, String menuName, Consumer<GroupType> consumer) {
        this.menuNumber = menuNumber;
        this.menuName = menuName;
        this.consumer = consumer;
    }

    public boolean isEqualMenuNumber(int menuNumber) {
        return this.menuNumber == menuNumber;
    }

    public static boolean isQuit(int menuNumber) {
        return values().length + 1 == menuNumber;
    }

    @Override
    public String toString() {
        return "  " + menuNumber + ". " + menuName + "\n";
    }

    public void execute(GroupType groupType) {
        consumer.accept(groupType);
    }

    public static void findMenuAndExecution(int menuNumber, GroupType groupType) {
        Arrays.stream(ModifyParameterMenu.values())
                .filter(subMenus -> subMenus.isEqualMenuNumber(menuNumber))
                .findFirst()
                .orElseThrow(NotFoundException::new)
                .execute(groupType);
    }
}
