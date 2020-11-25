package com.cookandroid.volunteer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<searchItem> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public MyAdapter(Context context, ArrayList<searchItem> items) {
        this.mList = items;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //binding
        holder.progrmSj.setText(mList.get(position).progrmSj);
        holder.actPlace.setText(mList.get(position).actPlace);
        holder.progrmBgnde.setText(mList.get(position).progrmBgnde);
        holder.progrmEndde.setText(mList.get(position).progrmEndde);
        holder.progrmRegistNo.setText(mList.get(position).progrmRegistNo);

        final String s = holder.progrmRegistNo.getText().toString();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("마이어뎁터",s);
                ((ResultView)ResultView.context_main).select(s);
            }
        });
        //Click event
    }

    @Override
    public int getItemCount() {
        return mList.size();    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView progrmSj;
        public TextView actPlace;
        public TextView progrmBgnde;
        public TextView progrmEndde;
        public TextView progrmRegistNo;

        public MyViewHolder(final View itemView) {
            super(itemView);

            progrmSj = itemView.findViewById(R.id.tv_progrmSj);
            actPlace = itemView.findViewById(R.id.tv_actPlace);
            progrmBgnde = itemView.findViewById(R.id.tv_progrmBgnde);
            progrmEndde = itemView.findViewById(R.id.tv_progrmEndde);
            progrmRegistNo = itemView.findViewById(R.id.tv_progrmRegNo);

        }

    }

}