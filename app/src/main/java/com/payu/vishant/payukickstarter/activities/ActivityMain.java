package com.payu.vishant.payukickstarter.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.payu.vishant.payukickstarter.R;
import com.payu.vishant.payukickstarter.adapters.RvAdapterKickStarter;
import com.payu.vishant.payukickstarter.dialogs.DialogFilter;
import com.payu.vishant.payukickstarter.dialogs.DialogSort;
import com.payu.vishant.payukickstarter.models.KickStarter;
import com.payu.vishant.payukickstarter.network.VolleySingelton;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ActivityMain extends SlidingActivity implements View.OnClickListener,
        DialogSort.DialogSortCallBack,
        SearchView.OnQueryTextListener {

    private static final String TAG = "ActivityMain";
    private TextView tv_title;
    private TextView tv_msg;
    private TextView tv_sort;
    private TextView tv_filter;
    private ProgressBar pb;
    private RecyclerView rv_kickstarter;
    private View ll_empty_view;
    private View cl_non_empty_view;
    private RvAdapterKickStarter rvAdapterKickStarter;

    private ArrayList<KickStarter> kickStarterArrayList;
    private ArrayList<KickStarter> projectWeLoveList;

    private static final int MAX_FUNDING_LIMIT = 60;
    private static final int MAX_BACKERS_LIMIT = 30000;

    @Override
    protected void onStart() {
        kickStarterArrayList = new ArrayList<>();
        projectWeLoveList = new ArrayList<>();
        super.onStart();
    }

    @Override
    public void onSwipeRight() {

    }

    @Override
    public void onSwipeLeft() {
        Intent intent = new Intent(this,ActivityProjectWeLove.class);
        for(KickStarter k : kickStarterArrayList){
            if((k.getPercentage_funded() > MAX_FUNDING_LIMIT) && (k.getNum_backers() > MAX_BACKERS_LIMIT)){
                projectWeLoveList.add(k);
            }
        }
        intent.putExtra("PWL",projectWeLoveList);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        getKickStarterListFromServer();
    }

    private void initializeViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_title = (TextView) toolbar.findViewById(R.id.tv_title);
        tv_title.setText(R.string.pay_u_kick_start);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        ll_empty_view = findViewById(R.id.ll_empty_view);
        cl_non_empty_view = findViewById(R.id.cl_non_empty_view);

        tv_msg = findViewById(R.id.tv_msg);
        pb = findViewById(R.id.pb);

        tv_sort = findViewById(R.id.tv_sort);
        tv_filter = findViewById(R.id.tv_filter);

        tv_sort.setText(getFooterString("Sort"));
        tv_filter.setText(getFooterString("Filter"));

        tv_sort.setOnClickListener(this);
        tv_filter.setOnClickListener(this);
    }

    public CharSequence getFooterString(String string) {

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_sort, null);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        SpannableString spannableString = new SpannableString(string + "   .");
        spannableString.setSpan(imageSpan, string.length() + 1, spannableString.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }


    private void getKickStarterListFromServer() {
        JSONArray jSonObject = new JSONArray();

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, "http://starlord.hackerearth.com/kickstarter", jSonObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray server_response = response;
                for (int i = 0; i < server_response.length(); i++) {
                    try {
                        kickStarterArrayList.add(new KickStarter(server_response.getJSONObject(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                initializeRecyclerView();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    tv_msg.setText(R.string.no_internet_msg);
                } else if (error instanceof ServerError) {
                    tv_msg.setText(R.string.server_error_msg);
                } else if (error instanceof TimeoutError) {
                    tv_msg.setText(R.string.timeout_error_msg);
                }
            }
        });

        RequestQueue requestQueue = VolleySingelton.getVolleySingelton(this).getmRequestQueue();
        requestQueue.add(stringRequest);
    }

    private void initializeRecyclerView() {
        Log.i(TAG, kickStarterArrayList.toString());
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


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_sort) {
            showSortigDialog();
        } else if (view.getId() == R.id.tv_filter) {
            showFilterDialog();
        }
    }

    private void showFilterDialog() {
        FragmentManager manager = getSupportFragmentManager();
        DialogFilter dialogFilter = DialogFilter.newInstance(kickStarterArrayList);
        dialogFilter.show(manager, "dialogFilter");
    }

    private void showSortigDialog() {
        FragmentManager manager = getSupportFragmentManager();
        DialogSort dialogSort = new DialogSort();
        dialogSort.show(manager, "dialogSort");
    }

    @Override
    public void SortAlphabatically(boolean ASC) {
        rvAdapterKickStarter.SortAlphabatically(ASC);
    }

    @Override
    public void SortByEndTime(boolean ASC) {
        rvAdapterKickStarter.SortByEndTime(ASC);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();

        ArrayList<KickStarter> newList = new ArrayList<>();
        for (KickStarter kickStarter : kickStarterArrayList) {
            String Title = kickStarter.getTitle().toLowerCase();
            if (Title.contains(newText)) {
                newList.add(kickStarter);
            }
        }

        rvAdapterKickStarter.setSearchFilter(newList);
        return true;
    }

}
