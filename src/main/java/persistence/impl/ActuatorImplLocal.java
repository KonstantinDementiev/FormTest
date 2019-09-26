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
        //Set<Actuator> arr = cm.getAllActuators();

        if (desiredSignalFinal != null) {
            for (Actuator actuator : cm.getAllActuators()) {
                if (actuator.getSignaltype().contains(desiredSignalFinal)) sortedSignal.add(actuator.getSignaltype());
            }
        } else {
            sortedSignal.add(null);
        }

        List<Actuator> listActuatorsWithoutValve = new ArrayList<>();

        for (String signalIterate : sortedSignal) {
            for (Actuator actuator : cm.getAllActuators()) {
                if (desiredVoltage != null && desiredVoltage.equals(actuator.getVoltage())) {

                }


                signalIterate.equals(actuator.getSignaltype()) && desiredNonc.equals(actuator.getNonc()) && desiredEndpos.equals(actuator.getEndpos()) && desiredTimepos.equals(actuator.getTimepos()) && desiredPower.equals(actuator.getPower()) && desiredStroke.equals(actuator.getStroke()))
                {
                    listActuatorsWithoutValve.add(actuator);
                }
            }
        }

        List<Actuator> listActuatorsWithValve = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < listActuatorsWithoutValve.size(); i++) {
            if (listActuatorsWithoutValve.get(i).getValves().contains(candidateValve)) {
                listActuatorsWithValve.add(j, listActuatorsWithoutValve.get(i));
                j++;
            }
        }

        return listActuatorsWithValve;
    }

}
