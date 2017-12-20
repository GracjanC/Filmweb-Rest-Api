package com.codecool.bolec.services;

import com.codecool.bolec.utils.ReflectionHelpers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.util.List;

public class ServletService<T> {

    public T getObject(Long id, Type type) throws ClassNotFoundException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();

        Class<?> classType = ReflectionHelpers.getClass(type);

        T object = (T) em.find(classType, id);
        em.close();
        emf.close();

        return object;
    }


    public List<T> getAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<T> categories = em.createQuery("SELECT c FROM Category c").getResultList();
        em.getTransaction().commit();
        em.close();
        emf.close();

        return categories;
    }
}
