package com.codemobi.android.tvthailand.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codemobi.android.tvthailand.R;
import com.codemobi.android.tvthailand.adapter.ProgramAdapter;
import com.codemobi.android.tvthailand.datasource.Programs;

/**
 * Created by nattapong on 7/14/15 AD.
 */
public class ProgramActivity extends AppCompatActivity {

    public static final String EXTRAS_MODE = "EXTRAS_MODE";
    public static final String EXTRAS_ID = "EXTRAS_ID";
    public static final String EXTRAS_TITLE = "EXTRAS_TITLE";
    public static final String EXTRAS_ICON = "EXTRAS_ICON";
    public static final String EXTRAS_URL = "EXTRAS_URL";

    /** MODE **/
    public static final int BY_CATEGORY = 1;
    public static final int BY_CHANNEL = 2;

    private Programs mPrograms;
    private String id;
    private int mode;
    private String title;
    private String icon;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        initExtras();
        initToolbar();
        initInstances();
    }

    private void initExtras() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString(EXTRAS_ID);
        mode = bundle.getInt(EXTRAS_MODE);
        title = bundle.getString(EXTRAS_TITLE);
        icon = bundle.getString(EXTRAS_ICON);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        Glide.with(this).load(icon)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        toolbar.setLogo(new BitmapDrawable(getResources(), resource));
                    }
                });
        setSupportActionBar(toolbar);
    }

    private void initInstances() {

        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.rvProgram);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final RecyclerView.Adapter mAdapter = new ProgramAdapter(this, mPrograms = new Programs());
        mRecyclerView.setAdapter(mAdapter);

        mPrograms.setOnProgramChangeListener(new Programs.OnProgramChangeListener() {
            @Override
            public void onProgramChange(Programs Programs) {
                mAdapter.notifyDataSetChanged();
            }
        });


        loadProgram(0);
    }


    private void loadProgram(int start) {
        switch (mode) {
            case BY_CATEGORY:
                mPrograms.loadProgramByCategory(id, start);
                break;
            case BY_CHANNEL:
                mPrograms.loadProgramByChannel(id, start);
                break;

            default:
                break;
        }
    }
}
