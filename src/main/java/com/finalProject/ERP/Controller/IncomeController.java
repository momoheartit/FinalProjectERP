package com.finalProject.ERP.Controller;

import com.finalProject.ERP.AppCore;
import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.Model.Model;
import com.finalProject.ERP.Model.PartnerEntity;
import com.finalProject.ERP.Model.jpqlBuilder.IncomeCondition;
import com.finalProject.ERP.Model.jpqlBuilder.IncomeJpqlBuilder;
import com.finalProject.ERP.View.GUI.InputForm;
import com.finalProject.ERP.View.IncomeFilter;
import com.finalProject.ERP.View.IncomeForm;
import com.finalProject.ERP.View.IncomeTable;

import java.util.List;

public class IncomeController {

    private AppController parent;
    private Model model;
    private IncomeJpqlBuilder incomeJpqlBuilder;
    private String jpqlQuery;  // JPQL lekérdezés tárolásához

    public IncomeController(AppController parent) {
        this.parent = parent;
        model = AppCore.getContext().getBean(Model.class);
        incomeJpqlBuilder = new IncomeJpqlBuilder();
    }

    public void newIncome() {
        parent.initView();
        
        List<PartnerEntity> partners = model.getPartners();
        
        IncomeForm form = new IncomeForm(parent.getContainer(), partners);
        
        form.submit("Save", inc -> {
            model.save(inc);
            form.clear();
        });
    }

    public void newFilter() {
        parent.initView();
        IncomeFilter incomeFilter = new IncomeFilter(parent.getContainer(), this, model);
    }

    public void handleSearchButtonClick(InputForm form) {
        List<IncomeCondition> filteredList = IncomeCondition.createConditionsList(form);

        // Létrehozzuk a JPQL lekérdezést
        jpqlQuery = incomeJpqlBuilder.buildQuery(filteredList);

        if (jpqlQuery != null) {
            System.out.println("JPQL lekérdezés: " + jpqlQuery);

            // Most használjuk a Model-t az adatok lekérdezésére
            List<IncomeEntity> filteredIncome = model.getFilteredIncome(jpqlQuery, filteredList);

            // Kiírjuk az eredményeket a konzolon
            System.out.println("Szűrt eredmények:");
            for (IncomeEntity income : filteredIncome) {
                System.out.println(income);
            }

            // Átadjuk a szűrt listát a showFiltered metódusnak
            showFiltered(filteredList);
        } else {
            System.out.println("A JPQL lekérdezés null.");
        }
    }

    public void showFiltered(List<IncomeCondition> filteredList) {
        parent.initView();

        // Létrehozzuk a JPQL lekérdezést
        jpqlQuery = incomeJpqlBuilder.buildQuery(filteredList);

        if (jpqlQuery != null) {
            // Most használjuk a Model-t az adatok lekérdezésére
            List<IncomeEntity> incomes = model.getFilteredIncome(jpqlQuery, filteredList);

            IncomeTable table = new IncomeTable(parent.getContainer());
            table.setItems(incomes);

            table.addActionColumn("...", (incomeEntity, index) -> {
                editIncome(incomeEntity);
            });
        } else {
            System.out.println("A JPQL lekérdezés null.");
        }
    }

    public void editIncome(IncomeEntity income) {
        parent.initView();
        
        List<PartnerEntity> partners = model.getPartners();
        IncomeForm form = new IncomeForm(parent.getContainer(), partners);
        form.setValues(income);
        form.submit("Save", inc
                -> {
            model.save(inc);
            //itt szeretném meghívni a showfiltered-et!
        });
    }

    public Model getModel() {
        return model;
    }
}
