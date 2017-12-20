package com.codecool.bolec.services;

import com.codecool.bolec.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CategoryService {

    public Category find(Integer id) {
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


}
