package com.hkdilan.android.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class HyperSpaceAnimationActivity extends Activity {

	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hyper_space);
		
		imageView = (ImageView) findViewById(R.id.imageView1);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toggle, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final Animation animation;
		
		switch(item.getItemId()){
		case R.id.menu_toggle:
			animation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
			imageView.startAnimation(animation);
			break;
		}
		return true;
	}
}
