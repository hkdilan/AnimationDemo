package com.hkdilan.android.animationdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class CardFlipActivity extends Activity {
	
	private boolean mToggle = true;
	private CardFrontFragment mCardFrontFragment;
	private CardBackFragment mCardBackFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_flip);

		mCardFrontFragment = new CardFrontFragment();
		mCardBackFragment = new CardBackFragment();
		
		getFragmentManager().beginTransaction().add(R.id.container, mCardFrontFragment).commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toggle_flip, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		final FragmentTransaction ft = getFragmentManager().beginTransaction();
		
		switch(item.getItemId()){
		case R.id.menu_toggle_left:
			ft.setCustomAnimations(R.animator.card_flip_left_in, R.animator.card_flip_left_out);
			break;
			
		case R.id.menu_toggle_right:
			ft.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out);
			break;
		}
		
		if(mToggle){
			ft.replace(R.id.container, mCardBackFragment);
			mToggle = false;
		}else{
			ft.replace(R.id.container, mCardFrontFragment);
			mToggle = true;
		}
		ft.commit();
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A fragment representing the front of the card.
	 */
	public static class CardFrontFragment extends Fragment {
		public CardFrontFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_card_front, container,
					false);
		}
	}

	/**
	 * A fragment representing the back of the card.
	 */
	public static class CardBackFragment extends Fragment {
		public CardBackFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_card_back, container,
					false);
		}
	}
}
