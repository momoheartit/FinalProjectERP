package com.finalProject.ERP.View;

import com.finalProject.ERP.Model.Export.PartnerExcel;
import com.finalProject.ERP.Model.PartnerEntity;
import com.finalProject.ERP.View.GUI.Table;
import java.io.File;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PartnerTable extends Table<PartnerEntity>{
    
    public PartnerTable(Pane parent) {
        super(parent);
        
        VBox vbox = new VBox();
        VBox.setMargin(this, new Insets(0, 0,15, 0));

        Button exportButton = new Button("Export to Excel");

        vbox.getChildren().add(this);

        parent.getChildren().add(vbox);
        
        addColumn("ID", "id", 10);
        addColumn("Name", "name", 225);
        addColumn("Contact", "contact", 255);
  
        String buttonFontStyle
                = "-fx-background-color: #3e5c76; -fx-text-fill: #0d1321; -fx-border-width: 2; -fx-font-family: 'Britannic Bold'; -fx-font-size: 16;";
        exportButton.setStyle(buttonFontStyle);

        exportButton.setOnAction(event -> exportToExcel());
        vbox.getChildren().add(exportButton);

        setPlaceholder("Nothing to see here");
    }

    public void setItems(List<PartnerEntity> partners) {
        var items = getItems();
        items.clear();

        for (PartnerEntity partner : partners) {
            items.add(partner);
        }
    }

    private void exportToExcel() {
        List<PartnerEntity> data = getItems();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mentés");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel fájlok (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            String filePath = file.getAbsolutePath();
            PartnerExcel.exportToExcel(data, filePath);
        }
    }

}