package com.example.administrator.myapplication;

public class P<V extends com.example.administrator.myapplication.V> {
    private V v;
    public P(V v) {
        this.v = v;
    }

    M.CallBack cb = new M.CallBack(){
        @Override
        public void success(String result, String info) {
            v.showData(result);
        }

        @Override
        public void fail(String info) {
            v.showFail(info);
        }
    };
    public void getData(String type){
        v.showProgress();
        M.getData(type, cb);
    }
}
