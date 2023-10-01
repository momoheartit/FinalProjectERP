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
    
    IncomeController incomeController;
    
    @FXML
    void loadIncomeFilter(){
        System.out.println("=== loadIncomeFilter pushed ===");
        incomeController.newFilter();
        
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
    
       public void initView() {
        inputController.getChildren().clear();
    }
}