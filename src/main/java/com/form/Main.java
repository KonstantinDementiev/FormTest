package com.form;

import calculations.Kvs;
import calculations.KvsKlapana;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Main extends Application {


    @FXML
    private TextField textField1, textField2, textField3;
    @FXML
    private ComboBox<Integer> comboBox1;


    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("/MyForm2.fxml"));
        primaryStage.setTitle("Підбір регулюючих клапанів та приводів HERZ Select 3.0");
        primaryStage.setScene(new Scene(root, 1000, 700));

        ObservableList<Integer> percentGlikol = FXCollections.observableArrayList(0, 10, 15, 20, 25, 30, 35, 40, 45, 50);
        comboBox1.setItems(percentGlikol);



        primaryStage.show();



    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void button1Action() {
        Kvs kvs = new Kvs();
        KvsKlapana kvsKlapana = new KvsKlapana();


        if (textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Заповніть всі поля для вихідних даних!", ButtonType.CLOSE);

        } else {
            String tf1 = textField1.getText();
            String tf2 = textField2.getText();
            String tf3 = textField3.getText();

            if (isDouble(tf1) || isInteger(tf2) || isInteger(tf3)) {

                kvs.setFlow(Double.parseDouble(tf1));
                kvs.setPressureDrop(Integer.parseInt(tf2));
                kvs.setTemperature(Integer.parseInt(tf3));

            }


        }

    }


    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {

        launch(args);


    }
}
