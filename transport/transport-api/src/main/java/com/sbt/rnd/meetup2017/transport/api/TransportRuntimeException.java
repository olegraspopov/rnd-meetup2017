package com.sbt.rnd.meetup2017.transport.api;

public class TransportRuntimeException extends RuntimeException {

    public TransportRuntimeException() {
        super();
    }

    public TransportRuntimeException(String message) {
        super(message);
    }

    public TransportRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransportRuntimeException(Throwable cause) {
        super(cause);
    }
}
