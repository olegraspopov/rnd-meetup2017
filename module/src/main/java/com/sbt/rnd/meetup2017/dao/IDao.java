package com.sbt.rnd.meetup2017.dao;

import org.apache.lucene.search.Query;

import java.util.List;

public interface IDao {

    <T> T execute(IExecutor<T> executor);

    <T> Boolean save(T entity);

    <T> Boolean remove(T entity);

    <T> T findById(Class<T> entityClass, Long id);

    <T> List<T> search(String query);

    <T> List<T> fullTextSearch(Class<T> entityClass, Query query);

}
