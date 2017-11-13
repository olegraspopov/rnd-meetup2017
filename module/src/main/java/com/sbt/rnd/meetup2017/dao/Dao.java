package com.sbt.rnd.meetup2017.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class Dao implements IDao {
    @Autowired
    private EntityManager em;

    private static final Logger LOGGER = LogManager.getLogger(Dao.class.getName());

    public <T> T execute(IExecutor<T> executor) {
        boolean inTransaction = em.getTransaction().isActive();
        int txId = em.getTransaction().hashCode();
        try {
            if (!inTransaction) {
                em.getTransaction().begin();
                LOGGER.trace("Tx start {}, {}, thread = {}:{}",
                        txId, executor.getClass().getName(), Thread.currentThread().getId(), Thread.currentThread().getName());
            }
            T res = executor.execute(em);

            if (!inTransaction) {
                em.getTransaction().commit();
                LOGGER.trace("Tx commit {}, {}, thread = {}:{}",
                        txId, executor.getClass().getName(), Thread.currentThread().getId(), Thread.currentThread().getName());
            }
            return res;

        } catch (Exception ex) {
            LOGGER.error("Tx fail id = " + txId + ", " + executor.getClass().getName(), ex);
            try {

                if (!inTransaction) {
                    em.getTransaction().rollback();
                }

            } catch (Exception e) {
                LOGGER.error("Ошибка при фиксации транзакции {}. Cause: ",
                        txId, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e);
                throw e;
            }

        }
        return null;
    }

    public <T> Boolean save(T entity) {
        return execute(new IExecutor<Boolean>() {
            @Override
            public Boolean execute(EntityManager em) throws Exception {
                em.persist(entity);
                return true;
            }
        });
    }

    public <T> Boolean remove(T entity) {
        return execute(new IExecutor<Boolean>() {
            @Override
            public Boolean execute(EntityManager em) throws Exception {
                em.remove(entity);
                return true;
            }
        });
    }

    public <T> T find(Class<T> entityClass,Long id) {
        return em.find(entityClass, id);
    }

    public <T> List<T> search(String query){
        return em.createQuery(query).getResultList();
    }

    public <T> List<T> fullTextSearch(Class<T> entityClass, Query query){
        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);

        //Optionally use the QueryBuilder to simplify Query definition:
        QueryBuilder b = ftem.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(entityClass)
                .get();

        //Create a Lucene Query:
        //Query lq = b.keyword().onField("name").matching("dina").createQuery();

        //Transform the Lucene Query in a JPA Query:
        FullTextQuery ftQuery = ftem.createFullTextQuery(query, entityClass);

        //List all matching Hypothesis:
        return ftQuery.getResultList();
    }
}
