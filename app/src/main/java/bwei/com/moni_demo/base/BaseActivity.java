package bwei.com.moni_demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
   protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId());
        initView();
        presenter=setprosenter();
    }

    protected abstract P setprosenter();

    protected abstract void initView();

    protected abstract int LayoutId();

    @Override
    protected void onDestroy() {
        presenter.OnDestroy();
        super.onDestroy();
    }
}
