package com.danielkarlkvist.umberent.UI;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.util.IslamicCalendar;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.danielkarlkvist.umberent.Model.IStand;
import com.danielkarlkvist.umberent.Model.Umberent;
import com.danielkarlkvist.umberent.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A fragment for the map which consists of markers for the umbrella stands.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap googleMap;
    private StandFragment standFragment;
    private Umberent umberent = Umberent.getInstance();
    private List<IStand> stands;

    HashMap<Marker, Integer> mHashMap = new HashMap<>();

    ImageButton locationButton;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        stands = umberent.getStands();

        standFragment = new StandFragment();

        View v =  inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initializeViews(v);
        initializeButtonListener();

        return v;
    }

    private void initializeViews(View v) {
        locationButton = (ImageButton) v.findViewById(R.id.myLocationBtn);
    }

    private void initializeButtonListener(){
        locationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                moveCameraToMyLocation();
            }
        });
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

        enableMyLocationIfPermitted();
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMinZoomPreference(11);
        moveCameraToMyLocation();
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
    }


    // This metod moves the camera to the current location
    private void moveCameraToMyLocation() {
        LocationManager locMan = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria crit = new Criteria();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location loc = locMan.getLastKnownLocation(locMan.getBestProvider(crit, false));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(loc.getLatitude(), loc.getLongitude()), 12.8f));
    }

    // This method sets current location
    private void enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (googleMap != null) {
            googleMap.setMyLocationEnabled(true);
        }
    }

    // This method checks if we are able to use the current location, else use default location
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocationIfPermitted();
                } else {
                    showDefaultLocation();
                }
                return;
            }
        }
    }

    // This is a default location over Gothenburg if we are not able to use current location
    private void showDefaultLocation() {
        Toast.makeText(getActivity(), "Location permission not granted, " +
                        "showing default location",
                Toast.LENGTH_SHORT).show();
        LatLng Goteborg = new LatLng(57.70887000, 11.97456000);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Goteborg));
    }


    // Called when the user clicks a marker.
    /** Called when the user clicks a marker. Shows the stand card with the right info for that stand location */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        int key = mHashMap.get(marker);

        for (IStand stand : stands){
            if(key == stand.getID()){
                standFragment.showPopupWindow(getView());
                standFragment.setStandInfo(stand);
            }
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    private void addMarker(IStand stand) {
        Marker m = googleMap.addMarker((new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(getStandIcon(stand)))
                .position(stand.getLatLng())
                .title("134 m")));
        mHashMap.put(m, stand.getID());
    }

    private int getStandIcon(IStand stand) {
        double availability = (double) stand.getAmountOfUmbrellas() / (double) stand.getCapacity();

        if (availability >= 0.65) {
            return R.drawable.umberella_icon_available;
        } else if (availability > 0) {
            return R.drawable.umberella_icon_few_available;
        } else {
            return R.drawable.umberella_icon_not_available;
        }
    }


}
