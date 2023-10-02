package com.finalProject.ERP.Model;

import com.finalProject.ERP.Model.jpqlBuilder.IncomeCondition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.persistence.Query;
import java.time.LocalDate;
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

    public void save(IncomeEntity income) {
        incomeEntityRepository.save(income);
    }

    public List<IncomeEntity> getAllIncome() {
        return (List<IncomeEntity>) incomeEntityRepository.findAllByOrderByCreatedDesc();
    }

    public List<IncomeEntity> findFirst3ByOrderByIdAsc() {
        return (List<IncomeEntity>) incomeEntityRepository.findFirst3ByOrderByIdAsc();
    }

    public List<PartnerEntity> getPartners() {
        return partnerEntityRepository.findAllByOrderById();
    }

    public List<IncomeEntity> getFilteredIncome(String jpqlQuery, List<IncomeCondition> conditions) {
        Query query = entityManager.createQuery(jpqlQuery, IncomeEntity.class);

        // Paraméterek hozzáadása, ha vannak
        for (IncomeCondition condition : conditions) {
            if (condition.isDate()) {
                //Ha a feltétel dátum típusú, akkor adj hozzá egy LocalDate paramétert
                query.setParameter(condition.getName(), LocalDate.parse(condition.getValue()));
            } else {
                //A továbbiak, ahogy eddig
                query.setParameter(condition.getName(), condition.getValue());
            }
        }

        return query.getResultList();
    }

}
