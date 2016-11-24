package cn.huafei.androidm.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.huafei.androidm.R;

/**
 * Created by Administrator on 2016/9/3 0003.
 */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {

    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;
    private LayoutInflater mInflater;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RecyclerAdapter(Context mContext, List<T> mDatas, int mLayoutId) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mLayoutId = mLayoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //这里是创建ViewHolder的地方，RecyclerAdapter内部已经实现了ViewHolder的重用
        //这里我们直接new就好了
        return new RecyclerHolder(mInflater.inflate(mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerHolder holder, int position) {
        if (onItemClickListener != null) {
            //设置背景
            holder.itemView.setBackgroundResource(R.drawable.recycler_bg);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //注意，这里的position不要用上面参数中的position，会出现位置错乱
                    onItemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
        convert(holder, mDatas.get(position), position);
    }

    public abstract void convert(RecyclerHolder holder, T data, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 自定义RecyclerView item的点击事件的点击事件
     */
    public interface OnItemClickListener {
        void OnItemClickListener(View view, int position);
    }

}
