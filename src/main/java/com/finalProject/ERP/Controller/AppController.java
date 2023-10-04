package com.finalProject.ERP.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AppController implements Initializable {

    @FXML
    private Label dateTimeLabel;

    @FXML
    private AnchorPane inputController;
    
    @FXML
    private Label txtTitle;

    IncomeController incomeController;
    PartnerController partnerController;
    
    @FXML
    void loadIncomeFilter() {
        incomeController.newFilter();
    }

    @FXML
    public void newIncome() {
        incomeController.newIncome();
    }
    
    @FXML
    public void notApproved() {
        incomeController.notApproved();
    }
    
    @FXML
    public void newStatistics() {
        incomeController.newStatistics();
    }

    @FXML
    void loadPartnerFilter() {
        partnerController.newFilter();
    }

    @FXML
    public void newPartner() {
        partnerController.newPartner();
    }

    private void updateDateTimeLabel() {
        Platform.runLater(() -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String formattedDateTime = dateFormat.format(new Date());
            dateTimeLabel.setText(formattedDateTime);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        incomeController = new IncomeController(this);
        partnerController = new PartnerController(this);

        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateDateTimeLabel();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public AnchorPane getContainer() {
        return inputController;
    }

    public void initView(String title) {
        txtTitle.setText(title);
        inputController.getChildren().clear();
    }
}
