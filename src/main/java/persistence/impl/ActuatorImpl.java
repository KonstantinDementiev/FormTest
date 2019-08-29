package persistence.impl;

import entity.Actuator;

public class ActuatorImpl extends ModelImpl {

    private String tableName = "Actuator";
    private ModelImpl modelImpl = new ModelImpl();

    public void findActuatorByArticle() {
        modelImpl.find(tableName, "article");
    }

    public void findAllActuator() {
        modelImpl.findAll(tableName);
    }

    public void addActuator() {
        Actuator actuator = new Actuator();
        modelImpl.add(actuator);
    }

    public void delActuatorByArticle() {
        modelImpl.del(tableName,"article");
    }
}