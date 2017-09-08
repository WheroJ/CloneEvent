package com.github.wheroj.cloneevent;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.wheroj.cloneevent.common.Utils;

import java.util.ArrayList;

public class CharAdapter extends RecyclerView.Adapter<CharHolder> {

    private ArrayList<Character> datas;

    public CharAdapter(ArrayList<Character> datas) {
        this.datas = datas;
    }

    @Override
    public CharHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_char, null);
        return new CharHolder(view);
    }

    @Override
    public void onBindViewHolder(CharHolder holder, int position) {
        holder.setData(datas.get(position), position, mListener);
    }

    @Override
    public int getItemCount() {
        if (datas == null)
            return 0;
        return datas.size();
    }

    private onItemClickListener mListener;

    public void setOnItemCLickListener(onItemClickListener OnItemClickListener) {
        mListener = OnItemClickListener;
    }

    public interface onItemClickListener {
        void onDownEvent(int position);

        void onCancelEvent(int position);

        void onItemClick(int position, Character character);
    }
}

class CharHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public CharHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.textView);
    }

    public void setData(final Character str, final int position, final CharAdapter.onItemClickListener mListener) {
        textView.setText(str.toString());

        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mListener != null) {
                            mListener.onDownEvent(position);
                            itemView.setBackgroundColor(Utils.getContext().getResources().getColor(R.color.gray_6));
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        if (mListener != null) {
                            mListener.onCancelEvent(position);
                            itemView.setBackgroundResource(R.drawable.transparent);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mListener != null) {
                            itemView.setBackgroundResource(R.drawable.transparent);
                            mListener.onItemClick(position, str);
                        }
                        break;
                }
                return true;
            }
        });
    }
}
