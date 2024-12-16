package org.example;

import javax.persistence.EntityManagerFactory;

public class ObjectDBUtil {
    private static EntityManagerFactory emf;
    static {
        emf = javax.persistence.Persistence.createEntityManagerFactory("data.odb");
    }
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
