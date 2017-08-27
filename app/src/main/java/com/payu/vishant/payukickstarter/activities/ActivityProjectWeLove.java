package com.payu.vishant.payukickstarter.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.payu.vishant.payukickstarter.R;
import com.payu.vishant.payukickstarter.adapters.RvAdapterKickStarter;

public class ActivityProjectWeLove extends SlidingActivity {

    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_we_love);
        initializeViews();
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void initializeRecyclerView() {
       /* Log.i(TAG, kickStarterArrayList.toString());
        if (kickStarterArrayList.size() > 0) {
            rv_kickstarter = (RecyclerView) findViewById(R.id.rv_kickstarter);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            rv_kickstarter.setLayoutManager(manager);
            rvAdapterKickStarter = new RvAdapterKickStarter(this, kickStarterArrayList);
            rv_kickstarter.setAdapter(rvAdapterKickStarter);

            ll_empty_view.setVisibility(View.GONE);
            cl_non_empty_view.setVisibility(View.VISIBLE);
        } else {
            tv_msg.setText(R.string.empty_list_msg);
        }
*/

    }
}
