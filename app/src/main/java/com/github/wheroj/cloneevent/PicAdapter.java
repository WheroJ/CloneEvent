package com.github.wheroj.cloneevent;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.wheroj.cloneevent.common.Utils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/19.
 */

public class PicAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Bitmap> mDatas;

    public PicAdapter(Context context, ArrayList<Bitmap> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(mContext);
        RecyclerView.LayoutParams layoutParams
                = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getPx(50));
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        if (mDatas == null)
            return 0;
        return mDatas.size();
    }
}


class ViewHolder extends RecyclerView.ViewHolder{
    public ViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(Bitmap bitmap) {
        ((ImageView)itemView).setImageBitmap(bitmap);
    }
}
