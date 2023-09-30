package com.finalProject.ERP.Controller;

import com.finalProject.ERP.AppCore;
import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.Model.Model;
import com.finalProject.ERP.View.IncomeFilter;
import java.util.List;
import javafx.scene.control.Button;

public class IncomeController {
    
    private AppController parent;
    private Model model;

    public IncomeController(AppController parent) {
        this.parent = parent;
        model = AppCore.getContext().getBean(Model.class);
    }
    
    public void newActivity(){
        
        IncomeFilter incomeFilter = new IncomeFilter(parent.getContainer());
        List<IncomeEntity> incomes = model.findFirst3ByOrderByIdAsc();
    }    
    
   private void handleSearchButtonAction() {
        // Itt határozod meg, hogy mi történjen a gomb megnyomásakor
        System.out.println("Search button pressed");
        List<IncomeEntity> incomeList = model.findFirst3ByOrderByIdAsc();
        for (IncomeEntity income : incomeList) {
            System.out.println(income.toString());
        }
    }
    
}
