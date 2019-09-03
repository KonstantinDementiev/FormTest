package com.form;

import calculations.Kvs;
import calculations.KvsKlapana;
import entity.Valve;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import persistence.impl.ValveImpl;
import java.io.IOException;
import java.util.*;

public class ControllerMain {

    private final Stage thisStage;

    @FXML
    private TextField textField1, textField2, textField3;
    @FXML
    private ComboBox<Integer> comboBox1;
    @FXML
    private Label label1, label2, label3, label4, label5, label6, label7, label8;
    @FXML
    private Button buttonClose1, button2;

    private ValveImpl valveImpl = new ValveImpl();
    private List<Double> sortedArrayKvs;
    private Double currentFlow;


    ControllerMain() {

        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainForm.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load(), 1000, 700));
            thisStage.setTitle("Підбір регулюючих клапанів та приводів HERZ Select 3.0");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        ObservableList<Integer> percentGlikol = FXCollections.observableArrayList(0, 10, 15, 20, 25, 30, 35, 40, 45, 50);
        comboBox1.setItems(percentGlikol);
        comboBox1.setValue(0);
        button2.setOnAction(event -> openCalcForm());
        List<Valve> allValves = valveImpl.findAllValve();
        sortedArrayKvs = bubbleSortKvs(allValves);
        //printSortedKvs();

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
        kvs.setDensityMix(kvsKlapana.calcDensityMix(kvs.getGlikol(), kvs.getTemperature()));
        kvs.setKv(kvsKlapana.calcKv(kvs.getPressureDrop(), kvs.getFlow(), kvs.getDensityMix()));
        label1.setText(String.format("%.1f", kvs.getDensityMix()));
        label2.setText(String.format("%.1f", kvs.getKv()));
        currentFlow = kvs.getFlow();
        findOptimalKvs(kvs.getKv());
    }

    private List<Double> bubbleSortKvs(List<Valve> arr) {

        SortedSet<Double> sortedKvs = new TreeSet<>();
        for (int i = 0; i < arr.size() - 1; i++) {
            sortedKvs.add(arr.get(i).getKvs());
        }
        return new ArrayList<>(sortedKvs);
    }

    private void findOptimalKvs(Double calcKv) {

        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");

        int optimalIndex = 0;

        if (calcKv < sortedArrayKvs.get(0)) {
            label4.setText(String.valueOf(sortedArrayKvs.get(0)).replaceAll("[.]", ","));
            label5.setText(String.valueOf(sortedArrayKvs.get(1)).replaceAll("[.]", ","));
            label7.setText(dpKvs(0));
            label8.setText(dpKvs(1));
        } else {
            if (calcKv > sortedArrayKvs.get(sortedArrayKvs.size() - 1) || calcKv > sortedArrayKvs.get(sortedArrayKvs.size() - 2)) {
                label3.setText(String.valueOf(sortedArrayKvs.get(sortedArrayKvs.size() - 2)).replaceAll("[.]", ","));
                label4.setText(String.valueOf(sortedArrayKvs.get(sortedArrayKvs.size() - 1)).replaceAll("[.]", ","));
                label6.setText(dpKvs(sortedArrayKvs.size() - 2));
                label7.setText(dpKvs(sortedArrayKvs.size() - 1));

            } else {
                for (int i = 0; i < sortedArrayKvs.size() - 1; i++) {
                    if (sortedArrayKvs.get(i) > calcKv && sortedArrayKvs.get(i - 1) < calcKv) {
                        optimalIndex = i;
                    }
                }
                label3.setText(String.valueOf(sortedArrayKvs.get(optimalIndex - 1)).replaceAll("[.]", ","));
                label4.setText(String.valueOf(sortedArrayKvs.get(optimalIndex)).replaceAll("[.]", ","));
                label5.setText(String.valueOf(sortedArrayKvs.get(optimalIndex + 1)).replaceAll("[.]", ","));
                label6.setText(dpKvs(optimalIndex -1));
                label7.setText(dpKvs(optimalIndex));
                label8.setText(dpKvs(optimalIndex +1));
            }
        }
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
        label1.setText("0");
        label2.setText("0");
        label3.setText("0");
        label4.setText("0");
        label5.setText("0");
        label6.setText("0");
        label7.setText("0");
        label8.setText("0");
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) buttonClose1.getScene().getWindow();
        stage.close();
    }

    void showStage() {
        thisStage.showAndWait();
    }

    private void openCalcForm() {
        ControllerCalcFlow controllerCalcFlow = new ControllerCalcFlow(this);
        controllerCalcFlow.showStage();
    }

    void setTextFromCalcForm(String text) {
        textField1.setText(text);
    }

    private boolean isInt(String x) throws NumberFormatException {
        try {
            Integer.parseInt(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isDouble(String x) throws NumberFormatException {
        try {
            Double.parseDouble(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void printSortedKvs() {
        for (Double kvs : sortedArrayKvs) {
            System.out.println(kvs);
        }
    }

    private String dpKvs(int indexOfSortedList) {
        Double dp = Math.pow(0.01 * currentFlow / sortedArrayKvs.get(indexOfSortedList) * 1000, 2);
        return String.format("%.1f", dp);
    }

}
