package com.example.serial;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class SecondActivity extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		Bundle b = getIntent().getExtras();
		Person p = (Person)b.getParcelable("p");
		Toast.makeText(this, "name = "+p.getName()+",age = "+p.getAge(), Toast.LENGTH_SHORT).show();
	}

}
