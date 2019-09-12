package persistence.impl;

import entity.Actuator;
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

    public List<Actuator> findActuatorByComboBox(String desiredVoltage, String desiredSignal, String desiredNonc, String desiredEndpos, String desiredTimepos, String desiredPower, String desiredStroke) {

        Object[][] listComboBoxes = {{desiredVoltage, "voltage"},
                {desiredSignal, "signal"},
                {desiredNonc, "nonc"},
                {desiredEndpos, "endpos"},
                {desiredTimepos, "timepos"},
                {desiredPower, "power"},
                {desiredStroke, "stroke"}
        };
        return modelImpl.findModelByComboBox(listComboBoxes, "Actuator");

    }
}