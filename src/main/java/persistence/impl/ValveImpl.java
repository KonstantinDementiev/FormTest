package persistence.impl;

import entity.Valve;
import java.util.List;

public class ValveImpl extends ModelImpl {


    private ModelImpl modelImpl = new ModelImpl();

    public void findValveByArticle() {
        modelImpl.find("Valve", "article");
    }

    public List<Valve> findAllValve() {
        return modelImpl.findAll("Valve");
    }

    public void addValve() {
        Valve valve = new Valve();
        modelImpl.add(valve);
    }

    public void delValveByArticle() {
        modelImpl.del("Valve", "article");
    }


    public List<Valve> findValveByComboBox(Double desiredKvs, Integer desiredDn, Integer desiredPorts, String desiredPn, String desiredConn, String desiredType) {

        Object[][] listComboBoxes = {{desiredKvs, "kvs"},
                {desiredDn, "dn"},
                {desiredPorts, "ports"},
                {desiredPn, "pn"},
                {desiredConn, "connection"},
                {desiredType, "type"}
        };
        return modelImpl.findModelByComboBox(listComboBoxes, "Valve");
    }


}
