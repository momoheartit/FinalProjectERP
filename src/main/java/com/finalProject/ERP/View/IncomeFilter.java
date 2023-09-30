package com.finalProject.ERP.View;

import com.finalProject.ERP.Controller.IncomeController;
import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.Model.IncomeJpqlQueryBuilder;
import com.finalProject.ERP.Model.Model;
import com.finalProject.ERP.View.GUI.InputCheckBox;
import com.finalProject.ERP.View.GUI.InputComboBox;
import com.finalProject.ERP.View.GUI.InputDatePicker;
import com.finalProject.ERP.View.GUI.InputField;
import com.finalProject.ERP.View.GUI.InputForm;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.Pane;

public class IncomeFilter extends InputForm {
    
    Model model;
    private IncomeController controller;
    private IncomeJpqlQueryBuilder jpqlQueryBuilder;

    public IncomeFilter(Pane parent, IncomeController controller, Model model) {
        super(parent);
        this.controller = controller;
        this.jpqlQueryBuilder = new IncomeJpqlQueryBuilder(controller.getModel().getEntityManager());
        this.model = model;

        add("id", new InputField("ID"), 0, 0);
        add("idComboBox", new InputComboBox(null, new String[]{"=", "≠", "<", ">"}), 0, 1);

        // 2. sor
        add("partner", new InputField("Partner"), 1, 0);
        add("partnerComboBox", new InputComboBox(null, new String[]{"=", "≠", "<", ">"}), 1, 1);

        // 3. sor
        add("amount1", new InputField("Amount"), 2, 0);
        //add("amount2", new InputField(null), 2, 1);
        add("amountComboBox", new InputComboBox(null, new String[]{"=", "≠", "<", ">"}), 2, 2);

        // 4. sor
        add("created1", new InputDatePicker("Created"), 3, 0);
        //add("created2", new InputDatePicker(null), 3, 1);
        add("createdComboBox", new InputComboBox(null, new String[]{"=", "≠", "<", ">"}), 3, 2);

        // 5. sor
        add("approvedCheckBox", new InputCheckBox("Approved"), 4, 0);
        add("approvedDatePicker1", new InputDatePicker(null), 4, 1);
        //add("approvedDatePicker2", new InputDatePicker(null), 4, 2);
        add("approvedComboBox", new InputComboBox(null, new String[]{"=", "≠", "<", ">"}), 4, 3);

        button("Search", this::handleSearchButtonClick, 5, 0);
    }

    private List<IncomeEntity> buildJpqlQuery() {
        // Az IncomeJpqlQueryBuilder segítségével építsd fel a lekérdezést
        HashMap<String, String> filledValues = getFilledValues();

        // Érték validálása és parseInt használata
        String id = filledValues.get("id");
        String idOperator = filledValues.get("idComboBox");
        Integer idValue = (id != null && !id.isEmpty()) ? Integer.parseInt(id) : null;

        String partner = filledValues.get("partner");
        String partnerOperator = filledValues.get("partnerComboBox");

        String amount = filledValues.get("amount1");
        String amountOperator = filledValues.get("amountComboBox");
        Integer amountValue = (amount != null && !amount.isEmpty()) ? Integer.parseInt(amount) : null;

        String created = filledValues.get("created1");
        String createdOperator = filledValues.get("createdComboBox");

        // A többi mező értékének validálása és parseInt használata
        // ...
        // Ellenőrzés, hogy az előző mezők értékei megtöltöttek-e
        if (idValue == null || partner == null || amountValue == null || created == null) {
            // Az egyik előző mező üres, nem szükséges a ComboBoxokat figyelembe venni
            return Collections.emptyList();
        }

        // buildQuery metódus hívása a megfelelő változókkal
        return jpqlQueryBuilder.buildQuery(idOperator, idValue, partnerOperator, partner, amountOperator, amountValue);
    }

    private void handleSearchButtonClick(InputForm form) {
        System.out.println("Search gomb megnyomva");
        List<IncomeEntity> jpqlQuery = buildJpqlQuery();
        controller.handleSearchResult(jpqlQuery);
        
    }
}
