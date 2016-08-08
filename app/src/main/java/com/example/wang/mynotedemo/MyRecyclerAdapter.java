package com.example.wang.mynotedemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.noteTitle.setText(mPersons.get(position).getNote_title());
        holder.noteContent.setText(mPersons.get(position).getNote_content());
        holder.noteCreateTime.setText(mPersons.get(position).getNote_create_time());

    }


    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView noteTitle, noteContent, noteCreateTime;

        public ViewHolder(View itemView) {
            super(itemView);
            noteTitle = (TextView) itemView.findViewById(R.id.note_title);
            noteContent = (TextView) itemView.findViewById(R.id.note_content);
            noteCreateTime = (TextView) itemView.findViewById(R.id.note_create_time);
        }
    }

}
