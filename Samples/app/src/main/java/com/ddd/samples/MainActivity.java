package com.ddd.samples;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);


        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    public class MyPageAdapter extends FragmentPagerAdapter{
        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch(i){
                case 0:
                    return MyFragment.newInstance("first fragment");
                case 1:
                    return MyFragment.newInstance("secnd fragment");
                case 2:
                    return MyFragment.newInstance("third fragment");
            }
            throw new IllegalStateException("wrong position" + i);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "First";
                case 1:
                    return "Second";
                case 2:
                    return "Thrid";
            }
            return null;
        }
    }
}
