package persistence.implLocal;

import com.form.ControllerMain;
import entity.Nuts;
import persistence.NutsRepository;

public class NutsImplLocal implements NutsRepository {

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
