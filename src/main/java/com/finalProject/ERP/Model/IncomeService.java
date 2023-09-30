package com.finalProject.ERP.Model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {

    @Autowired
    private IncomeEntityRepository incomeRepository;

    public List<IncomeEntity> searchByCriteria(IncomeSearchCriteria criteria) {
        Specification<IncomeEntity> spec = IncomeSpecifications.byCriteria(criteria);
        return incomeRepository.findAll(spec);
    }
}

}
