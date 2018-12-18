package com.example.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    /**
     * ÓÃÓÚÕ¹Ê¾ÕÕÆ¬Ç½µÄGridView
     */
    private GridView mPhotoWall;

    /**
     * GridViewµÄÊÊÅäÆ÷
     */
    private PhotoWallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhotoWall = (GridView) findViewById(R.id.photo_wall);
        adapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls, mPhotoWall);
        mPhotoWall.setAdapter(adapter);
        Log.i("gejun","maxMemory = "+Runtime.getRuntime().maxMemory()/1024);//dalvik.vm.heapgrowthlimit

        ActivityManager activityManager =(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        Log.i("gejun",""+activityManager.getMemoryClass());//dalvik.vm.heapgrowthlimit
        Log.i("gejun",""+activityManager.getLargeMemoryClass());//dalvik.vm.heapsize
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ÍË³ö³ÌÐòÊ±½áÊøËùÓÐµÄÏÂÔØÈÎÎñ
        adapter.cancelAllTasks();
    }
}
