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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import persistence.impl.ValveImpl;

import java.io.IOException;
import java.util.*;

public class ControllerMain {

    private final Stage thisStage;

    @FXML
    private TextField textFieldFlow, textFieldDp, textFieldDtemp;
    @FXML
    private ComboBox<Integer> comboBoxDensityMix, comboDn, comboPorts;
    @FXML
    private ComboBox<Double> comboKvs;
    @FXML
    private ComboBox<String> comboPn, comboConnection, comboType;
    @FXML
    private Label labelDensityMix, labelKv, labelMinKvs, labelOptimalKvs, labelMaxKvs, labelMinDp, labelOptimalDp, labelMaxDp;
    @FXML
    private Button buttonClose, buttonCalcFlow, buttonAboutProgram;

    @FXML
    private TableView<Valve> valveTableView = new TableView<>();
    @FXML
    private TableColumn<Valve, String> articleColumn;
    @FXML
    private TableColumn<Valve, Double> kvsColumn;
    @FXML
    private TableColumn<Valve, Integer> dnColumn;
    @FXML
    private TableColumn<Valve, Integer> portsColumn;
    @FXML
    private TableColumn<Valve, String> pnColumn;
    @FXML
    private TableColumn<Valve, String> connectionColumn;
    @FXML
    private TableColumn<Valve, String> typeColumn;
    @FXML
    private TableColumn<Valve, String> temperatureColumn;
    @FXML
    private TableColumn<Valve, Double> priceColumn;
    @FXML
    private ImageView imageValve, imageNuts;


    private ValveImpl valveImpl = new ValveImpl();
    private List<Valve> allValves = valveImpl.findAllValve();
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

