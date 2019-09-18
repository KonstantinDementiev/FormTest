package com.form;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.LoadFromDataBase;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        LoadFromDataBase lfdb = new LoadFromDataBase();
        try {

            lfdb.valveFromExcel();
            lfdb.nutsFromExcel();
//            lfdb.actuatorFromExcel();
//            lfdb.adapterFromExcel();

        } catch (IOException e) {
            e.printStackTrace();
        }

        ControllerMain controllerMain = new ControllerMain();
        controllerMain.showStage();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
