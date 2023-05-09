package domain.menu.main.parameter;

import domain.group.GroupType;
import util.common.ViewMessage;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.Scanner;

public interface SetParameterExecute {
    static Runnable getMethod() {
        return () -> {
            Scanner scanner = InputScanner.get();
            while (true) {

                OutputView.chooseGroup(GroupType.generateFormatForView());
                String groupName = scanner.nextLine();
                if (groupName.equals(ViewMessage.EXIT_CHOICE)) {
                    break;
                }

                GroupType groupType = GroupType.getBySymbolOrName(groupName);

                if (groupType.getParameter() != null) {
                    System.out.println("해당 그룹은 이미 설정되어 있음");
                }

                groupType.initParameter();


                while (true) {
                    OutputView.chooseParameterForSetting();


                    String menu = scanner.nextLine();
                    if (menu.equals("1")) {
                        System.out.println("Input Minimum Spent Time:\n");
                        OutputView.viewExitGuide();
                        String minimumSpentTime = scanner.nextLine();
                        groupType.getParameter().setMinimumSpentTime(Integer.parseInt(minimumSpentTime));
                    } else if (menu.equals("2")) {
                        System.out.println("Input Minimum Total Pay:\n");
                        OutputView.viewExitGuide();
                        String minimumSpentTime = scanner.nextLine();
                        groupType.getParameter().setMinimumTotalPay(Integer.parseInt(minimumSpentTime));
                    } else if (menu.equals("3")) {
                        break;
                    } else {
                        continue;
                    }
                }

            }
        };
    }
}
