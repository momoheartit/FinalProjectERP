package com.finalProject.ERP.Model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Model {

    @Autowired
    IncomeEntityRepository incomeEntityRepository;

    @Autowired
    PartnerEntityRepository partnerEntityRepository;

    public List<IncomeEntity> getAllIncome() {
        return (List<IncomeEntity>) incomeEntityRepository.findAllByOrderByCreatedDesc();
    }

    public List<IncomeEntity> getFirstThreeIncomeRecords() {
        return (List<IncomeEntity>) incomeEntityRepository.findFirst3ByOrderByCreatedDesc();
    }
}
