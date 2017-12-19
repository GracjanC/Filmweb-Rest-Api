package com.codecool.bolec;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();

        //populateDb(em);

        em.close();
        emf.close();
    }

    public static void populateDb(EntityManager em) {


    }
}
