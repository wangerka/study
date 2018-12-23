package com.ddd.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyFragment extends ListFragment {
    private static final String ARG = "args";
//    public static MyFragment newInstance(String arg) {
//
//        Bundle args = new Bundle();
//        args.putString(ARG, arg);
//        MyFragment fragment = new MyFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment, null);
//        TextView textView = view.findViewById(R.id.text);
//        textView.setText(getArguments().getString(ARG));
//        return view;
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] strings = {"1",
                "2",
                "3",
                "4",
                "6",
                "6","4","vfs","gfs","gsfgf","gfsgfs","gfsgs","gfsfsg","gsfsgfs",};
//        getListView().setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,strings));
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,strings));
    }
}
