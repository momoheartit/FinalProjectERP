package com.finalProject.ERP.View;

import com.finalProject.ERP.Controller.IncomeController;
import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.Model.Model;
import com.finalProject.ERP.View.GUI.InputForm;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class IncomeChart extends InputForm {

    private IncomeController controller;

    public IncomeChart(Pane parent, IncomeController controller, Model model) {
        super(parent);
        this.controller = controller;

        List<IncomeEntity> incomeData = model.getAllIncomeWithinLastThreeMonths();

        //X
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Days of the month");
        xAxis.setStyle("-fx-font-size: 10px; -fx-font-family: 'Britannic Bold';");

        //Y
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("AMOUNT $");
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis) {
            @Override
            public String toString(Number object) {
                return String.format("%,.0f", object);
            }
        });
        yAxis.setStyle("-fx-font-size: 10px; -fx-font-family: 'Britannic Bold';");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("LAST 4 MONTHS");

        int dayCounter = 1;

        XYChart.Series<Number, Number> seriesCurrentMonth = new XYChart.Series<>();
        seriesCurrentMonth.setName("CURRENT MONTH");

        XYChart.Series<Number, Number> seriesLastMonth = new XYChart.Series<>();
        seriesLastMonth.setName("1 MONTH AGO");

        XYChart.Series<Number, Number> seriesThirdMonthAgo = new XYChart.Series<>();
        seriesThirdMonthAgo.setName("2 MONTHS AGO");

        XYChart.Series<Number, Number> seriesFourthMonthAgo = new XYChart.Series<>();
        seriesFourthMonthAgo.setName("3 MONTHS AGO");

        for (IncomeEntity income : incomeData) {
            int day = dayCounter++;
            int month = income.getCreated().getMonthValue();

            int maxDayOfMonth = LocalDate.now().withMonth(month).lengthOfMonth();
            day = Math.min(day, maxDayOfMonth);

            if (month == LocalDate.now().minusMonths(1).getMonthValue()) {
                seriesLastMonth.getData().add(new XYChart.Data<>(day, income.getAmount()));
            } else if (month == LocalDate.now().getMonthValue()) {
                seriesCurrentMonth.getData().add(new XYChart.Data<>(day, income.getAmount()));
            } else if (month == LocalDate.now().minusMonths(2).getMonthValue()) {
                seriesThirdMonthAgo.getData().add(new XYChart.Data<>(day, income.getAmount()));
            } else if (month == LocalDate.now().minusMonths(3).getMonthValue()) {
                seriesFourthMonthAgo.getData().add(new XYChart.Data<>(day, income.getAmount()));
            }

            if (dayCounter > maxDayOfMonth) {
                dayCounter = 1;
            }
        }

        lineChart.getData().addAll(seriesCurrentMonth, seriesLastMonth, seriesThirdMonthAgo, seriesFourthMonthAgo);

        lineChart.setTitle("LAST 4 MONTHS - TOTAL AMOUNT");
        lineChart.setStyle("-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';");

        lineChart.getData().forEach(series -> {
            double totalAmount = series.getData().stream().mapToDouble(data -> ((XYChart.Data<Number, Number>) data).getYValue().doubleValue()).sum();
            series.setName(series.getName() + " ($" + String.format("%,.0f", totalAmount) + ")");
            series.getNode().setStyle("-fx-font-size: 10px; -fx-font-family: 'Britannic Bold';");
        });

        lineChart.setTranslateY(50);
        lineChart.setTranslateX(10);
        parent.getChildren().add(lineChart);

        Button saveButton = new Button("Save Chart");
        String buttonFontStyle
                = "-fx-background-color: #3e5c76; -fx-text-fill: #0d1321; -fx-border-width: 2; "
                + "-fx-font-family: 'Britannic Bold'; -fx-font-size: 18; -fx-cursor: hand;";
        saveButton.setStyle(buttonFontStyle);
        //saveButton.setStyle("-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';");
        saveButton.setOnAction(event -> saveChartAsImage(lineChart));
        saveButton.setTranslateY(lineChart.getHeight() + 10);
        saveButton.setTranslateX(10);
        parent.getChildren().add(saveButton);
    }

    private void saveChartAsImage(Chart chart) {
        WritableImage image = chart.snapshot(new SnapshotParameters(), null);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try {
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(image, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
