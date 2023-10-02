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
  
        // Módosítások a mező típusain és a sor és oszlop pozícióin
        add("partner", new InputField("Partner"), 1, 0);
        add("amount", new InputField("Amount"), 2, 0);
        add("project", new InputField("Project"), 3, 0);
        add("created", new InputDatePicker("Created"), 4, 0);
        add("approved", new InputDatePicker("Approved"), 5, 0);
        
        this.partners = partners;
    }
    
    // Új metódus az értékek beállításához
    public void setValues(IncomeEntity income) {
        // Az értékek beállítása az adott IncomeEntity alapján

//        int partnerIndex = 0;
//        int partnerId = income.getPartner().getId();
//        for (int i = 0; i < partners.size(); i++) {
//            if (partners.get(i).getId() == partnerId) {
//                partnerIndex = i;
//                break;
//            }
//        }
        
        PartnerEntity partner = income.getPartner();
        if (partner != null) {
            setValue("partner", Integer.toString(partner.getId()));
        }
        //setValue("partner", Integer.toString(partnerIndex));
        setValue("amount", Integer.toString(income.getAmount()));
        setValue("project", income.getProject());
        setValue("created", income.getCreated().toString());
        setValue("approved", income.getApproved() != null ? income.getApproved().toString() : null);

        // Az instance változó beállítása
        instance = income;
    }

    public void submit(String buttonText, Consumer<IncomeEntity> onClick) {
        button(buttonText, form
                -> {
            if (instance == null) {
                instance = new IncomeEntity();
            }

            int partnerX = Integer.parseInt(form.getValue("partner"))-1;
            PartnerEntity partner = partners.get(partnerX);
            instance.setPartner(partner);

            instance.setAmount(Integer.parseInt(form.getValue("amount")));
            instance.setProject(form.getValue("project"));
            instance.setCreated(LocalDate.parse(form.getValue("created")));
            if (form.getValue("approved") != null && !form.getValue("approved").isEmpty()) {
                instance.setApproved(LocalDate.parse(form.getValue("approved")));
            } else {
                // Ha az approved üres, akkor csak hagyjuk null-nak
                instance.setApproved(null);
            }

            onClick.accept(instance);
            instance = null;
        }, 6, 0);
    }

}
