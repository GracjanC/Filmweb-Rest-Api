package com.codecool.bolec.services;

import com.codecool.bolec.utils.ReflectionHelpers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.util.List;

public class ServletService<T> {
    private Class<?> classType;

    public ServletService(Type type) throws ClassNotFoundException {
        this.classType = ReflectionHelpers.getClass(type);
    }

    public T getObject(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();

        T object = (T) em.find(this.classType, id);
        em.close();
        emf.close();

        return object;
    }

    public List<T> getAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();
        String queryString = String.format("SELECT t FROM %s t", classType.getSimpleName());
        em.getTransaction().begin();

        List<T> categories = em.createQuery(queryString).getResultList();
        em.getTransaction().commit();
        em.close();
        emf.close();

        return categories;
    }

    public Class<?> getClassType() {
        return classType;
    }
}
