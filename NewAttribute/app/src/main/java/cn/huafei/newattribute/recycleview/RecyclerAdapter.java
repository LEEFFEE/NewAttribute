package cn.huafei.newattribute.recycleview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.huafei.newattribute.R;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<RecycleViewActivity.DataBean> mList;
    private int mLayoutId;

    /**
     * @param context  上下文
     * @param list     数据集合
     * @param layoutId 条目布局
     */
    public RecyclerAdapter(Context context, ArrayList<RecycleViewActivity.DataBean> list, int layoutId) {
        super();
        mContext = context;
        mList = list;
        mLayoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, mLayoutId, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecycleViewActivity.DataBean bean = mList.get(position);
        holder.setData(bean);
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
        }

        private ImageView mIvIcon;
        private TextView mTvName;
        public void setData(RecycleViewActivity.DataBean bean){
            mIvIcon.setImageResource(bean.iconId);
            mTvName.setText(bean.content);
            mTvName.setTextColor(Color.BLACK);
        }
    }


}
