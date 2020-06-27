package com.bridgelabz.addressbook.exception;

public class AddressBookException extends Exception {
    exceptionType type;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public AddressBookException(String message, exceptionType type) {
        super(message);
        this.type = type;
    }

    public enum exceptionType {
        ENTERED_WRONG_ID
    }

}
