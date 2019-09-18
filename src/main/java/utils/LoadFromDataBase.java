package utils;

import entity.Actuator;
import entity.Adapter;
import entity.Nuts;
import entity.Valve;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import persistence.impl.ActuatorImpl;
import persistence.impl.AdapterImpl;
import persistence.impl.NutsImpl;
import persistence.impl.ValveImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadFromDataBase {

    private String pathFileName = "F:\\JavaProject\\FormTest\\src\\main\\resources\\herz_db.xlsx";

    public void valveFromExcel() throws IOException {
        Valve valve = new Valve();
        ValveImpl valveImpl = new ValveImpl();
        NutsImpl nutsImpl = new NutsImpl();
        try {
            FileInputStream fis = new FileInputStream(new File(pathFileName));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet1 = workbook.getSheet("Valves");
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
                valve.setNuts(nutsImpl.findNutsByArticle(row.getCell(9).getStringCellValue()));
                valve.setImageurl(row.getCell(10).getStringCellValue());
                valveImpl.insertValve(valve);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void actuatorFromExcel() throws IOException {
        Actuator actuator = new Actuator();
        ActuatorImpl actuatorImpl = new ActuatorImpl();
        try {
            FileInputStream fis = new FileInputStream(new File(pathFileName));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet3 = workbook.getSheet("Actuators");
            Row row;
            for (int i = 1; i <= sheet3.getLastRowNum(); i++) {
                row = sheet3.getRow(i);
                actuator.setArticle(row.getCell(0).getStringCellValue());
                actuator.setVoltage(row.getCell(1).getStringCellValue());
                actuator.setSignal(row.getCell(2).getStringCellValue());
                actuator.setNormalyopenclose(row.getCell(3).getStringCellValue());
                actuator.setOnoffendpos(row.getCell(4).getStringCellValue());
                actuator.setTimepos(row.getCell(5).getStringCellValue());
                actuator.setPower(row.getCell(6).getStringCellValue());
                actuator.setStroke(row.getCell(7).getStringCellValue());
                actuator.setPrice(row.getCell(8).getNumericCellValue());
                actuator.setActuatorimageurl(row.getCell(9).getStringCellValue());
                actuatorImpl.insertActuator(actuator);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void adapterFromExcel() throws IOException {
        Adapter adapter = new Adapter();
        AdapterImpl adapterImpl = new AdapterImpl();
        try {
            FileInputStream fis = new FileInputStream(new File(pathFileName));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet5 = workbook.getSheet("Adapters");
            adapterImpl.delAllAdapters();
            Row row;
            for (int i = 1; i <= sheet5.getLastRowNum(); i++) {
                row = sheet5.getRow(i);
                adapter.setArticle(row.getCell(0).getStringCellValue());
                adapter.setPrice(row.getCell(1).getNumericCellValue());
                adapter.setImageurl(row.getCell(2).getStringCellValue());
                adapterImpl.insertAdapter(adapter);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void nutsFromExcel() throws IOException {
        Nuts nuts = new Nuts();
        NutsImpl nutsImpl = new NutsImpl();
        try {
            FileInputStream fis = new FileInputStream(new File(pathFileName));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet7 = workbook.getSheet("Nuts");
            Row row;
            for (int i = 1; i <= sheet7.getLastRowNum(); i++) {
                row = sheet7.getRow(i);
                nuts.setArticle(row.getCell(0).getStringCellValue());
                nuts.setPrice(row.getCell(1).getNumericCellValue());
                nutsImpl.insertNuts(nuts);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fillingValvesAndNuts() {

        ValveImpl valveImpl = new ValveImpl();
        NutsImpl nutsImpl = new NutsImpl();

        try {
            valveImpl.delAllValves();
            nutsImpl.delAllNuts();
            nutsFromExcel();
            valveFromExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillingActuators() {

        try {
            actuatorFromExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
