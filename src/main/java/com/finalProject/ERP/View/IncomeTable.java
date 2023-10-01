package com.finalProject.ERP.View;

import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.View.GUI.Table;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class IncomeTable extends Table<IncomeEntity> {

    public IncomeTable(Pane parent) {
        super(parent);
        
        Button exportButton = new Button("Export to Excel");
        exportButton.setOnAction(event -> exportToExcel());
        parent.getChildren().add(exportButton);
        
        addColumn("ID", "id", 10);
        addColumn("Partner", "partnerName", 100);
        addColumn("Amount", "amount", 75);
        addColumn("Project", "project", 175);
        addColumn("Created", "created", 175);
        addColumn("Approved", "approved", 175);

        setPlaceholder("Nothing to see here");
    }

    public void setItems(List<IncomeEntity> incomes) {
        var items = getItems();
        items.clear();

        for (IncomeEntity income : incomes) {
            items.add(income);
        }
    }
    
    private void exportToExcel(){
        System.out.println("teszem excelbe asszonyom!");
    }
}
