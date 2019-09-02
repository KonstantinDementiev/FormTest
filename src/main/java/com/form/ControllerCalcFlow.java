package com.form;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCalcFlow {

    private Stage thisStage;
    private ControllerMain controllerMain;

    @FXML
    private TextField textField4, textField5;

    @FXML
    private Label label3;

    @FXML
    private Button buttonClose2;

    ControllerCalcFlow(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CalcFlowForm.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load(), 500, 170));
            thisStage.setTitle("Розрахунок витрати води");
            thisStage.initModality(Modality.APPLICATION_MODAL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        buttonClose2.setOnAction(event -> closeWindow());
    }

    @FXML
    private Double calcFlow() {
        double q = 0;
        int dt = 1;
        double f = 0;

        if (textField4.getText().equals("") || textField5.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Заповніть всі поля для вихідних даних!", ButtonType.CLOSE);
            alert.show();

        } else {
            String tf4 = textField4.getText().replaceAll(",", ".");
            String tf5 = textField5.getText().replaceAll(",", ".");

            try {
                q = Double.parseDouble(tf4);
                dt = Integer.parseInt(tf5);

            } catch (NumberFormatException e) {
                if (!isDouble(tf4)) {
                    textField4.setStyle("-fx-background-color: #F89393");
                }
                if (!isInt(tf5)) {
                    textField5.setStyle("-fx-background-color: #F89393");
                }

                Alert alert = new Alert(Alert.AlertType.ERROR, "Введене значення не є числовим або невірний формат числа!", ButtonType.CLOSE);
                alert.show();
                e.printStackTrace();

            }

            f = q / dt / 1.163;
            label3.setText(String.format("%.1f", f));
        }
        return f;
    }

    @FXML
    private void backColorTextField4() {
        textField4.setStyle(null);
    }

    @FXML
    private void backColorTextField5() {
        textField5.setStyle(null);
    }

    private void closeWindow() {
        controllerMain.setTextFromCalcForm(label3.getText());
        Stage stage = (Stage) buttonClose2.getScene().getWindow();
        stage.close();
    }

    void showStage() {
        thisStage.showAndWait();
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

}
