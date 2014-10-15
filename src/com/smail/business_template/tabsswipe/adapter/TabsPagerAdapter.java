package com.smail.business_template.tabsswipe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smail.business_template.tabsswipe.ContactFragment;
import com.smail.business_template.tabsswipe.ProductsFragment;
import com.smail.business_template.tabsswipe.TopRatedFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new TopRatedFragment();
		case 1:
			// Games fragment activity
			return new ProductsFragment();
		case 2:
			// Movies fragment activity
			return new ContactFragment();

//		case 3:
//			// Movies fragment activity
//			return new ContactFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
