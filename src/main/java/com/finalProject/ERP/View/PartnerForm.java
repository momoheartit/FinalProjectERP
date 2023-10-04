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

        add("name", new InputField("Name: ", InputField.FieldType.LETTER), 1, 0);
        add("contact", new InputField("Contact: ", InputField.FieldType.LETTER), 2, 0);

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
        String name = part.getValue("name").trim();
        String contact = part.getValue("contact").trim();

        if (name.isEmpty() || contact.isEmpty()) {
            // Hiba esetén hibaüzenet megjelenítése
            ErrorDialog.showError("Mandatory fields:\n"
                    + "- Partner's name\n"
                    + "- Contact");
        } else {
            try {
                instance.setName(name);
                instance.setContact(contact);

                onClick.accept(instance);
                instance = null;
            } catch (Exception e) {
                // Hiba esetén hibaüzenet megjelenítése
                ErrorDialog.showError("Unexpected error occurred.");
            }
        }
        }, 3, 0);
    }
}
