package com.smail.business_template.tabsswipe;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.smail.business_template.DetailsActivity;
import com.smail.business_template.ProductDetails;
import com.smail.business_template.R;
import com.smail.business_template.products.AppController;
import com.smail.business_template.products.CustomListAdapter;
import com.smail.business_template.products.Movie;

public class ProductsFragment extends Fragment {

	// Log tag
	private static final String TAG = ProductsFragment.class.getSimpleName();

	// Movies json url
	private static final String url = "http://api.androidhive.info/json/movies.json";//file:///android_asset/www/index.html
	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_products, container,
				false);

		listView = (ListView) rootView.findViewById(R.id.list);
		adapter = new CustomListAdapter(ProductDetails.mActivity, movieList);
		listView.setAdapter(adapter);

		// Item Click Listener for the listview
		OnItemClickListener itemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View container,
					int position, long id) {
				// Getting the Container Layout of the ListView
				/*LinearLayout linearLayoutParent = (LinearLayout) container;

				// Getting the inner Linear Layout
				LinearLayout linearLayoutChild = (LinearLayout) linearLayoutParent
						.getChildAt(1);

				// Getting the Country TextView
				TextView tvCountry = (TextView) linearLayoutChild.getChildAt(0);*/

				Intent intent = new Intent (ProductDetails.mActivity,DetailsActivity.class);
				startActivity(intent);
			}
		};

		// Setting the item click listener for the listview
		listView.setOnItemClickListener(itemClickListener);

		pDialog = new ProgressDialog(ProductDetails.mActivity);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// changing action bar color
		// getActionBar().setBackgroundDrawable(
		// new ColorDrawable(Color.parseColor("#1b1b1b")));

		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Movie movie = new Movie();
								movie.setTitle(obj.getString("title"));
								movie.setThumbnailUrl(obj.getString("image"));
								movie.setRating(((Number) obj.get("rating"))
										.doubleValue());
								movie.setYear(obj.getInt("releaseYear"));

								// Genre is json array
								JSONArray genreArry = obj.getJSONArray("genre");
								ArrayList<String> genre = new ArrayList<String>();
								for (int j = 0; j < genreArry.length(); j++) {
									genre.add((String) genreArry.get(j));
								}
								movie.setGenre(genre);

								// adding movie to movies array
								movieList.add(movie);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);

		return rootView;

	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

}
