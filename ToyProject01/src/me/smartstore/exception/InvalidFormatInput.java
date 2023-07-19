package me.smartstore.exception;

public class InvalidFormatInput extends Exception{
    private final static String MESSAGE = "Invalid Format for Input. Please try again.";
    public InvalidFormatInput(){
        super(MESSAGE);
    }
}
