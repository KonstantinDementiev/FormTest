package persistence.impl;

import com.form.ControllerMain;
import entity.Adapter;

public class AdapterImplLocal {
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
