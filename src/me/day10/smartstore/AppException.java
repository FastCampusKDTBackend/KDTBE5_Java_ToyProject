package me.day10.smartstore;

public class AppException extends Exception {
    public AppException(String msg) { super(msg); }
    public AppException(Exception e) { super(e); }
    public AppException(String msg, Exception e) { super(msg, e); }
}
