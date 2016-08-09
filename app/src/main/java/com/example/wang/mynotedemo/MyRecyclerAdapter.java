package com.example.wang.mynotedemo;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wang on 16/8/8.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    List<Person> mPersons;
    Context mContext;
    int[] colors = new int[]{0xFFDA4336, 0xFF0288d1, 0xFF009688,0xFF673ab7,0xFFffc107};

    public MyRecyclerAdapter(List<Person> mPersons, Context mContext) {
        this.mPersons = mPersons;
        this.mContext = mContext;
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.personName.setText(mPersons.get(position).getPerson_title());
        holder.personPhone.setText(mPersons.get(position).getPerson_content());
        int colorIndex = (int) (Math.random() * 5);
        holder.personCard.setCardBackgroundColor(colors[colorIndex]);
    }


    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView personName, personPhone;
        CircleImageView personPortrait;
        CardView personCard;

        public ViewHolder(View itemView) {
            super(itemView);
            personName = (TextView) itemView.findViewById(R.id.person_title);
            personPhone = (TextView) itemView.findViewById(R.id.person_content);
            personPortrait = (CircleImageView) itemView.findViewById(R.id.person_portrait);
            personCard = (CardView) itemView.findViewById(R.id.person_card);
        }
    }

}
