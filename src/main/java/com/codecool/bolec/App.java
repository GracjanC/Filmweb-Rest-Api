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

public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();

        populateDb(em);

        em.close();
        emf.close();
    }

    public static void populateDb(EntityManager em) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date lynchdate = Calendar.getInstance().getTime();
        Date jarmuschdate = Calendar.getInstance().getTime();
        Date jacksondate = Calendar.getInstance().getTime();
        Date wiseaudate = Calendar.getInstance().getTime();
        try {
            lynchdate = sdf.parse("1947-01-20");
            jarmuschdate = sdf.parse("1953-01-22");
            jacksondate = sdf.parse("1961-10-31");
            wiseaudate = sdf.parse("1955-10-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Category horror = new Category("Horror");
        Category drama = new Category("Drama");
        Category thriller = new Category("Thriller");
        Category fantasy = new Category("Fantasy");
        Director wiseau = new Director("Tommy", "Wiseau", wiseaudate, "male", "Black", 181);
        Director jackson = new Director("Peter", "Jackson", jacksondate, "male", "Brown", 169);
        Director lynch = new Director("David", "Lynch", lynchdate, "male", "Gray", 182);
        Director jarmusch = new Director("Jim", "Jarmusch", jarmuschdate, "male", "Gray", 180);
        Movie eraserhead = new Movie("Eraserhead", horror, lynch, "fajne", 18, 8, 78, 1971);
        Movie paterson = new Movie("Paterson", drama, jarmusch, "hmmm ciekawe", 12, 7, 118, 2016);
        Movie lotr = new Movie("Lord of the Rings: The Two Towers", fantasy, jackson, "megakozak", 12, 8, 179, 2002);
        Movie mulholland = new Movie("Mulholland Drive", thriller, lynch, "fajne ale nie wiem ocb", 18, 8, 148, 1999);
        Movie theroom = new Movie("The Room", drama, wiseau, "stracone 2 godziny, najgorzej", 16, 3, 145, 2003);
        Movie braindead = new Movie("Braindead", horror, jackson, "super scena jak rzucil ziomkowi widelec w oko", 18, 6, 104, 1992);


        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(horror);
        em.persist(drama);
        em.persist(thriller);
        em.persist(fantasy);
        em.persist(jackson);
        em.persist(wiseau);
        em.persist(lynch);
        em.persist(jarmusch);
        em.persist(eraserhead);
        em.persist(paterson);
        em.persist(mulholland);
        em.persist(lotr);
        em.persist(theroom);
        em.persist(braindead);

        transaction.commit();
    }
}
