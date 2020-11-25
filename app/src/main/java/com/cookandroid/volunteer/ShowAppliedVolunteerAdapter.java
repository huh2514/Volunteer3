package com.cookandroid.volunteer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ShowAppliedVolunteerAdapter extends RecyclerView.Adapter<ShowAppliedVolunteerAdapter.MyViewHolder>  {

    private ArrayList<searchItem> mList;
    private LayoutInflater mInflate;
    private Context mContext;


    public ShowAppliedVolunteerAdapter(Context context, ArrayList<searchItem> items) {
        this.mList = items;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.show_applied_volunteer_items, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
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

                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE", Locale.getDefault());
                SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
                SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

                String weekDay = weekdayFormat.format(currentTime);
                String year = yearFormat.format(currentTime);
                String month = monthFormat.format(currentTime);
                String day = dayFormat.format(currentTime);
                if(Integer.parseInt(holder.progrmEndde.getText().toString()) < Integer.parseInt(year + month + day)){
                    final Context selected = ShowAppliedVolunteer.showApplied;
                    AlertDialog.Builder builder = new AlertDialog.Builder(selected);
                    builder.setMessage("기간이 지난 봉사활동입니다!");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(selected,"봉사활동 제거",Toast.LENGTH_LONG);
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    Log.d("마이어뎁터", s);
                    ((ShowAppliedVolunteer) ShowAppliedVolunteer.context_main_ShowAppliedVolunteer).select(s);
                }
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

            progrmSj = itemView.findViewById(R.id.show_progrmSj);
            actPlace = itemView.findViewById(R.id.show_actPlace);
            progrmBgnde = itemView.findViewById(R.id.show_progrmBgnde);
            progrmEndde = itemView.findViewById(R.id.show_progrmEndde);
            progrmRegistNo = itemView.findViewById(R.id.show_progrmRegNo);

        }

    }

}
