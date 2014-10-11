package com.smail.business_template.tabsswipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.smail.business_template.ProductDetails;
import com.smail.business_template.R;

public class ContactFragment extends Fragment {

	private ImageButton mMessage, mCall, mInfo;
	private TextView mShare;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_contacts, container,
				false);
		mMessage = (ImageButton) rootView.findViewById(R.id.button_message);
		mCall = (ImageButton) rootView.findViewById(R.id.button_call);
		mInfo = (ImageButton) rootView.findViewById(R.id.button_info);
		mShare = (TextView) rootView.findViewById(R.id.share);
		mShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(ProductDetails.mActivity, "Share Option Clicked", Toast.LENGTH_LONG).show();

			}
		});
		mMessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(ProductDetails.mActivity, "Message Option Clicked", Toast.LENGTH_LONG).show();

			}
		});
		mCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(ProductDetails.mActivity, "Call Option Clicked", Toast.LENGTH_LONG).show();

			}
		});
		mInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(ProductDetails.mActivity, "Info Option Clicked", Toast.LENGTH_LONG).show();

			}
		});

		return rootView;
	}
}
