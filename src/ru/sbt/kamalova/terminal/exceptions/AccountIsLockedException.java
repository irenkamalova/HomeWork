package ru.sbt.kamalova.terminal.exceptions;

/**
 * Created by Irina Kamalova on 12.11.16.
 */
public class AccountIsLockedException extends Exception {
    public AccountIsLockedException(String message) {
        super(message);
    }
}
