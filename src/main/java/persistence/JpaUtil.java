package persistence;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("VeterinariaPU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return EMF;
    }
}

