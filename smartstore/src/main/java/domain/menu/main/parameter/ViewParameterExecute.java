package domain.menu.main.parameter;

import domain.group.GroupType;
import util.common.ErrorMessage;
import util.view.InputScanner;
import util.view.OutputView;
import util.view.ViewMessage;

import java.util.NoSuchElementException;

public interface ViewParameterExecute {
    static Runnable getMethod() {
        return ViewParameterExecute::run;
    }

    private static void run() {
        while (true) {
            OutputView.chooseType(GroupType.generateFormatForView());

            String groupName = InputScanner.get().nextLine();
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
