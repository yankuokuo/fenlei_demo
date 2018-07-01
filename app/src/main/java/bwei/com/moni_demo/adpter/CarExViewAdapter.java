package bwei.com.moni_demo.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.security.PublicKey;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwei.com.moni_demo.R;
import bwei.com.moni_demo.app.MyApp;
import bwei.com.moni_demo.mvp.car.model.CarBean;

public class CarExViewAdapter extends BaseExpandableListAdapter {
    private List<CarBean.DataBean> data;
    private Context context;

    public CarExViewAdapter(List<CarBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return data.get(i).getList()==null? 0:data.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        parentViewHolder holder;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.cart_parent, null);
            holder=new parentViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (parentViewHolder) view.getTag();
        }
        holder.sellerNameTv.setText(data.get(i).getSellerName());
        //判断选中的方法，并赋给控件
        final boolean ischeak = ischeak(i);
        holder.parentSellerCb.setChecked(ischeak);
        holder.parentSellerCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oncheckchanglister!=null){
                    oncheckchanglister.onparentcheckchange(i);
                }
            }
        });
        return view;
    }
    //是否被选中的方法
    public boolean ischeak(int i){
        final CarBean.DataBean dataBean = data.get(i);
        final List<CarBean.DataBean.ListBean> list = dataBean.getList();
        for(CarBean.DataBean.ListBean listBean:list){
            if (listBean.getSelected()==0){
                return false;
            }
        }
        return true;
    }
    @Override
    public View getChildView(final int i, final int i1, boolean b, View mview, ViewGroup viewGroup) {

        childViewHolder holder;
        if (mview == null) {
            mview = View.inflate(viewGroup.getContext(), R.layout.car_child, null);
            holder=new childViewHolder(mview);
            mview.setTag(holder);
        }else{
            holder= (childViewHolder) mview.getTag();
        }

        CarBean.DataBean dataBean = data.get(i);
         List<CarBean.DataBean.ListBean> list = dataBean.getList();
         CarBean.DataBean.ListBean listBean = list.get(i1);

        ImageLoader.getInstance().displayImage(listBean.getImages().split("\\|")[0],holder.productIconIv, MyApp.getOptions());
        holder.productTitieNameTv.setText(listBean.getTitle());
        holder.productPriceTv.setText(listBean.getPrice()+"");
        holder.childCd.setChecked(listBean.getSelected()==1);
        holder.childCd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oncheckchanglister!=null){
                    oncheckchanglister.onchildcheckchange(i,i1);
                }
            }
        });
        holder.addRemoveView.setNumber(listBean.getNum());
        holder.addRemoveView.setOnNumBerChangeListener(new MyAddSubView.OnNumBerChangeListener() {
            @Override
            public void onNumBerChange(int num) {
                if (oncheckchanglister!=null){
                    oncheckchanglister.onNumBerchange(i,i1,num);
                }
            }
        });
        return mview;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    //所有商品被选中
    public boolean isAllProductselected(){
        for (int i = 0; i <data.size() ; i++) {
            final CarBean.DataBean dataBean = data.get(i);
            final List<CarBean.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected()==0){
                    return false;
                }
            }
        }
        return true;
    }
    //计算总价
    public float calculateToalprice(){
        float totalprice=0;
        for (int i = 0; i <data.size() ; i++) {
            final CarBean.DataBean dataBean = data.get(i);
            final List<CarBean.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j < list.size(); j++) {
               if (list.get(j).getSelected()==1){
                   float price=list.get(j).getPrice();
                   int num=list.get(j).getNum();
                   totalprice+=price*num;
               }
            }
        }
        return totalprice;
    }
    //结算
    public int calculToalNumBer(){
        int toNumber=0;
        for (int i = 0; i <data.size() ; i++) {
            final CarBean.DataBean dataBean = data.get(i);
            final List<CarBean.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected()==1){
                   int num=list.get(j).getNum();
                   toNumber+=num;
                }
            }
        }
        return toNumber;
    }
    //当商家被点下面商品的状态
    public void changCurrentSellerAllProdStatus(int i,boolean b){
        final CarBean.DataBean dataBean = data.get(i);
        final List<CarBean.DataBean.ListBean> listBeans = dataBean.getList();
        for (int j = 0; j <listBeans.size() ; j++) {
            final CarBean.DataBean.ListBean listBean = listBeans.get(j);
            listBean.setSelected(b?1:0);
        }
    }
    //当商品被点击时调用
    public void changchildcheckboxsratus(int i,int i1){
        final CarBean.DataBean dataBean = data.get(i);
        final List<CarBean.DataBean.ListBean> list = dataBean.getList();
        final CarBean.DataBean.ListBean listBean = list.get(i1);
        listBean.setSelected(listBean.getSelected()==0?1:0);
    }
    //加减器改变数量
    public void changNumberstatus(int i,int i1,int num){
        final CarBean.DataBean dataBean = data.get(i);
        final List<CarBean.DataBean.ListBean> list = dataBean.getList();
        final CarBean.DataBean.ListBean listBean = list.get(i1);
        listBean.setNum(num);
    }
    //设置所有商品的状态
    public void changAllproductStatus(boolean b){
        for (int i = 0; i <data.size() ; i++) {
            final CarBean.DataBean dataBean = data.get(i);
            final List<CarBean.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j <list.size() ; j++) {
                list.get(j).setSelected(b?1:0);
            }
        }
    }
    class parentViewHolder {
        @BindView(R.id.parent_seller_cb)
        CheckBox parentSellerCb;
        @BindView(R.id.seller_name_tv)
        TextView sellerNameTv;

        parentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class childViewHolder {
        @BindView(R.id.child_cd)
        CheckBox childCd;
        @BindView(R.id.product_icon_iv)
        ImageView productIconIv;
        @BindView(R.id.product_titie_name_tv)
        TextView productTitieNameTv;
        @BindView(R.id.product_price_tv)
        TextView productPriceTv;
        @BindView(R.id.add_remove_view)
        MyAddSubView addRemoveView;

        childViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    oncheckchanglister oncheckchanglister;
    public void setOncheckchangelister(oncheckchanglister oncheckchangelister){
        this.oncheckchanglister=oncheckchangelister;
    }
    public interface oncheckchanglister{
        void onparentcheckchange(int i);
        void onchildcheckchange(int i,int i1);
        void onNumBerchange(int i,int i1,int num);
    }
}
