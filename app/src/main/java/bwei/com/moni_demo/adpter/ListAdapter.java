package bwei.com.moni_demo.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwei.com.moni_demo.R;
import bwei.com.moni_demo.mvp.fenlei.model.FenBean;

public class ListAdapter extends BaseAdapter {
    private List<FenBean.DataBean> data;
    private Context context;

    public ListAdapter(List<FenBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            view = View.inflate(context, R.layout.main_lisview_item, null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.listItemTv.setText(data.get(i).getName());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.list_item_tv)
        TextView listItemTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
