package com.form;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAboutProgram {

    private Stage thisStage;
    private ControllerMain controllerMain;

    @FXML
    private Button buttonClose3;


    ControllerAboutProgram(ControllerMain controllerMain) {

        this.controllerMain = controllerMain;
        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AboutProgramForm.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load(), 713, 228));
            thisStage.setTitle("HERZ Select 3.0");
            thisStage.initModality(Modality.APPLICATION_MODAL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        buttonClose3.setOnAction(event -> closeWindow());
    }

    private void closeWindow() {
        Stage stage = (Stage) buttonClose3.getScene().getWindow();
        stage.close();
    }

    void showStage() {
        thisStage.showAndWait();
    }

}
