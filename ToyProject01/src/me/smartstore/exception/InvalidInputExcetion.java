package me.smartstore.exception;

public class InvalidInputExcetion extends Exception{
    private final static String MESSAGE = "Invalid Input. Please try again.";
    public InvalidInputExcetion(){
        super(MESSAGE);
    }
}
