package com.finalProject.ERP.Model;

import java.time.LocalDate;
import java.util.List;
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
    
    @Query("SELECT i FROM IncomeEntity i WHERE i.created >= :threeMonthsAgo")
    List<IncomeEntity> findAllByCreatedAfter(@Param("threeMonthsAgo") LocalDate threeMonthsAgo);
        
    @Query("SELECT p FROM IncomeEntity p WHERE p.approved IS NULL ORDER BY p.created ASC")
    List<IncomeEntity> findAllUnapprovedIncomes();
}
    
    