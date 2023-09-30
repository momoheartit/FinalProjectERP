package com.finalProject.ERP.Model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.persistence.Query;
import java.util.List;

@Component
public class Model {

    @Autowired
    IncomeEntityRepository incomeEntityRepository;

    @Autowired
    PartnerEntityRepository partnerEntityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<IncomeEntity> getAllIncome() {
        return (List<IncomeEntity>) incomeEntityRepository.findAllByOrderByCreatedDesc();
    }

    public List<IncomeEntity> findFirst3ByOrderByIdAsc() {
        return (List<IncomeEntity>) incomeEntityRepository.findFirst3ByOrderByIdAsc();
    }

    public List<IncomeEntity> performCustomQuery(List<IncomeEntity> jpqlQuery) {
        // Itt hozz létre egy Query objektumot az EntityManager segítségével
        // Fontos, hogy a jpqlQuery egy listát tartalmaz, és itt létrehozunk egy JPQL lekérdezést,
        // amelyet az IN operátorral állítunk elő, majd az adatokat beállítjuk a paraméternek.
        String queryString = "SELECT i FROM IncomeEntity i WHERE i IN :entities";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("entities", jpqlQuery);

        // Futtasd le a lekérdezést és adj vissza egy listát az eredményekkel
        return query.getResultList();
    }

}
