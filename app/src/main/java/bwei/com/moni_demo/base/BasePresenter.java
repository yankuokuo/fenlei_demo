package bwei.com.moni_demo.base;

import bwei.com.moni_demo.mvp.fenlei.model.FenModel;

public abstract class BasePresenter<V extends IView> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        initModel();
    }

    protected abstract void initModel();
    void OnDestroy(){
        view=null;
    }
}
