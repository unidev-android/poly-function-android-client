package com.unidev.polyfunciton;

/**
 * Exception on execution during poly function execution.
 */
public class PolyFunctionException extends Exception {

    public PolyFunctionException() {
        super();
    }

    public PolyFunctionException(String message) {
        super(message);
    }

    public PolyFunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PolyFunctionException(Throwable cause) {
        super(cause);
    }
}
