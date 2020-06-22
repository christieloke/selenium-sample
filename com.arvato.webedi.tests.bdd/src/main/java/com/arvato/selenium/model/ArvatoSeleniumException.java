package com.arvato.selenium.model;

public class ArvatoSeleniumException extends RuntimeException {
    public ArvatoSeleniumException() {
        super();
    }

    public ArvatoSeleniumException(String message) {
        super(message);
    }

    public ArvatoSeleniumException(String message, Throwable cause) {
        super(message, cause);
    }

}
