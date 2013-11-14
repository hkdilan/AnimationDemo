package com.hkdilan.android.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationViewActivity extends Activity implements AnimationListener {

	private static final String TAG = AnimationViewActivity.class.getSimpleName();
	
	private ImageView imageView;
	
	private Animation animFadeIn;
	private Animation animFadeOut;
	private Animation animFadeInOut;//repeat one
	private Animation animBlink;//repeat
	
	private Animation animZoomIn;
	private Animation animZoomOut;
	private Animation animZoomInOut;
	private Animation animZoomInOutRepeat;//repeat
	
	private Animation animRotate;
	private Animation animMove;
	
	private Animation animSlideUp;
	private Animation animSlideDown;
	private Animation animSlideUpDown;
	private Animation animSlideUpDownRepeat;
	private Animation animSlideLeftRightRepeat;
	
	private Animation animBounce;
	private Animation animSequencial;
	private Animation animTogether;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_view);
		
		imageView = (ImageView) findViewById(R.id.imageViewAnimation);
		
		//load animation
		animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		animFadeInOut = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);
		
		animZoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
		animZoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
		animZoomInOut = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
		animZoomInOutRepeat = AnimationUtils.loadAnimation(this, R.anim.zoom_in_out_repeat);
		
		animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
		animMove = AnimationUtils.loadAnimation(this, R.anim.move);
		
		animSlideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
		animSlideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
		animSlideUpDown = AnimationUtils.loadAnimation(this, R.anim.slide_up);
		animSlideUpDownRepeat = AnimationUtils.loadAnimation(this, R.anim.slide_up_down_repeat);
		animSlideLeftRightRepeat = AnimationUtils.loadAnimation(this, R.anim.slide_left_right_repeat);
		
		animBounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
		animSequencial = AnimationUtils.loadAnimation(this, R.anim.sequencial);
		animTogether = AnimationUtils.loadAnimation(this, R.anim.together);
		
		//set animation listener
		animFadeInOut.setAnimationListener(this);
		animZoomInOut.setAnimationListener(this);
		animSlideUpDown.setAnimationListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_animation_view, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		switch(item.getItemId()){
		case R.id.menu_fade_in:
			//start animation
			imageView.startAnimation(animFadeIn);
			break;
			
		case R.id.menu_fade_out:
			imageView.startAnimation(animFadeOut);
			break;
			
		case R.id.menu_fade_in_out:
			imageView.startAnimation(animFadeInOut);
			break;
			
		case R.id.menu_blink:
			imageView.startAnimation(animBlink);
			break;
			
			
		case R.id.menu_zoom_in:
			imageView.startAnimation(animZoomIn);
			break;
			
		case R.id.menu_zoom_out:
			imageView.startAnimation(animZoomOut);
			break;
			
		case R.id.menu_zoom_in_out:
			imageView.startAnimation(animZoomInOut);
			break;
			
		case R.id.menu_zoom_in_out_repeat:
			imageView.startAnimation(animZoomInOutRepeat);
			break;
			
			
		case R.id.menu_rotate:
			imageView.startAnimation(animRotate);
			break;
			
		case R.id.menu_move:
			imageView.startAnimation(animMove);
			break;
			
			
		case R.id.menu_slide_up:
			imageView.startAnimation(animSlideUp);
			break;
			
		case R.id.menu_slide_down:
			imageView.startAnimation(animSlideDown);
			break;
			
		case R.id.menu_slide_up_down:
			imageView.startAnimation(animSlideUpDown);
			break;
			
		case R.id.menu_slide_up_down_repeat:
			imageView.startAnimation(animSlideUpDownRepeat);
			break;
			
		case R.id.menu_slide_left_right_repeat:
			imageView.startAnimation(animSlideLeftRightRepeat);
			break;
			
			
		case R.id.menu_bounce:
			imageView.startAnimation(animBounce);
			break;
			
		case R.id.menu_sequencial:
			imageView.startAnimation(animSequencial);
			break;
			
		case R.id.menu_together:
			imageView.startAnimation(animTogether);
			break;
		}
		
		return true;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		Log.v(TAG, "onAnimationEnd()");
		
		if(animation == animFadeInOut){
			imageView.startAnimation(animFadeOut);
			
		}else if(animation == animZoomInOut){
			imageView.startAnimation(animZoomOut);
			
		}else if(animation == animSlideUpDown){
			imageView.startAnimation(animSlideDown);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		Log.v(TAG, "onAnimationRepeat()");
	}

	@Override
	public void onAnimationStart(Animation animation) {
		Log.v(TAG, "onAnimationStart()");
	}
}
