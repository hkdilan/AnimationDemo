package com.hkdilan.android.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SlideShowActivity extends Activity {
	private static final String TAG = SlideShowActivity.class.getSimpleName();
	
	private ImageView imageView;
	private ImageView iv1;
	private ImageView iv2;
	private ImageView iv3;
	private ImageView iv4;
	
	private int imageNo = 1;
	
     int swipeMinDistance;
     int swipeMaxOffPath;
     int swipeThresholdVelocity;
     
     private GestureDetector gestureDetector;
     private View.OnTouchListener gestureListener;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slideshow);
		
		imageView = (ImageView) findViewById(R.id.ivSlideShow);
		iv1 = (ImageView) findViewById(R.id.iv1);
		iv2 = (ImageView) findViewById(R.id.iv2);
		iv3 = (ImageView) findViewById(R.id.iv3);
		iv4 = (ImageView) findViewById(R.id.iv4);
		
		// Gesture detection
        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        
        //swipe params
        //final ViewConfiguration vc = ViewConfiguration.get(this);
        swipeMinDistance = 120;//vc.getScaledPagingTouchSlop();
        swipeThresholdVelocity = 200;//vc.getScaledMinimumFlingVelocity();
        swipeMaxOffPath = 250;//vc.getScaledTouchSlop();
        		
        imageView.setOnTouchListener(gestureListener);
        imageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "onClick()");
			}
		}); ;
		
		startAnimation(R.anim.fadein_zoomin_fadeout);
	}
	
	private void startAnimation(int animation){
		Log.v(TAG, "startAnimation()");
		
		//load animation
		final Animation anim = AnimationUtils.loadAnimation(this, animation);
		
		//start animation
		imageView.startAnimation(anim);
		
		//fade out
		anim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				Log.v(TAG, "fade in out: "+imageNo);
				switch (imageNo) {
				case 1:
					iv1.setImageDrawable(getResources().getDrawable(R.drawable.ic_members));
					iv4.setImageDrawable(getResources().getDrawable(R.drawable.ic_location));
					break;
					
				case 2:
					iv2.setImageDrawable(getResources().getDrawable(R.drawable.ic_members));
					iv1.setImageDrawable(getResources().getDrawable(R.drawable.ic_location));
					break;
					
				case 3:
					iv3.setImageDrawable(getResources().getDrawable(R.drawable.ic_members));
					iv2.setImageDrawable(getResources().getDrawable(R.drawable.ic_location));
					break;
					
				case 4:
					iv4.setImageDrawable(getResources().getDrawable(R.drawable.ic_members));
					iv3.setImageDrawable(getResources().getDrawable(R.drawable.ic_location));
					break;
				}
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				//change image
				switch (imageNo) {
				case 1:
					imageNo = 2;
					imageView.setImageDrawable(getResources().getDrawable(R.drawable.intro_2));
					break;
					
				case 2:
					imageNo = 3;
					imageView.setImageDrawable(getResources().getDrawable(R.drawable.intro_3));
					break;
					
				case 3:
					imageNo = 4;
					imageView.setImageDrawable(getResources().getDrawable(R.drawable.intro_4));
					break;
					
				case 4:
					imageNo = 1;
					imageView.setImageDrawable(getResources().getDrawable(R.drawable.intro_1));
					break;
				}
				
				startAnimation(R.anim.fadein_zoomin_fadeout);
			}
		});
	}
	
	private void changeImage(int i) {
		Log.v(TAG, "changeImage: "+i);
		
		imageView.clearAnimation();
		
		if(i == 0){//left swipe, next
			
			switch(imageNo){
			case 1:
				imageNo = 3;
				break;
			case 2:
				imageNo = 4;
				break;
			case 3:
				imageNo = 1;
				break;
			case 4:
				imageNo = 2;
				break;
			}
			
		}
		
		startAnimation(R.anim.slide_left);
	}
	
	private class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > swipeMaxOffPath*2){
                	Log.i(TAG, "Not left or right Swipe");
                    return false;
                }
                
                if(e1.getX() - e2.getX() > swipeMinDistance && Math.abs(velocityX) > swipeThresholdVelocity) {
                    Log.i(TAG, "Left Swipe");
                    changeImage(1);
                    
                }else if (e2.getX() - e1.getX() > swipeMinDistance && Math.abs(velocityX) > swipeThresholdVelocity) {
                	Log.i(TAG, "Right Swipe");
                	changeImage(0);
                }
                
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            
            return false;
        }
    }
}
