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
    @FXML
    private Label label1, label2;


    @Override
    public void start(final Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/MyForm2.fxml"));
        primaryStage.setTitle("Підбір регулюючих клапанів та приводів HERZ Select 3.0");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        ObservableList<Integer> percentGlikol = FXCollections.observableArrayList(0, 10, 15, 20, 25, 30, 35, 40, 45, 50);
        comboBox1.setItems(percentGlikol);
        comboBox1.setValue(0);
    }

    @FXML
    public void button1Action() {
        Kvs kvs = new Kvs();
        KvsKlapana kvsKlapana = new KvsKlapana();

        if (textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Заповніть всі поля для вихідних даних!", ButtonType.CLOSE);
            alert.show();

        } else {
            String tf1 = textField1.getText().replaceAll(",", ".");
            String tf2 = textField2.getText().replaceAll(",", ".");
            String tf3 = textField3.getText().replaceAll(",", ".");

            try {
                kvs.setFlow(Double.parseDouble(tf1));
                kvs.setPressureDrop(Integer.parseInt(tf2));
                kvs.setTemperature(Integer.parseInt(tf3));
                kvs.setGlikol(comboBox1.getValue());

            } catch (NumberFormatException e) {
                if (!isDouble(tf1)) {
                    textField1.setStyle("-fx-background-color: #F89393");
                }
                if (!isInt(tf2)) {
                    textField2.setStyle("-fx-background-color: #F89393");
                }
                if (!isInt(tf3)) {
                    textField3.setStyle("-fx-background-color: #F89393");
                }
                Alert alert = new Alert(Alert.AlertType.ERROR, "Введене значення не є числовим або невірний формат числа!", ButtonType.CLOSE);
                alert.show();
                e.printStackTrace();
            }
        }
        Double mixDest = kvsKlapana.calcDensityMix(kvs.getGlikol(), kvs.getTemperature());
        Double kvKlap = kvsKlapana.calcKv(kvs.getPressureDrop(), kvs.getFlow(), mixDest);
        label1.setText(String.format("%.1f", mixDest));
        label2.setText(String.format("%.1f", kvKlap));
    }


    @FXML
    public void button2Action() {
        WindowCalcFlow w2 = new WindowCalcFlow();
        w2.showWindowCalcFlow();

    }

    public boolean isInt(String x) throws NumberFormatException {
        try {
            Integer.parseInt(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDouble(String x) throws NumberFormatException {
        try {
            Double.parseDouble(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setFlowInField(double d){
        textField1.setText(String.valueOf(d));
    }

    @FXML
    private void backColorTextField1() {
        textField1.setStyle(null);
    }

    @FXML
    private void backColorTextField2() {
        textField2.setStyle(null);
    }

    @FXML
    private void backColorTextField3() {
        textField3.setStyle(null);
    }

    @FXML
    private void clearAllTextField() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        comboBox1.setValue(0);
    }

    public static void main(String[] args) {

        launch(args);

    }
}
