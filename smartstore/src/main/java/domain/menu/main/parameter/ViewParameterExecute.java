package domain.menu.main.parameter;

import domain.group.GroupType;
import domain.group.Parameter;
import util.common.ViewMessage;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.NoSuchElementException;
import java.util.Scanner;

public interface ViewParameterExecute {
    static Runnable getMethod() {
        return ViewParameterExecute::run;
    }

    private static void run() {
        Scanner scanner = InputScanner.get();
        while (true) {

            OutputView.chooseType(GroupType.generateFormatForView());

            String groupName = scanner.nextLine();
            if (ViewMessage.isQuitMenuName(groupName)) {
                break;
            }

            try {
                GroupType groupType = GroupType.getBySymbolOrName(groupName);


                if (!groupType.isParameterExist()) {
                    OutputView.showErrorMessage(ErrorMessage.SET_PARAMETER);
                    continue;
                }

                OutputView.showInfo(groupType.getParameter().toString());
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println(noSuchElementException.getMessage());
            }
        }
    }
}
