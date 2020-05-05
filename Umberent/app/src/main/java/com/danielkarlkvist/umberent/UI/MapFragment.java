package com.danielkarlkvist.umberent.UI;

import android.icu.util.IslamicCalendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielkarlkvist.umberent.Model.IStand;
import com.danielkarlkvist.umberent.Model.Umberent;
import com.danielkarlkvist.umberent.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * A fragment for the map which consists of markers for the umbrella stands.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    GoogleMap googleMap;
    StandFragment standFragment;
    Umberent umberent = Umberent.getInstance();
    List<IStand> stands;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        stands = umberent.getStands();

        standFragment = new StandFragment();

        View v =  inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return v;
    }

    /** Creates the map and add markers to the umbrella stands */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        for (int i = 0; i < stands.size(); i++) {
            addMarker(stands.get(i));
        }

        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(stands.get(0).getLatLng())); //change to current location and more zoomed in

        this.googleMap.setOnMarkerClickListener(this);
    }

    // Called when the user clicks a marker.
    @Override
    public boolean onMarkerClick(final Marker marker) {
        // Retrieve the data from the marker.
        if(marker.getTitle().equals("Emilsborg")) {
            standFragment.showPopupWindow(getView());
        } else if (marker.getTitle().equals("Chalmers")) {
            standFragment.showPopupWindow(getView());
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    private void addMarker(IStand stand) {
        googleMap.addMarker((new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(getStandIcon(stand)))
                .position(stand.getLatLng())
                .title(stand.getTitle())));
    }

    private int getStandIcon(IStand stand) {
        double availability = (double) stand.getAmountOfUmbrellas() / (double) stand.getCapacity();

        if (availability >= 0.5) {
            return R.drawable.umberella_icon_available;
        } else if (availability > 0) {
            return R.drawable.umberella_icon_few_available;
        } else {
            return R.drawable.umberella_icon_not_available;
        }
    }
}
