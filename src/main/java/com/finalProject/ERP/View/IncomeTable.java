package com.finalProject.ERP.View;

import com.finalProject.ERP.Model.Export.IncomeExcel;
import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.View.GUI.Table;
import java.io.File;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class IncomeTable extends Table<IncomeEntity> {

    public IncomeTable(Pane parent) {
        super(parent);

        VBox vbox = new VBox();
        VBox.setMargin(this, new Insets(0, 0,15, 0));

        Button exportButton = new Button("Export to Excel");

        vbox.getChildren().add(this);

        parent.getChildren().add(vbox);

        addColumn("ID", "id", 10);
        addColumn("Partner", "partnerName", 175);
        addColumn("Amount", "amount", 75);
        addColumn("Project", "project", 175);
        addColumn("Created", "created", 95);
        addColumn("Approved", "approved", 95);
                
        String buttonFontStyle
                = "-fx-background-color: #3e5c76; -fx-text-fill: #0d1321; -fx-border-width: 2; -fx-font-family: 'Britannic Bold'; -fx-font-size: 16;";
        exportButton.setStyle(buttonFontStyle);
        
        exportButton.setOnAction(event -> exportToExcel());
        vbox.getChildren().add(exportButton);

        setPlaceholder("Nothing to see here");
    }

    public void setItems(List<IncomeEntity> incomes) {
        var items = getItems();
        items.clear();

        for (IncomeEntity income : incomes) {
            items.add(income);
        }
    }

    private void exportToExcel() {
        List<IncomeEntity> data = getItems();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mentés");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel fájlok (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            String filePath = file.getAbsolutePath();
            IncomeExcel.exportToExcel(data, filePath);
        }
    }

}
