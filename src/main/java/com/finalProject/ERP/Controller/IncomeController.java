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
    private List<IncomeCondition> lastFilteredList;
    

    public IncomeController(AppController parent) {
        this.parent = parent;
        model = AppCore.getContext().getBean(Model.class);
        incomeJpqlBuilder = new IncomeJpqlBuilder();
    }

    public void newIncome() {
        parent.initView();
        List<PartnerEntity> partners = model.getPartners();

        IncomeForm form = new IncomeForm(parent.getContainer(), partners);

        form.submit("Create", inc -> {
            model.save(inc);
            form.clear();
            
        });
    }

    public void newFilter() {
        parent.initView();
        IncomeFilter incomeFilter = new IncomeFilter(parent.getContainer(), this, model);
    }

    public void searchButtonClick(InputForm form) {
        List<IncomeCondition> filteredList = IncomeCondition.createConditionsList(form);

        jpqlQuery = incomeJpqlBuilder.buildQuery(filteredList);

        if (jpqlQuery != null) {
            System.out.println("JPQL lekérdezés: " + jpqlQuery);
            List<IncomeEntity> filteredIncome = model.getFilteredIncome(jpqlQuery, filteredList);
            System.out.println("Szűrt eredmények:");
            for (IncomeEntity income : filteredIncome) {
                System.out.println(income);
            }
            showFiltered(filteredList);
        } else {
            System.out.println("A JPQL lekérdezés null.");
        }
    }

    public void showFiltered(List<IncomeCondition> filteredList) {
        parent.initView();

        jpqlQuery = incomeJpqlBuilder.buildQuery(filteredList);

        if (jpqlQuery != null) {
            lastFilteredList = filteredList;
            List<IncomeEntity> incomes = model.getFilteredIncome(jpqlQuery, filteredList);

            IncomeTable table = new IncomeTable(parent.getContainer());
            table.setItems(incomes);

            table.addActionColumn("...", (incomeEntity, index) -> {
                editIncome(incomeEntity);
            });
            table.addActionColumn("X", (incomeEntity, index)
                    -> {
                model.delete(incomeEntity);
                showFiltered(lastFilteredList);
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
            // Mentsd el az adatbázisba az új bevételt
            model.save(inc);

            // Hívd meg a showFiltered metódust a filteredList-del
            showFiltered(lastFilteredList);
        });

    }

    public Model getModel() {
        return model;
    }

    public List<IncomeCondition> getLastFilteredList() {
        return lastFilteredList;
    }
}
