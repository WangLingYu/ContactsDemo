package com.example.wang.mynotedemo.ui;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.example.wang.mynotedemo.MyRecyclerAdapter;
import com.example.wang.mynotedemo.api.Api;
import com.example.wang.mynotedemo.model.Person;
import com.example.wang.mynotedemo.R;
import com.example.wang.mynotedemo.customview.ScrollableRecyclerView;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ScrollableRecyclerView contactsListView;
    List<Person> mPersons;
    FloatingActionMenu floatingActionMenu;
    com.github.clans.fab.FloatingActionButton search, f2, logout;
    NestedScrollView nestedScrollView;
    SearchView mSearchView;
    MyRecyclerAdapter myRecyclerAdapter;
    boolean isFloatingMenuHide = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.100:5000/")
                .build();
        Api api = retrofit.create(Api.class);

        Call<List<Person>> call = api.getContacts("1");

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {

                Log.d("onResponse", String.valueOf(response.code()));
                if (response.code() == 200) {
                    Log.d("onResponse", String.valueOf(response.body().size()));
                    mPersons = response.body();
                    Log.d("onResponse", String.valueOf(mPersons.size()));
                    initView();
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.d("MainActivity", "网络请求失败");
            }
        });
    }

    private void initView() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        contactsListView = (ScrollableRecyclerView) findViewById(R.id.note_recycler_view);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        assert nestedScrollView != null;

        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.menu_green);
        search = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.search);
        f2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.f2);
        logout = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.logout);
        search.setOnClickListener(this);
        f2.setOnClickListener(this);
        logout.setOnClickListener(this);

        myRecyclerAdapter = new MyRecyclerAdapter(mPersons, this);
        contactsListView.setLayoutManager(new LinearLayoutManager(this));
        contactsListView.setAdapter(myRecyclerAdapter);

        initSearchView();
        setViewListener();
    }

    private void initSearchView() {
        mSearchView = (SearchView) findViewById(R.id.search_view);
        final int editViewId = getResources().getIdentifier("search_src_text", "id", getPackageName());

        SearchView.SearchAutoComplete mEdit = (SearchView.SearchAutoComplete) mSearchView.findViewById(editViewId);
        if (mEdit != null) {
            mEdit.setHintTextColor(0xFF000000);
            mEdit.setTextColor(0xFF000000);
            mEdit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            mEdit.setHint("输入进行搜索");
        }
        assert mSearchView != null;
        mSearchView.setIconified(false);

    }

    private void setViewListener() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "提交完毕", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Person> filteredModelList = filter(mPersons, newText);
                Log.d("MainActivity", String.valueOf(filteredModelList.size()));
                myRecyclerAdapter.animateTo(filteredModelList);
                contactsListView.scrollToPosition(0);
                return true;
            }
        });

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

    //SearchView的过滤
    private List<Person> filter(List<Person> models, String query) {
        query = query.toLowerCase();
        List<Person> filteredModelList = new ArrayList<>();
        for (Person person : models) {
            final String text = person.getPerson_phone().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(person);
            }
        }
        return filteredModelList;
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
            case R.id.search:
                floatingActionMenu.toggle(true);

                break;
            case R.id.f2:
                Snackbar.make(nestedScrollView, "点击了f2", Snackbar.LENGTH_SHORT).show();
                floatingActionMenu.toggle(true);
                break;
            case R.id.logout:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                floatingActionMenu.toggle(true);
                break;
        }
    }
}