package com.ddd.demo.listview_pic;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.widget.ImageView;

import com.ddd.demo.utils.NetUtil;

import java.util.ArrayList;
import java.util.List;

public class ImageLoader {
    LruCache<String, Bitmap> lruCache;
    RecyclerView recyclerView;
    List<DownAsyncTask> taskList;

    public ImageLoader(RecyclerView listview) {
        recyclerView = listview;

        int maxMemory = (int)Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory/4;
        lruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
//                return super.sizeOf(key, value);
                return value.getByteCount();
            }
        };

        taskList = new ArrayList<DownAsyncTask>();
    }

    public void addToCache(String url, Bitmap bitmap){
        if(getFromcache(url) == null){
            lruCache.put(url, bitmap);
        }
    }

    public Bitmap getFromcache(String url) {
        return lruCache.get(url);
    }


    public void loadImages(int start, int end) {
        for(int i =start;i<=end;i++){
            String url = ((ListviewAdapter)recyclerView.getAdapter()).getDatas().get(i).getPicSmall();
            if(getFromcache(url) == null){
                DownAsyncTask task = new DownAsyncTask();
                task.execute(url);
                taskList.add(task);
            } else {
                ImageView imageView = recyclerView.findViewWithTag(url);
                imageView.setImageBitmap(getFromcache(url));
            }
        }
    }

    public void cancelAllTask() {
        for(DownAsyncTask task : taskList){
            task.cancel(false);
        }
    }

    class DownAsyncTask extends AsyncTask<String, Void, Bitmap>{
        String url;
        @Override
        protected Bitmap doInBackground(String... strings) {
            url = strings[0];
            return NetUtil.decodePic(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            addToCache(url, bitmap);
            ImageView imageView = recyclerView.findViewWithTag(url);
            if(imageView != null){
                imageView.setImageBitmap(bitmap);
            }
            taskList.remove(this);
        }
    }
}
