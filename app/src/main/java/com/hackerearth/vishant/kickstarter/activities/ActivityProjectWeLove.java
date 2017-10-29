package com.hackerearth.vishant.kickstarter.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.hackerearth.vishant.kickstarter.R;
import com.hackerearth.vishant.kickstarter.adapters.RvAdapterProjectWeLove;
import com.hackerearth.vishant.kickstarter.database.RealmInterfaceProjects;
import com.hackerearth.vishant.kickstarter.models.KickStarter;

import java.util.ArrayList;
import java.util.Collections;

public class ActivityProjectWeLove extends SlidingActivity {

    private TextView tv_title;
    private ArrayList<KickStarter> projectWeLoveList;
    private RecyclerView rv_project_we_love;
    private RvAdapterProjectWeLove rvAdapterProjectWeLove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_we_love);
        projectWeLoveList = RealmInterfaceProjects.getProjectWeLove(this); /*(ArrayList<KickStarter>)getIntent().getSerializableExtra("PWL");*/
        Collections.sort(projectWeLoveList, new KickStarter.PopularityComaparator());
        initializeViews();
        initializeRecyclerView();
    }

    @Override
    public void onSwipeRight() {

    }

    @Override
    public void onSwipeLeft() {

    }

    private void initializeViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_title = (TextView) toolbar.findViewById(R.id.tv_title);
        tv_title.setText(R.string.project_we_love);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initializeRecyclerView() {
        if (projectWeLoveList.size() > 0) {
            rv_project_we_love = (RecyclerView) findViewById(R.id.rv_project_we_love);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            rv_project_we_love.setLayoutManager(manager);
            rvAdapterProjectWeLove = new RvAdapterProjectWeLove(this, projectWeLoveList);
            rv_project_we_love.setAdapter(rvAdapterProjectWeLove);
        } else {
            Toast.makeText(this, "Empty List!!!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
