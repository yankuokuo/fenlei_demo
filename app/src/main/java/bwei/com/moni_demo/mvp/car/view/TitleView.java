package bwei.com.moni_demo.mvp.car.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import bwei.com.moni_demo.R;

public class TitleView extends LinearLayout implements View.OnClickListener{

    private TypedArray typedArray;
    private String btnleft;
    private String titletv;
    private String btnright;
    private View inflate;
    private Button leftid;
    private Button rightid;
    private TextView tilieid;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleView, 0, 0);
        btnleft = typedArray.getString(R.styleable.TitleView_left_btn);
        titletv = typedArray.getString(R.styleable.TitleView_tv_title);
        btnright = typedArray.getString(R.styleable.TitleView_right_btn);
        inflate = inflate(context, R.layout.titile_louty, this);
        leftid = inflate.findViewById(R.id.left_btn);
        rightid = inflate.findViewById(R.id.right_btn);
        tilieid = inflate.findViewById(R.id.tv_title);
        leftid.setOnClickListener(this);
        rightid.setOnClickListener(this);
        tilieid.setOnClickListener(this);
        //给控件添加属性
        leftid.setText(btnleft);
        rightid.setText(btnright);
        tilieid.setText(titletv);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left_btn:
                onClick.onleft();
                break;
            case R.id.right_btn:
                onClick.onright();
                break;
            case R.id.tv_title:
                onClick.ontilte();
                break;
        }
    }
    onClick onClick;
    public interface onClick{
        void onleft();
        void onright();
        void ontilte();
    }
    public void setOnchlick(onClick onchlick){
        this.onClick=onchlick;
    }
}
