package persistence.impl;

import entity.Valve;

public class ValveImpl extends ModelImpl{

    private String tableName = "Valve";
    private ModelImpl modelImpl = new ModelImpl();

    public void findValveByArticle() {
        modelImpl.find(tableName, "article");
    }

    public void findAllValve() {
        modelImpl.findAll(tableName);
    }

    public void addValve() {
        Valve valve = new Valve();
        modelImpl.add(valve);
    }

    public void delValveByArticle() {
        modelImpl.del(tableName,"article");
    }

}
