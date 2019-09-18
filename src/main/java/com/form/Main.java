package com.form;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.LoadFromDataBase;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        LoadFromDataBase lfdb = new LoadFromDataBase();
        lfdb.fillingActuators();

        ControllerMain controllerMain = new ControllerMain();
        controllerMain.showStage();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
