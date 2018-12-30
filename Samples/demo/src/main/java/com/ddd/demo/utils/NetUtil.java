package com.ddd.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {
    public static String parseData(String urlString){
        HttpURLConnection conn=null;
        InputStream in=null;
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            if(conn.getResponseCode() == 200){
                in = conn.getInputStream();
                return readStream(in);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if(conn != null) conn.disconnect();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String readStream(InputStream inputStream)throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer= new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) != -1){
            out.write(buffer,0,length);
        }
        inputStream.close();
        out.close();
        return out.toString();
    }
}
