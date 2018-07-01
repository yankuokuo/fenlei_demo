package bwei.com.moni_demo.mvp.car.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.moni_demo.R;
import bwei.com.moni_demo.adpter.CarExViewAdapter;
import bwei.com.moni_demo.base.BaseActivity;
import bwei.com.moni_demo.mvp.car.model.CarBean;
import bwei.com.moni_demo.mvp.car.presenter.CarPresenter;

public class CarActivity extends BaseActivity<CarPresenter> implements CarView{

    private static final String TAG = "carActivity----------";
    @BindView(R.id.ex_cart)
    ExpandableListView exCart;
    @BindView(R.id.cart_all_select)
    CheckBox cartAllSelect;
    @BindView(R.id.tv_cart_total_price)
    TextView tvCartTotalPrice;
    @BindView(R.id.btn_cart_pay)
    Button btnCartPay;
    private CarExViewAdapter myAdapter;
    @Override
    protected CarPresenter setprosenter() {
        final CarPresenter carPresenter = new CarPresenter(this);
        carPresenter.CarLogin();
        return carPresenter;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_car;
    }

    @Override
    public void onFailure(String error) {
    }

    @Override
    public void OnRespose(String json) {
        Log.i(TAG,"json+++++"+json);
        final Gson gson = new Gson();
        final CarBean carBean = gson.fromJson(json, CarBean.class);
        final List<CarBean.DataBean> data = carBean.getData();
        myAdapter = new CarExViewAdapter(data, CarActivity.this);
        myAdapter.setOncheckchangelister(new CarExViewAdapter.oncheckchanglister() {
            @Override
            public void onparentcheckchange(int i) {
                final boolean ischeak = myAdapter.ischeak(i);
                myAdapter.changCurrentSellerAllProdStatus(i,!ischeak);
                myAdapter.notifyDataSetChanged();
                refresh();
            }

            @Override
            public void onchildcheckchange(int i, int i1) {
                myAdapter.changchildcheckboxsratus(i,i1);
                myAdapter.notifyDataSetChanged();
                refresh();
            }

            @Override
            public void onNumBerchange(int i, int i1, int num) {
                myAdapter.changNumberstatus(i,i1,num);
                myAdapter.notifyDataSetChanged();
                refresh();
            }
        });
        exCart.setAdapter(myAdapter);
        for (int i = 0; i <data.size() ; i++) {
            exCart.expandGroup(i);
        }
        refresh();
    }
    private void refresh(){
        //判断所有商品被选中
        boolean allProductselected = myAdapter.isAllProductselected();
        //设置给全选
        cartAllSelect.setChecked(allProductselected);
        //计算总价
        final float toalprice = myAdapter.calculateToalprice();
        tvCartTotalPrice.setText("总价："+toalprice);
        //计算总数量
        final int i = myAdapter.calculToalNumBer();
        btnCartPay.setText("去结算("+i+")");
    }
    @OnClick(R.id.cart_all_select)
    public void onViewClicked() {
        final boolean allProductselected = myAdapter.isAllProductselected();
        myAdapter.changAllproductStatus(!allProductselected);
        myAdapter.notifyDataSetChanged();
        refresh();
    }

}
