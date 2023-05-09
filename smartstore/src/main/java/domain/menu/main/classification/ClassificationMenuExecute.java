package domain.menu.main.classification;

import domain.customer.ClassifiedCustomers;
import domain.customer.Customer;
import domain.group.GroupType;
import domain.menu.type.SortType;
import util.common.ViewMessage;
import util.common.exception.NotFoundException;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.Arrays;
import java.util.Comparator;

public interface ClassificationMenuExecute {
    static Runnable getMethod(Comparator<Customer> comparator) {
        return () -> {
            while (true) {
                if (comparator == null) {
                    executeView(null);
                    return;
                }

                OutputView.chooseGroup(SortType.generateFormatForView());
                String command = InputScanner.get().nextLine();

                if (command.equals(ViewMessage.EXIT_CHOICE)) {
                    return;
                }

                try {
                    SortType sortType = getSortOrder(command);
                    if (sortType.equals(SortType.ASCENDING)) {
                        executeView(comparator);
                        return;
                    }

                    executeView(comparator.reversed());

                } catch (NotFoundException notFoundException) {
                    OutputView.viewErrorMessage(notFoundException.getMessage());
                }

            }
        };
    }

    private static SortType getSortOrder(String command) {
        return SortType.findOrder(command);
    }

    private static void executeView(Comparator<Customer> comparator) {
        Arrays.stream(GroupType.values()).forEach(groupType -> {
            ClassifiedCustomers.getInstance().viewByClassifiedGroup(groupType, comparator);
        });
    }
}
