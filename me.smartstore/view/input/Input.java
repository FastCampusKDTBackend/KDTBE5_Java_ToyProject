package view.input;

import exception.InputFormatException;
import exception.InputRangeException;
import util.Console;

public class Input {

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
