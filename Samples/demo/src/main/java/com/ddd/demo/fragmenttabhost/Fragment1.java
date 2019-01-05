package com.ddd.demo.fragmenttabhost;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ddd.demo.R;

public class Fragment1 extends Fragment {

    public static Fragment newInstance(String arg) {
        Fragment1 fragment = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putString("args", arg);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_fragment, null);
        TextView textView = view.findViewById(R.id.text);
        textView.setText("AHHAHAHHHHHHHHHHHHHH");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("gejun","onCreate "+this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("gejun","onViewCreated "+this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("gejun","onActivityCreated "+this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("gejun","onStart "+this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("gejun","onResume "+this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("gejun","onPause "+this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("gejun","onStop "+this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("gejun","onDestroyView "+this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("gejun","onDestroy "+this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("gejun","onDetach "+this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("gejun","onAttach "+this);
    }



}
