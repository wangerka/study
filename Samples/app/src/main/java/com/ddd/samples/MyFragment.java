package com.ddd.samples;

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

    public MyFragment() {
    }

    public static Fragment newInstance(String arg) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, null);
        TextView textview = (TextView) view.findViewById(R.id.text);
        textview.setText(getArguments().getString(ARG));
        return view;
    }
}
