package com.finalProject.ERP.Model;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PartnerEntityRepository extends CrudRepository<IncomeEntity, Integer>{
    IncomeEntity save (IncomeEntity incomeEntity);
    PartnerEntity save (PartnerEntity partnerEntity);
    List<PartnerEntity> findAllByOrderByIdAsc();
    
    @Query("SELECT p FROM PartnerEntity p ORDER BY p.id")
    List<PartnerEntity> findAllByOrderById();
    
   
}
