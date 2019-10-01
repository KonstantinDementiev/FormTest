package persistence.impl;

import entity.Actuator;
import entity.Valve;
import persistence.ActuatorRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActuatorImpl extends ModelImpl implements ActuatorRepository {


    private ModelImpl modelImpl = new ModelImpl();

    public void findActuatorByArticle(String article) {
        modelImpl.find("Actuator", "article", article);
    }

    public Set<Actuator> findAllActuator() {
        return modelImpl.findAll("Actuator");
    }

    public void insertActuator(Actuator actuator) {
        modelImpl.insertModel(actuator);
    }

    public void saveOrUpdateActuator(Actuator actuator) {
        modelImpl.saveOrUpdateModel(actuator);
    }

    public void delAllActuators() {
        modelImpl.delAll("Actuator");
    }

    public void delActuatorByArticle() {
        modelImpl.delByArticle("Actuator", "article");
    }


    public List<Actuator> findActuatorByComboBox(String desiredVoltage, String desiredSignal, String desiredNonc, String desiredEndpos, String desiredTimepos, String desiredPower, String desiredStroke, Valve candidateValve) {

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

        if (desiredSignalFinal != null) {
            Set<Actuator> arr = findAllActuator();
            for (Actuator actuator : arr) {
                if (actuator.getSignaltype().contains(desiredSignalFinal))
                    sortedSignal.add(actuator.getSignaltype());
            }
        }else{
            sortedSignal.add(null);
        }

        List<Actuator> listActuatorsWithoutValve = new ArrayList<>();

        for (String signalIterate : sortedSignal) {
            Object[][] listComboBoxes = {
                    {desiredVoltage, "voltage"},
                    {signalIterate, "signaltype"},
                    {desiredNonc, "nonc"},
                    {desiredEndpos, "endpos"},
                    {desiredTimepos, "timepos"},
                    {desiredPower, "power"},
                    {desiredStroke, "stroke"}};
            List<Actuator> listAct = modelImpl.findModelByComboBox(listComboBoxes, "Actuator");
            listActuatorsWithoutValve.addAll(listAct);
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