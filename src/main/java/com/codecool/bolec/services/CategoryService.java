package com.codecool.bolec.services;

import com.codecool.bolec.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CategoryService {

    public Category find(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();

        Category category = em.find(Category.class, id);
        em.close();
        emf.close();

        return category;
    }

    public List<Category> getAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Category> categories = em.createQuery("SELECT c FROM Category c").getResultList();
        em.getTransaction().commit();
        em.close();
        emf.close();

        return categories;
    }

    public void addToDb(String json) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        //json to object method

        transaction.commit();
        em.close();
        emf.close();
    }

    public void delete(Long id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Category category = em.find(Category.class, id);

        transaction.begin();
        em.remove(category);
        transaction.commit();

        em.close();
        emf.close();
    }
}
