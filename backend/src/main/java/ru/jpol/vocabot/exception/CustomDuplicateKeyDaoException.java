package ru.jpol.vocabot.exception;

public class CustomDuplicateKeyDaoException extends Exception {
    public CustomDuplicateKeyDaoException(String errorMessage) {
        super(errorMessage);
    }
}
