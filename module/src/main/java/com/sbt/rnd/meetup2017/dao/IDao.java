package com.sbt.rnd.meetup2017.dao;

public interface IDao {

    <T> T execute(IExecutor<T> executor);

    <T> Boolean save(T entity);

    <T> Boolean remove(T entity);

    <T> T find(Class<T> entityClass,Long id);

}
