package dev.dren.linkplus.assignment.transaction;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String msg) {
        super(msg);
    }
}