package com.form;

import calculations.Kvs;
import calculations.KvsKlapana;
import entity.Actuator;
import entity.Adapter;
import entity.Valve;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persistence.impl.ActuatorImpl;
import persistence.impl.ValveImpl;

import java.io.IOException;
import java.util.*;

public class ControllerMain {

    private final Stage thisStage;

    //---------------------------------------------------------------------------VALVES-----------------------------------------------------------
    @FXML
    private TextField textFieldFlow, textFieldDp, textFieldDtemp;
    @FXML
    private ComboBox<Integer> comboBoxDensityMix, comboDn, comboPorts;
    @FXML
    private ComboBox<Double> comboKvs;
    @FXML
    private ComboBox<String> comboPn, comboConnection, comboType;
    @FXML
    private Label labelDensityMix, labelKv, labelMinKvs, labelOptimalKvs, labelMaxKvs, labelMinDp, labelOptimalDp, labelMaxDp, labelNutsArticle1, labelNutsArticle2, labelNutsPrice1, labelNutsPrice2, labelWaterSpeed, labelValveArticle, labelValvePrice, labelNutsArticle3, labelNutsPrice3;
    @FXML
    private Button buttonClose, buttonCalcFlow, buttonAboutProgram;

    @FXML
    private TableView<Valve> valveTableView = new TableView<>();
    @FXML
    private TableColumn<Valve, String> articleValveColumn;
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
    private TableColumn<Valve, Double> priceValveColumn;
    @FXML
    private ImageView imageValve1, imageNuts1, imageValve2, imageNuts2;

    //---------------------------------------------------------------------------ACTUATORS-----------------------------------------------------------
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> comboVoltage, comboSignal, comboContacts, comboEndSwitch, comboTimeWay, comboPower, comboStock;
    @FXML
    private TextField TextFieldArtValveForActuator;
    @FXML
    private Label labelActuatorArticle, labelActuatorPrice, labelAdapterArticle1, labelAdapterPrice1, labelAdapterArticle2, labelAdapterPrice2, labelSumm;
    @FXML
    private Button buttonFindActuator;

    @FXML
    private TableView<Actuator> actuatorTableView = new TableView<>();

    @FXML
    private TableColumn<Actuator, String> articleActuatorColumn;
    @FXML
    private TableColumn<Actuator, String> voltageColumn;
    @FXML
    private TableColumn<Actuator, String> signalColumn;
    @FXML
    private TableColumn<Actuator, String> noncColumn;
    @FXML
    private TableColumn<Actuator, String> endposColumn;
    @FXML
    private TableColumn<Actuator, String> timeposColumn;
    @FXML
    private TableColumn<Actuator, String> powerColumn;
    @FXML
    private TableColumn<Actuator, String> strokeColumn;
    @FXML
    private TableColumn<Actuator, Double> priceActuatorColumn;

    @FXML
    private ImageView imageActuator, imageAdapter;


