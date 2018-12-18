package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gejun on 18-12-20.
 */

public class PhotoWallAdapter extends ArrayAdapter<String> implements AbsListView.OnScrollListener {

    /**
     * ¼ÇÂ¼ËùÓÐÕýÔÚÏÂÔØ»òµÈ´ýÏÂÔØµÄÈÎÎñ¡£
     */
    private Set<BitmapWorkerTask> taskCollection;

    /**
     * Í¼Æ¬»º´æ¼¼ÊõµÄºËÐÄÀà£¬ÓÃÓÚ»º´æËùÓÐÏÂÔØºÃµÄÍ¼Æ¬£¬ÔÚ³ÌÐòÄÚ´æ´ïµ½Éè¶¨ÖµÊ±»á½«×îÉÙ×î½üÊ¹ÓÃµÄÍ¼Æ¬ÒÆ³ýµô¡£
     */
    private LruCache<String, Bitmap> mMemoryCache;

    /**
     * GridViewµÄÊµÀý
     */
    private GridView mPhotoWall;

    /**
     * µÚÒ»ÕÅ¿É¼ûÍ¼Æ¬µÄÏÂ±ê
     */
    private int mFirstVisibleItem;

    /**
     * Ò»ÆÁÓÐ¶àÉÙÕÅÍ¼Æ¬¿É¼û
     */
    private int mVisibleItemCount;

    /**
     * ¼ÇÂ¼ÊÇ·ñ¸Õ´ò¿ª³ÌÐò£¬ÓÃÓÚ½â¾ö½øÈë³ÌÐò²»¹ö¶¯ÆÁÄ»£¬²»»áÏÂÔØÍ¼Æ¬µÄÎÊÌâ¡£
     */
    private boolean isFirstEnter = true;

