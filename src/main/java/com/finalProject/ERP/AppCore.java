package com.finalProject.ERP;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class AppCore extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("app.fxml"));
        

        Scene scene = new Scene(loader.load(),1000,750);
        stage.setScene(scene);
        stage.setTitle("Feladatok kezelése");
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        context = new SpringApplicationBuilder(ErpApplication.class).run();
    }

    private static ConfigurableApplicationContext context;

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}