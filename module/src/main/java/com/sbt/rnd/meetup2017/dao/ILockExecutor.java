package com.sbt.rnd.meetup2017.dao;

import javax.persistence.EntityManager;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

public interface ILockExecutor<T> {
    boolean lock(T key,ConcurrentMap<T, ReentrantLock> lockCurator) throws Exception;

}
