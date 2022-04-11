package com.cotton.algorithmer.algorithmhandler.exceptions;

public class AlgorithmHandlerException extends RuntimeException {
    public AlgorithmHandlerException(String message) {
        super(message);
    }

    public AlgorithmHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
