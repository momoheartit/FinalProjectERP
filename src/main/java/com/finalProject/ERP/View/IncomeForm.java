package com.finalProject.ERP.View;

import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.Model.PartnerEntity;
import com.finalProject.ERP.View.GUI.InputDatePicker;
import com.finalProject.ERP.View.GUI.InputField;
import com.finalProject.ERP.View.GUI.InputForm;
import java.time.LocalDate;
import java.util.List;
import javafx.scene.layout.Pane;
import java.util.function.Consumer;

public class IncomeForm extends InputForm {

    private IncomeEntity instance;
    private List<PartnerEntity> partners;

    public IncomeForm(Pane parent, List<PartnerEntity> partners) {
        super(parent);

        add("partner", new InputField("Partner"), 1, 0);
        add("amount", new InputField("Amount"), 2, 0);
        add("project", new InputField("Project"), 3, 0);
        add("created", new InputDatePicker("Created"), 4, 0);
        add("approved", new InputDatePicker("Approved"), 5, 0);

        this.partners = partners;
    }

    public void setValues(IncomeEntity income) {

        PartnerEntity partner = income.getPartner();
        if (partner != null) {
            setValue("partner", Integer.toString(partner.getId()));
        }
        setValue("amount", Integer.toString(income.getAmount()));
        setValue("project", income.getProject());
        setValue("created", income.getCreated().toString());
        setValue("approved", income.getApproved() != null ? income.getApproved().toString() : null);

        instance = income;
    }

    public void submit(String buttonText, Consumer<IncomeEntity> onClick) {
        button(buttonText, form -> {
            if (instance == null) {
                instance = new IncomeEntity();
            }

            int partnerX = Integer.parseInt(form.getValue("partner")) - 1;
            PartnerEntity partner = partners.get(partnerX);
            instance.setPartner(partner);

            instance.setAmount(Integer.parseInt(form.getValue("amount")));
            instance.setProject(form.getValue("project"));

            String createdText = form.getValue("created");
            if (createdText != null && !createdText.isEmpty()) {
                instance.setCreated(LocalDate.parse(createdText));
            } else {
                System.out.println("A 'Created' mező értéke üres vagy null.");
            }
            
            String approvedText = form.getValue("approved");
            if (approvedText != null && !approvedText.isEmpty()) {
                instance.setApproved(LocalDate.parse(approvedText));
            } else {
                instance.setApproved(null);
            }


            System.out.println("Eredmény: " + instance);

            onClick.accept(instance);
            instance = null;
        }, 6, 0);

    }
}
