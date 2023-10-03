package com.finalProject.ERP.Model;

import com.finalProject.ERP.Model.jpqlBuilder.IncomeCondition;
import com.finalProject.ERP.Model.jpqlBuilder.PartnerCondition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
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

    public void save(PartnerEntity partner) {
        partnerEntityRepository.save(partner);
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

    public void delete(IncomeEntity income) {
        incomeEntityRepository.delete(income);
    }

    

    public List<IncomeEntity> getFilteredIncome(String jpqlQuery, List<IncomeCondition> conditions) {
        Query query = entityManager.createQuery(jpqlQuery, IncomeEntity.class);

        for (IncomeCondition condition : conditions) {
            if (condition.isDate()) {
                query.setParameter(condition.getName(), LocalDate.parse(condition.getValue()));
            } else {
                query.setParameter(condition.getName(), condition.getValue());
            }
        }

        return query.getResultList();
    }

    public List<PartnerEntity> getFilteredPartner(String jpqlQuery, List<PartnerCondition> conditions) {
        Query query = entityManager.createQuery(jpqlQuery, IncomeEntity.class);

        for (PartnerCondition condition : conditions) {
            if (condition.isDate()) {
                query.setParameter(condition.getName(), LocalDate.parse(condition.getValue()));
            } else {
                query.setParameter(condition.getName(), condition.getValue());
            }
        }

        return query.getResultList();
    }

}
