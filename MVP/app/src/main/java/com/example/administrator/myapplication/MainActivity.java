package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, V{

    private Button normal,fail;
    private TextView result;
    ProgressBar bar;
    private P p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        normal = (Button)findViewById(R.id.normal);
        fail = (Button)findViewById(R.id.fail);
        normal.setOnClickListener(this);
        fail.setOnClickListener(this);

        bar = (ProgressBar) findViewById(R.id.progressBar);
        result = (TextView)findViewById(R.id.result);

        p = new P(this);
    }

    @Override
    public void showProgress() {
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        bar.setVisibility(View.GONE);
    }

    @Override
    public void showData(String result) {
        hideProgress();
        this.result.setText(result);
    }

    @Override
    public void showFail(String info) {
        hideProgress();
        this.result.setText(info);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.normal:
                p.getData("normal");
                break;
            case R.id.fail:
                p.getData("fail");
                break;
            case R.id.result:
                break;
        }



    }
}
