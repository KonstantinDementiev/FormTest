package persistence.impl;

import entity.Adapter;
import java.util.List;

public class AdapterImpl extends ModelImpl{
    private ModelImpl modelImpl = new ModelImpl();
    public List<Adapter> findAllAdapter() {
        return modelImpl.findAll("Adapter");
    }

}
