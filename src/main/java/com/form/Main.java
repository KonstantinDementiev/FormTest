package com.form;

import entity.Valve;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import persistence.impl.ValveImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            fromDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerMain controllerMain = new ControllerMain();
        controllerMain.showStage();
    }

    public static void main(String[] args)  {
        launch(args);
    }

    private static void fromDataBase() throws IOException{
        Valve valve = new Valve();
        ValveImpl valveImpl = new ValveImpl();

        try {
            FileInputStream fis = new FileInputStream(new File("F:\\JavaProject\\FormTest\\src\\main\\resources\\herz_db_2.xlsx"));
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet1 = workbook.getSheet("Valves");
//            Sheet sheet2 = workbook.getSheet("Valves-Actuators");
//            Sheet sheet3 = workbook.getSheet("Actuators");
//            Sheet sheet4 = workbook.getSheet("Actuators-Adapters");
//            Sheet sheet5 = workbook.getSheet("Adapters");
//            Sheet sheet6 = workbook.getSheet("Valves-Adapters");
//            Sheet sheet7 = workbook.getSheet("Nuts");

            valveImpl.delAllValves();
            Row row;
            for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
                row = sheet1.getRow(i);

                valve.setArticle(row.getCell(0).getStringCellValue());
                valve.setKvs(row.getCell(1).getNumericCellValue());
                valve.setDn(((int) row.getCell(2).getNumericCellValue()));
                valve.setPorts((int) row.getCell(3).getNumericCellValue());
                valve.setPn(row.getCell(4).getStringCellValue());
                valve.setConnection(row.getCell(5).getStringCellValue());
                valve.setType(row.getCell(6).getStringCellValue());
                valve.setTemperature(row.getCell(7).getStringCellValue());
                valve.setPrice(row.getCell(8).getNumericCellValue());
                valve.setImageurl(row.getCell(10).getStringCellValue());


                valveImpl.insertValve(valve);
            }

            System.out.println(valveImpl.findAllValve());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
