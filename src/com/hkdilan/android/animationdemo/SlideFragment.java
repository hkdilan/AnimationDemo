package com.hkdilan.android.animationdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SlideFragment extends Fragment {
	private static final String TAG = SlideFragment.class.getSimpleName();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(TAG, "onCreateView()");
		
		final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_slide, container, false);
		return rootView;
	}
}
