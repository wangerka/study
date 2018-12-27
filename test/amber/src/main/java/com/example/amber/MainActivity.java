package com.example.amber;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ImageLoaderTask> loaderList;
    private String[] paths ={
            "http://data.whicdn.com/images/131206517/large.jpg",
            "http://cdn.duitang.com/uploads/blog/201409/17/20140917134110_4jBmi.thumb.700_0.jpeg",
            "http://images4.fanpop.com/image/photos/23700000/Amber-Heard-2011-ESPY-Awards-in-L-A-July-13-amber-heard-23704331-1349-2000.jpg",
            "http://c4.haibao.cn/img/0_0_100_1/liZ5kpMOE5KkE/piccommon/1184/11844/liZ5kpMOE5KkE.jpg",
            "http://c3.haibao.cn/img/600_0_100_0/1323086837.1799/bigfiles/201149/1323086837.1799.jpg",
            "http://images6.fanpop.com/image/photos/37700000/Amber-Heard-hottest-actresses-37735305-1920-1080.jpg",
            "http://img1.cache.netease.com/catchpic/F/FB/FB6521920119926E6B127C36F7AA56A0.jpg",
            "http://img32.mtime.cn/up/2012/08/29/212148.60011617_500.jpg",
            "http://wx3.sinaimg.cn/large/62262fe8gy1fcaobgeje4j20tn18gwpo.jpg",
            "http://ww1.sinaimg.cn/large/d7963497gw1evw3cw6u9oj20ix0sg0zr.jpg",
            "http://p0.qhimgs4.com/t01d7852ab707e47767.jpg",
            "http://x.gto.cc/img/scontent-sjc3-1.cdn_iii_.com/vp/da6efea61602441d6f84018f972cbd03/5CA02011/t51.2885-15/sh0.08/e35/s640x640/27582177_215215495712604_701589607950581760_n.1046.jpg",
            "http://b-ssl.duitang.com/uploads/item/201406/14/20140614105342_4jEys.jpeg",
            "http://c1.haibao.cn/img/600_0_100_1/1322463495.4916/bigfiles/201148/1322463495.4916.jpg",
            "http://c3.haibao.cn/img/0_0_100_1/1435141564.9012/e54efb0b7b1a3eb145987380502bdbaf.jpg",
            "http://images4.fanpop.com/image/photos/21800000/Leaving-Urth-Cafe-in-West-Hollywood-05-04-amber-heard-21837941-1362-2038.jpg",
            "http://p0.qhimgs4.com/t013c4224936d97430b.jpg",
            "http://pic23.photophoto.cn/20120417/0036036339508504_b.jpg",
            "http://img0.pclady.com.cn/pclady/1808/13/1843586_28.jpg",
            "http://p1.hoopchina.com.cn/gdc/weibo/pic/21f5c97eaf396a0b68566415bdf8f084.jpg",
            "http://i2.hdslb.com/bfs/archive/499be4e80e32235ab4be62ea7626403026606f85.jpg",
            "http://images5.fanpop.com/image/photos/28300000/The-Art-Of-Elysium-s-5th-Annual-Heaven-Gala-Arrivals-January-14-amber-heard-28309043-1703-2560.jpg",
            "http://p1.qhimgs4.com/t011c1877e115326733.jpg",
            "http://p31.qhimg.com/t01fe227bdd9f262109.jpg?size=640x640",
            "http://n.sinaimg.cn/sinacn10116/679/w1080h1199/20181213/b332-hqackac1816262.jpg",
            "http://img.auctiva.com/imgdata/9/6/7/6/4/2/webimg/736459794_o.jpg",
            "http://p1.qhimgs4.com/t01e043a28e45a14bc6.jpg",
            "http://p0.qhimgs4.com/t01951def7b9e6e239f.jpg",
            "http://5b0988e595225.cdn.sohucs.com/images/20181117/9e4dcc4fcf4044ae8b184a27851603b7.jpeg",
            "http://img0.pclady.com.cn/pclady/1808/13/1843586_22.jpg",
            "http://photocdn.sohu.com/20111231/Img330913522.jpg",
            "http://s9.rr.itc.cn/g/wapChange/20157_9_12/a0ni6a580260670451.jpeg",
            "http://www.people.com.cn/mediafile/pic/20170308/52/9677996140505348172.jpg",
            "http://spider.nosdn.127.net/d6a153392f30c8287585bdd8c906a7d0.jpeg",
            "http://img0.pclady.com.cn/pclady/1808/13/1843586_19.jpg",
            "http://p1.hoopchina.com.cn/gdc/weibo/pic/73e7ce23e306d3475702919f87f4b368.jpg",
            "http://i1.hoopchina.com.cn/hupuapp/bbs/254599296967002/thread_254599296967002_20181208180503_s_222497_o_w_1080_h_1620_71648.jpg?x-oss-process=image/resize,w_800/format,jpg",
            "http://s9.rr.itc.cn/r/wapChange/20165_30_14/a0sgzz7312334569855.jpg",
            "http://n.sinaimg.cn/front/288/w640h448/20181114/izAP-hnvukfe7585524.jpg",
            "http://img3.duitang.com/uploads/item/201606/09/20160609100907_WGZ5K.thumb.700_0.jpeg",
            "http://wx1.sinaimg.cn/orj360/dedecc7dly1frj99knquhj21hb0zkwja.jpg",
            "http://wx1.sinaimg.cn/large/d7963497gy1fdd4155knsj21kw2db19a.jpg",
            "http://pic.makepolo.net/news/allimg/20161227/1482824171492252.jpg",
            "http://p1.hoopchina.com.cn/gdc/weibo/pic/a374d416dcd6bb2e4df9a9a5a3e1decc.jpg",
            "http://i2.hoopchina.com.cn/blogfile/201812/07/BbsImg_42429360324551_1544189949_s_34064_o_w_600_h_752_84832.jpg?x-oss-process=image/resize,w_800/format,jpg",
            "http://pic23.photophoto.cn/20120417/0036036360542123_b.jpg",
            "http://wwe.imgmax.com/images/2017/11/15/RlD7B.jpg",
            "http://img3.doubanio.com/view/note/l/public/p50672161.jpg",
            "http://p1.hoopchina.com.cn/gdc/jike/pic/500f3469e8b7726702731320a1152b48.jpg",
            "http://p2.qhimgs4.com/t0195c8d935f9e1f47d.jpg",
            "http://hbimg.b0.upaiyun.com/73a8c3fb9e750d2ff0959b0c02c0672d160c9052a51f9-8WhT1C_fw658",
            "http://p2.qhimgs4.com/t01cbf85850390e57c4.jpg",
            "http://ww1.sinaimg.cn/large/006i4gm2jw1ezvizr6lewj30zk1e2438.jpg",
            "http://s8.rr.itc.cn/g/wapChange/20143_17_16/a0q7st5241296492323.jpg",
            "http://imglf2.ph.126.net/yq9PcnGmzYLcGUqXk_XgAQ==/6608941193701639397.jpg",
            "http://img3.ph.126.net/4nohjW7UA8jYYtHCFWcLyQ==/1558808421040586273.jpg",
            "http://n1.itc.cn/img8/wb/smccloud/fetch/2015/02/03/87279487422721180.JPEG",
            "http://wx1.sinaimg.cn/nmw690/005M94J9ly4fxzu72wbxmj30hs09rwf8.jpg",


    };

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout)findViewById(R.id.image_container);
        loaderList = new ArrayList<>();

        for(String path : paths){
            ImageLoaderTask task = new ImageLoaderTask();
            task.execute(path);
            loaderList.add(task);
        }
    }


    public void addImage(Bitmap bitmap){
        ImageView image = new ImageView(this);
        image.setImageBitmap(bitmap);
        image.setScaleType(ImageView.ScaleType.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        container.addView(image);
    }

    public class ImageLoaderTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(3000);
                if(conn.getResponseCode() == 200){
                    return computeSample(inputStream2Byte(conn.getInputStream()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap o) {
            super.onPostExecute(o);
            if(o!=null) addImage(o);
        }
    }

    public byte[] inputStream2Byte(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while((len = in.read(buffer)) != -1){
            out.write(buffer,0,len);
        }
        in.close();
        out.close();
        return out.toByteArray();
    }

    public Bitmap computeSample(byte[] b){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(b,0, b.length, options);
        if(options.outWidth > 720)
            options.inSampleSize = Math.round((float)options.outWidth/(float)720);
        else
            options.inSampleSize = 1;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(b,0, b.length, options);
    }
}
