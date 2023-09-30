package com.finalProject.ERP.Controller;

import com.finalProject.ERP.AppCore;
import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.Model.IncomeJpqlQueryBuilder;
import com.finalProject.ERP.Model.Model;
import com.finalProject.ERP.View.IncomeFilter;
import java.util.List;

public class IncomeController {

    private AppController parent;
    private Model model;

    public IncomeController(AppController parent) {
        this.parent = parent;
        model = AppCore.getContext().getBean(Model.class);
    }

    public void newActivity() {
        IncomeFilter incomeFilter = new IncomeFilter(parent.getContainer(), this, model);
    }

    public void handleSearchResult(List<IncomeEntity> jpqlQuery) {
        // A JpqlQueryBuildertől kapott lekérdezést továbbíthatjuk a Modelnek
        List<IncomeEntity> results = model.performCustomQuery(jpqlQuery);

        // A Modeltől kapott eredmények megjelenítése a konzolon
        displayResults(results);
    }

    private void displayResults(List<IncomeEntity> results) {
        if (results.isEmpty()) {
            System.out.println("Nincs találat.");
        } else {
            for (IncomeEntity income : results) {
                System.out.println(income.toString());
            }
        }
    }
    
    public Model getModel(){
        return model;
    }

}
