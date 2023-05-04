package view;

import domain.customer.Customers;
import domain.group.GroupType;
import exception.InputEndException;
import exception.InputFormatException;
import exception.InputRangeException;
import util.Console;

public class Input {

    public static final String MENU_PRINT_FORMAT = "%d. %s\n";
    public static final String INPUT_MENU_CHOICE = "메뉴 번호를 입력해주세요: "; // choose one:

    public static final String INPUT_CUSTOMER_NUMBER = "How many customers to input?";
    public static final String INPUT_CUSTOMER_NAME = "Input Customer's Name: ";
    public static final String INPUT_CUSTOMER_ID = "Input Customer's Id: ";
    public static final String INPUT_CUSTOMER_STORE_USAGE_TIME = "Input Customer's Store Usage Time: ";
    public static final String INPUT_CUSTOMER_TOTAL_PAYMENT_AMOUNT = "Input Customer's Total Payment Amount: ";
    public static final String INPUT_UPDATE_CUSTOMER_NUMBER = "\nWhich customer ( 1 ~ %d )? > ";

    public static final String INPUT_GROUP = "\nWhich group (GENERAL (G), VIP (V), VVIP (VV))?";
    public static final String INPUT_MINIMUM_SPENT_TIME = "Input Minimum Spent Time: ";
    public static final String INPUT_MINIMUM_TOTAL_PAYMENT = "Input Minimum Total Payment: ";

    public static final String INPUT_SUMMARY_ORDER = "Which order (ASCENDING (A), DESCENDING (D))?";

    public static int chooseMenuNumber(String[] menu) {
        System.out.println("\n==============================");
        for (int i = 0; i < menu.length; i++) {
            System.out.printf(MENU_PRINT_FORMAT, i+1, menu[i]);
        }
        System.out.println("==============================");
        System.out.print(INPUT_MENU_CHOICE);
        return Console.readLineToInteger();
    }

    public static int inputCustomerNumber() throws InputEndException {
        return inputValueToInteger(INPUT_CUSTOMER_NUMBER);
    }

    public static String inputCustomerName() throws InputEndException {
        System.out.println(INPUT_CUSTOMER_NAME);
        return Console.readLineEnd();
    }

    public static String inputCustomerId() throws InputEndException {
        System.out.println(INPUT_CUSTOMER_ID);
        return Console.readLineEnd();
    }

    public static int inputCustomerStoreUsageTime() throws InputEndException {
        int customerStoreUsageTime = inputValueToInteger(INPUT_CUSTOMER_STORE_USAGE_TIME);
        validateNegativeNumber(customerStoreUsageTime);
        return customerStoreUsageTime;
    }

    public static int inputCustomerTotalPaymentAmount() throws InputEndException {
        int customerTotalPaymentAmount = inputValueToInteger(INPUT_CUSTOMER_TOTAL_PAYMENT_AMOUNT);
        validateNegativeNumber(customerTotalPaymentAmount);
        return customerTotalPaymentAmount;
    }

    public static int inputTargetCustomerNumber() {
        while (true) {
            try {
                int number = inputValueToInteger(String.format(INPUT_UPDATE_CUSTOMER_NUMBER, Customers.getInstance().size()));
                if (number < 0 || number > Customers.getInstance().size())
                    throw new InputFormatException();
                return number - 1;
            } catch (InputFormatException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static GroupType chooseGroupType(){
        while (true) {
            try {
                System.out.println(INPUT_GROUP);
                String group = Console.readLineEnd();
                GroupType choiceGroup = GroupType.valueOf(group);
                return choiceGroup.replaceFullName();
            } catch (IllegalArgumentException exception) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public static int inputMinSpentTime() {
        while (true) {
            try {
                int minimumSpentTime = inputValueToInteger(INPUT_MINIMUM_SPENT_TIME);
                validateNegativeNumber(minimumSpentTime);
                return minimumSpentTime;
            } catch (InputRangeException exception){
                Output.printErrorMessage(exception.getMessage());
            }
        }
    }

    public static int inputMinTotalPayment() {
        while (true) {
            try {
                int minimumSpentTime = inputValueToInteger(INPUT_MINIMUM_TOTAL_PAYMENT);
                validateNegativeNumber(minimumSpentTime);
                return minimumSpentTime;
            } catch (InputRangeException exception){
                Output.printErrorMessage(exception.getMessage());
            }
        }
    }

    public static boolean isSummarySortOrderDesc() {
        System.out.println(INPUT_SUMMARY_ORDER);
        while (true) {
            String str = Console.readLineEnd();
            if (str.equals("A")) return false;
            if (str.equals("D")) return true;
            System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
        }
    }

    public static int inputValueToInteger(String message){
        while (true) {
            try {
                System.out.println(message);
                return Console.inputValueToInteger(Console.readLineEnd());
            } catch (InputFormatException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static void validateNegativeNumber(int number){
        if (number < 0) throw new InputRangeException();
    }
}
