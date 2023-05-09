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
            if (isPureSummary(comparator)) {
                executeView(null);
                return;
            }

            sortSummaryExecute(comparator);
        };
    }

    private static boolean isPureSummary(Comparator<Customer> comparator) {
        return comparator == null;
    }

    private static boolean isQuit(String command) {
        return command.equals(ViewMessage.EXIT_CHOICE);
    }

    private static void sortSummaryExecute(Comparator<Customer> comparator) {
        while (true) {
            OutputView.chooseType(SortType.generateFormatForView());
            String command = InputScanner.get().nextLine();

            if (isQuit(command)) {
                return;
            }

            if (SortType.isAscending(getSortOrder(command))) {
                executeView(comparator);
                return;
            }

            executeView(comparator.reversed());
        }
    }

    private static SortType getSortOrder(String command) {
        while (true) {
            try {
                return SortType.findOrder(command);
            } catch (NotFoundException notFoundException) {
                OutputView.viewErrorMessage(notFoundException.getMessage());
            }
        }
    }

    private static void executeView(Comparator<Customer> comparator) {
        Arrays.stream(GroupType.values()).forEach(groupType -> {
            ClassifiedCustomers.getInstance().viewByClassifiedGroup(groupType, comparator);
        });
    }
}
