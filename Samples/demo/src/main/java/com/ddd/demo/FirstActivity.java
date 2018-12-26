package com.ddd.demo;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SimpleAdapter(this,getData(),
                android.R.layout.simple_list_item_1,new String[]{"title"},new int[]{android.R.id.text1}));
    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> data = new ArrayList<>();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory("com.ddd.demo.category.DEMO");

        PackageManager pm = getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent,0);
        for(ResolveInfo info : infos){
            Map<String, Object> map = new HashMap<>();
            map.put("title", info.loadLabel(pm));
            map.put("intent",activityIntent(info.activityInfo.packageName,
                    info.activityInfo.name));
            data.add(map);
        }
        return data;
    }

    public Intent activityIntent(String pckName, String atyName){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(pckName,atyName));
        return intent;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Map<String, Object> map = (Map<String, Object>)l.getItemAtPosition(position);
        startActivity((Intent) map.get("intent"));
    }
}
