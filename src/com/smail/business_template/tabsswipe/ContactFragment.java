package com.smail.business_template.tabsswipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
				// Toast.makeText(ProductDetails.mActivity,
				// "Share Option Clicked", Toast.LENGTH_LONG).show();
				makeShare();

			}
		});
		mMessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				Toast.makeText(ProductDetails.mActivity,
//						"Message Option Clicked", Toast.LENGTH_LONG).show();
				makeMessage();

			}
		});
		mCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(ProductDetails.mActivity,
				// "Call Option Clicked", Toast.LENGTH_LONG).show();

				makeCall();

			}
		});
		mInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent infoIntent = new Intent(ProductDetails.mActivity,CompanyInfo.class);
				startActivity(infoIntent);

			}
		});

		return rootView;
	}

	protected void makeMessage() {
		String smsBody="Smail Product";
		Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		sendIntent.putExtra("sms_body", smsBody); 
		sendIntent.setType("vnd.android-dir/mms-sms");
		startActivity(sendIntent);
		
	}

	protected void makeShare() {
		//create the send intent
		Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		//set the type  
		shareIntent.setType("text/plain");
		//add a subject  
		shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,   
		 "Smail Product Shared By Android App");  
		  
		//build the body of the message to be shared  
		String shareMessage = "Use this Smail product";  
		  
		//add the message  
		shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,   
		 shareMessage);  
		  
		//start the chooser for sharing  
		startActivity(Intent.createChooser(shareIntent,   
		 "Share with your friends"));

	}

	protected void makeCall() {
		Log.i("Make call", "");

		Intent phoneIntent = new Intent(Intent.ACTION_CALL);
		phoneIntent.setData(Uri.parse("213 0552600611"));

		try {
			startActivity(phoneIntent);
			ProductDetails.mActivity.finish();
			Log.i("Finished making a call...", "");
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(ProductDetails.mActivity,
					"Call faild, please try again later.", Toast.LENGTH_SHORT)
					.show();
		}

	}
}
