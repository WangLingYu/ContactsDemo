package com.example.wang.mynotedemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ScrollableRecyclerView contactsListView;
    List<Person> mPersons;
    FloatingActionMenu floatingActionMenu;
    NestedScrollView nestedScrollView;
    boolean isFloatingMenuHide = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        contactsListView = (ScrollableRecyclerView) findViewById(R.id.note_recycler_view);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);

        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.menu_yellow);
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

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (Math.abs(scrollY - oldScrollY) > 10) {
                    if ((scrollY - oldScrollY) > 0) {
                        onScrollUp();
                        isFloatingMenuHide = true;
                    } else {
                        onScrollDown();
                        isFloatingMenuHide = false;
                    }
                }
            }
        });
    }

    private void hideFloatingActionMenu() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(floatingActionMenu, "translationX", 0, 500);
        objectAnimator.setDuration(500);//如果AnimatorSet设置了该属性则是Set的值为准
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (!isFloatingMenuHide) {
            objectAnimator.start();
        }
    }

    private void showFloatingActionMenu() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(floatingActionMenu, "translationX", 500, 0);
        objectAnimator.setDuration(500);//如果AnimatorSet设置了该属性则是Set的值为准
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (isFloatingMenuHide) {
            objectAnimator.start();
        }
    }

    private void onScrollUp() {
        Log.d("onScrollUp", "执行了");
        hideFloatingActionMenu();
    }

    private void onScrollDown() {
        Log.d("onScrollDown", "执行了");
        showFloatingActionMenu();
    }
}
