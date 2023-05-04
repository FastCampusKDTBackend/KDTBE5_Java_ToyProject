package me.smartstore.exception;

public enum Message {
    ERR_MSG_INVALID_ARR_EMPTY("No Customers. Please input one first."),
    ERR_MSG_NULL_ARR_ELEMENT("Elements in Array has null. Array can't be sorted."),
    ERR_MSG_INVALID_INPUT_NULL("Null Input. Please input something."),
    ERR_MSG_INVALID_INPUT_EMPTY("Empty Input. Please input something."),
    ERR_MSG_INVALID_INPUT_RANGE("Invalid Input. Please try again."),
    ERR_MSG_INVALID_INPUT_TYPE("Invalid Type for Input. Please try again."),
    ERR_MSG_INVALID_INPUT_FORMAT("Invalid Format for Input. Please try again."),
    ERR_MSG_INPUT_END("END is pressed. Exit this menu."),
    END_MSG("END");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}