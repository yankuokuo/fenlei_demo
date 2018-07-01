package bwei.com.moni_demo.mvp.car.view;

import bwei.com.moni_demo.base.IView;

public interface CarView extends IView{
    void onFailure(String error);
    void OnRespose(String json);
}
