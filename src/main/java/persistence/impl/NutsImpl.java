package persistence.impl;

import entity.Model;
import entity.Nuts;

import java.util.Set;

public class NutsImpl extends Model {

    private ModelImpl modelImpl = new ModelImpl();

    public Set<Nuts> findAllNuts() {
        return modelImpl.findAll("Nuts");
    }

    public Nuts findNutsByArticle(String article) {
        return (Nuts) modelImpl.find("Nuts", "article", article);
    }

    public void insertNuts(Nuts nuts) {
        modelImpl.insertModel(nuts);
    }

    public void delAllNuts() {
        modelImpl.delAll("Nuts");
    }
}
