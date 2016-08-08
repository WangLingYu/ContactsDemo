package com.example.wang.mynotedemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wang on 16/8/8.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    List<Person> mPersons;

    public MyRecyclerAdapter(List<Person> mPersons) {
        this.mPersons = mPersons;
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.personName.setText(mPersons.get(position).getPerson_title());
        holder.personPhone.setText(mPersons.get(position).getPerson_content());
    }


    @Override
    public int getItemCount() {
        Log.d("MyRecyclerAdapter", "" + mPersons.size());
        return mPersons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView personName, personPhone;
        ImageView personPortrait;

        public ViewHolder(View itemView) {
            super(itemView);
            personName = (TextView) itemView.findViewById(R.id.person_title);
            personPhone = (TextView) itemView.findViewById(R.id.person_content);
            personPortrait = (ImageView) itemView.findViewById(R.id.person_portrait);
        }
    }

}
