package com.sbt.rnd.meetup2017.transport.api;

public interface TransportProxyFactory {

    <T> T createTransportProxy(Class<T> clazz);

}
