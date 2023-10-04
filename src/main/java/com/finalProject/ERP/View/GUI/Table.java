package com.finalProject.ERP.View.GUI;

import java.util.function.BiConsumer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class Table<T> extends TableView<T> {

    public Table(Pane parent) {
        super();
        this.setStyle("-fx-control-inner-background: #3e5c76; -fx-accent: #3677e0; -fx-border-color: black; -fx-border-width: 3;");
        parent.getChildren().add(this);
    }

    public void addColumn(String header, String propertyName, int width) {
        TableColumn<T, String> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        column.setMinWidth(width);
        column.setStyle("-fx-font-family: 'Britannic Bold'; -fx-font-size: 14;");
        getColumns().add(column);
    }

    public void addActionColumn(String buttonColor, String buttonTextColor,String buttonText, BiConsumer<T, Integer> onClick) {
        TableColumn<T, String> column = new TableColumn<>();

        Callback< TableColumn<T, String>, TableCell<T, String>> factory;
        factory = new Callback<>() {
            @Override
            public TableCell<T, String> call(TableColumn<T, String> param) {
                TableCell<T, String> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            Button button = new Button(buttonText);
                            button.setStyle("-fx-background-color: " + buttonColor + "; -fx-text-fill: " + buttonTextColor + "; -fx-border-width: 1; -fx-font-family: 'Britannic Bold'; -fx-font-size: 14; -fx-cursor: hand;");
                            button.setOnAction(evt
                                    -> {
                                int index = getIndex();
                                T entity = getTableRow().getItem();

                                onClick.accept(entity, index);
                            });

                            setGraphic(button);
                        }
                        setText(null);
                    }
                };
                return cell;
            }
        };

        column.setCellFactory(factory);
        getColumns().add(column);
    }

    public void addItem(T item) {
        getItems().add(item);
    }

    public void setPlaceholder(String placeholder) {
        setPlaceholder(new Label(placeholder));
    }
}
