package com.finalProject.ERP.Model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PartnerEntityRepository extends CrudRepository<IncomeEntity, Integer>{
    
    IncomeEntity save (IncomeEntity incomeEntity);
    List<PartnerEntity> findAllByOrderByIdAsc();
    
}
