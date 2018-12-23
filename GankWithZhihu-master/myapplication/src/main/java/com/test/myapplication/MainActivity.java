package com.test.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("gejun","activity create"+Thread.currentThread().getId());
        startService(new Intent(this, MyService.class));



        Thread t = new Thread(){
            int i =0;
            @Override
            public void run() {
                Log.i("gejun","run "+Thread.currentThread().getId());
                while(i < 10){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        };
        t.start();
    }
}
