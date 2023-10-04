package com.finalProject.ERP.View;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ErrorDialog {

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}

