package com.finalProject.ERP.Controller;

import com.finalProject.ERP.AppCore;
import com.finalProject.ERP.Model.Model;
import com.finalProject.ERP.View.IncomeFilter;

public class IncomeController {
    
    private AppController parent;
    private Model model;

    public IncomeController(AppController parent) {
        this.parent = parent;
        model = AppCore.getContext().getBean(Model.class);
    }
    
    public void newActivity(){
        IncomeFilter incomeFilter = new IncomeFilter();
        parent.getContainer().getChildren().add(incomeFilter);
    }    
    
}
