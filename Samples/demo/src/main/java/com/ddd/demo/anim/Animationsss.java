package com.ddd.demo.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.ddd.demo.R;

public class Animationsss extends AppCompatActivity implements View.OnClickListener {

    Button alpa,rotate,translate,scale;
    ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_lay);

        alpa = (Button) findViewById(R.id.alpha);
        rotate = (Button) findViewById(R.id.rotate);
        translate = (Button) findViewById(R.id.translate);
        scale = (Button) findViewById(R.id.scale);
        alpa.setOnClickListener(this);
        rotate.setOnClickListener(this);
        translate.setOnClickListener(this);
        scale.setOnClickListener(this);
        image = (ImageView) findViewById(R.id.iamge);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alpha:
                AlphaAnimation ani = new AlphaAnimation(1,0);
                ani.setDuration(2000);
                image.startAnimation(ani);
                break;
            case R.id.rotate:
                break;
            case R.id.translate:
                break;
            case R.id.scale:
                break;
        }
    }
}
