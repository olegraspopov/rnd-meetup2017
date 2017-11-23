package com.sbt.rnd.meetup2017.transport;

import javax.persistence.EntityManager;

public interface MsgHandler<T> {
    <V> V handle(T msg);
}
