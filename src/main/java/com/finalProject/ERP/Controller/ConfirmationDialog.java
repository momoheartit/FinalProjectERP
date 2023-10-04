package com.finalProject.ERP.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ConfirmationDialog {

    public static boolean showDeleteConfirmation() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        alert.setTitle("Confirm deletion");
        alert.setHeaderText("Are you sure you want to delete it?");
        alert.setContentText("This item will be permanently removed!");

        alert.showAndWait().ifPresent(result -> {
            System.out.println("ConfirmationDialog answer: " + result);
        });

        return alert.getResult() == ButtonType.OK;
    }
}
