package com.codecool.bolec;

import com.codecool.bolec.model.Category;
import com.codecool.bolec.model.Director;
import com.codecool.bolec.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

        populateDb(em);

        Movie foundMovie1 = em.find(Movie.class, 1L);
        System.out.println("--Found student #1");
        System.out.println("----name----" + foundMovie1.getTitle());
        System.out.println("----review----" + foundMovie1.getReview());

        Movie foundMovie2 = em.find(Movie.class, 2L);
        System.out.println("--Found student #2");
        System.out.println("----name----" + foundMovie2.getTitle());
        System.out.println("----category----" + foundMovie2.getCategory().getName());

        em.close();
        emf.close();
    }

    public static void populateDb(EntityManager em) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date lynchdate = Calendar.getInstance().getTime();
        Date jarmuschdate = Calendar.getInstance().getTime();
        try {
            lynchdate = sdf.parse("1947-01-20");
            jarmuschdate = sdf.parse("1953-01-22");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Category horror = new Category("Horror");
        Category drama = new Category("Drama");
        Director lynch = new Director("David", "Lynch", lynchdate, "male", "Gray", 182);
        Director jarmusch = new Director("Jim", "Jarmusch", jarmuschdate, "male", "Gray", 180);
        Movie eraserhead = new Movie("Eraserhead", horror, lynch, "fajne", 18, 8, 78, 1971);
        Movie paterson = new Movie("Paterson", drama, jarmusch, "hmmm ciekawe", 12, 7, 118, 2016);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(horror);
        em.persist(drama);
        em.persist(lynch);
        em.persist(jarmusch);
        em.persist(eraserhead);
        em.persist(paterson);
        transaction.commit();
    }
}
