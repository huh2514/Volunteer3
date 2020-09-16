package com.cookandroid.volunteer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Dictionary> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected  int ID;
        protected TextView id;
        protected TextView english;
        protected TextView korean;


        public CustomViewHolder(View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.id_listitem);
            this.english = (TextView) view.findViewById(R.id.english_listitem);
            this.korean = (TextView) view.findViewById(R.id.korean_listitem);
        }

    }


    public CustomAdapter(ArrayList<Dictionary> list) {
        this.mList = list;
    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder viewholder, int position) {

        viewholder.ID = mList.get(position).getID();

        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.english.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.korean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        viewholder.id.setGravity(Gravity.CENTER);
        viewholder.english.setGravity(Gravity.CENTER);
        viewholder.korean.setGravity(Gravity.CENTER);

        viewholder.id.setText(mList.get(position).getId());
        viewholder.english.setText(mList.get(position).getEnglish());
        viewholder.korean.setText(mList.get(position).getKorean());

        viewholder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.SelectedVolunteer");
                intent.setComponent(cmpName);

                ((SearchResult)SearchResult.mContext).newIntent(intent, viewholder);

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}