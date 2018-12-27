package com.example.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {

    private static final String ARG = "args";

    public static MyFragment newInstance(String ar) {

        Bundle args = new Bundle();
        args.putString(ARG,ar);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(getArguments().getString(ARG));
        return view;
    }
}
