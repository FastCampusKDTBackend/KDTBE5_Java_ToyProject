package domain.menu.main.parameter;

import domain.group.GroupType;
import util.common.ErrorMessage;
import util.common.exception.NotFoundException;
import util.view.InputScanner;
import util.view.OutputView;
import util.view.ViewMessage;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public interface UpdateParameterExecute {
    static Runnable getMethod() {
        return UpdateParameterExecute::run;
    }


    private static void run() {
        while (true) {
            GroupType groupType = inputGroupName();

            if (Objects.isNull(groupType)) {
                return;
            }

            if (!groupType.isParameterExist()) {
                OutputView.showErrorMessage(ErrorMessage.SET_PARAMETER);
                continue;
            }

            executeSubmenu(groupType);
        }
    }


    private static GroupType inputGroupName() {
        while (true) {
            OutputView.chooseType(GroupType.generateFormatForView());
            String groupName = InputScanner.get().nextLine();
            if (ViewMessage.isExit(groupName)) {
                return null;
            }
            try {
                return getGroupType(groupName);
            } catch (NoSuchElementException e) {
                OutputView.showErrorMessage(e.getMessage());
            }
        }
    }

    private static GroupType getGroupType(String groupName) {
        while (true) {
            try {
                return GroupType.getBySymbolOrName(groupName);
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println(noSuchElementException.getMessage());
                groupName = InputScanner.get().nextLine();
                if (ViewMessage.isExit(groupName)) {
                    return null;
                }
            }
        }

    }

    private static void executeSubmenu(GroupType groupType) {
        while (true) {
            OutputView.showMenus(ModifyParameterMenu.values());
            try {
                int menuNumber = Integer.parseInt(InputScanner.get().nextLine());

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
