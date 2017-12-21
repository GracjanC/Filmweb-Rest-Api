package com.codecool.bolec;

import com.codecool.bolec.model.Category;
import com.codecool.bolec.model.Director;
import com.codecool.bolec.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

        String lynchdate = "1947-01-20";
        String jarmuschdate = "1953-01-22";
        String jacksondate = "1961-10-31";
        String wiseaudate = "1955-10-03";

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
