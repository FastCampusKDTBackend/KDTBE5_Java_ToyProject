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

public interface SetParameterExecute {
    static Runnable getMethod() {
        return SetParameterExecute::run;
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

    private static GroupType getGroup() {
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
                System.out.println(noSuchElementException);
                groupName = InputScanner.get().nextLine();
                if (ViewMessage.isExit(groupName)) {
                    return null;
                }
            }
        }

    }

    private static void run() {
        while (true) {

            GroupType groupType = getGroup();

            if (Objects.isNull(groupType)) {
                return;
            }


            if (groupType.isParameterExist()) {
                OutputView.showErrorMessage(ErrorMessage.ALREADY_SET);
                continue;
            }

            groupType.initParameter();

            executeSubmenu(groupType);
        }
    }
}
