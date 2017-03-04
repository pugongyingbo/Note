package com.it.zzb.note.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.it.zzb.note.R;


public class AboutActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.about_toolbar);

        textView = (TextView) findViewById(R.id.about_text);
        imageView = (ImageView) findViewById(R.id.image);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle("笔记");
        //Glide.with(this).load().into(imageView);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //按返回键时
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
