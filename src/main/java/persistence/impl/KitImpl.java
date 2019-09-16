package persistence.impl;

import entity.Kit;

import java.util.List;

public class KitImpl extends ModelImpl {

    private ModelImpl modelImpl = new ModelImpl();

    public List<Kit> findAllKits() {
        return modelImpl.findAll("Kit");
    }

}


