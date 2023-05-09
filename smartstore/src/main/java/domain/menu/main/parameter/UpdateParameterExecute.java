package domain.menu.main.parameter;

import domain.group.GroupType;
import util.common.ViewMessage;
import util.common.exception.NotFoundException;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.Scanner;

public interface UpdateParameterExecute {
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

                if (groupType.getParameter() == null) {
                    System.out.println("Parameter 설정하셈");
                    continue;
                }


                while (true) {
                    OutputView.chooseParameterForSetting();
                    try {
                        int menuNumber = Integer.parseInt(scanner.nextLine());

                        if (ModifyParameterMenu.isQuit(menuNumber)) {
                            break;
                        }

                        ModifyParameterMenu.findMenuAndExecution(menuNumber, groupType);
                    } catch (NotFoundException | NumberFormatException exception) {
                        OutputView.viewErrorMessage(exception.getMessage());
                    }
                }
            }
        };
    }
}