    private ValveImpl valveImpl = new ValveImpl();
    private ActuatorImpl actuatorImpl = new ActuatorImpl();
    private Set<Valve> allValves = valveImpl.findAllValve();
    private Set<Actuator> allActuators = actuatorImpl.findAllActuator();
    private List<Double> sortedArrayKvs;
    private Double currentFlow;
    private Kvs kvs = new Kvs();
    private Valve candidateValve = new Valve();


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
        fillingValveCombo(new ArrayList<>(allValves));
        fillingActuatorCombo(new ArrayList<>(allActuators));
        //printSortedKvs();

    }

    @FXML
    public void buttonCalculateAction() {

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
        imageValve1.setImage(new Image("images/0.jpg"));
        imageNuts1.setImage(new Image("images/0.jpg"));
        imageValve2.setImage(new Image("images/0.jpg"));
        imageNuts2.setImage(new Image("images/0.jpg"));
        labelWaterSpeed.setText("0");
    }

    @FXML
    private void clearAllActuators() {
        comboVoltage.setValue(null);
        comboSignal.setValue(null);
        comboContacts.setValue(null);
        comboEndSwitch.setValue(null);
        comboTimeWay.setValue(null);
        comboPower.setValue(null);
        comboStock.setValue(null);
        actuatorTableView.getItems().clear();
        imageActuator.setImage(new Image("images/0.jpg"));
        imageAdapter.setImage(new Image("images/0.jpg"));
        labelActuatorArticle.setText("");
        labelActuatorPrice.setText("");
        labelAdapterArticle1.setText("");
        labelAdapterArticle2.setText("");
        labelAdapterPrice1.setText("");
        labelAdapterPrice2.setText("");
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

        //Double dp = Math.pow(0.01 * currentFlow / sortedArrayKvs.get(indexOfSortedList) * 1000, 2);
        Double dp = kvs.getDensityMix() * Math.pow(currentFlow / sortedArrayKvs.get(indexOfSortedList), 2) / 10;

        return String.format("%.1f", dp);
    }

    public void fillingValveCombo(List<Valve> arr) {

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

    public void fillingActuatorCombo(List<Actuator> arr) {

        ObservableList<String> fillingVoltage = FXCollections.observableArrayList("230 В", "24 В");
        comboVoltage.setItems(fillingVoltage);
        ObservableList<String> fillingSignal = FXCollections.observableArrayList("Відкр./Закр.", "3 точки", "0-10 В");
        comboSignal.setItems(fillingSignal);
        ObservableList<String> fillingContacts = FXCollections.observableArrayList("НВ", "НЗ", "НВ/НЗ");
        comboContacts.setItems(fillingContacts);
        ObservableList<String> fillingEndSwitch = FXCollections.observableArrayList("Ні", "Сигн полож", "Кінц вим");
        comboEndSwitch.setItems(fillingEndSwitch);

        SortedSet<String> sortedTimeWay = new TreeSet<>();
        SortedSet<String> sortedPower = new TreeSet<>();
        SortedSet<String> sortedStock = new TreeSet<>();

        for (int i = 0; i < arr.size() - 1; i++) {

            sortedTimeWay.add(arr.get(i).getTimepos());
            sortedPower.add(arr.get(i).getPower());
            sortedStock.add(arr.get(i).getStroke());
        }

        ObservableList<String> fillingTimeWay = FXCollections.observableArrayList(new ArrayList<>(sortedTimeWay));
        comboTimeWay.setItems(fillingTimeWay);
        ObservableList<String> fillingPower = FXCollections.observableArrayList(new ArrayList<>(sortedPower));
        comboPower.setItems(fillingPower);
        ObservableList<String> fillingStock = FXCollections.observableArrayList(new ArrayList<>(sortedStock));
        comboStock.setItems(fillingStock);
    }

    @FXML
    public void buttonFindValveAction() {

        ObservableList<Valve> arrValveForTable = FXCollections.observableArrayList(valveImpl.findValveByComboBox(comboKvs.getValue(), comboDn.getValue(), comboPorts.getValue(), comboPn.getValue(), comboConnection.getValue(), comboType.getValue()));

        articleValveColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
        kvsColumn.setCellValueFactory(new PropertyValueFactory<>("kvs"));
        dnColumn.setCellValueFactory(new PropertyValueFactory<>("dn"));
        portsColumn.setCellValueFactory(new PropertyValueFactory<>("ports"));
        pnColumn.setCellValueFactory(new PropertyValueFactory<>("pn"));
        connectionColumn.setCellValueFactory(new PropertyValueFactory<>("connection"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        temperatureColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        priceValveColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        valveTableView.setItems(arrValveForTable);

        valveTableView.requestFocus();
        handleRowValveSelect();
        valveTableView.getSelectionModel().select(0);
        valveTableView.getFocusModel().focus(0);

    }

    @FXML
    public void buttonFindActuatorAction() {

        ValveImpl findArt = new ValveImpl();
        candidateValve = findArt.findValveByArticle(TextFieldArtValveForActuator.getText());


        ObservableList<Actuator> arrActuatorForTable = FXCollections.observableArrayList(actuatorImpl.findActuatorByComboBox(comboVoltage.getValue(), comboSignal.getValue(), comboContacts.getValue(), comboEndSwitch.getValue(), comboTimeWay.getValue(), comboPower.getValue(), comboStock.getValue(), candidateValve));

        articleActuatorColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
        voltageColumn.setCellValueFactory(new PropertyValueFactory<>("voltage"));
        signalColumn.setCellValueFactory(new PropertyValueFactory<>("signal"));
        noncColumn.setCellValueFactory(new PropertyValueFactory<>("nonc"));
        endposColumn.setCellValueFactory(new PropertyValueFactory<>("endpos"));
        timeposColumn.setCellValueFactory(new PropertyValueFactory<>("timepos"));
        powerColumn.setCellValueFactory(new PropertyValueFactory<>("power"));
        strokeColumn.setCellValueFactory(new PropertyValueFactory<>("stroke"));
        priceActuatorColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        actuatorTableView.setItems(arrActuatorForTable);

        actuatorTableView.requestFocus();
        handleRowActuatorSelect();
        actuatorTableView.getSelectionModel().select(0);
        actuatorTableView.getFocusModel().focus(0);


    }

    @FXML
    private void handleRowValveSelect() {

        TableView.TableViewSelectionModel<Valve> selectionValve = valveTableView.getSelectionModel();
        selectionValve.selectedItemProperty().addListener(new ChangeListener<Valve>() {

            @Override
            public void changed(ObservableValue<? extends Valve> observable, Valve oldValue, Valve newValue) {
                if (newValue != null) {
                    imageValve1.setImage(new Image(newValue.getImageurl()));
                    imageValve2.setImage(imageValve1.getImage());
                    labelValveArticle.setText(newValue.getArticle());
                    labelValvePrice.setText(String.format("%.2f", newValue.getPrice()));
                    clearAllActuators();
                    summCalculation();
                    labelActuatorArticle.setText("");
                    labelActuatorPrice.setText("");
                    labelAdapterArticle1.setText("");
                    labelAdapterArticle2.setText("");
                    labelAdapterPrice1.setText("");
                    labelAdapterPrice2.setText("");
                    //Double speed = 4 * kvs.getFlow() / (3600 * Math.PI * Math.pow(newValue.getDn() / 1000.0, 2));
                    Double speed = kvs.getFlow() * 10000 / (newValue.getKvs() * 828);

                    if (speed > 3.0) {
                        labelWaterSpeed.setTextFill(Color.web("red"));
                    } else {
                        labelWaterSpeed.setTextFill(Color.web("black"));

                    }
                    labelWaterSpeed.setText(String.format("%.1f", speed));
                    TextFieldArtValveForActuator.setText(newValue.getArticle());

                    if (newValue.getConnection().equals("Зовнішня різьба")) {
                        imageNuts1.setImage(new Image("images/62201.jpg"));
                        imageNuts2.setImage(imageNuts1.getImage());
                        labelNutsArticle1.setText(newValue.getNuts().getArticle());
                        labelNutsArticle2.setText(labelNutsArticle1.getText());
                        labelNutsArticle3.setText(labelNutsArticle1.getText());
                        labelNutsPrice1.setText(String.format("%.2f", newValue.getNuts().getPrice()));
                        labelNutsPrice2.setText(labelNutsPrice1.getText());
                        labelNutsPrice3.setText(String.format("%.2f", newValue.getNuts().getPrice() * newValue.getPorts()));
                    } else {
                        imageNuts1.setImage(new Image("images/0.jpg"));
                        imageNuts2.setImage(imageNuts1.getImage());
                        labelNutsArticle1.setText("");
                        labelNutsPrice1.setText("");
                        labelNutsArticle2.setText("");
                        labelNutsPrice2.setText("");
                        labelNutsArticle3.setText("");
                        labelNutsPrice3.setText("");
                    }
                    summCalculation();
                }
            }
        });

    }

    @FXML
    private void handleRowActuatorSelect() {

        TableView.TableViewSelectionModel<Actuator> selectionActuator = actuatorTableView.getSelectionModel();
        selectionActuator.selectedItemProperty().addListener(new ChangeListener<Actuator>() {

            @Override
            public void changed(ObservableValue<? extends Actuator> observable, Actuator oldValue, Actuator newValue) {
                if (newValue != null) {
                    imageActuator.setImage(new Image(newValue.getImageurl()));
                    labelActuatorArticle.setText(newValue.getArticle());
                    labelActuatorPrice.setText(String.format("%.2f", newValue.getPrice()));
                    Adapter adapter = findAdapter(newValue);

                    if (adapter != null) {
                        labelAdapterArticle1.setText(adapter.getArticle());
                        labelAdapterPrice1.setText(String.format("%.2f", adapter.getPrice()));
                        labelAdapterArticle2.setText(labelAdapterArticle1.getText());
                        labelAdapterPrice2.setText(labelAdapterPrice1.getText());
                        imageAdapter.setImage(new Image(adapter.getImageurl()));
                    } else {
                        labelAdapterArticle1.setText("");
                        labelAdapterPrice1.setText("");
                        labelAdapterArticle2.setText("");
                        labelAdapterPrice2.setText("");
                        imageAdapter.setImage(new Image("images/0.jpg"));
                    }
                    summCalculation();
                }
            }
        });
    }

    @FXML
    private void setFocusOnButton() {
        buttonFindActuator.setDefaultButton(true);
    }


    private void summCalculation() {

        double summ = 0.0;
        if (!labelActuatorPrice.getText().equals("")) {
            summ = summ + Double.valueOf(labelActuatorPrice.getText().replaceAll(",", "."));
        }
        if (!labelAdapterPrice1.getText().equals("")) {
            summ = summ + Double.valueOf(labelAdapterPrice1.getText().replaceAll(",", "."));
        }
        if (!labelValvePrice.getText().equals("")) {
            summ = summ + Double.valueOf(labelValvePrice.getText().replaceAll(",", "."));
        }
        if (!labelNutsPrice3.getText().equals("")) {
            summ = summ + Double.valueOf(labelNutsPrice3.getText().replaceAll(",", "."));
        }
        labelSumm.setText(String.format("%.2f", summ));
    }

    private Adapter findAdapter(Actuator actuator) {
        Adapter adapter = null;
        List<Adapter> list1 = new ArrayList<>(actuator.getAdapters());
        List<Adapter> list2 = new ArrayList<>(candidateValve.getAdapters());
        for (Adapter a1 : list1) {
            for (Adapter a2 : list2) {
                if (a1.equals(a2)) {
                    adapter = a1;
                }
            }
        }
        return adapter;
    }

}