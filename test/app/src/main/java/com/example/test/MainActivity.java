package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private String path = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1636192626,3288945283&fm=26&gp=0.jpg";
    private String path1 = "http://img.soogif.com/xNYXo4QG1NLFcaKSMCs8vLWCBmCoN2NB.gif";

    private String[] paths ={
            "http://b-ssl.duitang.com/uploads/item/201406/14/20140614105342_4jEys.jpeg",
            "http://hbimg.b0.upaiyun.com/f2b9ad98d8dc63fd8a78824dad22cfdc5d1f31611b12e-P7OhL9_fw658",
            "http://5b0988e595225.cdn.sohucs.com/images/20180509/66db0cd0e33b4ce1a606f6bdf14ff3c9.jpeg",
            "http://b-ssl.duitang.com/uploads/item/201805/13/20180513211955_RkKzZ.jpeg",
            "http://5b0988e595225.cdn.sohucs.com/images/20180129/3d4db8a1382e4aa49424305da3431f79.jpeg",
            "http://images4.fanpop.com/image/photos/23700000/Amber-Heard-2011-ESPY-Awards-in-L-A-July-13-amber-heard-23704331-1349-2000.jpg",
            "http://hbimg.b0.upaiyun.com/8e336513293666bdd892b989eebabe93009d620e2ab6e-TxwpwZ_fw658",
            "http://c4.haibao.cn/img/0_0_100_1/liZ5kpMOE5KkE/piccommon/1184/11844/liZ5kpMOE5KkE.jpg",
            "http://wx3.sinaimg.cn/large/62262fe8gy1fcaobgeje4j20tn18gwpo.jpg",
            "http://c4.haibao.cn/img/1080_1623_100_1/liWHcbAzOYWMI/piccommon/1184/11846/liWHcbAzOYWMI.jpg",
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58"
    };

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.image1)
    ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)				//加载成功之前占位图
                .error(R.mipmap.ic_launcher)					//加载错误之后的错误图
                .override(400,400)								//指定图片的尺寸
                //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
                .fitCenter()
                //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
                .centerCrop()
                .circleCrop()//指定图片的缩放类型为centerCrop （圆形）
                .skipMemoryCache(true)							//跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL)		//缓存所有版本的图像
                .diskCacheStrategy(DiskCacheStrategy.NONE)		//跳过磁盘缓存
                .diskCacheStrategy(DiskCacheStrategy.DATA)		//只缓存原来分辨率的图片
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);	//只缓存最终的图片

        Glide.with(this)
                .load(path)
//                .apply(options)
                .into(image);

//        Glide.with(this)
//                .load(path1)
//                .into(image1);
    }
}
