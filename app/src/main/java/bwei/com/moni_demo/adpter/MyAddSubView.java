package bwei.com.moni_demo.adpter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.moni_demo.R;

public class MyAddSubView extends LinearLayout {

    @BindView(R.id.sub_tv)
    TextView subTv;
    @BindView(R.id.number_tv)
    TextView numberTv;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    private int number=1;
    public MyAddSubView(Context context) {
        this(context, null);
    }

    public MyAddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.add_button, this);
        ButterKnife.bind(view);
    }
    @OnClick({R.id.sub_tv, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sub_tv:
                if (number>1){
                    --number;
                    numberTv.setText(number+"");
                    onNumBerChangeListener.onNumBerChange(number);
                }
                break;
            case R.id.tv_add:
                ++number;
                numberTv.setText(number+"");
                onNumBerChangeListener.onNumBerChange(number);
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        numberTv.setText(number+"");
    }

    OnNumBerChangeListener onNumBerChangeListener;
   public void setOnNumBerChangeListener(OnNumBerChangeListener onNumBerChangeListener){
       this.onNumBerChangeListener=onNumBerChangeListener;
   }
    interface  OnNumBerChangeListener{
        void onNumBerChange(int num);
    }
}
