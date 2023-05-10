package util.view;

import java.util.Objects;

public enum ViewMessage {
    QUIT_MENU_NAME("Quit"),
    SEPERATOR("=============================\n"),
    INPUT_MENU("Choose One: "),
    EXIT_CHOICE("end"),
    INPUT_MINIMUM_SPENT_TIME("Input Minimum Spent Time:"),
    INPUT_MINIMUM_TOTAL_PAY("Input Minimum Total Pay:"),
    INPUT_CUSTOMER_NAME("Input Customer's Name:"),
    INPUT_CUSTOMER_ID("Input Customer's Name:"),
    INPUT_CUSTOMER_SPENT_TIME("Input Customer's Name:"),
    INPUT_CUSTOMER_TOTAL_PAY("Input Customer's Name:"),
    INPUT_END_FOR_EXIT("** Press '" + EXIT_CHOICE.message + "', if you want to exit! **"),
    INPUT_CUSTOMER_NUMBER("How many customers to input?");

    private final String message;

    ViewMessage(String message) {
        this.message = message;
    }

    public static boolean isExit(String string) {
        return string.equals(EXIT_CHOICE.message);
    }

    public static boolean isQuitMenuName(String menuName) {
        return Objects.equals(EXIT_CHOICE.message, menuName);
    }

    public String getMessage() {
        return message;
    }
}
