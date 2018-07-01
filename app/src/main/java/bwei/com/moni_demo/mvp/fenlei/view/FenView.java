package bwei.com.moni_demo.mvp.fenlei.view;

import bwei.com.moni_demo.base.IView;

public interface FenView extends IView {
        void onFailure(String error);
        void OnRespose(String json);
        void OnUsscecd(String json);
}
