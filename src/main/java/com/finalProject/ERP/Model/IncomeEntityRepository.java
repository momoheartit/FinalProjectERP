package com.finalProject.ERP.Model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IncomeEntityRepository extends CrudRepository<IncomeEntity, Integer>{
    IncomeEntity save (IncomeEntity incomeEntity);
    List<IncomeEntity> findAllByOrderByCreatedDesc();
    List<IncomeEntity> findFirst3ByOrderByIdAsc();
    List<IncomeEntity> findAll();
}
    
    