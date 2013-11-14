package com.hkdilan.android.animationdemo;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LayoutAnimationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout_animation);
		
		final String[] arr = new String[]{"Android", "Windows", "Apple"};
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				new ArrayList<String>(Arrays.asList(arr)));
		
		final ListView listView = (ListView) findViewById(R.id.listView2);
		listView.setAdapter(adapter);
	}
}