        buttonCalcFlow.setOnAction(event -> openCalcForm());
        buttonAboutProgram.setOnAction(event -> openAboutProgramForm());
        fillingCombo(allValves);
        //printSortedKvs();

    }

    @FXML
    public void buttonCalculateAction() {
        Kvs kvs = new Kvs();
        KvsKlapana kvsKlapana = new KvsKlapana();

        if (textFieldFlow.getText().equals("") || textFieldDp.getText().equals("") || textFieldDtemp.getText().equals("")) {

            if (textFieldFlow.getText().equals("")) {
                textFieldFlow.setStyle("-fx-background-color: #F89393");
            }
            if (textFieldDp.getText().equals("")) {
                textFieldDp.setStyle("-fx-background-color: #F89393");
            }
            if (textFieldDtemp.getText().equals("")) {
                textFieldDtemp.setStyle("-fx-background-color: #F89393");
            }
            Alert alert = new Alert(Alert.AlertType.ERROR, "Заповніть всі поля для вихідних даних!", ButtonType.CLOSE);
            alert.show();

        } else {
            String tf1 = textFieldFlow.getText().replaceAll(",", ".");
            String tf2 = textFieldDp.getText().replaceAll(",", ".");
            String tf3 = textFieldDtemp.getText().replaceAll(",", ".");

            try {
                kvs.setFlow(Double.parseDouble(tf1));
                kvs.setPressureDrop(Integer.parseInt(tf2));
                kvs.setTemperature(Integer.parseInt(tf3));
                kvs.setGlikol(comboBoxDensityMix.getValue());

            } catch (NumberFormatException e) {
                if (!isDouble(tf1)) {
                    textFieldFlow.setStyle("-fx-background-color: #F89393");
                }
                if (!isInt(tf2)) {
                    textFieldDp.setStyle("-fx-background-color: #F89393");
                }
                if (!isInt(tf3)) {
                    textFieldDtemp.setStyle("-fx-background-color: #F89393");
                }
                Alert alert = new Alert(Alert.AlertType.ERROR, "Введене значення не є числовим або невірний формат числа!", ButtonType.CLOSE);
                alert.show();
                e.printStackTrace();
            }
        }
        kvs.setDensityMix(kvsKlapana.calcDensityMix(kvs.getGlikol(), kvs.getTemperature()));
        kvs.setKv(kvsKlapana.calcKv(kvs.getPressureDrop(), kvs.getFlow(), kvs.getDensityMix()));
        labelDensityMix.setText(String.format("%.1f", kvs.getDensityMix()));
        labelKv.setText(String.format("%.1f", kvs.getKv()));
        currentFlow = kvs.getFlow();
        findOptimalKvs(kvs.getKv());
    }

    private void findOptimalKvs(Double calcKv) {

        labelMinKvs.setText("");
        labelOptimalKvs.setText("");
        labelMaxKvs.setText("");
        labelMinDp.setText("");
        labelOptimalDp.setText("");
        labelMaxDp.setText("");

        int optimalIndex = 0;

        if (calcKv < sortedArrayKvs.get(0)) {
            labelOptimalKvs.setText(String.valueOf(sortedArrayKvs.get(0)).replaceAll("[.]", ","));
            labelMaxKvs.setText(String.valueOf(sortedArrayKvs.get(1)).replaceAll("[.]", ","));
            labelOptimalDp.setText(dpKvs(0));
            labelMaxDp.setText(dpKvs(1));
        } else {
            if (calcKv > sortedArrayKvs.get(sortedArrayKvs.size() - 1) || calcKv > sortedArrayKvs.get(sortedArrayKvs.size() - 2)) {
                labelMinKvs.setText(String.valueOf(sortedArrayKvs.get(sortedArrayKvs.size() - 2)).replaceAll("[.]", ","));
                labelOptimalKvs.setText(String.valueOf(sortedArrayKvs.get(sortedArrayKvs.size() - 1)).replaceAll("[.]", ","));
                labelMinDp.setText(dpKvs(sortedArrayKvs.size() - 2));
                labelOptimalDp.setText(dpKvs(sortedArrayKvs.size() - 1));

            } else {
                for (int i = 0; i < sortedArrayKvs.size() - 1; i++) {
                    if (sortedArrayKvs.get(i) > calcKv && sortedArrayKvs.get(i - 1) < calcKv) {
                        optimalIndex = i;
                    }
                }
                labelMinKvs.setText(String.valueOf(sortedArrayKvs.get(optimalIndex - 1)).replaceAll("[.]", ","));
                labelOptimalKvs.setText(String.valueOf(sortedArrayKvs.get(optimalIndex)).replaceAll("[.]", ","));
                labelMaxKvs.setText(String.valueOf(sortedArrayKvs.get(optimalIndex + 1)).replaceAll("[.]", ","));
                labelMinDp.setText(dpKvs(optimalIndex - 1));
                labelOptimalDp.setText(dpKvs(optimalIndex));
                labelMaxDp.setText(dpKvs(optimalIndex + 1));
            }
        }
    }

    @FXML
    private void backColorTextField1() {
        textFieldFlow.setStyle(null);
    }

    @FXML
    private void backColorTextField2() {
        textFieldDp.setStyle(null);
    }

    @FXML
    private void backColorTextField3() {
        textFieldDtemp.setStyle(null);
    }

    @FXML
    private void clearAllKvs() {
        textFieldFlow.setText("");
        textFieldDp.setText("");
        textFieldDtemp.setText("");
        comboBoxDensityMix.setValue(0);
        labelDensityMix.setText("0");
        labelKv.setText("0");
        labelMinKvs.setText("0");
        labelOptimalKvs.setText("0");
        labelMaxKvs.setText("0");
        labelMinDp.setText("0");
        labelOptimalDp.setText("0");
        labelMaxDp.setText("0");
    }

    @FXML
    private void clearAllValves() {
        comboKvs.setValue(null);
        comboDn.setValue(null);
        comboPorts.setValue(null);
        comboPn.setValue(null);
        comboConnection.setValue(null);
        comboType.setValue(null);
        valveTableView.getItems().clear();
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    void showStage() {
        thisStage.showAndWait();
    }

    private void openCalcForm() {
        ControllerCalcFlow controllerCalcFlow = new ControllerCalcFlow(this);
        controllerCalcFlow.showStage();
    }

    private void openAboutProgramForm() {
        ControllerAboutProgram controllerAboutProgram = new ControllerAboutProgram(this);
        controllerAboutProgram.showStage();
    }

    void setTextFromCalcForm(String text) {
        textFieldFlow.setText(text);
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

    public void fillingCombo(List<Valve> arr) {

        ObservableList<Integer> percentGlikol = FXCollections.observableArrayList(0, 10, 15, 20, 25, 30, 35, 40, 45, 50);
        comboBoxDensityMix.setItems(percentGlikol);
        comboBoxDensityMix.setValue(0);

        ObservableList<Integer> fillingPorts = FXCollections.observableArrayList(2, 3, 4);
        comboPorts.setItems(fillingPorts);

        SortedSet<Double> sortedKvs = new TreeSet<>();
        SortedSet<Integer> sortedDn = new TreeSet<>();
        SortedSet<String> sortedPn = new TreeSet<>();
        SortedSet<String> sortedConnection = new TreeSet<>();
        SortedSet<String> sortedType = new TreeSet<>();

        for (int i = 0; i < arr.size() - 1; i++) {

            sortedKvs.add(arr.get(i).getKvs());
            sortedDn.add(arr.get(i).getDn());
            sortedPn.add(arr.get(i).getPn());
            sortedConnection.add(arr.get(i).getConnection());
            sortedType.add(arr.get(i).getType());
        }

        sortedArrayKvs = new ArrayList<>(sortedKvs);
        ObservableList<Double> fillingKvs = FXCollections.observableArrayList(sortedArrayKvs);
        comboKvs.setItems(fillingKvs);

        ObservableList<Integer> fillingDn = FXCollections.observableArrayList(new ArrayList<>(sortedDn));
        comboDn.setItems(fillingDn);

        ObservableList<String> fillingPn = FXCollections.observableArrayList(new ArrayList<>(sortedPn));
        comboPn.setItems(fillingPn);

        ObservableList<String> fillingConnection = FXCollections.observableArrayList(new ArrayList<>(sortedConnection));
        comboConnection.setItems(fillingConnection);

        ObservableList<String> fillingType = FXCollections.observableArrayList(new ArrayList<>(sortedType));
        comboType.setItems(fillingType);
    }

    @FXML
    public void buttonFindValveAction() {

        ObservableList<Valve> arrValveForTable = FXCollections.observableArrayList(valveImpl.findValveByComboBox(comboKvs.getValue(), comboDn.getValue(), comboPorts.getValue(), comboPn.getValue(), comboConnection.getValue(), comboType.getValue()));

        articleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
        kvsColumn.setCellValueFactory(new PropertyValueFactory<>("kvs"));
        dnColumn.setCellValueFactory(new PropertyValueFactory<>("dn"));
        portsColumn.setCellValueFactory(new PropertyValueFactory<>("ports"));
        pnColumn.setCellValueFactory(new PropertyValueFactory<>("pn"));
        connectionColumn.setCellValueFactory(new PropertyValueFactory<>("connection"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        temperatureColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        valveTableView.setItems(arrValveForTable);

        //imageValve.setImage(new Image("images/21371.jpg"));
    }

    @FXML
    private void handleRowSelect() {
        //valveTableView.setRowFactory(tv -> {
            TableRow<Valve> row = new TableRow<>();
            //row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Valve rowData = row.getItem();
                    imageValve.setImage(new Image(rowData.getImageurl()));
                }
            //});
            //return row;
      //  });
    }
}