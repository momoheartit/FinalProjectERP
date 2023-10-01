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

    public List<IncomeEntity> getFilteredIncome(String jpqlQuery) {
        Query query = entityManager.createQuery(jpqlQuery, IncomeEntity.class);
        // itt még eventuális paramétereket is hozzáadhatsz a lekérdezéshez, ha szükséges

        return query.getResultList();
    }

}
