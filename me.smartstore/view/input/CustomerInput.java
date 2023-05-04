package view.input;

import domain.customer.Customers;
import exception.InputEndException;
import exception.InputFormatException;
import util.Console;

public class CustomerInput extends Input{

    public static final String INPUT_CUSTOMER_NUMBER = "How many customers to input?";
    public static final String INPUT_CUSTOMER_NAME = "Input Customer's Name: ";
    public static final String INPUT_CUSTOMER_ID = "Input Customer's Id: ";
    public static final String INPUT_CUSTOMER_STORE_USAGE_TIME = "Input Customer's Store Usage Time: ";
    public static final String INPUT_CUSTOMER_TOTAL_PAYMENT_AMOUNT = "Input Customer's Total Payment Amount: ";
    public static final String INPUT_UPDATE_CUSTOMER_NUMBER = "\nWhich customer ( 1 ~ %d )? > ";

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
}