    public PhotoWallAdapter(Context context, int textViewResourceId, String[] objects,
                            GridView photoWall) {
        super(context, textViewResourceId, objects);
        mPhotoWall = photoWall;
        taskCollection = new HashSet<BitmapWorkerTask>();
        // »ñÈ¡Ó¦ÓÃ³ÌÐò×î´ó¿ÉÓÃÄÚ´æ
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        // ÉèÖÃÍ¼Æ¬»º´æ´óÐ¡Îª³ÌÐò×î´ó¿ÉÓÃÄÚ´æµÄ1/8
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        mPhotoWall.setOnScrollListener(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String url = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.photo_layout, null);
        } else {
            view = convertView;
        }
        final ImageView photo = (ImageView) view.findViewById(R.id.photo);
        // ¸øImageViewÉèÖÃÒ»¸öTag£¬±£Ö¤Òì²½¼ÓÔØÍ¼Æ¬Ê±²»»áÂÒÐò
        photo.setTag(url);
        setImageView(url, photo);
        return view;
    }

    /**
     * ¸øImageViewÉèÖÃÍ¼Æ¬¡£Ê×ÏÈ´ÓLruCacheÖÐÈ¡³öÍ¼Æ¬µÄ»º´æ£¬ÉèÖÃµ½ImageViewÉÏ¡£Èç¹ûLruCacheÖÐÃ»ÓÐ¸ÃÍ¼Æ¬µÄ»º´æ£¬
     * ¾Í¸øImageViewÉèÖÃÒ»ÕÅÄ¬ÈÏÍ¼Æ¬¡£
     *
     * @param imageUrl
     *            Í¼Æ¬µÄURLµØÖ·£¬ÓÃÓÚ×÷ÎªLruCacheµÄ¼ü¡£
     * @param imageView
     *            ÓÃÓÚÏÔÊ¾Í¼Æ¬µÄ¿Ø¼þ¡£
     */
    private void setImageView(String imageUrl, ImageView imageView) {
        Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    /**
     * ½«Ò»ÕÅÍ¼Æ¬´æ´¢µ½LruCacheÖÐ¡£
     *
     * @param key
     *            LruCacheµÄ¼ü£¬ÕâÀï´«ÈëÍ¼Æ¬µÄURLµØÖ·¡£
     * @param bitmap
     *            LruCacheµÄ¼ü£¬ÕâÀï´«Èë´ÓÍøÂçÉÏÏÂÔØµÄBitmap¶ÔÏó¡£
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * ´ÓLruCacheÖÐ»ñÈ¡Ò»ÕÅÍ¼Æ¬£¬Èç¹û²»´æÔÚ¾Í·µ»Ønull¡£
     *
     * @param key
     *            LruCacheµÄ¼ü£¬ÕâÀï´«ÈëÍ¼Æ¬µÄURLµØÖ·¡£
     * @return ¶ÔÓ¦´«Èë¼üµÄBitmap¶ÔÏó£¬»òÕßnull¡£
     */
    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // ½öµ±GridView¾²Ö¹Ê±²ÅÈ¥ÏÂÔØÍ¼Æ¬£¬GridView»¬¶¯Ê±È¡ÏûËùÓÐÕýÔÚÏÂÔØµÄÈÎÎñ
        if (scrollState == SCROLL_STATE_IDLE) {
            loadBitmaps(mFirstVisibleItem, mVisibleItemCount);
        } else {
            cancelAllTasks();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        mVisibleItemCount = visibleItemCount;
        // ÏÂÔØµÄÈÎÎñÓ¦¸ÃÓÉonScrollStateChangedÀïµ÷ÓÃ£¬µ«Ê×´Î½øÈë³ÌÐòÊ±onScrollStateChanged²¢²»»áµ÷ÓÃ£¬
        // Òò´ËÔÚÕâÀïÎªÊ×´Î½øÈë³ÌÐò¿ªÆôÏÂÔØÈÎÎñ¡£
        if (isFirstEnter && visibleItemCount > 0) {
            loadBitmaps(firstVisibleItem, visibleItemCount);
            isFirstEnter = false;
        }
    }

    /**
     * ¼ÓÔØBitmap¶ÔÏó¡£´Ë·½·¨»áÔÚLruCacheÖÐ¼ì²éËùÓÐÆÁÄ»ÖÐ¿É¼ûµÄImageViewµÄBitmap¶ÔÏó£¬
     * Èç¹û·¢ÏÖÈÎºÎÒ»¸öImageViewµÄBitmap¶ÔÏó²»ÔÚ»º´æÖÐ£¬¾Í»á¿ªÆôÒì²½Ïß³ÌÈ¥ÏÂÔØÍ¼Æ¬¡£
     *
     * @param firstVisibleItem
     *            µÚÒ»¸ö¿É¼ûµÄImageViewµÄÏÂ±ê
     * @param visibleItemCount
     *            ÆÁÄ»ÖÐ×Ü¹²¿É¼ûµÄÔªËØÊý
     */
    private void loadBitmaps(int firstVisibleItem, int visibleItemCount) {
        try {
            for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
                String imageUrl = Images.imageThumbUrls[i];
                Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
                if (bitmap == null) {
                    BitmapWorkerTask task = new BitmapWorkerTask();
                    taskCollection.add(task);
                    task.execute(imageUrl);
                } else {
                    ImageView imageView = (ImageView) mPhotoWall.findViewWithTag(imageUrl);
                    if (imageView != null && bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * È¡ÏûËùÓÐÕýÔÚÏÂÔØ»òµÈ´ýÏÂÔØµÄÈÎÎñ¡£
     */
    public void cancelAllTasks() {
        if (taskCollection != null) {
            for (BitmapWorkerTask task : taskCollection) {
                task.cancel(false);
            }
        }
    }

    /**
     * Òì²½ÏÂÔØÍ¼Æ¬µÄÈÎÎñ¡£
     *
     * @author guolin
     */
    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

        /**
         * Í¼Æ¬µÄURLµØÖ·
         */
        private String imageUrl;

        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            // ÔÚºóÌ¨¿ªÊ¼ÏÂÔØÍ¼Æ¬
//            Log.i("gejun","后台下载："+imageUrl);
            Bitmap bitmap = downloadBitmap(params[0]);
            if (bitmap != null) {
                // Í¼Æ¬ÏÂÔØÍê³Éºó»º´æµ½LrcCacheÖÐ
                addBitmapToMemoryCache(params[0], bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // ¸ù¾ÝTagÕÒµ½ÏàÓ¦µÄImageView¿Ø¼þ£¬½«ÏÂÔØºÃµÄÍ¼Æ¬ÏÔÊ¾³öÀ´¡£
            ImageView imageView = (ImageView) mPhotoWall.findViewWithTag(imageUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            taskCollection.remove(this);
        }

        /**
         * ½¨Á¢HTTPÇëÇó£¬²¢»ñÈ¡Bitmap¶ÔÏó¡£
         *
         * @param imageUrl
         *            Í¼Æ¬µÄURLµØÖ·
         * @return ½âÎöºóµÄBitmap¶ÔÏó
         */
        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return bitmap;
        }

    }

}
