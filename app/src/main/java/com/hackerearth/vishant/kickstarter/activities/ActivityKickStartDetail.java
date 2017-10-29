package com.hackerearth.vishant.kickstarter.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hackerearth.vishant.kickstarter.R;
import com.hackerearth.vishant.kickstarter.database.RealmInterfaceProjects;
import com.hackerearth.vishant.kickstarter.models.KickStarter;
import com.hackerearth.vishant.kickstarter.utils.Utils;

import static com.hackerearth.vishant.kickstarter.utils.Utils.BaseHost;

public class ActivityKickStartDetail extends SlidingActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    private int kickStarter_s_no;
    private KickStarter kickStarter;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private TextView tv_main_ll_title;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private WebView wv_web_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kick_start_detail_sec);
        kickStarter_s_no =  getIntent().getIntExtra("kickStarter",0);
        kickStarter = RealmInterfaceProjects.getKickStarterProjectByS_No(this,kickStarter_s_no);
        initializeView();


    }

    @Override
    public void onSwipeRight() {

    }

    @Override
    public void onSwipeLeft() {

    }

    private void initializeView() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mTitle = (TextView) findViewById(R.id.main_textview_title);
        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
        tv_main_ll_title = findViewById(R.id.tv_main_ll_title);
        mTitle.setText(kickStarter.getTitle());
        tv_main_ll_title.setText(kickStarter.getTitle());

        mAppBarLayout.addOnOffsetChangedListener(this);

        mToolbar.inflateMenu(R.menu.activity_main_menu);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wv_web_details = findViewById(R.id.wv_web_details);

        wv_web_details.loadUrl(Utils.getUrl(kickStarter.getUrl()));
        WebSettings webSettings = wv_web_details.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv_web_details.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if(request.getUrl().getHost().equals(BaseHost))
                    {
                        return false;
                    }

                    Intent intent = new Intent(Intent.ACTION_VIEW,request.getUrl());
                    startActivity(intent);
                    return true;
                }else {
                    return true;
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }
}
