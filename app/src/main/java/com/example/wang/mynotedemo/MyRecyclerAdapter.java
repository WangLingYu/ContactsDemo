package com.example.wang.mynotedemo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wang.mynotedemo.model.Person;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wang on 16/8/8.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    List<Person> mPersons;
    Context mContext;
    int[] colors = new int[]{0xFFDA4336, 0xFF0288d1, 0xFF009688, 0xFF673ab7, 0xFFffc107};

    public MyRecyclerAdapter(List<Person> mPersons, Context mContext) {
        this.mPersons = new ArrayList<>(mPersons);
        this.mContext = mContext;
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.personName.setText(mPersons.get(position).getPerson_phone());
        holder.personPhone.setText(mPersons.get(position).getPerson_name());
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
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personPhone = (TextView) itemView.findViewById(R.id.person_phone);
            personPortrait = (CircleImageView) itemView.findViewById(R.id.person_portrait);
            personCard = (CardView) itemView.findViewById(R.id.person_card);
        }
    }

    //searchView的即时更新动画
    public void animateTo(List<Person> models) {
        Log.d("MainActivity", "animateTo中mPersons的长度为" + mPersons.size());
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Person> newModels) {
        for (int i = mPersons.size() - 1; i >= 0; i--) {
            final Person model = mPersons.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Person> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Person model = newModels.get(i);
            if (!mPersons.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Person> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Person model = newModels.get(toPosition);
            final int fromPosition = mPersons.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Person removeItem(int position) {
        final Person model = mPersons.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Person model) {
        if (position >= mPersons.size()) {
            mPersons.add(model);
            notifyItemInserted(mPersons.size() - 1);
        } else {
            mPersons.add(position, model);
            notifyItemInserted(position);
        }
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Person model = mPersons.remove(fromPosition);
        if(toPosition >= mPersons.size()) {
            mPersons.add(model);
            notifyItemMoved(fromPosition, mPersons.size()-1);
        } else {
            mPersons.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }
    }
}
