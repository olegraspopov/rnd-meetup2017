package com.sbt.rnd.meetup2017.transport.impl;

public interface Rpc extends AutoCloseable {
    <T> T callRemoteMethod(String methodName, Object[] args, Class[] types);
}
