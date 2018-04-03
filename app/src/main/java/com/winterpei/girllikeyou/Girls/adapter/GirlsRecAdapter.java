package com.winterpei.girllikeyou.Girls.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winterpei.girllikeyou.Girls.protocol.GirlsItemPorotocol;
import com.winterpei.girllikeyou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xingyang.pei
 * @date 2017/9/1.
 */
public class GirlsRecAdapter extends RecyclerView.Adapter<GirlsRecAdapter.GirlsViewHolder> {

    private List<GirlsItemPorotocol> mBeanList;
    private Context mContext;

    public GirlsRecAdapter(Context context) {
        mBeanList = new ArrayList<>();
        mContext = context;
    }

    @Override
    public GirlsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GirlsViewHolder girlsViewHolder = new GirlsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_girls_layout,
                        parent,
                        false)
        );
        return girlsViewHolder;
    }

    @Override
    public void onBindViewHolder(GirlsViewHolder holder, int position) {
        Glide.with(mContext).load(mBeanList.get(position).url).into(holder.mItemGirlsIv);
        holder.mItemGirlsWhoTv.setText(mBeanList.get(position).who);

        if (null != mItemClickListener) {
            holder.mItemGirlsIv.setOnClickListener(view -> mItemClickListener.onItemClick(view, position));
        }

    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    public void setData(List<GirlsItemPorotocol> resultsBeen) {
        mBeanList.clear();
        mBeanList.addAll(resultsBeen);
        notifyDataSetChanged();
    }

    public void addData(List<GirlsItemPorotocol> resultsBeen) {
        mBeanList.addAll(resultsBeen);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    static class GirlsViewHolder extends RecyclerView.ViewHolder {

        private ImageView mItemGirlsIv;
        private TextView mItemGirlsWhoTv;

        GirlsViewHolder(View view) {
            super(view);
            mItemGirlsIv = (ImageView) view.findViewById(R.id.item_girls_iv);
            mItemGirlsWhoTv = (TextView) view.findViewById(R.id.item_girls_who_tv);
        }
    }
}
