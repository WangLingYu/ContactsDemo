package com.example.wang.mynotedemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ScrollableRecyclerView contactsListView;
    List<Person> mPersons;
    FloatingActionMenu floatingActionMenu;
    com.github.clans.fab.FloatingActionButton f1, f2, f3;
    NestedScrollView nestedScrollView;
    boolean isFloatingMenuHide = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        mPersons = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Person person = new Person();
            person.setPerson_title("王苓宇");
            person.setPerson_content("18383038628");
            person.setPerson_portrait("2016-8-30");
            mPersons.add(person);
        }
    }


    private void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        contactsListView = (ScrollableRecyclerView) findViewById(R.id.note_recycler_view);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        assert nestedScrollView != null;

        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.menu_green);
        f1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.f1);
        f2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.f2);
        f3 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.f3);
        f1.setOnClickListener(this);
        f2.setOnClickListener(this);
        f3.setOnClickListener(this);

        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(mPersons, this);
        contactsListView.setLayoutManager(new LinearLayoutManager(this));
        contactsListView.setAdapter(myRecyclerAdapter);

        nestedScrollView.setOnScrollChangeListener(
                new NestedScrollView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if (!floatingActionMenu.isOpened()) {
                            if (Math.abs(scrollY - oldScrollY) > 1) {
                                if ((scrollY - oldScrollY) > 0) {
                                    onScrollUp();
                                    isFloatingMenuHide = true;
                                } else {
                                    onScrollDown();
                                    isFloatingMenuHide = false;
                                }
                            }
                        }
                    }
                }
        );
    }

    private void hideFloatingActionMenu() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(floatingActionMenu, "translationY", 0, 350);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (!isFloatingMenuHide) {
            objectAnimator.start();
        }
    }

    private void showFloatingActionMenu() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(floatingActionMenu, "translationY", 350, 0);
        objectAnimator.setDuration(500);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f1:
                Snackbar.make(nestedScrollView, "点击了f1", Snackbar.LENGTH_SHORT).show();
                floatingActionMenu.toggle(true);
                break;
            case R.id.f2:
                Snackbar.make(nestedScrollView, "点击了f2", Snackbar.LENGTH_SHORT).show();
                floatingActionMenu.toggle(true);
                break;
            case R.id.f3:
                Snackbar.make(nestedScrollView, "点击了f3", Snackbar.LENGTH_SHORT).show();
                floatingActionMenu.toggle(true);
                break;
        }
    }
}
