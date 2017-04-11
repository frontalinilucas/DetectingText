package com.lf.detectingtext.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lf.detectingtext.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 11/4/17.
 */

public class DetectTextAdapter extends RecyclerView.Adapter<DetectTextAdapter.DetectTextViewHolder> {

    private List<String> mResponsesList;

    public DetectTextAdapter(List<String> list){
        setResponsesList(list);
    }

    public void setResponsesList(List<String> list){
        mResponsesList = list;
        notifyDataSetChanged();
    }

    @Override
    public DetectTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_textdescription, parent, false);
        return new DetectTextAdapter.DetectTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetectTextViewHolder holder, int position) {
        String text = mResponsesList.get(holder.getAdapterPosition());
        holder.bindText(text);
    }

    @Override
    public int getItemCount() {
        return (mResponsesList != null ? mResponsesList.size() : 0);
    }

    public class DetectTextViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textview)
        TextView mText;

        public DetectTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindText(String text){
            mText.setText(text);
        }
    }
}
