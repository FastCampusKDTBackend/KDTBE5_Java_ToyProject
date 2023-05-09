package domain.menu.main.parameter;

import domain.group.GroupType;
import domain.group.Parameter;
import util.common.ViewMessage;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.Scanner;

public interface ViewParameterExecute {
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

                Parameter parameterOfGroup = groupType.getParameter();
                if (parameterOfGroup == null) {
                    System.out.println("현재 분류 기준이 정의되어있지 않습니다. 설정해주세용");
                    continue;
                }

                System.out.println(groupType.getParameter());
            }
        };
    }
}
