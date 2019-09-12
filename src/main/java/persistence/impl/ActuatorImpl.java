package persistence.impl;

import entity.Actuator;
import entity.Valve;

import java.util.ArrayList;
import java.util.List;

public class ActuatorImpl extends ModelImpl {


    private ModelImpl modelImpl = new ModelImpl();

    public void findActuatorByArticle() {
        modelImpl.find("Actuator", "article");
    }

    public List<Actuator> findAllActuator() {
        return modelImpl.findAll("Actuator");
    }

    public void addActuator() {
        Actuator actuator = new Actuator();
        modelImpl.add(actuator);
    }

    public void delActuatorByArticle() {
        modelImpl.del("Actuator", "article");
    }

    public List<Actuator> findActuatorByComboBox(String desiredVoltage, String desiredSignal, String desiredNonc, String desiredEndpos, String desiredTimepos, String desiredPower, String desiredStroke, Valve candidateValve) {

        Object[][] listComboBoxes = {{desiredVoltage, "voltage"}, {desiredSignal, "signal"}, {desiredNonc, "nonc"}, {desiredEndpos, "endpos"}, {desiredTimepos, "timepos"}, {desiredPower, "power"}, {desiredStroke, "stroke"}};
        List<Actuator> listWithValve = new ArrayList<>();
        List<Actuator> listWithoutValve = modelImpl.findModelByComboBox(listComboBoxes, "Actuator");
        int j = 0;
        for (int i = 0; i < listWithoutValve.size() - 1; i++) {
            if (listWithoutValve.get(i).getValves().contains(candidateValve.getArticle())) {
                listWithValve.add(j, listWithoutValve.get(i));
                j++;
            }
        }
        return listWithValve;
    }
}