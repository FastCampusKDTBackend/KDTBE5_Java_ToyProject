package domain.menu.main.parameter;

import domain.group.GroupType;
import util.common.ViewMessage;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.Scanner;

public interface UpdateParameterExecute {
    static Runnable getMethod() {

        return () -> {
            Scanner scanner = InputScanner.get();
            while (true) {


                OutputView.chooseGroup();

                String groupName = scanner.nextLine();
                if (groupName.equals(ViewMessage.EXIT_CHOICE)) {
                    break;
                }
                GroupType groupType = GroupType.getBySymbolOrName(groupName);

                if (groupType.getParameter() == null) {
                    System.out.println("Parameter 설정하셈");
                    continue;
                }


                OutputView.chooseParameterForSetting();


                String menu = scanner.nextLine();
                if (menu.equals("1")) {
                    System.out.println("Input Minimum Spent Time:\n");
                    OutputView.viewExitGuide();
                    String minimumSpentTime = scanner.nextLine();
                    groupType.getParameter().setMinimumSpentTime(Integer.parseInt(minimumSpentTime));
                    return;
                } else if (menu.equals("2")) {
                    System.out.println("Input Minimum Total Pay:\n");
                    OutputView.viewExitGuide();
                    String minimumSpentTime = scanner.nextLine();
                    groupType.getParameter().setMinimumTotalPay(Integer.parseInt(minimumSpentTime));
                    return;
                } else if (menu.equals("3")) {
                    return;
                } else {
                    continue;
                }
            }
        };
    }
}
