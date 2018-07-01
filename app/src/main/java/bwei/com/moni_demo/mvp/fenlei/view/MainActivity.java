package bwei.com.moni_demo.mvp.fenlei.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwei.com.moni_demo.R;
import bwei.com.moni_demo.adpter.ChildReviewAdapter;
import bwei.com.moni_demo.adpter.ExViewAdapter;
import bwei.com.moni_demo.adpter.ListAdapter;
import bwei.com.moni_demo.base.BaseActivity;
import bwei.com.moni_demo.mvp.car.view.CarActivity;
import bwei.com.moni_demo.mvp.fenlei.model.FenBean;
import bwei.com.moni_demo.mvp.fenlei.model.ZiBean;
import bwei.com.moni_demo.mvp.fenlei.presenter.FenPresenter;

public class MainActivity extends BaseActivity<FenPresenter> implements FenView {

    private static String TAG = "MainActivity------------";
    @BindView(R.id.main_listview)
    ListView mainListview;
    @BindView(R.id.main_exview)
    ExpandableListView mainExview;
    @BindView(R.id.main_iamge)
    ImageView mainImage;
    private List<FenBean.DataBean> data;
    private Unbinder unbinder;
    @Override
    protected FenPresenter setprosenter() {
        final FenPresenter fenPresenter = new FenPresenter(this);
        fenPresenter.FenLogin();
        fenPresenter.ZiLogin(1);
        mainListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int cid = data.get(i).getCid();
                fenPresenter.ZiLogin(cid);
            }
        });
        return fenPresenter;
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MainActivity.this, CarActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onFailure(String error) {

    }

    @Override
    public void OnRespose(String json) {
        Gson gson = new Gson();
        FenBean fenBean = gson.fromJson(json, FenBean.class);
        data = fenBean.getData();
        final ListAdapter listAdapter = new ListAdapter(data, MainActivity.this);
        mainListview.setAdapter(listAdapter);
    }

    @Override
    public void OnUsscecd(String json) {
        final Gson gson = new Gson();
        final ZiBean ziBean = gson.fromJson(json, ZiBean.class);
        final List<ZiBean.DataBean> data = ziBean.getData();
        final ExViewAdapter exViewAdapter = new ExViewAdapter(data, MainActivity.this);
        mainExview.setAdapter(exViewAdapter);
        for (int i = 0; i <data.size() ; i++) {
            mainExview.expandGroup(i);
        }
    }
}
