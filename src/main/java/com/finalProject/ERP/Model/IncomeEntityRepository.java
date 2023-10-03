package com.finalProject.ERP.Model;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IncomeEntityRepository extends CrudRepository<IncomeEntity, Integer>{
    IncomeEntity save (IncomeEntity incomeEntity);
    List<IncomeEntity> findAllByOrderByCreatedDesc();
    List<IncomeEntity> findFirst3ByOrderByIdAsc();
    List<IncomeEntity> findAll();
    void delete(IncomeEntity incomeEntity);
    List<IncomeEntity> findFirst1ByOrderByIdDesc();
    
    
}
    
    