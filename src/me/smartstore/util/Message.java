package me.smartstore.util;

public interface Message {
    String ERR_INVALID_INPUT_EMPTY = "\nEmpty Input. Please input something.";
    String ERR_INVALID_INPUT_FORMAT = "\nInvalid Format for Input. Please try again.";
    String ERR_INVALID_INPUT_RANGE = "\nInvalid Input Range. Please try again.";

    String ERR_INVALID_ARR_EMPTY = "\nNo Customers. Please input one first.";
    String ERR_INVALID_PARAMETER_EMPTY = "\nNo parameter. Set the parameter first.";
    String ERR_NULL_ELEMENT = "\nElements in Array has null. Array can't be sorted.";

    String INFO_INPUT_EXIT_FLAG = "\nEND is pressed. Exit this menu.";
    String EXIT_FLAG = "end";
}
