import data.ogm.breed_n_dog.Breed;
import data.ogm.breed_n_dog.Dog;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.transaction.jta.platform.spi.JtaPlatform;
import org.hibernate.jpa.HibernateEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;

public class TestOgm {
    public static void main(String[] args) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ogm-jpa-tutorial");
//        TransactionManager transactionManager = extractJBossTransactionManager(emf);
//
//        transactionManager.begin();
        EntityManager em =  emf.createEntityManager();
        em.getTransaction().begin();
        Breed collie = new Breed();
        collie.setName("breed-collie");
        em.persist(collie);
        Dog dina = new Dog();
        dina.setName("dina");
        dina.setBreed(collie);
        //persist dina
        em.persist(dina);
        em.getTransaction().commit();
//        transactionManager.commit();
        //get ID dina
        Long dinaId = dina.getId();
        // query
        Dog ourDina =  em.find(Dog.class, dinaId);
        System.out.println("Dina:"+ ourDina);
        em.close();
    }

    private static TransactionManager extractJBossTransactionManager(EntityManagerFactory factory) {
        SessionFactoryImplementor sessionFactory = ( (HibernateEntityManagerFactory) factory ).getSessionFactory();
        return sessionFactory.getServiceRegistry().getService( JtaPlatform.class).retrieveTransactionManager();
    }
}
