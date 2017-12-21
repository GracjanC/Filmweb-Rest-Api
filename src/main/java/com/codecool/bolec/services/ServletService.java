package com.codecool.bolec.services;

import com.codecool.bolec.utils.JSonParser;
import com.codecool.bolec.utils.ReflectionHelpers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
        String queryString = String.format("SELECT t FROM %s t", this.classType.getSimpleName());
        em.getTransaction().begin();

        List<T> categories = em.createQuery(queryString).getResultList();
        em.getTransaction().commit();
        em.close();
        emf.close();

        return categories;
    }

    public void post(T object) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(object);
        transaction.commit();

        em.close();
        emf.close();
    }

    public void put(T newObject, T oldObject) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        oldObject = newObject;

        em.getTransaction().begin();
        em.merge(oldObject);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public void delete(Long id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        T object = (T) em.find(this.classType, id);

        transaction.begin();
        em.remove(object);
        transaction.commit();

        em.close();
        emf.close();
    }

    public Class<?> getClassType() {
        return classType;
    }

    public void addToDb(String json) throws ClassNotFoundException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        JSonParser<T> jp = new JSonParser<>();
        transaction.begin();

        T object = jp.jsonToObject(json, this.classType);
        em.persist(object);

        transaction.commit();
        em.close();
        emf.close();
    }
}
