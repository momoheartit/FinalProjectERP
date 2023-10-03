package com.finalProject.ERP.Controller;

import com.finalProject.ERP.AppCore;
import com.finalProject.ERP.Model.Model;
import com.finalProject.ERP.Model.PartnerEntity;
import com.finalProject.ERP.Model.jpqlBuilder.PartnerCondition;
import com.finalProject.ERP.Model.jpqlBuilder.PartnerJpqlBuilder;
import com.finalProject.ERP.View.GUI.InputForm;
import com.finalProject.ERP.View.PartnerFilter;
import com.finalProject.ERP.View.PartnerForm;
import com.finalProject.ERP.View.PartnerTable;
import java.util.List;

public class PartnerController {

    private AppController parent;
    private Model model;
    private PartnerJpqlBuilder partnerJpqlBuilder;
    private String jpqlQuery;  // JPQL lekérdezés tárolásához
    private List<PartnerCondition> lastFilteredList;

    public PartnerController(AppController parent) {
        this.parent = parent;
        model = AppCore.getContext().getBean(Model.class);
        partnerJpqlBuilder = new PartnerJpqlBuilder();
    }

    public void newFilter() {
        parent.initView("Search among partners");
        PartnerFilter partnerFilter = new PartnerFilter(parent.getContainer(), this, model);
    }

    public void searchButtonClick(InputForm form) {
        List<PartnerCondition> filteredList = PartnerCondition.createConditionsList(form);

        jpqlQuery = partnerJpqlBuilder.buildQuery(filteredList);

        if (jpqlQuery != null) {
            System.out.println("JPQL lekérdezés: " + jpqlQuery);
            List<PartnerEntity> filteredIncome = model.getFilteredPartner(jpqlQuery, filteredList);
            System.out.println("Szűrt eredmények:");
            for (PartnerEntity partner : filteredIncome) {
                System.out.println(partner);
            }
            showFiltered(filteredList);
        } else {
            System.out.println("A JPQL lekérdezés null.");
        }
    }

    public void newPartner() {
        parent.initView("Create a new partner");

        PartnerForm form = new PartnerForm(parent.getContainer());
        form.saveButton("Create", part
                -> {
            model.save(part);
            form.clear();
        });
    }

    public void showFiltered(List<PartnerCondition> filteredList) {
        parent.initView("Displaying partners and saving the results");

        jpqlQuery = partnerJpqlBuilder.buildQuery(filteredList);

        if (jpqlQuery != null) {
            lastFilteredList = filteredList;
            List<PartnerEntity> partners = model.getFilteredPartner(jpqlQuery, filteredList);

            PartnerTable table = new PartnerTable(parent.getContainer());
            table.setItems(partners);

            table.addActionColumn("...", (partnerEntity, index) -> {
                editPartner(partnerEntity);
            });
//            table.addActionColumn("X", (partnerEntity, index)
//                    -> {
//                //Itt kellene törölni, most ez egy sout//model.delete(partnerEntity);
//                System.out.println("Most kellene törölni........");
//                showFiltered(lastFilteredList);
//            });
        } else {
            System.out.println("A JPQL lekérdezés null.");
        }
    }

    public void editPartner(PartnerEntity partner) {
        parent.initView("Edit selected partner");

        PartnerForm form = new PartnerForm(parent.getContainer());
        form.setValues(partner);
        form.saveButton("Save", part
                -> {
            model.save(part);
            showFiltered(lastFilteredList);
        });
    }

}
