package domain.menu.main;

import domain.menu.Menu;
import util.view.InputScanner;
import util.view.OutputView;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Scanner;

public interface MainMenuExecute {

    static <T> Runnable getMethod(Class<T> clazz) {
        return () -> {
            Scanner scanner = InputScanner.get();

            while (true) {
                try {
                    Menu[] menus = (Menu[]) getResult(clazz, "values", null, null);
                    OutputView.viewMenus(menus);
                    OutputView.chooseMenu();

                    int menuNumber = Integer.parseInt(scanner.nextLine());
                    boolean isQuit = (boolean) getResult(clazz, "isQuit", null, new Object[]{menuNumber}, new Class[]{int.class});
                    if (isQuit) {
                        return;
                    }

                    findMenuAndExecution(clazz, menuNumber);

                } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    System.out.println(e);
                }
            }
        };
    }

    private static <T> Object getResult(Class<T> clazz, String methodName, Object target, Object[] args, Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(methodName, parameterTypes).invoke(target, args);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println(e);
        }
        return null;
    }

    private static <T> void findMenuAndExecution(Class<T> clazz, int menuNumber) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        findByNumber(clazz, menuNumber).ifPresent(
                (menu) -> {
//                    getResult(clazz, "execute", menu, null);
                    execute(clazz, menu);
                }
        );
    }

    private static <T> void execute(Class<T> clazz, T menu) {
        try {
            clazz.getDeclaredMethod("execute").invoke(menu);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> Optional<T> findByNumber(Class<T> clazz, int menuNumber) {
        return (Optional<T>) getResult(clazz, "findByNumber", null, new Object[]{menuNumber}, int.class);
//        return (Optional<T>) (clazz.getDeclaredMethod("findByNumber", int.class).invoke(null, menuNumber));
    }



    //    private static <T> T[] getValues(Class<T> clazz, Object target, Object[] args, Class<?>... parameterTypes) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        return (T[]) clazz.getMethod("values", parameterTypes).invoke(target, args);
//    }
//
//    private static <T> boolean isQuit(Class<T> clazz, int menuNumber) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        return (boolean) clazz.getDeclaredMethod("isQuit", int.class).invoke(null, menuNumber);
//    }

}
