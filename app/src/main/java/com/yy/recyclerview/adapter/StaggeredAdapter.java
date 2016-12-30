package com.yy.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13160677911 on 2016-12-29.
 */

public class StaggeredAdapter extends SimpleAdapter{

    private List<Integer> mHeights;

    //构造方法
    public StaggeredAdapter(Context context, List<String> datas) {
        super(context,datas);
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);

        mHeights = new ArrayList<Integer>();
        //初始化随机数
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }





    //绑定ViewHolder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //为holder的itemview设置高度
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        //给TextView赋值
        holder.tv.setText(mDatas.get(position));

        setUpItemEvent(holder);
    }
    public void addData(int pos) {
        mDatas.add(pos, "Insert Staggered One");
        notifyItemInserted(pos);
    }

    public void deleteData(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }






}

