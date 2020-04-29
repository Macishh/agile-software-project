package com.danielkarlkvist.umberent;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;



/**
 * A fragment for the map which consists of markers of the umbrella stands.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    GoogleMap mMap;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return v;
    }

    /** Creates the map and add markers to the umbrella stands */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Stand stand1 = new Stand("Emilsborg",57.680960, 11.984787, Stand.Availability.HIGH, mMap);
        Stand stand2 = new Stand("Chalmers",57.686330588, 11.972662776, Stand.Availability.NONE, mMap);


        mMap.moveCamera(CameraUpdateFactory.newLatLng(stand1.getLatLng())); //change to current location and more zoomed in

        mMap.setOnMarkerClickListener(this);
    }

    public void onMarkerClickShowPopupWindow(View view) {

        // inflate the layout of the popup window
/*
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.stand_card, null);

        PopupWindow pop = new PopupWindow(inflater.inflate(R.layout.stand_card, null, false), 500, 500, true);
        pop.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);

/*
        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

 */
    }

    // Called when the user clicks a marker.
    @Override
    public boolean onMarkerClick(final Marker marker) {


        // Retrieve the data from the marker.
        if(marker.getTitle().equals("Emilsborg")){
            StandFragment standFragment = new StandFragment();
            standFragment.showPopupWindow(getView());
            //Toast.makeText(getActivity().getApplicationContext(), "5 kr / min", Toast.LENGTH_SHORT).show();

        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }


}
