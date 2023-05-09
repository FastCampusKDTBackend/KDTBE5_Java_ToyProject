package domain.menu.main.classification;

import domain.menu.Menu;
import util.view.InputScanner;
import util.view.OutputView;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Scanner;

public interface ClassificationMenuExecute {

//    static <T> Runnable getMethod(int menuIndex) {
//        Runnable[] methods = new Runnable[]{
//                getAddCustomerExecuteMethod(),
//                getViewCustomerExecuteMethod(),
//                getUpdateCustomerExecuteMethod(),
//                getDeleteCustomerExecuteMethod(),
//        };
//        return methods[menuIndex];
//    }
//    static <T> Runnable getMethod(Comparator<T> comparator) {
//        return () -> {
//            Scanner scanner = InputScanner.get();
//
//            while (true) {
//                try {
//                    Menu[] menus = (Menu[]) getResult(clazz, "values", null, null);
//                    OutputView.viewMenus(menus);
//                    OutputView.chooseMenu();
//
//                    int menuNumber = Integer.parseInt(scanner.nextLine());
//                    boolean isQuit = (boolean) getResult(clazz, "isQuit", null, new Object[]{menuNumber}, new Class[]{int.class});
//                    System.out.println(isQuit);
//                    if (isQuit) {
//                        return;
//                    }
//
//                    findMenuAndExecution(clazz, menuNumber);
//
//                } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException |
//                         NoSuchMethodException e) {
//                    System.out.println(e);
//                }
//            }
//        };
//    }
}
