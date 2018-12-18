package com.example.viewstudy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        text =  (TextView) findViewById(R.id.text);
//        text.setOnTouchListener(new View.OnTouchListener(){
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i("gejun","EventAction = "+event.getAction());
//                return false;
//            }
//        });

        mViewPager = (ViewPager)findViewById(R.id.vp);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter{
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
            }
            throw new IllegalStateException("No fragment at position " + position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.i("gejun","onTouchEvent EventAction = "+event.getAction());
//        return super.onTouchEvent(event);
//    }
}
