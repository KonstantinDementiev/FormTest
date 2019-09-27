package persistence.impl;

import com.form.ControllerMain;
import entity.Actuator;
import entity.Valve;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActuatorImplLocal {


    public List<Actuator> findActuatorByComboBoxLocal1(String desiredVoltage, String desiredSignal, String desiredNonc, String desiredEndpos, String desiredTimepos, String desiredPower, String desiredStroke, Valve candidateValve) {

        String desiredSignalFinal = null;
        if (desiredSignal != null) {
            if (desiredSignal.equals("2 точки")) {
                desiredSignalFinal = "2т";
            }
            if (desiredSignal.equals("3 точки")) {
                desiredSignalFinal = "3т";
            }
            if (desiredSignal.equals("0-10 В")) {
                desiredSignalFinal = "0-10 В";
            }
        }

        Set<String> sortedSignal = new HashSet<>();
        ControllerMain cm = new ControllerMain();
        Set<Actuator> setActuatorsWithoutValve1 = new HashSet<>();
        Set<Actuator> setActuatorsWithoutValve2 = new HashSet<>();
        Set<Actuator> setActuatorsWithoutValve3 = new HashSet<>();

        if (desiredSignalFinal != null) {
            for (Actuator actuator : cm.getAllActuators()) {
                if (actuator.getSignaltype().contains(desiredSignalFinal)) sortedSignal.add(actuator.getSignaltype());
            }
        } else {
            sortedSignal.add(null);
        }

        for (String signalIterate : sortedSignal) {
            setActuatorsWithoutValve1.addAll(cm.getAllActuators());

            if (desiredVoltage != null) {
                for (Actuator actuator : setActuatorsWithoutValve1) {
                    if (desiredVoltage.equals(actuator.getVoltage())) {
                        setActuatorsWithoutValve2.add(actuator);
                    }
                }
                if (!setActuatorsWithoutValve2.isEmpty()) {
                    setActuatorsWithoutValve1.clear();
                    setActuatorsWithoutValve1.addAll(setActuatorsWithoutValve2);
                    setActuatorsWithoutValve2.clear();
                }
            }
            if (signalIterate != null) {
                for (Actuator actuator : setActuatorsWithoutValve1) {
                    if (signalIterate.equals(actuator.getSignaltype())) {
                        setActuatorsWithoutValve2.add(actuator);
                    }
                }
                if (!setActuatorsWithoutValve2.isEmpty()) {
                    setActuatorsWithoutValve1.clear();
                    setActuatorsWithoutValve1.addAll(setActuatorsWithoutValve2);
                    setActuatorsWithoutValve2.clear();
                }
            }
            if (desiredNonc != null) {
                for (Actuator actuator : setActuatorsWithoutValve1) {
                    if (desiredNonc.equals(actuator.getNonc())) {
                        setActuatorsWithoutValve2.add(actuator);
                    }
                }
                if (!setActuatorsWithoutValve2.isEmpty()) {
                    setActuatorsWithoutValve1.clear();
                    setActuatorsWithoutValve1.addAll(setActuatorsWithoutValve2);
                    setActuatorsWithoutValve2.clear();
                }
            }
            if (desiredEndpos != null) {
                for (Actuator actuator : setActuatorsWithoutValve1) {
                    if (desiredEndpos.equals(actuator.getEndpos())) {
                        setActuatorsWithoutValve2.add(actuator);
                    }
                }
                if (!setActuatorsWithoutValve2.isEmpty()) {
                    setActuatorsWithoutValve1.clear();
                    setActuatorsWithoutValve1.addAll(setActuatorsWithoutValve2);
                    setActuatorsWithoutValve2.clear();
                }
            }
            if (desiredTimepos != null) {
                for (Actuator actuator : setActuatorsWithoutValve1) {
                    if (desiredTimepos.equals(actuator.getTimepos())) {
                        setActuatorsWithoutValve2.add(actuator);
                    }
                }
                if (!setActuatorsWithoutValve2.isEmpty()) {
                    setActuatorsWithoutValve1.clear();
                    setActuatorsWithoutValve1.addAll(setActuatorsWithoutValve2);
                    setActuatorsWithoutValve2.clear();
                }
            }
            if (desiredPower != null) {
                for (Actuator actuator : setActuatorsWithoutValve1) {
                    if (desiredPower.equals(actuator.getPower())) {
                        setActuatorsWithoutValve2.add(actuator);
                    }
                }
                if (!setActuatorsWithoutValve2.isEmpty()) {
                    setActuatorsWithoutValve1.clear();
                    setActuatorsWithoutValve1.addAll(setActuatorsWithoutValve2);
                    setActuatorsWithoutValve2.clear();
                }
            }
            if (desiredStroke != null) {
                for (Actuator actuator : setActuatorsWithoutValve1) {
                    if (desiredStroke.equals(actuator.getStroke())) {
                        setActuatorsWithoutValve2.add(actuator);
                    }
                }
                if (!setActuatorsWithoutValve2.isEmpty()) {
                    setActuatorsWithoutValve1.clear();
                    setActuatorsWithoutValve1.addAll(setActuatorsWithoutValve2);
                    setActuatorsWithoutValve2.clear();
                }
            }
            setActuatorsWithoutValve3.addAll(setActuatorsWithoutValve1);
            setActuatorsWithoutValve1.clear();
        }

        List<Actuator> listActuatorsWithValve = new ArrayList<>();
        int j = 0;
        for (Actuator actuator : setActuatorsWithoutValve3) {
            if (actuator.getValves().contains(candidateValve)) {
                listActuatorsWithValve.add(j, actuator);
                j++;
            }
        }

        return listActuatorsWithValve;
    }

    public List<Actuator> findActuatorByComboBoxLocal2(String desiredVoltage, String desiredSignal, String desiredNonc, String desiredEndpos, String desiredTimepos, String desiredPower, String desiredStroke, Valve candidateValve) {

        String desiredSignalFinal = null;
        if (desiredSignal != null) {
            if (desiredSignal.equals("2 точки")) {
                desiredSignalFinal = "2т";
            }
            if (desiredSignal.equals("3 точки")) {
                desiredSignalFinal = "3т";
            }
            if (desiredSignal.equals("0-10 В")) {
                desiredSignalFinal = "0-10 В";
            }
        }

        Set<String> sortedSignal = new HashSet<>();
        ControllerMain cm = new ControllerMain();
        Set<Actuator> setActuatorsWithoutValve1 = new HashSet<>();
        StringBuilder strQuery = new StringBuilder();

        if (desiredVoltage != null) {
            if (strQuery == null) {
                strQuery.append("desiredVoltage.equals(actuator.getVoltage())");
            }else {
                strQuery.append(" && desiredVoltage.equals(actuator.getVoltage())");
            }
        }
        Boolean bol = Boolean.valueOf(strQuery.toString());
        if (bol) {
            if (strQuery == null) {
                strQuery.append("desiredVoltage.equals(actuator.getVoltage())");
            }else {
                strQuery.append(" && desiredVoltage.equals(actuator.getVoltage())");
            }
        }





        return listActuatorsWithValve;
    }


}

