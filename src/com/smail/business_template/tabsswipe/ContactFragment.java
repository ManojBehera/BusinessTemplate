package com.smail.business_template.tabsswipe;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.smail.business_template.ProductDetails;
import com.smail.business_template.R;

public class ContactFragment extends Fragment implements LocationListener{

	private ImageButton mMessage, mCall, mInfo;
	private TextView mShare;
	private GoogleMap googleMap;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_contacts, container,
				false);
		
		// Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(ProductDetails.mActivity);
     // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
        	
        	/*int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();*/
            
        }
        else{
        	// Google Play Services are available	
    		
			// Getting reference to the SupportMapFragment of activity_main.xml
			SupportMapFragment fm = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
			
			// Getting GoogleMap object from the fragment
			googleMap = fm.getMap();
			
			// Enabling MyLocation Layer of Google Map
			googleMap.setMyLocationEnabled(true);				
					
			
			 // Getting LocationManager object from System Service LOCATION_SERVICE
	        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
	
	        // Creating a criteria object to retrieve provider
	        Criteria criteria = new Criteria();
	
	        // Getting the name of the best provider
	        String provider = locationManager.getBestProvider(criteria, true);
	
	        // Getting Current Location
	        Location location = locationManager.getLastKnownLocation(provider);
	
	        if(location!=null){
	                onLocationChanged(location);
	        }
	
	        locationManager.requestLocationUpdates(provider, 20000, 0, this);
        
        }
		
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
		/*String smsBody="Smail Product";
		Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		sendIntent.putExtra("sms_body", smsBody); 
		sendIntent.setType("vnd.android-dir/mms-sms");
		startActivity(sendIntent);*/
		
		String[] recipients = {"mnj.behera1@gmail.com","mnj.behera2@gmail.com"};
	      Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
	      // prompts email clients only
	      email.setType("message/rfc822");

	      email.putExtra(Intent.EXTRA_EMAIL, recipients);
	      email.putExtra(Intent.EXTRA_SUBJECT, "Subject Line here");
	      email.putExtra(Intent.EXTRA_TEXT, "Mail Body Text Goes here");

	      try {
		    // the user can choose the email client
	         startActivity(Intent.createChooser(email, "Choose an email client from..."));
	     
	      } catch (android.content.ActivityNotFoundException ex) {
	         Log.i("HI","No email client installed.");
	      }
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

	@Override
	public void onLocationChanged(Location location) {

		
		
		
		// Getting latitude of the current location
		double latitude = location.getLatitude();
		
		// Getting longitude of the current location
		double longitude = location.getLongitude();		
		
		// Creating a LatLng object for the current location
		LatLng latLng = new LatLng(latitude, longitude);
		
		// Showing the current location in Google Map
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		
		// Zoom in the Google Map
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));		
		
		
		// Setting latitude and longitude in the TextView tv_location
//		tvLocation.setText("Latitude:" +  latitude  + ", Longitude:"+ longitude );		
				
	
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}
