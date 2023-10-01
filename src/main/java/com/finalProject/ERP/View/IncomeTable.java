package com.finalProject.ERP.View;

import com.finalProject.ERP.Model.Export.IncomeExcel;
import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.View.GUI.Table;
import java.io.File;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class IncomeTable extends Table<IncomeEntity> {

    public IncomeTable(Pane parent) {
        super(parent);

        // VBox létrehozása
        VBox vbox = new VBox();

        // Gomb hozzáadása a VBox-hoz
        Button exportButton = new Button("Export to Excel");
        exportButton.setOnAction(event -> exportToExcel());
        vbox.getChildren().add(exportButton);

        // Táblázat hozzáadása a VBox-hoz
        vbox.getChildren().add(this);

        // VBox hozzáadása a szülőhöz
        parent.getChildren().add(vbox);

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

    private void exportToExcel() {
        List<IncomeEntity> data = getItems();

        // Fájlkiválasztó dialógus inicializálása
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mentés");

        // A mentés helyének kiválasztása
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel fájlok (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            // A kiválasztott helyen mentés
            String filePath = file.getAbsolutePath();
            IncomeExcel.exportToExcel(data, filePath);
        }
    }

}
