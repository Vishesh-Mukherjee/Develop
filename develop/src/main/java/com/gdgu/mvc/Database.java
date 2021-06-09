package com.gdgu.mvc;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Database {

    private EntityManager entityManager;

    public Database() {
        entityManager = Persistence.createEntityManagerFactory("developDatabase")
                                   .createEntityManager();
    }

    public void addData(String data) {
        executeInsideTransaction(entityManager -> entityManager.persist(new Data(data)));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        action.accept(entityManager);
        tx.commit();
    }
}