package com.hilltop.search.exception;

public class HillTopSearchApplicationException extends RuntimeException{
    public HillTopSearchApplicationException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
    public HillTopSearchApplicationException(String errorMessage) {
        super(errorMessage);
    }
}
