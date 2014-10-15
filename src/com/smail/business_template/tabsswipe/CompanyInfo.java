package com.smail.business_template.tabsswipe;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.smail.business_template.R;

public class CompanyInfo extends Activity{
	private ActionBar bar;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
//		bar = getActionBar();
//		/*bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//		bar.setCustomView(R.layout.action_bar);*/
//		bar.setHomeButtonEnabled(false);
//		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#DBD7D7"));
//		bar.setBackgroundDrawable(colorDrawable);
	}

} 
