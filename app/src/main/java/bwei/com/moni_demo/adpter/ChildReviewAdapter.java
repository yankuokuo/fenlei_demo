package bwei.com.moni_demo.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bwei.com.moni_demo.R;
import bwei.com.moni_demo.app.MyApp;
import bwei.com.moni_demo.mvp.fenlei.model.ZiBean;
import okhttp3.Interceptor;

public class ChildReviewAdapter extends RecyclerView.Adapter<ChildReviewAdapter.MyViewHodler> {
    private List<ZiBean.DataBean.ListBean> list;
    private Context context;

    public ChildReviewAdapter(List<ZiBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ChildReviewAdapter.MyViewHodler onCreateViewHolder( ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_exview_child_item, viewGroup, false);
        final MyViewHodler myViewHodler = new MyViewHodler(view);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(ChildReviewAdapter.MyViewHodler myViewHodler, int i) {
        myViewHodler.textView.setText(list.get(i).getName());
        final String icon = list.get(i).getIcon();
        ImageLoader.getInstance().displayImage(icon,myViewHodler.imageView, MyApp.getOptions());
    }

    @Override
    public int getItemCount() {
        return list==null? 0:list.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public MyViewHodler(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.main_child_item_image);
            textView = itemView.findViewById(R.id.main_child_item_tv);
        }
    }
}
