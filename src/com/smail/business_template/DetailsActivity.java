package com.smail.business_template;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class DetailsActivity extends Activity{
	private ActionBar bar;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_confirmation);
		/*bar = getActionBar();
		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		bar.setCustomView(R.layout.action_bar);
		bar.setHomeButtonEnabled(false);
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#DBD7D7"));
		bar.setBackgroundDrawable(colorDrawable);*/
	}

}
