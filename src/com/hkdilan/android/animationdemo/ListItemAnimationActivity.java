package com.hkdilan.android.animationdemo;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListItemAnimationActivity extends Activity {
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item_animation);
		
		final String[] arr = new String[]{"Android", "Apple", "Windows"};
		final ArrayList<String> list = new ArrayList<String>(Arrays.asList(arr));
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		
		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		
		//set animation
		final LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_slide_in_bottom);
		listView.setLayoutAnimation(animation);
	}
}
