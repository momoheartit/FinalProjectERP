package com.finalProject.ERP.Controller;

import com.finalProject.ERP.AppCore;
import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.Model.Model;
import com.finalProject.ERP.Model.PartnerEntity;
import com.finalProject.ERP.Model.jpqlBuilder.IncomeCondition;
import com.finalProject.ERP.Model.jpqlBuilder.IncomeJpqlBuilder;
import com.finalProject.ERP.View.GUI.InputForm;
import com.finalProject.ERP.View.IncomeChart;
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
        parent.initView("Create a new project");
        List<PartnerEntity> partners = model.getPartners();

        IncomeForm form = new IncomeForm(parent.getContainer(), partners);

        form.submit("Create", inc -> {
            model.save(inc);
            form.clear();

        });
    }

    public void newFilter() {
        parent.initView("Search among projects");
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
        parent.initView("Displaying projects and saving the results");

        jpqlQuery = incomeJpqlBuilder.buildQuery(filteredList);

        if (jpqlQuery != null) {
            lastFilteredList = filteredList;
            List<IncomeEntity> incomes = model.getFilteredIncome(jpqlQuery, filteredList);

            IncomeTable table = new IncomeTable(parent.getContainer());
            table.setItems(incomes);

            table.addActionColumn("✔", (incomeEntity, index) -> {
                editIncome(incomeEntity);
            });
            table.addActionColumn("✖", (incomeEntity, index)
                    -> {
                model.delete(incomeEntity);
                showFiltered(lastFilteredList);
            });
        } else {
            System.out.println("A JPQL lekérdezés null.");
        }
    }

    public void editIncome(IncomeEntity income) {
        parent.initView("Edit selected project");

        List<PartnerEntity> partners = model.getPartners();
        IncomeForm form = new IncomeForm(parent.getContainer(), partners);
        form.setValues(income);
        form.submit("Save", inc -> {
            model.save(inc);

            showFiltered(lastFilteredList);
        });

    }

    public Model getModel() {
        return model;
    }

    public List<IncomeCondition> getLastFilteredList() {
        return lastFilteredList;
    }

    public void notApproved() {
        parent.initView("Not approved yet");

        List<IncomeEntity> notApproved = model.findUnapproved();
        IncomeTable table = new IncomeTable(parent.getContainer());
        table.setItems(notApproved);

        table.addActionColumn("✔️", (incomeEntity, index) -> {
            editIncome(incomeEntity);
        });
        table.addActionColumn("✖", (incomeEntity, index)
                -> {
            model.delete(incomeEntity);
            notApproved();
        });
    }

    void newStatistics() {
        parent.initView("Making statistics");
        IncomeChart chart = new IncomeChart(parent.getContainer(), this, model);
    }

    public void statisticsButtonClick(IncomeChart aThis) {
        System.out.println("hát ez még nagyon kezdetleges te....");
    }
}
