package com.google.mw.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.mw.backend.caseApi.model.CaseBean;

public class CaseMap extends Activity {

	private final String TAG = getClass().getSimpleName();
		
	private GoogleMap map;
	private CaseBean bean;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.case_map);
	    
	    Intent i = getIntent();
		bean = new CaseBean();
		bean.setLongitude( i.getDoubleExtra("CASEBEAN_LONGITUDE", 0 ));
		bean.setLatitude( i.getDoubleExtra("CASEBEAN_LATITUDE", 0 ));	
		bean.setTitle( i.getStringExtra("CASEBEAN_TITLE"));
		bean.setOwner( i.getStringExtra("CASEBEAN_OWNER"));
		bean.setComments(i.getStringExtra("CASEBEAN_COMMENTS"));

	    // Show map
	    map = ((MapFragment) getFragmentManager()
	    		.findFragmentById(R.id.map)).getMap();

	    // Add marker
	    Log.d(TAG, "LatLng: " + bean.getLatitude() + " " + bean.getLongitude());
	    
	    if (map!=null)
	    	map.addMarker(new MarkerOptions()
	    	.position(new LatLng(bean.getLatitude(), bean.getLongitude()))
	    	.title(bean.getTitle())
	    	.snippet(bean.getComments()));
	    	//.icon(BitmapDescriptorFactory
	    	//		.fromResource(R.drawable.ic_launcher)));

	    	//map.moveCamera(CameraUpdateFactory.newLatLngZoom(
	    	//        new LatLng(-33.86997, 151.2089), 18));
	  }
}
