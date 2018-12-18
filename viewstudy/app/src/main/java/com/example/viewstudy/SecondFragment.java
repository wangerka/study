package com.example.viewstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by gejun on 18-12-18.
 */

public class SecondFragment extends Fragment {
    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.second_fragment,null);
        button = (Button)v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyListActivity.class));
            }
        });
        return v;
    }

    public void click(View view){
        startActivity(new Intent(getActivity(), MyListActivity.class));
    }
}
