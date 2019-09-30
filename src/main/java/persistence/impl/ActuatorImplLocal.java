package persistence.impl;

import com.form.ControllerMain;
import entity.Actuator;
import entity.Valve;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActuatorImplLocal {

    private ControllerMain cm = new ControllerMain();

    public List<Actuator> findActuatorByComboBoxLocal(String desiredVoltage, String desiredSignal, String desiredNonc, String desiredEndpos, String desiredTimepos, String desiredPower, String desiredStroke, Valve candidateValve) {

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
        Set<Actuator> listActuatorsWithoutValve1 = cm.getAllActuators();
        Set<Actuator> listActuatorsWithoutValve2 = new HashSet<>();

        if (desiredSignalFinal != null) {
            for (Actuator actuator : listActuatorsWithoutValve1) {
                if (actuator.getSignaltype().contains(desiredSignalFinal)) {
                    sortedSignal.add(actuator.getSignaltype());
                }
            }
        } else {
            sortedSignal.add(null);
        }

        boolean b1, b2, b3, b4, b5, b6, b7;

        for (String signalIterate : sortedSignal) {
            for (Actuator actuatorIterate : listActuatorsWithoutValve1) {

                if (desiredVoltage != null) {
                    b1 = desiredVoltage.equals(actuatorIterate.getVoltage());
                } else {
                    b1 = true;
                }
                if (signalIterate != null) {
                    b2 = signalIterate.equals(actuatorIterate.getSignaltype());
                } else {
                    b2 = true;
                }
                if (desiredNonc != null) {
                    b3 = desiredNonc.equals(actuatorIterate.getNonc());
                } else {
                    b3 = true;
                }
                if (desiredEndpos != null) {
                    b4 = desiredEndpos.equals(actuatorIterate.getEndpos());
                } else {
                    b4 = true;
                }
                if (desiredTimepos != null) {
                    b5 = desiredTimepos.equals(actuatorIterate.getTimepos());
                } else {
                    b5 = true;
                }
                if (desiredPower != null) {
                    b6 = desiredPower.equals(actuatorIterate.getPower());
                } else {
                    b6 = true;
                }
                if (desiredStroke != null) {
                    b7 = desiredStroke.equals(actuatorIterate.getStroke());
                } else {
                    b7 = true;
                }

                if (b1 && b2 && b3 && b4 && b5 && b6 && b7){
                    listActuatorsWithoutValve2.add(actuatorIterate);
                }
            }
        }

        List<Actuator> listActuatorsWithValve = new ArrayList<>();
        int j = 0;
        for (Actuator actuatorWithValve : listActuatorsWithoutValve2) {
            if (actuatorWithValve.getValves().contains(candidateValve)) {
                listActuatorsWithValve.add(actuatorWithValve);
                j++;
            }
        }

        return listActuatorsWithValve;
    }

}
