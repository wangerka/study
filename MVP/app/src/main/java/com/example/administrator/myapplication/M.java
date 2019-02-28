package com.example.administrator.myapplication;

import android.os.Handler;
import android.os.Message;

public class M {

    interface CallBack{
        void success(String result, String info);
        void fail(String info);
    }

    static CallBack cb;

    private static Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch(what){
                case 0:
                    cb.success("hello", "yes");
                break;
                case 1:
                    cb.fail("no");
                break;
            }
        }
    };

    public static void getData(String type, CallBack callBack){
        cb = callBack;
        switch(type){
            case "normal":
                h.sendEmptyMessageDelayed(0,2000);
                break;
            case "fail":
                h.sendEmptyMessageDelayed(1,5000);
                break;

        }
    }
}
