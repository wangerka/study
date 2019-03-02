package com.ddd.mvvm;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

public class ViewModel {

    String name;
    String pwd;

    public TextWatcher nameChangeListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public TextWatcher pwdChangeListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pwd = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    Context context;

    public ViewModel(Context context) {
        this.context = context;
    }

    public void login(View view){
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)){
            if(name.equals("gejun") && pwd.equals("123456")){
                Toast.makeText(context, "登录成功",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "登录失败",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "登录名或密码为空",Toast.LENGTH_SHORT).show();
        }
    }
}
