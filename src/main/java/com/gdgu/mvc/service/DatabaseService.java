package com.gdgu.mvc.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.gdgu.mvc.entity.Data;
import com.gdgu.mvc.entity.Task;
import com.gdgu.mvc.entity.Tip;
import com.gdgu.mvc.util.State;

public class DatabaseService {

    private final static EntityManager entityManager;

    static {
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
            archiverPersistenceUnitInfo(),
            Map.of(
                "javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver",
                "javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/develop?createDatabaseIfNotExist=true",
                "javax.persistence.jdbc.user", "root",
                "javax.persistence.jdbc.password", "",
                "hibernate.hbm2ddl.auto", "update",
                "hibernate.dialect", "org.hibernate.dialect.MariaDB53Dialect"
            ));

        entityManager = entityManagerFactory.createEntityManager();
    }

    private static PersistenceUnitInfo archiverPersistenceUnitInfo() {
    return new PersistenceUnitInfo() {
        @Override
        public String getPersistenceUnitName() {
            return "ApplicationPersistenceUnit";
        }

        @Override
        public String getPersistenceProviderClassName() {
            return "org.hibernate.jpa.HibernatePersistenceProvider";
        }

        @Override
        public PersistenceUnitTransactionType getTransactionType() {
            return PersistenceUnitTransactionType.RESOURCE_LOCAL;
        }

        @Override
        public DataSource getJtaDataSource() {
            return null;
        }

        @Override
        public DataSource getNonJtaDataSource() {
            return null;
        }

        @Override
        public List<String> getMappingFileNames() {
            return Collections.emptyList();
        }

        @Override
        public List<URL> getJarFileUrls() {
            try {
                return Collections.list(this.getClass()
                                            .getClassLoader()
                                            .getResources(""));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        @Override
        public URL getPersistenceUnitRootUrl() {
            return null;
        }

        @Override
        public List<String> getManagedClassNames() {
            return Collections.emptyList();
        }

        @Override
        public boolean excludeUnlistedClasses() {
            return false;
        }

        @Override
        public SharedCacheMode getSharedCacheMode() {
            return null;
        }

        @Override
        public ValidationMode getValidationMode() {
            return null;
        }

        @Override
        public Properties getProperties() {
            return new Properties();
        }

        @Override
        public String getPersistenceXMLSchemaVersion() {
            return null;
        }

        @Override
        public ClassLoader getClassLoader() {
            return null;
        }

        @Override
        public void addTransformer(ClassTransformer transformer) {

        }

        @Override
        public ClassLoader getNewTempClassLoader() {
            return null;
        }
    };
}

    public void addData(String data) {
        executeInsideTransaction(entityManager -> entityManager.persist(new Data(data)));
    }

    public void addTask(String description)  {
        executeInsideTransaction(entityManager -> entityManager.persist(new Task(description)));
    }

    public Task getTask(int id) {
        return entityManager.find(Task.class, id);
    }

    public void updateTask(int id, String state) {
        Task task = getTask(id);
        task.setStates(state);
        executeInsideTransaction(entityManager -> entityManager.merge(task));
    }

    public List<Tip> getTips() {
        Query query = entityManager.createQuery("From Tip t");
        List <Tip> tips = query.getResultList();
        return tips;
    }

    public List<Task> getTasks() {
        Query query = entityManager.createQuery("From Task t");
        List <Task> tasks = query.getResultList();
        return tasks;
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        action.accept(entityManager);
        tx.commit();
    }
}