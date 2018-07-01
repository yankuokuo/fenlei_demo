package bwei.com.moni_demo.mvp.fenlei.model;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import bwei.com.moni_demo.http.HttpUrl;
import bwei.com.moni_demo.http.HttpUtils;

public class FenModel {
    public void login(final FenModelHttpUtils fenModelHttpUtils){
        HttpUtils.getintence()
                .DoGet(HttpUrl.FenUrl, new HttpUtils.HttpCallBack() {
                    @Override
                    public void onFailure(String error) {

                    }

                    @Override
                    public void OnRespose(String json) {
                        if (fenModelHttpUtils!=null){
                            fenModelHttpUtils.OnRespose(json);
                        }
                    }
                });
    }
    public void zilogin(int cid,final FenModelHttpUtils2 fenModelHttpUtils2){
        HttpUtils.getintence()
                .DoGet(HttpUrl.ZiUrl + "?cid=" + cid, new HttpUtils.HttpCallBack() {
                    @Override
                    public void onFailure(String error) {

                    }

                    @Override
                    public void OnRespose(String json) {
                      if (fenModelHttpUtils2!=null){
                          fenModelHttpUtils2.OnRespose2(json);
                      }
                    }
                });
    }
    public interface FenModelHttpUtils{
        void onFailure(String error);
        void OnRespose(String json);
    }
    public interface FenModelHttpUtils2{
        void OnRespose2(String json);
    }
}
