package com.hkdilan.android.animationdemo;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CrossfadingActivity extends Activity {
	private static final String TAG = CrossfadingActivity.class.getSimpleName();
	
	private View mContentView;
	private int mAnimationDuration;
	
	private boolean showing = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate()");
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_crossfading);
		mContentView = findViewById(R.id.content);
		
		//Retrieve and cahe the system's default "short" animation time.
		mAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.toggle, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.menu_toggle:
			crossfade();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	//minSdkVersion 12 or greater
	private void crossfade(){
		Log.v(TAG, "crossfade()");
		
		if(showing){//hide with animation
			//animate to hide
			mContentView.animate().alpha(0f).setDuration(mAnimationDuration).setListener(new AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animation) {
					Log.v(TAG, "onAnimationStart()");
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
					Log.v(TAG, "onAnimationRepeat()");
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					Log.v(TAG, "onAnimationEnd()");
					
					//gone end of the animation
					//no space and improve performance
					mContentView.setVisibility(View.GONE);
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					Log.v(TAG, "onAnimationCancel()");
				}
			});
			
			showing = false;//hiden
			
		}else{//show with animation
			//Set the content view to 0% opacity but visible, so that it is visible but fully transparent during animation.
			//minSdkVersion should be 11 or greater.
			mContentView.setAlpha(0f);
			mContentView.setVisibility(View.VISIBLE);
			
			//Animate the content view to 100% opacity.
			//listener set on the view
			//minSdkVersion should be 12 or greater. 
			mContentView.animate().alpha(1f).setDuration(mAnimationDuration).setListener(null);
			
			showing = true;//shown
		}
	}
	
}
