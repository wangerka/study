package com.ddd.demo.fragmenttabhost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;

import com.ddd.demo.R;

public class FragmentTabHostDemo extends AppCompatActivity {

    FragmentTabHost tabHost;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tabhost_activity);

        tabHost = findViewById(R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);


        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("FRIST"), Fragment1.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("SECOND"), Fragment1.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("THIRD"), Fragment1.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("FOUR"), Fragment1.class, null);

    }
}
