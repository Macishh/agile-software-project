package com.danielkarlkvist.umberent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


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

    // Called when the user clicks a marker.
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        if(marker.getTitle().equals("9/10")){
            Toast.makeText(getActivity().getApplicationContext(), "5 kr / min", Toast.LENGTH_SHORT).show();

        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }


}
