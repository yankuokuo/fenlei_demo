package bwei.com.moni_demo.mvp.car.presenter;


import bwei.com.moni_demo.base.BasePresenter;
import bwei.com.moni_demo.mvp.car.model.CarModel;
import bwei.com.moni_demo.mvp.car.view.CarView;

public class CarPresenter extends BasePresenter<CarView> {
    private CarModel carModel;

    public CarPresenter(CarView carView) {
        super(carView);

    }

    @Override
    protected void initModel() {
        carModel = new CarModel();
    }

    public void CarLogin(){
        carModel.carlogin(new CarModel.CarModelHttpUtils() {
            @Override
            public void onFailure(String error) {

            }

            @Override
            public void OnRespose(String json) {
                view.OnRespose(json);
            }
        });
    }

}
