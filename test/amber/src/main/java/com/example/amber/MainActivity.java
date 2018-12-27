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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",            "http://b-ssl.duitang.com/uploads/item/201406/14/20140614105342_4jEys.jpeg",
            "http://hbimg.b0.upaiyun.com/f2b9ad98d8dc63fd8a78824dad22cfdc5d1f31611b12e-P7OhL9_fw658",
            "http://5b0988e595225.cdn.sohucs.com/images/20180509/66db0cd0e33b4ce1a606f6bdf14ff3c9.jpeg",
            "http://b-ssl.duitang.com/uploads/item/201805/13/20180513211955_RkKzZ.jpeg",
            "http://5b0988e595225.cdn.sohucs.com/images/20180129/3d4db8a1382e4aa49424305da3431f79.jpeg",
            "http://images4.fanpop.com/image/photos/23700000/Amber-Heard-2011-ESPY-Awards-in-L-A-July-13-amber-heard-23704331-1349-2000.jpg",
            "http://hbimg.b0.upaiyun.com/8e336513293666bdd892b989eebabe93009d620e2ab6e-TxwpwZ_fw658",
            "http://c4.haibao.cn/img/0_0_100_1/liZ5kpMOE5KkE/piccommon/1184/11844/liZ5kpMOE5KkE.jpg",
            "http://wx3.sinaimg.cn/large/62262fe8gy1fcaobgeje4j20tn18gwpo.jpg",
            "http://c4.haibao.cn/img/1080_1623_100_1/liWHcbAzOYWMI/piccommon/1184/11846/liWHcbAzOYWMI.jpg",
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",
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
            "http://s9.sinaimg.cn/middle/69ef8fd4hc3859b576a58",


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
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
