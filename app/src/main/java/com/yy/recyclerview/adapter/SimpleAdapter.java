package com.yy.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.yy.recyclerview.R;

import java.util.List;

/**
 * Created by 13160677911 on 2016-12-29.
 */

public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {

    public LayoutInflater mInflater;
    public Context mContext;
    public List<String> mDatas;

    //观察者模式创建监听
    public interface OnItemClickListener {
        /**
         * Item的点击事件
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);

        /**
         * Item的长点击事件
         * @param view
         * @param position
         */
        void onItemLongClick(View view, int position);
    }

    //创建接口
    private OnItemClickListener mOnItemClickListener;
    //创建接口构造方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //构造方法
    public SimpleAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    //创建ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_single_textview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    //绑定ViewHolder
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //给TextView赋值
        holder.tv.setText(mDatas.get(position));
        setUpItemEvent(holder);


    }

    protected void setUpItemEvent(final MyViewHolder holder) {
        //回调监听的触发
        if (mOnItemClickListener != null) {
            //点击事件的监听
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, layoutPosition);
                }
            });
            //长点击事件的监听
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutPosition);
                    return false;
                }
            });
        }
    }


    public void addData(int pos) {
        mDatas.add(pos, "Insert One");
        notifyItemInserted(pos);
    }

    public void deleteData(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }


}

class MyViewHolder extends RecyclerView.ViewHolder {
    //当前item的所有控件
    TextView tv;

    public MyViewHolder(View view) {
        super(view);
        //初始化
        tv = (TextView) view.findViewById(R.id.id_tv);
    }
}
