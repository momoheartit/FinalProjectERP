package com.finalProject.ERP.View;

import com.finalProject.ERP.Model.PartnerEntity;
import com.finalProject.ERP.View.GUI.InputField;
import com.finalProject.ERP.View.GUI.InputForm;
import javafx.scene.layout.Pane;
import java.util.function.Consumer;

public class PartnerForm extends InputForm {

    private PartnerEntity instance;

    public PartnerForm(Pane parent) {
        super(parent);
        
        add("name", new InputField("Name: "),1,0);
        add("contact", new InputField("Contact: "),2,0);
        

        instance = null;
    }

    public void setValues(PartnerEntity partner) {
        setValue("name", partner.getName());
        setValue("contact", partner.getContact());

        instance = partner;
    }

    public void saveButton(String buttonText, Consumer<PartnerEntity> onClick) {
        button(buttonText, part
                -> {
            if (instance == null) {
                instance = new PartnerEntity();
            }
            
            instance.setName(part.getValue("name"));
            instance.setContact(part.getValue("contact"));

            onClick.accept(instance);
            instance = null;
        },3,0);
    }
}
