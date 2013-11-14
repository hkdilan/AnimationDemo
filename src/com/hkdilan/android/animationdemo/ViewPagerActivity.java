package com.hkdilan.android.animationdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class ViewPagerActivity extends FragmentActivity {
	private static final String TAG = ViewPagerActivity.class.getSimpleName();
	
	private static final int NUM_PAGES = 5;
	
	//the pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps.
	private ViewPager mPager;
	
	//the pager adatper, which provides the pages to the view pager widget.
	private PagerAdapter mPagerAdapter;
	
	//zoom animation
	private ZoomOutPageTransformer mZoomOutPageTransformer;
	
	//depth page animation
	private DepthPageTransformer mDepthPageTransformer;
	
	@Override
	protected void onCreate(Bundle arg0) {
		Log.v(TAG, "onCreate()");
		super.onCreate(arg0);
		
		setContentView(R.layout.activity_viewpager);
		
		//initiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		
		//create zoom animation
		mZoomOutPageTransformer = new ZoomOutPageTransformer();
		
		//create depth page animation
		mDepthPageTransformer = new DepthPageTransformer();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.viewpager_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.simple:
			mPager.setPageTransformer(true, null);
			break;
			
		case R.id.zoomout:
			//set custom animation
			mPager.setPageTransformer(true, mZoomOutPageTransformer);
			break;
			
		case R.id.depthpage:
			mPager.setPageTransformer(true, mDepthPageTransformer);
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		if(mPager.getCurrentItem() == 0){
			//If the user is currently looking at the first step, allow the system to handle the BAck button. 
			//This calls finish() on this activity and pops the back stack
			super.onBackPressed();
		}else{
			//otherwise, select the previous step.
			mPager.setCurrentItem(mPager.getCurrentItem()-1);
		}
	}
	
	//a simple pager adapter that represents 5 SlideFragment objects, in sequence.
	private class SlidePagerAdapter extends FragmentStatePagerAdapter{

		public SlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return new SlideFragment();
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
		
	}
	
	//shrinks and fades pages when scrolling between adjacent pages. 
	//As a page gets closer to the center, it grows back to its normal size and fades in.
	public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
		
	    private static final float MIN_SCALE = 0.85f;
	    private static final float MIN_ALPHA = 0.5f;

	    public void transformPage(View view, float position) {
	        int pageWidth = view.getWidth();
	        int pageHeight = view.getHeight();

	        if (position < -1) { // [-Infinity,-1)
	            // This page is way off-screen to the left.
	            view.setAlpha(0);

	        } else if (position <= 1) { // [-1,1]
	            // Modify the default slide transition to shrink the page as well
	            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
	            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
	            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
	            if (position < 0) {
	                view.setTranslationX(horzMargin - vertMargin / 2);
	            } else {
	                view.setTranslationX(-horzMargin + vertMargin / 2);
	            }

	            // Scale the page down (between MIN_SCALE and 1)
	            view.setScaleX(scaleFactor);
	            view.setScaleY(scaleFactor);

	            // Fade the page relative to its size.
	            view.setAlpha(MIN_ALPHA +
	                    (scaleFactor - MIN_SCALE) /
	                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

	        } else { // (1,+Infinity]
	            // This page is way off-screen to the right.
	            view.setAlpha(0);
	        }
	    }
	}


	//uses the default slide animation for sliding pages to the left, while using a "depth" animation for sliding pages to the right. 
	//This depth animation fades the page out, and scales it down linearly.
	public class DepthPageTransformer implements ViewPager.PageTransformer {
	    private static final float MIN_SCALE = 0.75f;

	    public void transformPage(View view, float position) {
	        int pageWidth = view.getWidth();

	        if (position < -1) { // [-Infinity,-1)
	            // This page is way off-screen to the left.
	            view.setAlpha(0);

	        } else if (position <= 0) { // [-1,0]
	            // Use the default slide transition when moving to the left page
	            view.setAlpha(1);
	            view.setTranslationX(0);
	            view.setScaleX(1);
	            view.setScaleY(1);

	        } else if (position <= 1) { // (0,1]
	            // Fade the page out.
	            view.setAlpha(1 - position);

	            // Counteract the default slide transition
	            view.setTranslationX(pageWidth * -position);

	            // Scale the page down (between MIN_SCALE and 1)
	            float scaleFactor = MIN_SCALE
	                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
	            view.setScaleX(scaleFactor);
	            view.setScaleY(scaleFactor);

	        } else { // (1,+Infinity]
	            // This page is way off-screen to the right.
	            view.setAlpha(0);
	        }
	    }
	}
}
