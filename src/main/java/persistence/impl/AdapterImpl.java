package persistence.impl;

import entity.Adapter;
import persistence.AdapterRepository;

import java.util.Set;

public class AdapterImpl extends ModelImpl implements AdapterRepository {

    private ModelImpl modelImpl = new ModelImpl();

    public Set<Adapter> findAllAdapter() {
        return modelImpl.findAll("Adapter");
    }

    public void insertAdapter(Adapter adapter) {
        modelImpl.insertModel(adapter);
    }

    public void delAllAdapters() {
        modelImpl.delAll("Adapter");
    }

}
