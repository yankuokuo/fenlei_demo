package bwei.com.moni_demo.adpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwei.com.moni_demo.R;
import bwei.com.moni_demo.mvp.fenlei.model.ZiBean;

public class ExViewAdapter extends BaseExpandableListAdapter {
    private List<ZiBean.DataBean> list;
    private Context context;
    private ChildReviewAdapter childReviewAdapter;

    public ExViewAdapter(List<ZiBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
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
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.main_exview_group, null);
            holder = new GroupViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (GroupViewHolder) view.getTag();
        }
        holder.itemGroupTv.setText(list.get(i).getName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View mview, ViewGroup viewGroup) {
        childViewHolder holder;
        if (mview == null) {
            mview = View.inflate(context, R.layout.main_exview_child, null);
            holder=new childViewHolder(mview);
            mview.setTag(holder);
        } else {
            holder = (childViewHolder) mview.getTag();
        }
        final List<ZiBean.DataBean.ListBean> childlist = this.list.get(i).getList();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(viewGroup.getContext(), 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        childReviewAdapter = new ChildReviewAdapter(childlist, viewGroup.getContext());
            holder.childRecycerview.setLayoutManager(gridLayoutManager);
            holder.childRecycerview.setAdapter(childReviewAdapter);
        return mview;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    class GroupViewHolder {
        @BindView(R.id.item_group_tv)
        TextView itemGroupTv;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class childViewHolder {
        @BindView(R.id.child_recycerview)
        RecyclerView childRecycerview;

        childViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
