package com.sbt.rnd.meetup2017.transport.api;

public class TransportTimeoutException extends RuntimeException {

    public TransportTimeoutException() {
        super();
    }

    public TransportTimeoutException(String message) {
        super(message);
    }
}
