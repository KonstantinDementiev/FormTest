package persistence.impl;

import com.form.ControllerMain;
import entity.Nuts;

public class NutsImplLocal {

    private ControllerMain cm = new ControllerMain();

    public Nuts findNutsByArticleLocal(String article) {
        Nuts nutsResult = null;
        for (Nuts nuts : cm.getAllNuts()){
            if (article.equals(nuts.getArticle())){
                nutsResult = nuts;
            }
        }
        return nutsResult;
    }

}
