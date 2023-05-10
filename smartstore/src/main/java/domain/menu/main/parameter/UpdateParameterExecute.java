package domain.menu.main.parameter;

import domain.group.GroupType;
import util.common.ErrorMessage;
import util.common.exception.NotFoundException;
import util.view.InputScanner;
import util.view.OutputView;
import util.view.ViewMessage;

import java.util.Scanner;

public interface UpdateParameterExecute {
    static Runnable getMethod() {
        return UpdateParameterExecute::run;
    }

    private static void run() {
        Scanner scanner = InputScanner.get();
        while (true) {

            OutputView.chooseType(GroupType.generateFormatForView());
            String groupName = scanner.nextLine();

            if (ViewMessage.isExit(groupName)) {
                break;
            }

            GroupType groupType = GroupType.getBySymbolOrName(groupName);

            if (!groupType.isParameterExist()) {
                OutputView.showErrorMessage(ErrorMessage.SET_PARAMETER);
                continue;
            }


            while (true) {
                OutputView.showMenus(ModifyParameterMenu.values());
                try {
                    int menuNumber = Integer.parseInt(scanner.nextLine());

                    if (ModifyParameterMenu.isQuit(menuNumber)) {
                        break;
                    }

                    ModifyParameterMenu.findMenuAndExecution(menuNumber, groupType);
                } catch (NotFoundException | NumberFormatException exception) {
                    OutputView.showErrorMessage(exception.getMessage());
                }
            }
        }
    }
}
