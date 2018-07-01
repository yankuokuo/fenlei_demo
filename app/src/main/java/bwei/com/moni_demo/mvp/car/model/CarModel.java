package bwei.com.moni_demo.mvp.car.model;

import bwei.com.moni_demo.http.HttpUtils;

public class CarModel {
    private String url="https://www.zhaoapi.cn/product/getCarts?Uid=71";
    public void carlogin(final CarModelHttpUtils carModelHttpUtils){
        HttpUtils.getintence()
                .DoGet(url, new HttpUtils.HttpCallBack() {
                    @Override
                    public void onFailure(String error) {

                    }

                    @Override
                    public void OnRespose(String json) {
                        if (carModelHttpUtils!=null){
                            carModelHttpUtils.OnRespose(json);
                        }
                    }
                });
    }
    public interface CarModelHttpUtils{
        void onFailure(String error);
        void OnRespose(String json);
    }
}
