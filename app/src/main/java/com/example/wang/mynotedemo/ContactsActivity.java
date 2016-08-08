package com.example.wang.mynotedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    RecyclerView contactsListView;
    List<Person> mPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contactsListView = (RecyclerView) findViewById(R.id.note_recycler_view);
        mPersons = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Person person = new Person();
            person.setPerson_title("王苓宇");
            person.setPerson_content("18383038628");
            person.setPerson_portrait("2016-8-30");
            mPersons.add(person);
        }
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(mPersons, this);

        contactsListView.setLayoutManager(new LinearLayoutManager(this));
        contactsListView.setAdapter(myRecyclerAdapter);
    }

}
