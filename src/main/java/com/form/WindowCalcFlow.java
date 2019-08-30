package com.form;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCalcFlow extends Main {

    @FXML
    private TextField textField4, textField5;

    @FXML
    private Label label3;

    @FXML
    private Button buttonClose2;


    @FXML
    public void initialize() {
    }

    void showWindowCalcFlow() {
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("/MyForm3.fxml"));
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Розрахунок витрати води");
            secondaryStage.setScene(new Scene(root2, 500, 170));
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void calcFlow() {
        double q = 0;
        int dt = 1;
        double f;

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

                    Alert alert = new Alert(Alert.AlertType.ERROR, "Введене значення не є числовим або невірний формат числа!", ButtonType.CLOSE);
                    alert.show();
                    e.printStackTrace();
                }
            }

            f = q / dt / 1.163;
            label3.setText(String.format("%.1f", f));




            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                fxmlLoader.load(getClass().getResource("/MyForm2.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main controller = fxmlLoader.getController();

            controller.setFlowInField(f);

        }
    }

    @FXML
    private void backColorTextField4() {
        textField4.setStyle(null);
    }

    @FXML
    private void backColorTextField5() {
        textField5.setStyle(null);
    }

    @FXML
    private void closeWindow()  {
        Stage stage = (Stage) buttonClose2.getScene().getWindow();
        stage.close();
    }

}
