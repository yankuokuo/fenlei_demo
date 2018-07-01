package bwei.com.moni_demo.mvp.fenlei.presenter;

import bwei.com.moni_demo.base.BasePresenter;
import bwei.com.moni_demo.mvp.fenlei.model.FenModel;
import bwei.com.moni_demo.mvp.fenlei.view.FenView;

public class FenPresenter extends BasePresenter<FenView> {

    private  FenModel fenModel;

    public FenPresenter(FenView fenView) {
        super(fenView);

    }

    @Override
    protected void initModel() {
        fenModel = new FenModel();
    }

    public void FenLogin(){
        fenModel.login(new FenModel.FenModelHttpUtils() {
            @Override
            public void onFailure(String error) {

            }

            @Override
            public void OnRespose(String json) {
                    view.OnRespose(json);
            }
        });
    }
    public void ZiLogin(int cid){
        fenModel.zilogin(cid, new FenModel.FenModelHttpUtils2() {
            @Override
            public void OnRespose2(String json) {
                view.OnUsscecd(json);
            }
        });
    }
}
