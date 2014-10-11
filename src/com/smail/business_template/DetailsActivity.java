package com.smail.business_template;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DetailsActivity extends FragmentActivity{
	private ActionBar bar;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_confirmation);
		bar = getActionBar();
		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		bar.setCustomView(R.layout.action_bar);
		bar.setHomeButtonEnabled(false);
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#DBD7D7"));
		bar.setBackgroundDrawable(colorDrawable);
	}

}
