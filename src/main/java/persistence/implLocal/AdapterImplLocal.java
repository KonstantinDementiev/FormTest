package persistence.implLocal;

import com.form.ControllerMain;
import entity.Adapter;
import persistence.AdapterRepository;

public class AdapterImplLocal implements AdapterRepository {
    private ControllerMain cm = new ControllerMain();

    public Adapter findAdapterByArticleLocal(String article) {
        Adapter adapterResult = null;
        for (Adapter adapter : cm.getAllAdapters()){
            if (article.equals(adapter.getArticle())){
                adapterResult = adapter;
            }
        }
        return adapterResult;
    }
}
