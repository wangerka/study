package com.ddd.demo.amber;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ddd.demo.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Amber extends AppCompatActivity {

    ImageView image;
    private String path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545854560519&di=15416c7e2af2bb7dee922168638dcd98&imgtype=0&src=http%3A%2F%2Fc1.haibao.cn%2Fimg%2F1080_1515_100_1%2F1434427367.8994%2F010c8e54f7a06dac6615f0c6642b4788.jpg";

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap map = (Bitmap) msg.getData().get("bitmap");
            image.setImageBitmap(map);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amber);
        image = (ImageView) findViewById(R.id.img);
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setConnectTimeout(5000);
                    con.setRequestMethod("GET");
                    if(con.getResponseCode() == 200){
                        Bundle b = new Bundle();
                        InputStream in = con.getInputStream();
                        b.putParcelable("bitmap", decodeBitMap(in));
                        Message msg = new Message();
                        msg.setData(b);
                        handler.sendMessage(msg);
                        in.close();
//                        image.setImageBitmap(bitmap);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public Bitmap decodeBitMap(InputStream in){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        return BitmapFactory.decodeStream(in, null, options);//第二個參數是圖片的padding
    }
}
